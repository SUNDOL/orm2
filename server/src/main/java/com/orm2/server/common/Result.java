package com.orm2.server.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {

	private ErrorResponse response;
	private Object data;
	
}
