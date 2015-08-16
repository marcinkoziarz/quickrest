package pl.koziarz.quickrest.httpclient;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import pl.koziarz.quickrest.HttpMethod;
import pl.koziarz.quickrest.QuickRestRequestFactoryAdapter;
import pl.koziarz.quickrest.RestRequest;
import pl.koziarz.quickrest.RestRequestWithBody;

public class HttpClientRequestFactory extends QuickRestRequestFactoryAdapter {

	private HttpClient httpClient;
	
	public HttpClientRequestFactory() {
		this.httpClient = HttpClientBuilder.create().build();
	}
	
	public HttpClientRequestFactory(HttpClient httpClient) {
		super();
		this.httpClient = httpClient;
	}

	@Override
	public RestRequest get(String url) {
		RestRequestImpl req = new RestRequestImpl(HttpMethod.GET,url,httpClient);
		return req;
	}

	@Override
	public RestRequestWithBody post(String url) {
		RestRequestImpl req = new RestRequestImpl(HttpMethod.POST,url,httpClient);
		return req;
	}

	@Override
	public RestRequestWithBody put(String url) {
		RestRequestImpl req = new RestRequestImpl(HttpMethod.PUT,url,httpClient);
		return req;
	}

}
