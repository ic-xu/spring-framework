package com.mvc.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class T {


	@GetMapping("/test")
	public String fff(){
		System.out.println("fffffffffffff");
		return "test";
	}

}
