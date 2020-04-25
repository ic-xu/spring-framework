package com;


import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.net.URL;

/**
 * 1、添加tomcat 依赖
 * 2、提供一个run(),
 *
 *
 *
 *
 */
public class SpringBoot内嵌Tomcat {

	public static int TOMCAT_PORT = 8080;


	public static void run() throws ServletException, LifecycleException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(SpringBoot内嵌Tomcat.TOMCAT_PORT);
		tomcat.addWebapp("/", "D:\\work\\tomcatWorkSpae" );
		tomcat.start();
		tomcat.getServer().await();
	}



	public static void main(String[] args) throws LifecycleException, ServletException {

		SpringBoot内嵌Tomcat.run();
	}

}
