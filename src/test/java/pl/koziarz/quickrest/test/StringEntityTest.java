package pl.koziarz.quickrest.test;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.koziarz.quickrest.QuickRest;
import pl.koziarz.quickrest.QuickRestException;
import pl.koziarz.quickrest.mapper.StringEntity;

public class StringEntityTest {

	@Test
	public void test() throws QuickRestException, JsonProcessingException {
		
		
		HashMap<String,Object> payload = new HashMap<>();
		payload.put("date_sale", "2016-06-22");
		payload.put("products", generateProducts());
		
		ObjectMapper mapper = new ObjectMapper();
		
		QuickRest rest = new QuickRest();
		
		StringEntity entity = new StringEntity(mapper.writeValueAsString(payload), "application/json");
		
		String s = rest.post("http://localhost/dump_post.php").header("Content-type", "application/json").entity(entity).asString();
		
		System.out.println(s);
	}
	
	private ArrayList<HashMap<String,String>> generateProducts() {
		ArrayList<HashMap<String,String>> products = new ArrayList<>();
		for( int i=0; i<10; ++i ) {
			HashMap<String,String> p = new HashMap<>();
			p.put("id", i+"");
			p.put("name", "product_"+i);
			products.add(p);
		}
		return products;
	}
	
}
