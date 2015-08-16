package pl.koziarz.quickrest.plugin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import pl.koziarz.quickrest.QuickRestException;
import pl.koziarz.quickrest.RestRequestWithBody;
import pl.koziarz.quickrest.RestResponse;
import pl.koziarz.quickrest.RestResponseHandler;

public abstract class RestRequestWithBodyAbstract implements RestRequestWithBody {

	private static Logger log = LogManager.getLogger(RestRequestWithBodyAbstract.class);

	@Override
	public String asString() throws QuickRestException {
		return asResponse().getEntity();
	}

	@Override
	public <T> RestResponse<T> asResponse(Class<T> contentType) throws QuickRestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T asObject(Class<T> class1) throws QuickRestException {
		return asResponse(class1).getEntity();
	}

	@Override
	public RestRequestWithBody field(String name, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestRequestWithBody header(String header, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestRequestWithBody expectResponseStatus(int expectedStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestRequestWithBody onSuccess(RestResponseHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestRequestWithBody onFail(RestResponseHandler handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestRequestWithBody query(String key, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestRequestWithBody pathParam(String key, String value) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
