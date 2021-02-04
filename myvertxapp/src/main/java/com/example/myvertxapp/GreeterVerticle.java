package com.example.myvertxapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.example.util.Runner;

public class GreeterVerticle extends AbstractVerticle {
  public static void main(String[] args) {

    Runner.runExample(GreeterVerticle.class);
//    //way 1 : get vertx instance
//    Vertx vertx = Vertx.vertx();
//    //option1 ; create object for verticle class
//    vertx.deployVerticle(new HelloWorldVerticle());
//    //option 2 ; verticle class
//    vertx.deployVerticle(HelloWorldVerticle.class.getName());
//    //option 3 : verticle class string
//    vertx.deployVerticle("com.example.myvertxapp.HelloWorldVerticle");

  }

  @Override
  public void start() throws Exception {
    super.start();
    vertx.deployVerticle("com.example.myvertxapp.HelloWorldVerticle");
  }
}
