package com.mycom;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

//List as Data source
public class ListOperator {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Observable stream = Observable.fromIterable(list);
        stream.subscribe(System.out::println, System.out::println, () -> {
            System.out.println("Done");
        });
    }
}
