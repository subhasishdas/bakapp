package com.subhasish.spring.ws.api.autoresponse;

import java.math.BigInteger;
import java.util.HashMap;

public class AutoResponse {
HashMap<String,String> response = new HashMap<String,String>();
	public AutoResponse() {
		response.put("174105211920898","We have got the best hotels");
		response.put("214560071207756","You can check in from 15:00.If you arrive earlier, you can store your baggage in the hotel's baggage area until your room is ready."
				+ "						If the room has been cleaned and is ready before 15:00, you may have access to the room earlier.Please be aware of our"
				+ "						 additional charge for early check-in."
				+ "						It’s free and subject to availability if you booked your stay directly on our website scandichotels.com.");
	}
	public String getResponse(String s) {
		String error="Sorry, couldn't find any answer";
		return response.get(s)==null?error:response.get(s);
	}
}
