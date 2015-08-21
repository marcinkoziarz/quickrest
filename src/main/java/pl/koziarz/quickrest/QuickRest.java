package pl.koziarz.quickrest;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import pl.koziarz.quickrest.httpclient.HttpClientRequestFactory;
import pl.koziarz.quickrest.mapper.JSONArrayMapper;
import pl.koziarz.quickrest.mapper.JSONObjectMapper;
import pl.koziarz.quickrest.mapper.QuickRestMapper;

public class QuickRest {
	
	private QuickRestRequestFactory factory;
	private HashMap<Class<?>,QuickRestMapper<?>> mappers = new HashMap<>();
	
	/**
	 * Default constructor for QuickRest instance
	 */
	public QuickRest() {
		registerInternalMappers();
		factory = new HttpClientRequestFactory();
	}
	
	public QuickRest(QuickRestRequestFactory factory) {
		registerInternalMappers();
		this.factory=factory;
	}

	private void registerInternalMappers() {
		registerMapper(JSONArray.class, new JSONArrayMapper());
		registerMapper(JSONObject.class, new JSONObjectMapper());
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

	public <T> void registerMapper(Class<T> clazz, QuickRestMapper<T> mapper) {
		mappers.put(clazz, mapper);
	}
	
	public <T> QuickRestMapper<T> getMapper(Class<T> clazz) {
		return (QuickRestMapper<T>) mappers.get(clazz);
	}

	
	private void appendDefaults(RestRequest req) {
		
	}
	
	private void appendDefaultsWithBody(RestRequestWithBody req) {
		appendDefaults(req);
	}
	
}
