package com.mycom;

import io.reactivex.Flowable;

public class BackPressureStream {
    public static void main(String[] args) {
        Flowable.just("Hello world").subscribe(System.out::println);
    }
}
