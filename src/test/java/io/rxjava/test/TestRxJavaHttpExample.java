package io.rxjava.test;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.junit.Test;
import rx.apache.http.ObservableHttp;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by gmind on 2015-04-14.
 */
public class TestRxJavaHttpExample {

	@Test
	public void httpObservable() throws URISyntaxException, IOException, InterruptedException {
		CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
		httpClient.start();
		ObservableHttp
			.createRequest(HttpAsyncMethods.createGet("http://www.wikipedia.com"), httpClient)
			.toObservable()
			.flatMap(response -> {
				return response.getContent().map(bytes -> {
					return new String(bytes);
				});
			})
			.toBlocking().forEach(resp -> {
				System.out.println(resp);
			});
		httpClient.close();
	}

}
