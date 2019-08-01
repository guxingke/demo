package com.gxk.demo;

import com.google.common.collect.Lists;
import com.google.common.primitives.Longs;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class EventToEs {

  public static void main(String[] args) throws IOException {
    EventToEs main = new EventToEs();
    main.work();
  }

  private void work() throws IOException {
    RestClientBuilder builder = RestClient.builder(
        new HttpHost("10.1.2.53", 9200, "http")
    );
    RestHighLevelClient client = new RestHighLevelClient(builder);

    Path path = Paths.get("/Users/gxk/temp/m2t");
    List<String> lines = Files.readAllLines(path);
    System.out.println(lines.size());

    List<Event> events = lines.stream()
        .map(this::parse)
        .filter(it -> it.fromUid > 10)
        .filter(it -> it.toUid > 10)
        .distinct()
        .collect(Collectors.toList());

    List<List<Event>> partition = Lists.partition(events, 500);
    for (List<Event> records : partition) {
      bulkIndex(client, records);
      System.out.println(events.size());
    }

    client.close();
  }

  private void bulkIndex(RestHighLevelClient client, List<Event> events) throws IOException {
    BulkRequest bulkRequest = new BulkRequest();
    List<IndexRequest> requests = events.stream()
        .map(it -> {
          IndexRequest request = new IndexRequest("events", "event", it.getId());
          Map<String, Object> map = new HashMap<>();
          map.put("action", it.action);
          map.put("traceId", it.traceId);
          map.put("fromUid", it.fromUid);
          map.put("toUid", it.toUid);
          map.put("@timestamp", new Date(it.timestamp));
          request.source(map);

          return request;
        }).collect(Collectors.toList());

    if (requests.isEmpty()) {
      return;
    }

    requests.forEach(bulkRequest::add);

    BulkResponse responses = client.bulk(bulkRequest, RequestOptions.DEFAULT);
//    responses.forEach(it -> {
//      System.out.println(it.getResponse().getResult());
//    });
  }

  private Event parse(String line) {
    String[] split = line.split(",");
    return new Event(
        split[0],
        split[1],
        split[2],
        Longs.tryParse(split[3]),
        Longs.tryParse(split[4]),
        Longs.tryParse(split[5])
    );
  }


  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  class Event {

    private String id;
    private String action;
    private String traceId;
    private Long fromUid;
    private Long toUid;
    private Long timestamp;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Event event = (Event) o;
      return getAction().equals(event.getAction()) &&
          getFromUid().equals(event.getFromUid()) &&
          getToUid().equals(event.getToUid());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getAction(), getFromUid(), getToUid());
    }
  }
}
