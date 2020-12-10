package com.example.cmmn;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Common {
	
    public static Gson gson() {
    	Gson gson;
    	GsonBuilder builder = new GsonBuilder(); 
    	builder.serializeNulls(); 
    	builder.setPrettyPrinting(); 
    	gson = builder.create();
    	return gson;
    }
    
    public static Map<String, Object> jsonParser(String str){
    	Map map = gson().fromJson(str, Map.class);
    	return map;
    }
    
    public static Map[] jsonParserArr(String str){
    	Map map[] = gson().fromJson(str, Map[].class);
    	return map;
    }
}
