package com.mvc.web;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Main {

	static void run() throws LifecycleException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		tomcat.addWebapp("/app", "D:\\work\\tomcatWorkSpae" );
		tomcat.start();
		tomcat.getServer().await();
	}

	public static void main(String[] args) throws LifecycleException {
		Main.run();
	}
}
