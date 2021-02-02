package com.mycom;

import io.reactivex.Observable;

public class ArrayOperator {
    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5};
        Observable stream = Observable.fromArray(array);
        stream.subscribe(System.out::println, System.out::println, () -> {
            System.out.println("Done");
        });
    }
}
