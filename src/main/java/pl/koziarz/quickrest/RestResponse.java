package pl.koziarz.quickrest;

public interface RestResponse<T> {
	public int getStatusCode();
	public String getStatusText();
	public T getEntity();
}
