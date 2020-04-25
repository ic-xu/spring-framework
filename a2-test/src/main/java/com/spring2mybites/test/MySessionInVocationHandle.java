package com.mybites.spring.test;

import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MySessionInVocationHandle implements InvocationHandler {
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		List<String> objects = new ArrayList<>();
		Select annotation = method.getAnnotation(Select.class);
		String sql = annotation.value()[0];
		System.out.println(sql);
//		Object invoke = method.invoke(proxy, method,args);
		return objects.add(sql);
	}
}
