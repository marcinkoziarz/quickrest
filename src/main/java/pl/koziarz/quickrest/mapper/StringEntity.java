package pl.koziarz.quickrest.mapper;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;

import pl.koziarz.quickrest.Entity;
import pl.koziarz.quickrest.Header;

public class StringEntity implements Entity {

	private String body;
	private ArrayList<Header> headers = new ArrayList<>();
	
	public StringEntity(String body, String contentType) {
		this.body=body;
		this.headers.add(new Header("Content-Type",contentType));
	}

	@Override
	public void write(OutputStream os) throws IOException {
		byte[] bytes = body.getBytes(Charset.forName("UTF-8"));
		IOUtils.write(bytes, os);
		//os.close();
	}

	@Override
	public List<Header> getHeaders() {
		return Collections.unmodifiableList(headers);
	}
	
}
