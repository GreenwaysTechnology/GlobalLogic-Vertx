package com.example.myvertxapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.example.util.Runner;

class PromiseVerticle extends AbstractVerticle {

  //how to send success results : can be any type
//  public Promise<String> getSuccessPromise() {
//    //create Future object
//    Promise<String> promise = Promise.promise();
//    //empty response injection
//    promise.complete("Hello I am Promise result!!");
//    return promise;
//  }
  public Future<String> getSuccessPromise() {
    //create Future object
    Promise<String> promise = Promise.promise();
    //empty response injection
    promise.complete("Hello I am Promise result!!");
    return promise.future();
  }

  @Override
  public void start() throws Exception {
    super.start();
   // getSuccessPromise().future().onSuccess(System.out::println);
    getSuccessPromise().onSuccess(System.out::println);
  }
}

public class PromiseMainVerticle extends AbstractVerticle {
  public static void main(String[] args) {
    Runner.runExample(PromiseMainVerticle.class);
  }

  @Override
  public void start() throws Exception {
    super.start();
    vertx.deployVerticle(new PromiseVerticle());
  }
}
