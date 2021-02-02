package com.mycom;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class IntervalOperator {
    public static void main(String[] args) {
        //sequence of number for every 1000ms
        Observable<Long> stream = Observable.interval(1000, TimeUnit.MILLISECONDS);

        System.out.println(Thread.currentThread().getName());
        stream.subscribe(data->{
            System.out.println(Thread.currentThread().getName());
            System.out.println(data);
        }, System.out::println, () -> {
            System.out.println("Completed");
        });
        //pause main thread so that i can see data emission.
        try {
            Thread.sleep(10000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
