package io.rxjava.test;

import org.junit.Test;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by gmind on 2015-04-14.
 */
public class TestRxJavaSchedulerExample {

	public Observable<Integer> observableNextData() {
		return Observable.<Integer>create(subscribe -> {
			for(int i=0; i<10; i++) {
				subscribe.onNext(i);
			}
			subscribe.onCompleted();
		});
	}

	@Test
	public void schedulers1() throws InterruptedException {
		System.out.println("junit thread : " + Thread.currentThread().toString());

		Observable<Integer> eventData = observableNextData();

		eventData
			.subscribe(response -> {
				System.out.println("rx1 thread : " + Thread.currentThread().toString());
			});

		Observable<Integer> eventData1 = observableNextData();

		eventData1
			.subscribe(response -> {
				System.out.println("rx2 thread : " + Thread.currentThread().toString());
			});
	}

	@Test
	public void schedulers2() throws InterruptedException {

		System.out.println("junit thread : " + Thread.currentThread().toString());

		Observable<Integer> eventData = observableNextData();

		eventData
			.subscribeOn(Schedulers.computation())
			.subscribe(response -> {
				System.out.println("rx1 thread : " + Thread.currentThread().toString());
			});

		Observable<Integer> eventData1 = observableNextData();

		eventData1
			.subscribeOn(Schedulers.computation())
			.subscribe(response -> {
				System.out.println("rx2 thread : " + Thread.currentThread().toString());
			});
	}

}
