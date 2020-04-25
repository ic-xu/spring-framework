package com;


import com.spring2mvc.tomcat.Main;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 * 1、添加tomcat 依赖,这里我只测试过8.5.x的，9.x的没有成功过，可能原因是8.5.x功能比较全
 * 2、提供一个run(),然后在run() 中启动tomcat,基本配置如下
 *
 */
public class SpringBoot内嵌Tomcat {
	static void run() throws LifecycleException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		tomcat.addWebapp("/app", Main.class.getResource("/")
				.getFile()
				.replace("out/production/classes/","") );
		tomcat.start();
		tomcat.getServer().await();
	}

	public static void main(String[] args) throws LifecycleException {
		run();
	}
}
