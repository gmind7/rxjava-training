package io.rxjava.test;

import org.junit.Test;
import rx.Observable;

/**
 * Created by gmind on 2015-04-14.
 */
public class TestRxJavaErrorExample {

	public Observable<Integer> observableNextData() {
		return Observable.<Integer>create(subscribe -> {
			for(int i=0; i<10; i++) {
				subscribe.onNext(i);
			}
			subscribe.onCompleted();
		});
	}

	public Observable<Integer> observableNextErrorData() {
		return Observable.<Integer>create(subscribe -> {
			for(int i=0; i<10; i++) {
				subscribe.onNext(i);
				if(i==3) {
					throw new RuntimeException("error");
				}
			}
			subscribe.onCompleted();
		}).doOnError(error -> {
			System.out.println("doOnError : " + error);
		});
	}

	@Test
	public void errorResumeNext() {
		Observable<Integer> errorData = observableNextErrorData().onErrorResumeNext(observableNextData());
		errorData.subscribe(System.out::println);
        // 0 1 2 3 // error data
        // 0 ~ 9   // observable data

	}

	@Test
	public void errorReturn() {
		Observable<Integer> errorData = observableNextErrorData().onErrorReturn(error -> {
			return 777;
		});
        errorData.subscribe(System.out::println);
        // 0 1 2 3 // error data
        // 0 ~ 9   // return data
	}

	@Test
	public void errorException() {
		Observable<Integer> errorData = observableNextErrorData().onExceptionResumeNext(observableNextData());
        errorData.subscribe(System.out::println);
        // 0 1 2 3 // exception data
        // 0 ~ 9   // observable data
	}

	@Test
	public void errorHandler() throws InterruptedException {

		Observable<Integer> errorData1 = observableNextErrorData();

		errorData1.subscribe(System.out::println, System.out::println);

		Observable<Integer> errorData2 = observableNextData();

        errorData2.subscribe(System.out::println, System.out::println);
	}

	@Test
	public void errorHandler2() {
		Observable<Integer> errorData1 = observableNextErrorData();
		Observable<Integer> errorData2 = observableNextErrorData();

		Observable.zip(errorData1, errorData2, (x, y) -> x + y)
                  .subscribe(System.out::println, System.out::println);
	}

}
