package com.example.myvertxapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.example.util.Runner;

class UserServiceVerticle extends AbstractVerticle {
  //getUser----login----showpage
  public Future<String> getUser() {
    System.out.println("Get user is called");
    Promise<String> promise = Promise.promise();
    String userName = "admin";
    if (userName != null) {
      promise.complete(userName);
    } else {
      promise.fail(new RuntimeException("User not found"));
    }
    return promise.future();
  }

  public Future<String> login(String userName) {
    System.out.println("login user is called");

    Promise<String> promise = Promise.promise();
    if (userName.equals("admin")) {
      promise.complete("login success");
    } else {
      promise.fail(new RuntimeException("login failed"));
    }
    return promise.future();
  }

  public Future<String> showPage(String loginStatus) {
    System.out.println("show Page is called");

    Promise<String> promise = Promise.promise();
    if (loginStatus.equals("login success")) {
      promise.complete("You are admin");
    } else {
      promise.fail(new RuntimeException("You are guest"));
    }
    return promise.future();
  }

  public void callbackHellApi() {
    getUser().onComplete(userAsyncResult -> {
      System.out.println("Get user is called");
      if (userAsyncResult.succeeded()) {
        //call login method
        login(userAsyncResult.result()).onComplete(loginAsyncResult -> {
          System.out.println("login is called");
          if (loginAsyncResult.succeeded()) {
            //call showPage Method
            showPage(loginAsyncResult.result()).onComplete(pageAsyncResult -> {
              System.out.println("Show page is called");
              if (pageAsyncResult.succeeded()) {
                System.out.println(pageAsyncResult.result());
              } else {
                System.out.println(pageAsyncResult.cause());
              }
            });
          }

        });

      } else {
        System.out.println(userAsyncResult.cause());
      }
    });
  }

  public void composeApi() {
    getUser()
      .compose(user -> {
       // System.out.println("Get user is called");
        return login(user);
      })
      .compose(status -> {
      //  System.out.println("login is called");
        return showPage(status);
      })
      .onSuccess(System.out::println)
      .onFailure(System.out::println);
    //Refactoring using java lambdas
    getUser()
      .compose(user->login(user))
      .compose(status->showPage(status))
      .onSuccess(System.out::println)
      .onFailure(System.out::println);
    //Method Reference Syntax
    getUser()
      .compose(this::login)
      .compose(this::showPage)
      .onSuccess(System.out::println)
      .onFailure(System.out::println);

  }


  @Override
  public void start() throws Exception {
    super.start();
    composeApi();

  }
}


public class CallNestingVerticle extends AbstractVerticle {
  public static void main(String[] args) {
    Runner.runExample(CallNestingVerticle.class);
  }

  @Override
  public void start() throws Exception {
    super.start();
    vertx.deployVerticle(new UserServiceVerticle());
  }
}
