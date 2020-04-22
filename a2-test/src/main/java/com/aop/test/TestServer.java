package com.aop.test;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TestServer {
	public TestServer(){

		System.out.println("fff");
	}

	@Transactional
	public String list(){
		System.out.println("执行业务逻辑方法-----------");
		return "执行业务逻辑方法-----------";
	}
}
