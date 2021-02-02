package com.mycom;

import io.reactivex.Observable;

public class Transformation {
    public static void main(String[] args) {
        Observable.range(1, 10)
                .map(x -> 10 * x)
                .map(y -> 2 * y)
                .subscribe(System.out::println, System.out::println, () -> {
                    System.out.println("Done");
                });
    }
}
