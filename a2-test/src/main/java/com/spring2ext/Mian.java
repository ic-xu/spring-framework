package com.spring2ext;

import com.spring2ext.config.ExtConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Mian {


	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext
				= new AnnotationConfigApplicationContext(ExtConfig.class);


	}
}
