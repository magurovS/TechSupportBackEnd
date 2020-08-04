package ru.magurovs.TechSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;


public class JSONUtilsOrder {
	
	public static Order parserJSON(String requestJSON) throws JSONException, ParseException {
		
		JSONObject orderJson = new JSONObject(requestJSON);
		
		String name = orderJson.getString("name");
		String lastName = orderJson.getString("lastName");
		String middleName = orderJson.getString("middleName");
		String comment = orderJson.getString("comment");
		
		Date timeProblem = convertStringDate(orderJson.getString("timeProblem"));
		TypesProblems typeProblem = getEnumValueProblem(orderJson.getString("typeProblem"));
		
		return new Order(name, lastName, middleName, comment, timeProblem, typeProblem);

	}
	
	private static Date convertStringDate(String stringDate) throws ParseException{
		return new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(stringDate);
	}
	
	private static TypesProblems getEnumValueProblem(String stringType) {
		TypesProblems typeProblem = null;
				
		switch (stringType) {
			case "PROBLEM_1":
				typeProblem = TypesProblems.PROBLEM_1;
				break;
			case "PROBLEM_2":
				typeProblem = TypesProblems.PROBLEM_2;
				break;
			case "PROBLEM_3":
				typeProblem = TypesProblems.PROBLEM_3;
				break;
		}
		
		return typeProblem;				
	}
	
	
	public static JSONObject buildJSON(String name, String lastName, 
									   String middleName, String comment, 
									   String problemTime, TypesProblems typeProblem) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		jsonObject.put("lastName", lastName);
		jsonObject.put("middleName", middleName);
		jsonObject.put("comment", comment);
		jsonObject.put("timeProblem", problemTime);
		jsonObject.put("typeProblem", typeProblem);
		return jsonObject;
	}
	}

