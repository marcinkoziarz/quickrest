package pl.koziarz.quickrest;

public interface QuickRestRequestFactory {
	public RestRequest get(String url);
	public RestRequestWithBody post(String url);
	public RestRequestWithBody put(String url);
	public RestRequestWithBody patch(String url);
	public RestRequestWithBody delete(String url);
}
