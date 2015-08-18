package pl.koziarz.quickrest;

import pl.koziarz.quickrest.httpclient.HttpClientRequestFactory;

/**
 * Interface for factory creating requests with certain http backend.
 * @see {@link HttpClientRequestFactory}
 */
public interface QuickRestRequestFactory {
	
	/**
	 * Create GET request
	 * @param url resource address to be called
	 * @return GET request
	 */
	public RestRequest get(String url);

	/**
	 * Create POST request
	 * @param url resource address to be called
	 * @return POST request
	 */
	public RestRequestWithBody post(String url);
	
	/**
	 * Create PUT request
	 * @param url resource address to be called
	 * @return PUT request
	 */
	public RestRequestWithBody put(String url);

	/**
	 * Create PATCH request
	 * @param url resource address to be called
	 * @return PATCH request
	 */
	public RestRequestWithBody patch(String url);
	
	/**
	 * Create DELETE request
	 * @param url resource address to be called
	 * @return DELETE request
	 */
	public RestRequestWithBody delete(String url);
}
