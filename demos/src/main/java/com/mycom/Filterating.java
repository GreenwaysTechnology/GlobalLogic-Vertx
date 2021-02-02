package com.mycom;

import io.reactivex.Observable;

public class Filterating {
    public static void main(String[] args) {
        Observable.range(1, 100)
                .filter(x -> x % 2 != 0)
                .map(y -> y + 2)
                .subscribe(System.out::println, System.out::println, () -> {
                    System.out.println("Done");
                });
    }
}
