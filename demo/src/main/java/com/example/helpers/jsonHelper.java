package com.example.helpers;

import java.util.ArrayList;

public class jsonHelper {
	public static String toJsonArray(String ArrayLabel, String content) {
		//Removes last comma
		String lastCharacter = content.substring(content.length() - 1);
		if (lastCharacter.equals(",")) {
			content = content.substring(0, content.lastIndexOf(","));
		}
		
		String pattern = "\"%s\": [ %s ]";
		String returnString = String.format(
				pattern, 
				ArrayLabel, 
				content);

		return returnString;
	}

	
	public static String toJsonObject(ArrayList<keyValuePair> content) {
		String jsonString = "";
		String itemPattern = "\"%s\" : \"%s\", ";
		for (keyValuePair item : content) {
			jsonString += String.format(itemPattern, item.key, item.value);
		}
		jsonString = jsonString.substring(0, jsonString.lastIndexOf(","));

		String objectPattern = "{ %s }";
		return String.format(objectPattern, jsonString);		
	}

	public static String toJsonObjectFromStrings(ArrayList<String> content) {
		String jsonString = "";
		for (String item : content) {
			jsonString += item +",";
		}
		jsonString = jsonString.substring(0, jsonString.lastIndexOf(","));

		String objectPattern = "{ %s }";
		return String.format(objectPattern, jsonString);		
	}

	public static String toJsonDocument(String content) {
		return "{ " + content + " }";
	}
}
