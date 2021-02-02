package com.mycom;

import io.reactivex.Observable;

public class JustOperator {
    public static void main(String[] args) {
        Observable<Integer> stream = Observable.just(1, 2, 3, 4, 5);

//        stream.subscribe(data -> System.out.println(data), err -> System.out.println(err), () -> {
//            System.out.println("Done");
//        });
        stream.subscribe(System.out::println, System.out::println, () -> {
            System.out.println("Done");
        });
    }
}
