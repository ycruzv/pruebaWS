package com.valid.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.valid.dto.Customer;
import com.valid.ws.client.CustomerClientServices;

@Controller
public class ControllerHome {


	
	@Autowired
	private CustomerClientServices customerServices;
	

	@RequestMapping("/")
	public String home() {
		System.err.println("mapin/");
		return "index";
	}

	@RequestMapping("/index")
	public String createCustomer(@ModelAttribute("customer")Customer customer) {
		System.err.println("create mappin/");

		customerServices.createCustomerRestClien(customer);
		return "index";

	}

	@RequestMapping("/listcustomer")
	public String getCustomers(Map<String, Object> model) throws JsonMappingException, JsonProcessingException {
	
				
		  model.put("list", customerServices.getCustomerRestClient());
		 
	
	 return "listcustomer";
	

	}

	@RequestMapping(value = "/updateCustomer")

	public String updateCustomer(@RequestParam String[] requestList, Map<String, Object> model) throws JsonMappingException, JsonProcessingException {

		
		customerServices.updateCustomer(requestList);
		 model.put("list", customerServices.getCustomerRestClient());
		
		 	
	 return "listcustomer";

	}
}
