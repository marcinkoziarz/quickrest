package pl.koziarz.quickrest.mapper;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.koziarz.quickrest.Entity;

public class FormUrlEncodedEntitySerializer {

	private ObjectMapper jackson = new ObjectMapper();
	
	public Entity serialize(Object o) throws EntitySerializerException {
		StringBuilder sb = new StringBuilder();
		writeObject(sb, o, null);
		String str = sb.toString();
		return new StringEntity(str, "application/x-www-form-urlencoded");
	}
	
	public <T> T deserialize(String s,Class<T> clazz) throws EntitySerializerException {
		throw new EntitySerializerException("Cannot map x-www-form-urlencoded to "+clazz);
	}
	
	private void writeObject(StringBuilder sb, Object o, String prefix) throws EntitySerializerException {
		if( o == null ) {
			if( prefix == null )
				throw new EntitySerializerException("Cannot write null as root of hierarchy. Enclose it with a map!");
			sb.append(prefix).append("=&");
		} else if( o instanceof String ) {
			if( prefix == null )
				throw new EntitySerializerException("Cannot write String as root of hierarchy. Enclose it with a map!");
			sb.append(prefix).append("=").append((String)o).append("&");
		} else if( o instanceof Map<?,?> ) {
			writeMap(sb, (Map<String, Object>)o, prefix);
		} else if( o instanceof List ) {
			if( prefix == null )
				throw new EntitySerializerException("Cannot write List as root of hierarchy. Enclose it with a map!");
			writeList(sb, (List<Object>)o, prefix);
		} else {
			// actually, we need some map to be parent of this object,
			// so prefix is kind of required
			if( prefix == null )
				throw new EntitySerializerException("Cannot write arbitrary object as root of hierarchy. Enclose it with a map!");
			// do some magic!
			Map<String,Object> map = jackson.convertValue(o, Map.class);
			writeMap(sb, map, prefix);
		}
	}
	
	private void writeList(StringBuilder sb, List<Object> o, String prefix) throws EntitySerializerException {
		int i=0;
		for(Object e : o) {
			String newprefix;
			newprefix=prefix+"[]";
			writeObject(sb, e, newprefix);
		}
		
	}

	private void writeMap(StringBuilder sb, Map<String,Object> m, String prefix) throws EntitySerializerException {
		System.out.println("Writing map with prefix="+prefix);
		for(Entry<String,Object> e : m.entrySet()) {
			String newprefix;
			if( prefix == null )
				newprefix=e.getKey();
			else {
				newprefix=prefix+"["+e.getKey()+"]";
				System.out.println("New prefix="+newprefix);
			}
			writeObject(sb, e.getValue(), newprefix);
		}
	}
	
}
