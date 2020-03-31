package com.aop.test;


import org.springframework.stereotype.Component;

@Component
public class TestServer {

	public String list(){
		System.out.println("执行业务逻辑方法-----------");
		return "执行业务逻辑方法-----------";
	}
}
