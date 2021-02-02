package com.mycom;

import io.reactivex.Observable;

//Builder /fluent pattern

public class MoreDeclarative {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5)
        .subscribe(System.out::println, System.out::println, () -> {
            System.out.println("Done");
        });
    }


}
