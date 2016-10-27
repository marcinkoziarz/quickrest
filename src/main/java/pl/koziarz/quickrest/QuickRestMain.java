package pl.koziarz.quickrest;

import java.util.ArrayList;
import java.util.HashMap;

import pl.koziarz.quickrest.mapper.FormUrlEncodedEntitySerializer;
import pl.koziarz.quickrest.mapper.EntitySerializerException;

public class QuickRestMain {

	public static void main(String[] args) {
		HashMap<String,Object> root = new HashMap<>();
		root.put("a", "A");
		root.put("b", "B");
		root.put("c", "C");
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
		
		System.out.println(root);
		
		FormUrlEncodedEntitySerializer mapper = new FormUrlEncodedEntitySerializer();
		try {
			System.out.println(mapper.serialize(root));
		} catch (EntitySerializerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
