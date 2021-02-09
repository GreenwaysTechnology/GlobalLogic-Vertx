package com.example.myvertxapp;

import io.vertx.core.Vertx;

public class NonBlockingWithoutVerticle {
  public static void main(String[] args) {
    System.out.println("Plain Java app is running on " + Thread.currentThread().getName());
    Vertx vertx = Vertx.vertx();

    vertx.setPeriodic(1000, aHandler -> {
      System.out.println("Periodic " + Thread.currentThread().getName());
      // vertx.close();
    });
    //without verticle i am creating timer non blocking.
    vertx.setTimer(5000, aHandler -> {
      System.out.println("Timer : " +Thread.currentThread().getName());
      // vertx.close();
    });



  }
}
