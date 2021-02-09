package com.example.myvertxapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.example.util.Runner;

public class StreamingVerticle extends AbstractVerticle {
  public static void main(String[] args) {
    Runner.runExample(StreamingVerticle.class);
  }

  @Override
  public void start() throws Exception {
    super.start();
    OpenOptions opts = new OpenOptions().setRead(true);
    vertx.fileSystem().open("big.file", opts, ar -> {
      if (ar.succeeded()) {
        System.out.println("File open");
        //start reading file in streaming mode.
        AsyncFile file = ar.result();
        file.handler(System.out::println)
          .exceptionHandler(Throwable::printStackTrace)
          .endHandler(done -> {
            System.out.println("\n--- DONE");
            vertx.close();
          });
      } else {
        System.out.println(ar.cause());
      }
    });

  }
}
