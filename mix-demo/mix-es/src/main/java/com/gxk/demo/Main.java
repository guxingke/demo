package com.gxk.demo;

import java.io.IOException;
import java.util.Date;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.main.MainResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class Main {

  public static void main(String[] args) throws IOException {
    RestClientBuilder builder = RestClient.builder(
        new HttpHost("localhost", 9200, "http")
    );
    RestHighLevelClient client = new RestHighLevelClient(builder);

//    info(client);
//    index(client);

    client.close();
  }

  private static void index(RestHighLevelClient client) throws IOException {
//    IndexRequest request = new IndexRequest("posts", "post", "1");
//    String jsonString = "{" +
//        "\"user\":\"kimchy\"," +
//        "\"postDate\":\"2013-01-30\"," +
//        "\"message\":\"trying out Elasticsearch\"" +
//        "}";
//    request.source(jsonString, XContentType.JSON);

//    Map<String, Object> jsonMap = new HashMap<>();
////    jsonMap.put("user", "kimchy");
////    jsonMap.put("postDate", new Date());
////    jsonMap.put("message", "trying out Elasticsearch");
////    IndexRequest indexRequest = new IndexRequest("posts", "doc", "1")
////        .source(jsonMap);

//    XContentBuilder builder = XContentFactory.jsonBuilder();
//    builder.startObject();
//    {
//      builder.field("user", "kimchy");
//      builder.timeField("postDate", new Date());
//      builder.field("message", "trying out Elasticsearch");
//    }
//    builder.endObject();
//    IndexRequest indexRequest = new IndexRequest("posts", "doc", "1")
//        .source(builder);
    IndexRequest indexRequest = new IndexRequest("posts", "doc", "1")
        .source("user", "kimchy",
            "postDate", new Date(),
            "message", "trying out Elasticsearch");

    IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
    System.out.println(index.getResult());
  }

  private static void info(RestHighLevelClient client) throws IOException {
    MainResponse info = client.info(RequestOptions.DEFAULT);
    System.out.println(info.getVersion());
  }
}
