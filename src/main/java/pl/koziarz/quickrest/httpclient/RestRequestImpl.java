package pl.koziarz.quickrest.httpclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.koziarz.quickrest.Entity;
import pl.koziarz.quickrest.Header;
import pl.koziarz.quickrest.HttpMethod;
import pl.koziarz.quickrest.QuickRestException;
import pl.koziarz.quickrest.QuickRestUtils;
import pl.koziarz.quickrest.RestRequestWithBody;
import pl.koziarz.quickrest.RestResponse;
import pl.koziarz.quickrest.RestResponseHandler;
import pl.koziarz.quickrest.plugin.RestRequestWithBodyAbstract;

class RestRequestImpl extends RestRequestWithBodyAbstract {

	private static Logger log = LogManager.getLogger(RestRequestImpl.class);
	
	private String url;
	private HttpClient client;
	private HttpMethod method;
	
	private HashMap<String,String> headers = new HashMap<>();
	private HashMap<String,Object> fields = new HashMap<>();
	private HashMap<String,Object> query = new HashMap<>();
	private HashMap<String,String> pathParam = new HashMap<>();
	private boolean exceptionOnFail;
	
	private int expectedStatus=200;
	private RestResponseHandler onSuccess=null;
	private RestResponseHandler onFailure=null;

	private Entity entity=null;
	
	public RestRequestImpl(HttpMethod method, String url, HttpClient client) {
		this.method=method;
		this.url=url;
		this.client=client;
	}

	public RestRequestWithBody header(String header, String value) {
		this.headers.put(header, value);
		return this;
	}
	
	public RestRequestWithBody field(String key, String value) {
		this.fields.put(key, value);
		return this;
	}	
	

	public RestRequestWithBody expectResponseStatus(int expectedStatus) {
		this.expectedStatus=expectedStatus;
		return this;
	}

	public RestRequestWithBody onSuccess(RestResponseHandler handler) {
		this.onSuccess = handler;
		return this;
	}

	public RestRequestWithBody onFail(RestResponseHandler handler) {
		this.onFailure=handler;
		return this;
	}

	private HttpUriRequest createRequest() throws QuickRestException {
		
		String url = replaceTokens(this.url, pathParam);
		if( query.size() > 0 ) {
			String queryString;
			try {
				queryString = IOUtils.toString(createURLEncodedEntity(query).getContent());
				if( url.indexOf('?') >= 0 )
					url = url+"&"+queryString;
				else {
					url = url+"?"+queryString;
				}
			} catch (IOException e) {
				throw new QuickRestException("Unable to create Query String",e);
			}
		}
		
		switch(method) {
		case POST:
			HttpPost post = new HttpPost(url);
			post.setEntity(createEntity(fields));
			return post;
		case GET:
			try {
				HttpGet get = new HttpGet(url);
				return get;
			} catch (UnsupportedOperationException e) {
				throw new QuickRestException(e);
			}
		case PUT:
			HttpPut put = new HttpPut(url);
			put.setEntity(createEntity(fields));
			return put;
		default:
			throw new QuickRestException("Unsupported method: "+method);
		}
	}

	private HttpEntity createEntity(HashMap<String,Object> map) throws QuickRestException {
		try {
			if( this.entity != null ) {
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				entity.write(os);
				os.close();
				//String str = new String(os.toByteArray(),Charset.forName("UTF-8"));
				//org.apache.http.entity.StringEntity e = new org.apache.http.entity.StringEntity(str, Charset.forName("UTF-8"));
				org.apache.http.entity.ByteArrayEntity e = new ByteArrayEntity(os.toByteArray());
				return e;
			} else {
				return createURLEncodedEntity(map);
			}
		} catch(IOException ioe) {
			throw new QuickRestException(ioe);
		}
	}
	
	private HttpEntity createURLEncodedEntity(HashMap<String,Object> map) throws QuickRestException {
		ArrayList<BasicNameValuePair> nvp = new ArrayList<>();
		for( Entry<String,Object> e : map.entrySet() ) {
			Object o = e.getValue();
			String str;
			if( o instanceof String ) {
				str = (String) o;
			} else {
				ObjectMapper om = new ObjectMapper();
				try {
					str=om.writeValueAsString(o);
				} catch (JsonProcessingException ex) {
					throw new QuickRestException("Error when mapping object to value",ex);
				}
			}
			nvp.add(new BasicNameValuePair(e.getKey(), str));
		}
		UrlEncodedFormEntity e;
		try {
			e = new UrlEncodedFormEntity(nvp,"UTF-8");
			return e;
		} catch (UnsupportedEncodingException e1) {
			throw new QuickRestException(e1);
		}
	}

