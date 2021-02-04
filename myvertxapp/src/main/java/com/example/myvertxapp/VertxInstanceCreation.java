package com.example.myvertxapp;

import io.vertx.core.Vertx;

public class VertxInstanceCreation {
  public static void main(String[] args) {
    //create and start vertx engine
    Vertx myverx = Vertx.vertx();
    System.out.println(myverx.getClass().getName());
  }
}
