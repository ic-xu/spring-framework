package com.test;

import com.config.Config;
import com.entity.B;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Mian {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext
				= new AnnotationConfigApplicationContext(Config.class);

		B b = annotationConfigApplicationContext.getBean(B.class);
		b.init("HHHHHHHHHHHH");

	}
}
