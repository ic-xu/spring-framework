package com.spring2mvc.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Main {

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
		Main.run();
	}
}
