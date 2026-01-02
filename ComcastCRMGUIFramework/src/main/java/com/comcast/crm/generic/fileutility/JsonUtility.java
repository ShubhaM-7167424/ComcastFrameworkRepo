package com.comcast.crm.generic.fileutility;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
	
	public String getDataFromJsonFile(String key) throws Throwable, ParseException {
		FileReader fr = new FileReader("./configAppData/appCommonData.json");
		
		JSONParser parser = new JSONParser();
		Object object = parser.parse(fr);
		
		JSONObject map = (JSONObject)object;
		
		String data = (String)map.get(key);
		
		return data;
	}
}
