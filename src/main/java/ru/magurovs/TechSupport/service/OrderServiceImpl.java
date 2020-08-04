package ru.magurovs.TechSupport.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.ibm.icu.text.Transliterator;

import ru.magurovs.TechSupport.Order;
import ru.magurovs.TechSupport.TypesProblems;

@Service
public class OrderServiceImpl implements OrderService {
	
		// Хранилище заявок
	   private static final Map<Integer, Order> ORDER_REPOSITORY_MAP = new HashMap<>();
	   // Переменная для генерации ID клиента
	   private static final AtomicInteger ORDER_ID_HOLDER = new AtomicInteger();

	   
	   @Override
	   public void create(Map<String, String> orderJson) throws ParseException {
	       final int orderId = ORDER_ID_HOLDER.incrementAndGet();
	       String name = orderJson.get("name");
	       String lastName = orderJson.get("lastName");
	       String middleName = orderJson.get("middleName");
	       String comment = orderJson.get("comment");
	       Date timeProblem = convertStringDate(orderJson.get("timeProblem"));
	       TypesProblems typeProblem = getEnumValueProblem(orderJson.get("typeProblem"));
	       Order order = new Order(name, lastName, middleName, comment, timeProblem, typeProblem);
	       order.setId(orderId);
	       order.setNumber(getNumber(timeProblem, name, lastName, middleName));
	       ORDER_REPOSITORY_MAP.put(orderId, order);
	   }
	   
		@Override
		public ArrayList<Map<String, String>> getAllOrders(){
			ArrayList<Order> orders= new ArrayList<>(ORDER_REPOSITORY_MAP.values());
			ArrayList<Map<String, String>> listOrdersForJson = new ArrayList<>();
			
			
			for (Order arr: orders) {
				Map<String, String> mapOrdersForJson = new HashMap<>();
				mapOrdersForJson.put("Number", arr.getNumber());
				mapOrdersForJson.put("typeProblem", arr.getTypeProblem().toString());
				mapOrdersForJson.put("timeProblem", arr.getTimeProblem().toString());
				listOrdersForJson.add(mapOrdersForJson);
			}
			
			return listOrdersForJson;
		}

		private Date convertStringDate(String stringDate) throws ParseException{
			return new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(stringDate);
		}
		
		private TypesProblems getEnumValueProblem(String stringType) {
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
		
		private String getNumber(Date timeProblem, String ... values) {
			String letterPartId = "";
			for (String val:values) {
				try{
					letterPartId += getLatin(val).toUpperCase().charAt(0);
				}catch (NullPointerException e) {}
				 catch (StringIndexOutOfBoundsException s) {};
			}
			String numPartId = formatDate(timeProblem);
			return letterPartId + "-" + numPartId;
		}
		
		private String getLatin(String str) {
			Transliterator toLatinTrans = Transliterator.getInstance("Russian-Latin/BGN");
			String result = toLatinTrans.transliterate(str);
			return result;
		}
		
		private String formatDate(Date timeProblem) {
			return new SimpleDateFormat("YYYYMMddHHmm").format(timeProblem);
		}


	
}
