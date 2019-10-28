package com.dyz.util;

import org.springframework.stereotype.Component;

 
import lombok.Data;

@Component
@Data
public class Msg {
	private Boolean success;
	private String message;
	private String token;

	 
	
}
