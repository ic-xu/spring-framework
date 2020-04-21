package com.ext;

import com.ext.config.ExtConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Mian {


	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext
				= new AnnotationConfigApplicationContext(ExtConfig.class);


	}
}
