package com.valid.util;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.valid.dto.GenericResponse;
import com.valid.dto.GenericResponseObject;

public class ResponseService implements Serializable {

	/**
	 * 
	 */
	
	
	
	private static final long serialVersionUID = 5711429983251806190L;

	public static String buildResponse(int status, String message) {
		GenericResponse genericResponse = new GenericResponse(String.valueOf(status), message);
		GenericResponseObject response = new GenericResponseObject();
		response.setResponse(genericResponse);
		String jsonResponse = toJson(response);
		return jsonResponse;
	}
	
	
	

		  public static String toJson(Object obj) {
		    Gson gson = (new GsonBuilder()).setPrettyPrinting().serializeNulls().create();
		    String jsonStr = gson.toJson(obj);
		    return jsonStr;
		  }



	
		  
		  
		  

}