	@Override
	public RestRequestWithBody query(String key, String value) {
		this.query.put(key, value);
		return this;
	}

	@Override
	public RestRequestWithBody pathParam(String key, String value) {
		this.pathParam.put(key, value);
		return this;
	}

	/**
	 * See {@link http://stackoverflow.com/questions/959731/how-to-replace-a-set-of-tokens-in-a-java-string}
	 * @param text
	 * @param replacements
	 * @return
	 */
	private static String replaceTokens(String text, Map<String, String> replacements) {
		Pattern pattern = Pattern.compile("\\{(.+?)\\}");
		Matcher matcher = pattern.matcher(text);
		StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			String replacement = replacements.get(matcher.group(1));
			if (replacement != null) {
				// matcher.appendReplacement(buffer, replacement);
				// see comment 
				matcher.appendReplacement(buffer, "");
				buffer.append(replacement);
			}
		}
		matcher.appendTail(buffer);
		return buffer.toString();
	}

	@Override
	public <T> T asObject(Class<T> clazz) throws QuickRestException {
		if( clazz == String.class ) {
			return (T) asString();
		}
		if( clazz == JSONArray.class ) {
			return (T) new JSONArray(asString());
		}
		if( clazz == JSONObject.class ) {
			return (T) new JSONObject(asString());
		}
		throw new QuickRestException("No mapper found for class "+clazz);
	}

	public String asString() throws QuickRestException {
		return asResponse().getEntity();
	}
	
	@Override
	public <T> RestResponse<T> asResponse(Class<T> contentType) {
		// map object
		return null;
	}

	@Override
	public RestResponse<String> asResponse() throws QuickRestException {
		HttpUriRequest req = createRequest();
		
		// append entity headers, if not already set
		if( entity != null ) {
			for( Header h : entity.getHeaders() ) {
				if( !headers.containsKey(h.getName()) )
					headers.put(h.getName(), h.getValue());
			}
		}

		for(Entry<String,String> h : headers.entrySet()) {
			req.addHeader(h.getKey(), h.getValue());
		}
		
		log.info("Rest query "+method+" to "+req.getURI());
		
		if( req instanceof HttpEntityEnclosingRequestBase) {
			HttpEntityEnclosingRequestBase req2 = (HttpEntityEnclosingRequestBase)req;
			log.info("Entity: type="+req2.getEntity().getContentType()+", encoding="+req2.getEntity().getContentEncoding());
		}
		
		try {
			HttpResponse resp = client.execute(req);
			
			String strResp =  IOUtils.toString(resp.getEntity().getContent());
			log.info("Received response "+resp.getStatusLine().getStatusCode()+": "+QuickRestUtils.abbreviate(strResp,1000,"..."));
			
			RestResponse<String> response = new RestResponseImpl<String>(strResp, resp.getStatusLine().getStatusCode(), resp.getStatusLine().getReasonPhrase());
			
			if( resp.getStatusLine().getStatusCode() != expectedStatus ) {
				if( onFailure != null )
					onFailure.onResponse(response);
				
				if( exceptionOnFail ) {
					QuickRestException e = new QuickRestException("Received HTTP Status "+resp.getStatusLine().getStatusCode()+". Expected "+expectedStatus);
					log.error(e);
					throw e;
				}
			}
			
			if( onSuccess != null )
				onSuccess.onResponse(response);
			
			return response;
		} catch (IOException e) {
			throw new QuickRestException(e);
		}
	}

	@Override
	public RestRequestWithBody exceptionOnFail(boolean exceptionOnFail) {
		this.exceptionOnFail=exceptionOnFail;
		return this;
	}


	@Override
	public RestRequestWithBody entity(Entity entity) {
		this.entity=entity;
		return this;
	}

	@Override
	public RestRequestWithBody header(String header, String value, boolean condition) {
		if( condition )
			return header(header,value);
		return this;
	}
	
}
