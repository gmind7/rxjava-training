package io.rxjava.test;

import com.google.common.collect.Lists;
import org.junit.Test;
import rx.Observable;
import rx.subjects.AsyncSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

import java.util.List;

/**
 * Created by gmind on 2015-04-14.
 */
public class TestRxJavaSubjectExample {

	@Test
	public void publishSubject(){
		PublishSubject<Integer> subject = PublishSubject.create();

		subject.map(x -> x*x).subscribe(s -> {
			System.out.println("subject1: " + s);
		});

		subject.map(x -> x+x).subscribe(s -> {
			System.out.println("subject2: " + s);
		});

		subject.onNext(10);
		subject.onNext(20);
		subject.onCompleted();

		// subject1: 100
		// subject2: 20
		// subject1: 400
		// subject2: 40
	}

	@Test
	public void replaySubjectTest(){
		List<Integer> initialNumbers = Lists.newArrayList();
		initialNumbers.add(1);
		initialNumbers.add(2);

		Observable<Integer> observableInitial = Observable.from(initialNumbers);
		ReplaySubject<Integer> subject = ReplaySubject.create();

		Observable<Integer> source = Observable.merge(observableInitial, subject);

		source.subscribe(System.out::println);

		for (int i = 0; i < 100; ++i) {
			subject.onNext(i);
		}
	}

	@Test
	public void asyncSubjectTest(){
		AsyncSubject<Object> subject1 = AsyncSubject.create();
		subject1.subscribe(System.out::println);
		subject1.onNext("1one");
		subject1.onNext("1two");
		subject1.onNext("1three");
		subject1.onCompleted();

		AsyncSubject<Object> subject2 = AsyncSubject.create();
		subject2.subscribe(System.out::println);
		subject2.onNext("2one");
		subject2.onNext("2two");
		subject2.onNext("2three");
		subject2.onCompleted();
	}

}
