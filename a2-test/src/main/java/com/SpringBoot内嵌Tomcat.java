package com;


import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

/**
 * 1、添加tomcat 依赖
 * 2、提供一个run(),
 *
 *
 *
 *
 */
public class SpringBoot内嵌Tomcat {



public static void 	run() throws LifecycleException {
	Tomcat tomcat = new Tomcat();
	tomcat.setPort(9090);

	// 设置contextPath和路径
	String docBase = new File("a2-test/webapp").getAbsolutePath();

	StandardContext ctx = (StandardContext) tomcat.addWebapp("/",docBase);
	ctx.setReloadable(false);
	File additionWebInfClasses = new File("target/classes");
	WebResourceRoot resources = new StandardRoot(ctx);
	resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(),
			"/"));
	tomcat.start();
	tomcat.getServer().await();
	}



	public static void main(String[] args) throws LifecycleException {

		SpringBoot内嵌Tomcat.run();
//		long start = System.currentTimeMillis();
//		// 设置端口
//		int port = 8888;
//		Tomcat tomcat = new Tomcat();
//		tomcat.setPort(port);
//
//		// 添加listener
//		StandardServer server = (StandardServer) tomcat.getServer();
//		AprLifecycleListener listener = new AprLifecycleListener();
//		server.addLifecycleListener(listener);
//
//		// 设置contextPath和路径
//		String contextPath = "/a2-test";
//		String docBase = new File("webapp").getAbsolutePath();
//		Context context = tomcat.addWebapp(contextPath, docBase);
//		System.out.println("添加contextPath和docBase是:" + contextPath + "==>" + docBase);
//
//		// add servlet
//		// Context context = tomcat.addContext(contextPath, baseDir);
//		String servletName = "hello";
//		String servletMapping = "/hello";
//		tomcat.addServlet(contextPath, servletName, new HelloServlet());
//		context.addServletMappingDecoded(servletMapping, servletName);
//		// 启动tomcat
//		tomcat.start();
//		long end = System.currentTimeMillis();
//		System.out.println("启动完成,共使用了:" + (end - start) + "ms");
//		// 进入监听状态,如果不进入监听状态,启动tomat后就会关闭tomcat
//		tomcat.getServer().await();
	}

}
