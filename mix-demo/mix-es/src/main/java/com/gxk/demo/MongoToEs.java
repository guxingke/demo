package com.gxk.demo;

import com.google.common.primitives.Longs;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.io.IOException;
import java.util.ArrayList;
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
import org.bson.Document;
import org.bson.conversions.Bson;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class MongoToEs {

  private MongoCollection<Document> coll;
  private RestHighLevelClient esClient;

  private int count;

  public MongoToEs() {
    RestClientBuilder builder = RestClient.builder(
        new HttpHost("10.1.2.53", 9200, "http")
    );
    esClient = new RestHighLevelClient(builder);
    MongoClient mongoClient = MongoClients.create("mongodb://10.1.1.22");
    coll = mongoClient.getDatabase("pu-rec").getCollection("events");
  }

  public static void main(String[] args) throws IOException {
    MongoToEs main = new MongoToEs();
    main.work();
  }

  private void work() throws IOException {
    long anchor = System.currentTimeMillis() - 1000L * 3600 * 5;
    Bson bson = gt("timestamp", anchor);
    MongoCursor<Document> cursor = coll.find(bson).iterator();

    List<Event> events = new ArrayList<>();
    try {
      while (cursor.hasNext()) {
        count++;

        Document next = cursor.next();
        Event event = Event.fromDocument(next);
        events.add(event);

        if (events.size() == 10) {
          bulkIndex(esClient, events);
          events.clear();
          System.out.println(count);
        }
      }
    } finally {
      cursor.close();
    }

    esClient.close();
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
  static class Event {

    private String id;
    private String action;
    private String traceId;
    private Long fromUid;
    private Long toUid;
    private Long timestamp;

    public static Event fromDocument(Document doc) {
      Event event = new Event();

      event.setId(doc.getObjectId("_id").toString());
      event.setAction(doc.getString("cmd"));
      event.setFromUid(doc.getLong("fromUid"));
      event.setToUid(doc.getLong("toUid"));
      event.setTraceId(doc.getString("traceId"));
      event.setTimestamp(doc.getLong("timestamp"));

      return event;
    }

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
