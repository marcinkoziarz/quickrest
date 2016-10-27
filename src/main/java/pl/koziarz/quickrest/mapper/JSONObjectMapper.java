package pl.koziarz.quickrest.mapper;

import org.json.JSONObject;

public class JSONObjectMapper implements QuickRestMapper<JSONObject> {
	@Override
	public String write(JSONObject obj) {
		return obj.toString();
	}

	@Override
	public JSONObject read(String s) {
		return new JSONObject(s);
	}
}
