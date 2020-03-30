package com.mybites.spring.test;

import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean {

	private Class mapperClass;


	public void setMapperClass(Class mapperClass) {
		this.mapperClass = mapperClass;
	}

	@Override
	public Object getObject() throws Exception {
		return ProxSessionFactory.createMapper(mapperClass);
	}

	@Override
	public Class<?> getObjectType() {
		return mapperClass;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
