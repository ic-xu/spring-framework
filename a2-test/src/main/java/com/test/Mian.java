package com.test;

import com.config.Config;
import com.mapper.UserMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Mian {


	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext
				= new AnnotationConfigApplicationContext(Config.class);

		UserMapper userMapper = annotationConfigApplicationContext.getBean(UserMapper.class);

//		userMapper = (UserMapper) ProxSessionFactory.queryList(UserMapper.class);
		userMapper.getUser().stream().forEach(System.out::println);

	}
}
