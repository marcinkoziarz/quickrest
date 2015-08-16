package pl.koziarz.quickrest.httpclient;

import pl.koziarz.quickrest.RestResponse;

public class RestResponseImpl<T> implements RestResponse<T> {

	private int statusCode;
	private String statusText;
	private T entity;
	
	public RestResponseImpl(T entity, int statusCode, String statusText) {
		super();
		this.entity = entity;
		this.statusCode = statusCode;
		this.statusText = statusText;
	}

	@Override
	public int getStatusCode() {
		return statusCode;
	}

	@Override
	public String getStatusText() {
		return statusText;
	}

	@Override
	public T getEntity() {
		return entity;
	}

}
