package com.example.myvertxapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.example.util.Runner;

public class JSONMainVerticle extends AbstractVerticle {
  public static void main(String[] args) {
    Runner.runExample(JSONMainVerticle.class);
  }

  //create json object
  public void createJson() {
    JsonObject profile = new JsonObject();
    profile.put("id", 1);
    profile.put("name", "Subramanian");
    profile.put("city", "Coimbatore");
    //parsing
    System.out.println(profile.encodePrettily());
  }

  public void createNestedJson() {
    JsonObject profile = new JsonObject();
    profile.put("id", 1);
    profile.put("name", "Subramanian");
    profile.put("city", "Coimbatore");
    JsonObject skills = new JsonObject();
    skills.put("id", "Java");
    profile.put("skills", skills);
    //parsing
    System.out.println(profile.encodePrettily());
  }

  //json fluent
  public void createJsonFluent() {
    JsonObject profile = new JsonObject()
      .put("id", 1)
      .put("name", "Subramanian")
      .put("city", "coimbatore")
      .put("skills", new JsonObject().put("skills", "Vertx"));
    System.out.println(profile.encodePrettily());
  }

  public void createJsonList() {
    JsonArray list = new JsonArray()
      .add(new JsonObject()
        .put("id", 1)
        .put("name", "Subramanian")
        .put("city", "coimbatore")
        .put("skills", new JsonObject().put("skills", "Vertx")))
      .add(new JsonObject()
        .put("id", 2)
        .put("name", "Ram")
        .put("city", "Chennai")
        .put("skills", new JsonObject().put("skills", "Devops")));
    System.out.println(list.encodePrettily());
  }

  public void mergeJson(JsonObject jsonObject) {
    JsonObject appConfig = new JsonObject()
      .put("http.port", 8080)
      .mergeIn(jsonObject);
    System.out.println(appConfig.encodePrettily());
  }

  //Sending json via future
  public Future<JsonObject> sendingJsonViaFuture() {
    Promise<JsonObject> promise = Promise.promise();
    JsonObject profile = new JsonObject()
      .put("id", 1)
      .put("name", "Subramanian")
      .put("city", "coimbatore")
      .put("skills", new JsonObject().put("skills", "Vertx"));
    promise.complete(profile);
    return promise.future();
  }


  @Override
  public void start() throws Exception {
    super.start();
//    createJson();
//    createNestedJson();
//    createJsonFluent();
//    createJsonList();
    mergeJson(new JsonObject().put("http.port", 3000).put("http.host", "globallogic.com").put("http.ssl", true));
    sendingJsonViaFuture().onSuccess(profile -> {
      System.out.println(profile.encodePrettily());
    });
  }
}
