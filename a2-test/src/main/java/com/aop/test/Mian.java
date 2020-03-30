package com.aop.test;

import com.aop.config.AopConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Mian {


	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext
				= new AnnotationConfigApplicationContext(AopConfig.class);

		TestServer testServer = annotationConfigApplicationContext.getBean(TestServer.class);
		testServer.list();

	}
}
