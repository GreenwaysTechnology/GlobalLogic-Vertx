package com.example.myvertxapp;

import io.vertx.core.AbstractVerticle;

public class HelloWorldVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    super.start();
    //application
    System.out.println("Hello verticle : start");
  }

  @Override
  public void stop() throws Exception {
    System.out.println("Hello verticle : Stop");

  }
}
