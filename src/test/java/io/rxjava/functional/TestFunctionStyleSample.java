package io.rxjava.functional;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ch.lambdaj.Lambda.filter;
import static org.hamcrest.Matchers.startsWith;

public class TestFunctionStyleSample {

	@Test
	@Ignore
	public void task25() {
		for (Object o : take(25, squaresOf(integers()))) {
			System.out.println(o);
		}
	}

	public static List<?> take(int n, List<?> list) {
		return list.subList(0, n);
	}

	public static List<Integer> squaresOf(List<Integer> list) {
		List<Integer> result = new ArrayList<Integer>();
		for (Integer number : list) {
			result.add(number.intValue() * number.intValue());
		}
		return result;
	}

	public static List<Integer> integers() {
		List<Integer> result = new ArrayList<Integer>();
		//for (int i = 1; i <= Integer.MAX_VALUE; i++) {
        for (int i = 1; i <= 50; i++) {
			result.add(i);
		}
		return result;
	}

	@Test
	public void oldStyleJava() {
		List<String> names = Arrays.asList("RxJava", "Akka", "Reactor");
		List<String> filterNames = new ArrayList<>();
		for(String name: names) {
			if(name.startsWith("R")) {
				filterNames.add(name);
			}
		}
		System.out.println(filterNames.toString());
	}

	@Test
	public void Java8Style() {
		List<String> names = Arrays.asList("RxJava", "Akka", "Reactor");
		List<String> filterNames = names.stream()
			.filter(name -> name.startsWith("R"))
			.collect(Collectors.toList());
		System.out.println(filterNames.toString());
	}

	@Test
	public void lambdaJStyle() {
		List<String> names = Arrays.asList("RxJava", "Akka", "Reactor");
		List<String> filterNames = filter(startsWith("R"), names);
		System.out.println(filterNames.toString());
	}

}
