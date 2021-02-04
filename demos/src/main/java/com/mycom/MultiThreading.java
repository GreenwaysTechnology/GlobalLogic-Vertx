package com.mycom;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class MultiThreading {
    public static void simpleThreading() throws InterruptedException {
        Observable.range(1, 10)
                .map(i -> {
                    System.out.println("map operator is running on " + Thread.currentThread().getName());
                    return i * 2;
                })
                .subscribeOn(Schedulers.computation())
                .doOnNext(System.out::println)
                .subscribe(r -> {
                    System.out.println("Subscribe methods " + Thread.currentThread().getName());
                });
        Thread.sleep(10000);
    }

    public static void main(String[] args) throws InterruptedException {
        Observable.range(1, 5)
                .map(i -> {
                    System.out.println("map 1 " + Thread.currentThread().getName());
                    return i * 2;
                })
                .subscribeOn(Schedulers.computation())
                .map(i -> {
                    System.out.println("map 2 " + Thread.currentThread().getName());
                    return i * 2;
                })
               .observeOn(Schedulers.newThread())
                .map(i -> {
                    System.out.println("map 3 " + Thread.currentThread().getName());
                    return i * 2;
                })
                .observeOn(Schedulers.io())
                .map(i -> {
                    System.out.println("map 4 " + Thread.currentThread().getName());
                    return i * 2;
                }).subscribe();

        Thread.sleep(10000);
    }
}
