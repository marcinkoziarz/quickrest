package pl.koziarz.quickrest;

import org.json.JSONObject;

/**
 * Base class for HTTP REST requests.
 * Follows builder pattern to configure request.
 */
public interface RestRequest {
	
	/**
	 * Set HTTP header and its value.
	 * Example:
	 * <pre>
	 *     RestRequest rest = new QuickRest
	 *         .header("Content-type","application/json")
	 *         .asString();
	 * </pre>     
	 * @param header
	 * @param value
	 * @return
	 */
	public RestRequest header(String header, String value);
	
	/**
	 * Set expected HTTP response code. By default its 200, if not changed by
	 * {@link QuickRest#expectResponseStatus}
	 * @param expectedStatus returnet http status code that is recognized as expected
	 * @return current rest request
	 */
	public RestRequest expectResponseStatus(int expectedStatus);
	
	/**
	 * Set callback to call when expected HTTP status code equals received
	 * HTTP status code.
	 * @param handler object containing method to be called
	 * @return current rest request
	 */
	public RestRequest onSuccess(RestResponseHandler handler);

	/**
	 * Set callback to call when expected HTTP status code is not equal to received
	 * HTTP status code.
	 * @param handler object containing method to be called
	 * @return current rest request
	 */
	public RestRequest onFail(RestResponseHandler handler);
	
	/**
	 * Execute request and return its body as String
	 * @return String containing response body
	 * @throws QuickRestException
	 */
	public String asString() throws QuickRestException;
	
	/**
	 * Set element in query string 
	 * Adds key-value pair to the end of query string taking care of
	 * possible existing query string in given URL.
	 * 
	 * Example:
	 * <pre>
	 * // will call to http://myapp.com/api/?a=b
	 * rest.get("http://myapp.com/api/").query("a","b").asString();
	 * 
	 * // will call to http://myapp.com/api?a=b&c=d 
	 * rest.get("http://myapp.com/api/?a=b").query("c","d").asString();
	 * </pre>
	 * 
	 * @param key key of query string element
	 * @param value value of query string element
	 * @return current request
	 */
	public RestRequest query(String key, String value);
	
	/**
	 * Sets value for path parameter.
	 * If URL contains tokens enclosed in curly brackets, they are being replaced
	 * with values supplied by pathParam:
	 * <pre>
	 * // will call http://myapp.com/api/category/5?sort=asc
	 * rest
	 *     .get("http://myapp.com/api/{res}/5?sort=asc")
	 *     .pathParam("res","category")
	 *     .asString();
	 * </pre>
	 * Path parameters may be used in whole URL, also in query string.
	 * @param key token in path to be replaced
	 * @param value value to which token should be replaced
	 * @return current request
	 */
	public RestRequest pathParam(String key, String value);
	
	/**
	 * Set whether throw exception on mismatched HTTP status code in response
	 * @param exceptionOnFail true if executing request should throw exception on mismatched HTTP status code in response, false otherwise
	 * @return current request
	 */
	public RestRequest exceptionOnFail(boolean exceptionOnFail);
	
	/**
	 * Execute request and return response with body mapped to given type.
	 * Currently only String is supported.
	 * @param contentType
	 * @return
	 * @throws QuickRestException
	 */
	public <T> RestResponse<T> asResponse(Class<T> contentType) throws QuickRestException;
	
	
	/**
	 * Execute request and return response with body mapped to String.
	 * @return response
	 * @throws QuickRestException 
	 */
	public RestResponse<String> asResponse() throws QuickRestException;
	
	/**
	 * 
	 * @param class1
	 * @return
	 * @throws QuickRestException
	 */
	public <T> T asObject(Class<T> class1) throws QuickRestException;
	
	/**
	 * 
	 * @return
	 * @throws QuickRestException
	 */
	public JSONObject asJson() throws QuickRestException;
}
