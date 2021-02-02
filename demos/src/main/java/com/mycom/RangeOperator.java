package com.mycom;

import io.reactivex.Observable;

//i want to stream 1..1000
public class RangeOperator {
    public static void main(String[] args) {
     Observable<Integer>  stream = Observable.range(1,100);
        stream.subscribe(System.out::println, System.out::println, () -> {
            System.out.println("Done");
        });
    }
}
