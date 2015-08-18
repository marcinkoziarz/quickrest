package pl.koziarz.quickrest;

/**
 * Adapter class for creating {@link QuickRestRequestFactory}.
 * By default, methods return empty requests that throw exception when tried to be executed.
 */
public class QuickRestRequestFactoryAdapter implements QuickRestRequestFactory {

	@Override
	public RestRequest get(String url) {
		return new EmptyRestRequestWithBody(HttpMethod.GET);
	}

	@Override
	public RestRequestWithBody post(String url) {
		return new EmptyRestRequestWithBody(HttpMethod.POST);
	}

	@Override
	public RestRequestWithBody put(String url) {
		return new EmptyRestRequestWithBody(HttpMethod.PUT);
	}

	@Override
	public RestRequestWithBody patch(String url) {
		return new EmptyRestRequestWithBody(HttpMethod.PATCH);
	}

	@Override
	public RestRequestWithBody delete(String url) {
		return new EmptyRestRequestWithBody(HttpMethod.DELETE);
	}

}
