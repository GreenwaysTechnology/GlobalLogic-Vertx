package com.mycom;

import io.reactivex.Observable;

public class CreateStream {
    public static void main(String[] args) {
        Observable<Integer> stream = Observable.create(subscriber -> {
            //push data(data,error,complete) into stream , where consumer listens for data.
            subscriber.onNext(12);
            subscriber.onNext(23);
         //   subscriber.onError(new RuntimeException("SOmething went wrong"));
            subscriber.onNext(56);
            subscriber.onNext(89);
            subscriber.onComplete();
            subscriber.onNext(999999);

        });
        //subscriber/observer/listener
        stream.subscribe(data -> System.out.println(data), err -> System.out.println(err), () -> {
            System.out.println("Done");
        });
    }

}
