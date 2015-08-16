package pl.koziarz.quickrest;

public class QuickRestException extends Exception {

	private static final long serialVersionUID = -2063768808205473157L;

	public QuickRestException() {
		super();
	}

	public QuickRestException(String message) {
		super(message);
	}

	public QuickRestException(Throwable cause) {
		super(cause);
	}

	public QuickRestException(String message, Throwable cause) {
		super(message, cause);
	}

	public QuickRestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
