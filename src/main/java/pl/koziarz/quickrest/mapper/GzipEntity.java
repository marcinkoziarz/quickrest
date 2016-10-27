package pl.koziarz.quickrest.mapper;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

import pl.koziarz.quickrest.Entity;
import pl.koziarz.quickrest.Header;

public class GzipEntity implements Entity {

	private Entity e;
	private ArrayList<Header> headers = new ArrayList<>();
	
	public GzipEntity(Entity e) {
		this.e=e;
		
		headers.add(new Header("Content-encoding", "gzip"));
	}
	
	@Override
	public void write(OutputStream os) throws IOException {
		GZIPOutputStream zos = new GZIPOutputStream(os);
		e.write(zos);
		zos.finish();
	}

	@Override
	public List<Header> getHeaders() {
		ArrayList<Header> list = new ArrayList<>();
		list.addAll(headers);
		list.addAll(e.getHeaders());
		return list;
	}

}
