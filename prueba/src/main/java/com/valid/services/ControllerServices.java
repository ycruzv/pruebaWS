package com.valid.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.valid.dto.CommonConstants;
import com.valid.dto.Customer;
import com.valid.repository.CustomerRepository;
import com.valid.util.ResponseService;

@RestController
@RequestMapping(value = "customer")
public class ControllerServices {

	@Autowired
	CustomerRepository customerDAO;
	
	@Autowired
	private Environment env;

	@PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createCustomer(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "lastName", required = true) String lastName, @RequestParam(value = "isProcess", required = true)boolean isProcess) {

		Customer customer = customerDAO.save(new Customer(name, lastName, isProcess));
		return new ResponseEntity<>(
				ResponseService.buildResponse(HttpStatus.OK.value(), env.getProperty(CommonConstants.DATA_CREATED) + customer.getId()),
				HttpStatus.OK);

	}

	@PostMapping(path = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAllCustomer() throws Exception{

		try {
			List<Customer> listCustomer = (List<Customer>) customerDAO.findAll();

			if (listCustomer.isEmpty()) {
				return new ResponseEntity<>(ResponseService.buildResponse(HttpStatus.ACCEPTED.value(), env.getProperty(CommonConstants.DATA_NOT_FOUNT)),
						HttpStatus.NOT_FOUND);
			}
			String json = new Gson().toJson(listCustomer);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(ResponseService.buildResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@PutMapping(path = "/updateProcess", produces = MediaType.APPLICATION_JSON_VALUE)
	  @ExceptionHandler(Exception.class)
	public ResponseEntity<String> updateIsProcessCustomers(@RequestParam List<Long> requestList)throws Exception, MethodArgumentNotValidException, HttpMessageNotReadableException {
		try {
			
		for (Long id : requestList) {
			
		Optional<Customer> customer = 	customerDAO.findById(id);
			if(customer.isPresent()) {
				customer.get().setProcess(true);
				customerDAO.save(customer.get());
				
			}else {
				return new ResponseEntity<>(ResponseService
											.buildResponse(HttpStatus.NOT_FOUND.value(), env.getProperty(CommonConstants.DATA_NOT_FOUNT)),HttpStatus.NOT_FOUND);
				}
		}
		
		return new ResponseEntity<>(ResponseService.buildResponse(HttpStatus.CREATED.value(), env.getProperty(CommonConstants.DATA_UPDATED)),
				HttpStatus.CREATED);
		} catch (Exception  e ) {
			return new ResponseEntity<>(ResponseService.buildResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}
