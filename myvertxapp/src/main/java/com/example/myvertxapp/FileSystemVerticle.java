package com.example.myvertxapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.example.util.Runner;

class FileOperations extends AbstractVerticle {

  public Future<String> readFile() {
    Promise<String> promise = Promise.promise();
    String filePath = "hello.txt";
    vertx.fileSystem().readFile(filePath, fileHandler -> {
      if (fileHandler.succeeded()) {
        // System.out.println("Read content: " + fileHandler.result());
        promise.complete(fileHandler.result().toString());
        //  promise.complete("fiel read is completed");
      } else {
        System.out.println("read failed");
        promise.fail(fileHandler.cause());
      }
    });
    return promise.future();
  }

  public Future<String> writeFile(String data) {
    Promise<String> promise = Promise.promise();
    String filePath = "greetings.txt";
    //String filePath = "src/main/resources/write.txt";
    vertx.fileSystem().writeFile(filePath, Buffer.buffer(data), fileHandler -> {
      if (fileHandler.succeeded()) {
        promise.complete("Write Operation is completed");
      } else {
        System.err.println("Error while writing in file: ");

        promise.fail(fileHandler.cause());
      }
    });

    return promise.future();
  }
  private Future<String> copyFile(String input) {
    Future<String> future = Future.future();

    // Retrieve a FileSystem object from vertx instance and call the
    // non-blocking writeFile method
    vertx.fileSystem().copy(input, "src/main/resources/example03/writecopy.txt", handler -> {
      if (handler.succeeded()) {
        System.out.println("Copy done of " + input);
        future.complete("Copy success");
      } else {
        System.err.println("Error while copying a file: " + handler.cause().getMessage());
        future.fail(handler.cause());
      }
    });

    return future;
  }

  @Override
  public void start() throws Exception {
    super.start();
    System.out.println("start");
    readFile().onSuccess(result -> {
      System.out.println(result);
    }).onFailure(fileError -> {
      System.out.println(fileError.getCause().getMessage());
    });
    vertx.setTimer(1000, handler -> {
      System.out.println("Timer async");
    });

    writeFile("Hello how are you?").onSuccess(result -> {
      System.out.println(result);
    }).onFailure(fileError -> {
      System.out.println(fileError.getCause().getMessage());
    });

    System.out.println("end");

  }
}


public class FileSystemVerticle extends AbstractVerticle {
  public static void main(String[] args) {
    Runner.runExample(FileSystemVerticle.class);
  }

  @Override
  public void start() throws Exception {
    super.start();
    vertx.deployVerticle(new FileOperations());
  }
}
