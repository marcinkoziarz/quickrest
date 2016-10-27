package pl.koziarz.quickrest.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.Test;

import pl.koziarz.quickrest.mapper.FormUrlEncodedEntitySerializer;
import pl.koziarz.quickrest.mapper.EntitySerializerException;

public class FormUrlEncodedEntitySerializerTest {
	
	
	
	@Test
	public void testWrite() throws EntitySerializerException {
		HashMap<String,Object> root = new LinkedHashMap<>();
		root.put("a", "A");
		root.put("b", "B");
		root.put("c", "C");
		
		FormUrlEncodedEntitySerializer mapper = new FormUrlEncodedEntitySerializer();
		//String ret = mapper.serialize(root);
		//assertEquals("a=A&b=B&c=C&", ret);
		
		
		/*
		HashMap<String,Object> d = new HashMap<>();
		root.put("d", d);
		d.put("e", "E");
		d.put("f", "F");
		HashMap<String,Object> g = new HashMap<>();
		g.put("h", "H");
		g.put("i", "I");
		d.put("g", g);
		root.put("d[g][j]", "J");
		ArrayList<String> list = new ArrayList<>();
		list.add("K");
		list.add("L");
		list.add("M");
		root.put("m", list);
		*/
	}
	
	@Test
	public void testArray() throws EntitySerializerException {
		HashMap<String,Object> root = new LinkedHashMap<>();
		root.put("a", "A");
		root.put("b", "B");
		root.put("c", "C");
		ArrayList<Object> list = new ArrayList<>();
		list.add("d");
		list.add("e");
		list.add("f");
		root.put("d", list);
		
		FormUrlEncodedEntitySerializer mapper = new FormUrlEncodedEntitySerializer();
		//String ret = mapper.serialize(root);
		//assertEquals("a=A&b=B&c=C&d[]=d&d[]=e&d[]=f&", ret);
	}
	
	@Test
	public void testObject() throws EntitySerializerException {
		HashMap<String,Object> root = new LinkedHashMap<>();
		root.put("a", "A");
		root.put("b", "B");
		root.put("c", "C");
		TestObject t = new TestObject();
		t.t=new TestObject();
		root.put("obj", t);
		FormUrlEncodedEntitySerializer mapper = new FormUrlEncodedEntitySerializer();
		//String ret = mapper.serialize(root);
		//assertEquals("a=A&b=B&c=C&obj[x]=X&obj[y]=43&obj[t][x]=X&obj[t][y]=43&obj[t][t]=&", ret);
		
	}

	private class TestObject {
		String x = "X";
		int y=43;
		Object t;
		
		Object getObject() {
			return t;
		}
	}
	
}
