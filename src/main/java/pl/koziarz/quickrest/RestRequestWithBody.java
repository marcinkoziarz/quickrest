package pl.koziarz.quickrest;

public interface RestRequestWithBody extends RestRequest {
	/**
	 * Set request body element to given value
	 * @param name key of body element
	 * @param value value of body element
	 * @return current request
	 */
	public RestRequestWithBody field(String name, String value);
	public RestRequestWithBody entity(Entity entity);
	
	
	/* from RestRequest */
	public RestRequestWithBody header(String header, String value);
	public RestRequestWithBody header(String header, String value, boolean condition);
	public RestRequestWithBody expectResponseStatus(int expectedStatus);
	public RestRequestWithBody onSuccess(RestResponseHandler handler);
	public RestRequestWithBody onFail(RestResponseHandler handler);
	public RestRequestWithBody query(String key, String value);
	public RestRequestWithBody pathParam(String key, String value);
	public RestRequestWithBody exceptionOnFail(boolean exceptionOnFail);
}
