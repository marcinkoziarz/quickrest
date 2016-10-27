package pl.koziarz.quickrest.mapper;

import org.json.JSONArray;

public class JSONArrayMapper implements QuickRestMapper<JSONArray> {

	@Override
	public String write(JSONArray obj) {
		return obj.toString();
	}

	@Override
	public JSONArray read(String s) {
		return new JSONArray(s);
	}

}
