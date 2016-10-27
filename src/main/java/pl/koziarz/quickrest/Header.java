package pl.koziarz.quickrest;

public class Header {
	
	private String name;
	private String value;
	
	public Header(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
}
