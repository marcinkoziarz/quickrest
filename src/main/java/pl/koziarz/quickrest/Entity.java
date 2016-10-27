package pl.koziarz.quickrest;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface Entity {

	public void write(OutputStream os) throws IOException;
	public List<Header> getHeaders();
	
}
