package pl.koziarz.quickrest;

import org.json.JSONObject;

public class EmptyRestRequestWithBody implements RestRequestWithBody {

	HttpMethod method;
	
	public EmptyRestRequestWithBody(HttpMethod method) {
		super();
		this.method = method;
	}

	@Override
	public String asString() throws QuickRestException {
		throw new QuickRestException("Method "+this.method+" not implemented yet");
	}

	@Override
	public <T> T asObject(Class<T> class1) throws QuickRestException {
		throw new QuickRestException("Method "+this.method+" not implemented yet");
	}

	@Override
	public RestRequestWithBody field(String name, String value) {
		return this;
	}


	@Override
	public RestRequestWithBody header(String header, String value) {
		return this;
	}

	@Override
	public RestRequestWithBody expectResponseStatus(int expectedStatus) {
		return this;
	}

	@Override
	public RestRequestWithBody onSuccess(RestResponseHandler handler) {
		return this;
	}

	@Override
	public RestRequestWithBody onFail(RestResponseHandler handler) {
		return this;
	}

	@Override
	public RestRequestWithBody query(String key, String value) {
		return this;
	}

	@Override
	public RestRequestWithBody pathParam(String key, String value) {
		return this;
	}

	@Override
	public <T> RestResponse<T> asResponse(Class<T> contentType) throws QuickRestException {
		throw new QuickRestException("Method "+this.method+" not implemented yet");
	}

	@Override
	public RestResponse<String> asResponse() throws QuickRestException {
		throw new QuickRestException("Method "+this.method+" not implemented yet");
	}

	@Override
	public RestRequestWithBody exceptionOnFail(boolean exceptionOnFail) {
		return this;
	}

	

}
