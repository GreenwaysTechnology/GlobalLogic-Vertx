package com.mycom;

import io.reactivex.Observable;

public class SubscriptionOperators {
    public static void main(String[] args) {
        Observable
                .range(1, 10)
                .subscribe(System.out::println, System.out::println, () -> System.out.println("done"));
        //Operators for data event,error event, complete.
        Observable.range(1, 10)
                .doOnSubscribe(subscriber-> System.out.println("On Subscription"))
                .doOnNext(System.out::println)
                .doOnError(System.out::println)
                .doOnComplete(() -> System.out.println("done!!"))
                .subscribe();

    }
}
