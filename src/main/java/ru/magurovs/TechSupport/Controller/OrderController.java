package ru.magurovs.TechSupport.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.magurovs.TechSupport.Order;
import ru.magurovs.TechSupport.service.OrderService;

@RestController
public class OrderController {

	   private final OrderService orderService;

	   @Autowired
	   public OrderController(OrderService orderService) {
	       this.orderService = orderService;
	   }
	   
	   @GetMapping(value = "/orders")
	   public ResponseEntity<List<Order>> read() {
	       final ArrayList<Map<String, String>> orders = orderService.getAllOrders();
	       return (ResponseEntity<List<Order>>) (orders != null &&  !orders.isEmpty()
	               ? new ResponseEntity<>(orders, HttpStatus.OK)
	               : new ResponseEntity<>(HttpStatus.NOT_FOUND));
	   }
	   
	   @PostMapping(value = "/orders")
	   public ResponseEntity<?> create(@RequestBody Map<String, String> orderJson) throws ParseException {
	      orderService.create(orderJson);
	      return new ResponseEntity<>(HttpStatus.CREATED);
	   }
	   
	
}
