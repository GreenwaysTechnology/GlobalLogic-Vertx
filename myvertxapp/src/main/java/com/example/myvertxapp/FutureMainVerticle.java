package com.example.myvertxapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.example.util.Runner;

class MessageServiceVerticle extends AbstractVerticle {

  //empty Empty future
  public Future<Void> getEmptyFuture() {
    //create Future Object
    Future<Void> future = Future.future();
    future.complete();
    return future;
  }

  //send data only
  public Future<String> getSuccessMessage() {
    //create Future Object
    Future<String> future = Future.future();
    future.complete("Hello!");
    return future;
  }

  //send error only
  public Future<String> getErrorMessage() {
    //create Future Object
    Future<String> future = Future.future();
    future.fail(new RuntimeException("SOmething went wrong"));
    return future;
  }

  //logic
  public Future<String> login(String userName, String password) {
    Future<String> future = Future.future();
    if (userName.equals("admin") && password.equals("admin")) {
      future.complete("Login Success");
    } else {
      future.fail(new RuntimeException("Login Failed"));
    }

    return future;
  }

  //Function as parameter
  public void processMessage(String message,Handler<AsyncResult<String>> asyncHandler) {
    //encasulate data
    if (message.equals("greet")) {
      asyncHandler.handle(Future.succeededFuture("You have Message"));
    } else {
      asyncHandler.handle(Future.failedFuture("You have no Message"));

    }
  }
  //static method to create future instance
  //create Future object using factory apis
  public Future<String> getHelloMessage() {
    //encapsulate response
    String message = "hello";
    if (message.equals("hello")) {
      return Future.succeededFuture("Yes Hello Message i got it");
    } else {
      return Future.failedFuture(new RuntimeException("No, i did not get any message"));
    }
  }


  @Override
  public void start() throws Exception {
    super.start();
    //process future.
    getEmptyFuture().setHandler(asyncResult -> {
      if (asyncResult.succeeded()) {
        System.out.println("Empty future called");
      } else {
      }
    });
    getEmptyFuture().onComplete(asyncResult -> {
      if (asyncResult.succeeded()) {
        System.out.println("Empty future called");
      } else {
      }
    });
    //////////////////////////////////////////////////////////////////////////////////
    getSuccessMessage().onComplete(asyncResult -> {
      if (asyncResult.succeeded()) {
        System.out.println(asyncResult.result());
      }
    });
    getSuccessMessage().onSuccess(message -> {
      System.out.println(message);
    });
    getSuccessMessage().onSuccess(System.out::println);

    /////////////////////////////////////////////////////////
    getErrorMessage().onComplete(asynResult -> {
      if (asynResult.failed()) {
        System.out.println(asynResult.cause());
      }
    });
    getErrorMessage().onFailure(error -> System.out.println(error));
    getErrorMessage().onFailure(System.out::println);

    login("admin", "admin").onComplete(asyncResult -> {
      if (asyncResult.succeeded()) {
        System.out.println(asyncResult.result());
      } else {
        System.out.println(asyncResult.failed());
      }
    });
    login("foo", "bar").onComplete(asyncResult -> {
      if (asyncResult.succeeded()) {
        System.out.println(asyncResult.result());
      } else {
        System.out.println(asyncResult.cause());
      }
    });
    login("admin", "admin")
      .onSuccess(System.out::println)
      .onFailure(System.out::println);
    ////////////////////////////////////////////////////////////////
    processMessage("greet",asyncResult -> {
      if (asyncResult.succeeded()) {
        System.out.println(asyncResult.result());
      } else {
        System.out.println(asyncResult.cause());
      }
    });
    processMessage("foo",asyncResult -> {
      if (asyncResult.succeeded()) {
        System.out.println(asyncResult.result());
      } else {
        System.out.println(asyncResult.cause());
      }
    });

    ///static method processing
    getHelloMessage()
      .onSuccess(System.out::println)
      .onFailure(System.out::println);
  }
}


public class FutureMainVerticle extends AbstractVerticle {
  public static void main(String[] args) {
    Runner.runExample(FutureMainVerticle.class);
  }

  @Override
  public void start() throws Exception {
    super.start();
    vertx.deployVerticle(new MessageServiceVerticle());
  }
}
