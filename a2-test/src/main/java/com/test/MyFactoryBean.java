package com.test;

import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean {

	private Class mapperClass;


	public void setMapperClass(Class mapperClass) {
		this.mapperClass = mapperClass;
	}

	@Override
	public Object getObject() throws Exception {
		Object o = ProxSessionFactory.queryList(mapperClass);
		return o;
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
