package pl.koziarz.quickrest;

import pl.koziarz.quickrest.httpclient.HttpClientRequestFactory;

public class QuickRest {
	
	private QuickRestRequestFactory factory;
	
	/**
	 * Default constructor for QuickRest instance
	 */
	public QuickRest() {
		factory = new HttpClientRequestFactory();
	}
	
	public QuickRest(HttpClientRequestFactory factory) {
		this.factory=factory;
	}

	/**
	 * Begin creation of POST request
	 * @param url path to resource
	 * @return Post request
	 */
	public RestRequestWithBody post(String url) {
		RestRequestWithBody req = factory.post(url);
		appendDefaultsWithBody(req);
		return req;
	}
	
	public RestRequest get(String url) {
		RestRequest req = factory.get(url);
		appendDefaults(req);
		return req;
	}

	public RestRequestWithBody put(String url) {
		RestRequestWithBody req = factory.put(url);
		appendDefaultsWithBody(req);
		return req;
	}

	public RestRequestWithBody patch(String url) {
		RestRequestWithBody req = factory.patch(url);
		appendDefaultsWithBody(req);
		return req;
	}

	public RestRequestWithBody delete(String url) {
		RestRequestWithBody req = factory.delete(url);
		appendDefaultsWithBody(req);
		return req;
	}


	
	private void appendDefaults(RestRequest req) {
		
	}
	
	private void appendDefaultsWithBody(RestRequestWithBody req) {
		appendDefaults(req);
	}
	
}
