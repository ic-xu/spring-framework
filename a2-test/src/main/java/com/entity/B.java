package com.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class B {

	@Autowired
	A a;

	public B(){
		System.out.println("B初始化了");
	}


	public void init(String sout){
		System.err.println(sout);
	}
}
