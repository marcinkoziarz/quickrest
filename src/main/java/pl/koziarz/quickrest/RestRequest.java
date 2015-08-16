package pl.koziarz.quickrest;

import org.json.JSONObject;

public interface RestRequest {
	public RestRequest header(String header, String value);
	public RestRequest expectResponseStatus(int expectedStatus);
	public RestRequest onSuccess(RestResponseHandler handler);
	public RestRequest onFail(RestResponseHandler handler);
	public String asString() throws QuickRestException;
	public RestRequest query(String key, String value);
	public RestRequest pathParam(String key, String value);
	public <T> RestResponse<T> asResponse(Class<T> contentType) throws QuickRestException;
	public RestResponse<String> asResponse() throws QuickRestException;
	public <T> T asObject(Class<T> class1) throws QuickRestException;
	public JSONObject asJson() throws QuickRestException;
}
