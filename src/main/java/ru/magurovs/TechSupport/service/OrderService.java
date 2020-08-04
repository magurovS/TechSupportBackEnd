package ru.magurovs.TechSupport.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

public interface OrderService {
	public ArrayList<Map<String, String>> getAllOrders();
	void create(Map<String, String> orderJson) throws ParseException;
	
}
