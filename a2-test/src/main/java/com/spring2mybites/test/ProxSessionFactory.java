package com.mybites.spring.test;

import java.lang.reflect.Proxy;

public class ProxSessionFactory {


	public static Object createMapper(Class clazz) {

		/**
		 *
		 * @param   loader the class loader to define the proxy class
		 * @param   interfaces the list of interfaces for the proxy class to implement
		 * @param   h the invocation handler to dispatch method invocations to
		 *
		 *    这里的 o 就是动态代理产生的对象，也就是继承了clazz 的对象，那么怎么把这个对象交给
		 *            Spring 管理呢？
		 *            第一种办法：@bean
		 *            第二种办法：api registSingleton
		 *            第三种办法：factoryBean{
		 *      beanFactory 和FactoryBean有什么区别？
		 * 	  	beanFactory 是Spring中的一个工厂。可以产生bean,也可以获取bean
		 * 	  	FactoryBean是一个特殊的bean ,它需要实现FactoryBean 接口,表示当前类在Spring中，
		 * 	  	同时它里面的bean也在容器中（如果要获取FactoryBean 本身，需要在bean名字前面加上“&”符号，否则拿到的就是它getObject()返回的对象）
		 *
		 *            }
		 *            第四种办法：factoryMathom
		 *
		 */

		Class[] cla = new Class[]{clazz};
		return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), cla, new MySessionInVocationHandle());
	}
}
