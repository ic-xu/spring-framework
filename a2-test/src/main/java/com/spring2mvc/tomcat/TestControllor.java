package com.spring2mvc.tomcat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControllor {


	@GetMapping("/test")
	public String fff(){
		System.out.println("fffffffffffff");
		return "test";
	}

}
