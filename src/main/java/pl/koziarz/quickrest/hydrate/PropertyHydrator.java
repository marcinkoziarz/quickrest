package pl.koziarz.quickrest.hydrate;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class PropertyHydrator implements Hydrator {

	@Override
	public void hydrate(Object source, Object dest) {
		
		Map<String,Field> sourceFields = getFieldMap(source);
		//Map<String,Field> destFields = getFieldMap(dest);
		
		for( Field f : dest.getClass().getFields() ) {
			if( sourceFields.containsKey(f.getName()) ) {
				
			}
		}
		
	}
	
	private Map<String,Field> getFieldMap(Object o) {
		HashMap<String,Field> fields = new HashMap<>();
		for(Field f : o.getClass().getFields()) {
			fields.put(f.getName(), f);
		}
		return fields;
	}

}
