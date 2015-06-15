package io.springcamp.rx;

import com.google.common.collect.Lists;
import io.rxjava.domain.Brick;
import io.rxjava.domain.IPhong;
import org.junit.Test;
import rx.Observable;
import rx.subjects.PublishSubject;

import java.util.List;

/**
 * Created by gmind on 2015-04-14.
 */
public class TestRxJavaBasicExample {

	@Test
	public void just(){
		Observable.just("java").subscribe(s -> System.out.println(s));
		// java
	}

	@Test
	public void from(){
		List<Integer> intList = Lists.newArrayList();

		for(int i=0; i<10; i++) {
			intList.add(i);
		}

		Observable.from(intList).subscribe(s -> System.out.println(s));
		// 1
		// 2
		// ..
	}

	@Test
	public void create(){
		Observable.<String>create(subscribe -> {
			subscribe.onNext("java");
			subscribe.onCompleted();
		}).subscribe(System.out::println);
		// java
	}

	@Test
	public void filter(){

		List<Integer> intList = Lists.newArrayList();

		for(int i=0; i<10; i++) {
			intList.add(i);
		}

		Observable.from(intList)
			.filter(x -> x % 2 == 0)
			.subscribe(System.out::println);
		// 0
		// 2
		// ..
	}

	@Test
	public void map(){
		Observable.just("java")
			.map(x -> "rx" + x)
			.subscribe(System.out::println);
		// rxjava
	}

	@Test
	public void flatMap(){
		List<IPhong> iphongBoxs1 = Lists.newArrayList();
		List<IPhong> iphongBoxs2 = Lists.newArrayList();

		for(int i=0; i<10; i++) {
			iphongBoxs1.add(new IPhong(i, "아이퐁1"));
		}

		for(int i=0; i<10; i++) {
			iphongBoxs2.add(new IPhong(i, "아이퐁2"));
		}

		Observable.just(iphongBoxs1, iphongBoxs2)
			.flatMap(iphongs -> Observable.from(iphongs))
			.map(x -> new Brick(x.getId(), "벽돌"))
			.subscribe(System.out::println);
	}

	@Test
	public void merge(){
		Observable<String> low = Observable.just("a", "b", "c");
		Observable<String> upper = Observable.just("A", "B", "C");

		Observable.merge(low, upper).subscribe(System.out::println);
		// a
		// b
		// c
		// A
		// B
		// C
	}

	@Test
	public void startWith(){
		Observable<String> low = Observable.just("a", "b", "c");
		Observable<String> upper = Observable.just("d", "e");

		low.startWith(upper).subscribe(System.out::println);
		// d
		// e
		// a
		// b
		// c
	}

	@Test
	public void zip(){
		Observable<String> low = Observable.just("a", "b", "c");
		Observable<Integer> upper = Observable.just(1, 2, 3);

		Observable.zip(low, upper, (a, b) -> a + "-" + b)
			.subscribe(System.out::println);
		// a-A
		// b-B
		// c-C
	}

	@Test
	public void reduce(){
		Observable.just(1, 2, 3, 4, 5)
			.reduce((seed, value) -> seed + value)
			.subscribe(System.out::println);
		// 15
	}

	@Test
	public void publishSubject(){
		PublishSubject<Integer> subject = PublishSubject.create();

		subject.map(x -> x * x)
			.subscribe(s -> System.out.println("subject1: " + s));

		subject.map(x -> x + x)
			.subscribe(s -> System.out.println("subject2: " + s));

		subject.onNext(10);
		subject.onNext(20);
		subject.onCompleted();

		// subject1: 100
		// subject2: 20
		// subject1: 400
		// subject2: 40
	}

	public Observable<Integer> observableNextData() {
		return Observable.<Integer>create(subscribe -> {
			for (int i = 0; i < 10; i++) {
				subscribe.onNext(i);
			}
			subscribe.onCompleted();
		});
	}

	@Test
	public void event() throws InterruptedException {
		Observable<Integer> eventData = observableNextData();

		eventData
			.subscribe(response -> {
				System.out.println(response);
			});
	}

	@Test
	public void take() throws InterruptedException {
		Observable<Integer> eventData = observableNextData();

		eventData
			.take(3)
			.subscribe(response -> {
				System.out.println(response);
			});
	}

}
