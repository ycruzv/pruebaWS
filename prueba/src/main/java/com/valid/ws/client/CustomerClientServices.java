package com.valid.ws.client;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.valid.dto.CommonConstants;
import com.valid.dto.Customer;
import com.valid.dto.GenericResponse;

@Component
public class CustomerClientServices {

	@Autowired
	private Environment env;
	
	
	public void createCustomerRestClien(Customer customer) {

		System.err.println("Created");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(env.getProperty(CommonConstants.URL_REST_CREATE))
				.queryParam("name", customer.getName()).queryParam("lastName", customer.getLastName())
				.queryParam("isProcess", customer.getIsProcess());

		HttpEntity<?> entity = new HttpEntity<>(headers);

		HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				String.class);
	}

	public List<Customer> getCustomerRestClient() throws JsonMappingException, JsonProcessingException {

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(env.getProperty(CommonConstants.URL_REST_GET_ALL));

		HttpEntity<?> entity = new HttpEntity<>(headers);
		System.err.println(builder.toUriString());
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				String.class);

		ObjectMapper mapper = new ObjectMapper();

		return mapper.readValue(response.getBody(), new TypeReference<List<Customer>>() {
		});

	}

	public void updateCustomer(@RequestParam String[] requestList) {

		List<String> listId = Arrays.asList(requestList);
		List<Long> listRequest = listId.stream().mapToLong(x -> Long.parseLong(x)).boxed().collect(Collectors.toList());

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl(env.getProperty(CommonConstants.URL_REST_UPDATE))
				.queryParam("requestList", listRequest);

		HttpEntity<?> entity = new HttpEntity<>(headers);
		System.err.println(builder.toUriString());
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<GenericResponse> response = restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, entity,
				GenericResponse.class);
	}

}
