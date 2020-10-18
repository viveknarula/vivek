package com.Adidas.generalutils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;

public class JsonParsing {

	static HashMap<?, ?> jsonData = convertFileToJSON(
			System.getProperty("user.dir") + "\\Properties\\" + "config.json");

	/**
	 * Returns the application url set in config.json.
	 */
	public static String getApplicationURL() {
		return jsonData.get("applicationURL").toString();
	}

	public static HashMap<?, ?> convertFileToJSON(String fileName) {
		System.out.println(fileName);
		Gson gson = new Gson();
		HashMap<?, ?> hm = null;
		try {
			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(new FileReader(fileName));
			hm = gson.fromJson(jsonElement, HashMap.class);
		} catch (FileNotFoundException e) {
		}
		return hm;
	}

	public static LinkedTreeMap<?, ?> getWebdriverCapabilities(String runModeJenkins, String idJenkins) {
		LinkedTreeMap<?, ?> hashMap = null;
		String runMode = ((runModeJenkins == null) || (runModeJenkins == "")) ? jsonData.get("runMode").toString()
				: runModeJenkins;
		String identifier = ((idJenkins == null) || idJenkins == " ") ? jsonData.get("id").toString() : idJenkins;
		@SuppressWarnings("unchecked")
		List<LinkedTreeMap<String, String>> l = (List<LinkedTreeMap<String, String>>) ((LinkedTreeMap<?, ?>) jsonData
				.get(runMode)).get("webdriverCap");
		ArrayList<LinkedTreeMap<String, String>> data = (ArrayList<LinkedTreeMap<String, String>>) l;
		for (int i = 0; i < data.size(); i++) {
			hashMap = data.get(i);
			if (identifier.equalsIgnoreCase(hashMap.get("id").toString())) {
				return hashMap;
			}
		}
		return hashMap;
	}

	/**
	 * The method will return chrome options passed from config.json
	 */
	public static String getChromeOptions() {
		String runmode = jsonData.get("runMode").toString();
		return ((LinkedTreeMap<?, ?>) jsonData.get(runmode)).get("chromeOptions").toString();
	}

	public String getServerUrl(String runModeJenkins) {
		String runmode;
		if (runModeJenkins == null || runModeJenkins == "")
			runmode = jsonData.get("runMode").toString();
		else
			runmode = runModeJenkins;
		return ((LinkedTreeMap<?, ?>) jsonData.get(runmode)).get("serverURL").toString();
	}

}
