package com.example.myvertxapp;

import io.vertx.core.*;
import io.vertx.core.json.JsonObject;
import io.vertx.example.util.Runner;

import java.util.Date;

class TimersVerticle extends AbstractVerticle {

  public Future<JsonObject> delay(long delayTime) {
    //callee
//    vertx.setTimer(delayTime, handlerTime -> {
//      System.out.println("Timeout");
//    });
    Promise<JsonObject> promise = Promise.promise();
    vertx.setTimer(delayTime, handlerTime -> {
      boolean isSuccess = true;
      if (isSuccess) {
        //  return Future.succeededFuture(new JsonObject().put("message", "Greetings to Non blocking"));
        promise.complete(new JsonObject().put("message", "Greetings to Non blocking"));
      } else {
        //return Future.failedFuture(new RuntimeException(new JsonObject().put("message", "something went wrong").encodePrettily()));
        promise.fail(new RuntimeException(new JsonObject().put("message", "something went wrong").encodePrettily()));
      }
    });
    return promise.future();
  }

  public void heartBeat(long timeout, Handler<AsyncResult<JsonObject>> aHandler) {
    long timerId = vertx.setPeriodic(timeout, handler -> {
      // System.out.println(new Date());
      aHandler.handle(Future.succeededFuture(new JsonObject().put("now", new Date().toString())));
    });
    //logic to cancel timer
    vertx.setTimer(10000, stopTimerId -> {
      System.out.println("Stopping Heart Beat");
      vertx.cancelTimer(timerId);
    });
  }


  @Override
  public void start() throws Exception {
    super.start();
    super.start();
    System.out.println("start");
    //caller
    delay(5000)
      .onSuccess(message -> System.out.println(message.encodePrettily()))
      .onFailure(error -> System.out.println(error.getCause()));

    heartBeat(1000, handler -> {
      if (handler.succeeded()) {
        System.out.println(handler.result().encodePrettily());
      }
    });

    System.out.println("end");
  }
}


public class TimersMainVerticle extends AbstractVerticle {
  public static void main(String[] args) {
    Runner.runExample(TimersMainVerticle.class);
  }

  @Override
  public void start() throws Exception {
    super.start();
    vertx.deployVerticle(new TimersVerticle(), handler -> {
      if (handler.succeeded()) {
        System.out.println("TimersVerticle deployed successfully " + deploymentID());
      }
    });
  }
}
