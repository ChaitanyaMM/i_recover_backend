package com.irecover.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class Util {

	public Map<String, Object> responseObject(Object data) {

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", "Successful");
		response.put("statusCode", HttpStatus.OK);
		response.put("successful", true);
		response.put("data", data);

		return response;

	}

}
