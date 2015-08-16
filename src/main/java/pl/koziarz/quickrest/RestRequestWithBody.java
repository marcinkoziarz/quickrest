package pl.koziarz.quickrest;

import org.json.JSONObject;

public interface RestRequestWithBody extends RestRequest {
	public RestRequestWithBody field(String name, String value);

	/* from RestRequest */
	public RestRequestWithBody header(String header, String value);
	public RestRequestWithBody expectResponseStatus(int expectedStatus);
	public RestRequestWithBody onSuccess(RestResponseHandler handler);
	public RestRequestWithBody onFail(RestResponseHandler handler);
	public RestRequestWithBody query(String key, String value);
	public RestRequestWithBody pathParam(String key, String value);
}
