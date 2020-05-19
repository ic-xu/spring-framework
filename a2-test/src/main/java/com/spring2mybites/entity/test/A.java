package com.mybites.spring.entity.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {

	@Autowired
	com.mybites.spring.entity.test.B b;

	public A(){
		System.out.println("A初始化了");
	}
}
