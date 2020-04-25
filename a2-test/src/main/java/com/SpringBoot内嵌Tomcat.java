package com;


import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;

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
	public static String TOMCAT_HOSTNAME = "127.0.0.1";
	public static String WEBAPP_PATH = "a2-test/web";


	public static void run() throws ServletException, LifecycleException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(SpringBoot内嵌Tomcat.TOMCAT_PORT);
		/*
		 * https://www.cnblogs.com/ChenD/p/10061008.html
		 */
		tomcat.addWebapp("/",  "/local/sda/project/spring-framework-master/a2-test/web");
//		/*
//		 * true时：相关classes | jar 修改时，会重新加载资源，不过资源消耗很大
//		 * autoDeploy 与这个很相似，tomcat自带的热部署不是特别可靠，效率也不高。生产环境不建议开启。
//		 * 相关文档：
//		 * http://www.blogjava.net/wangxinsh55/archive/2011/05/31/351449.html
//		 */
//		myCtx.setReloadable(false);
//		// 上下文监听器
//		myCtx.addLifecycleListener(new AprLifecycleListener());
//
//        /*String webAppMount = System.getProperty("user.dir") + File.separator + TomcatStart.CLASS_PATH;
//        WebResourceRoot root = new StandardRoot(myCtx);
//        root.addPreResources(
//            new DirResourceSet(root, TomcatStart.WEBINF_CLASSES, webAppMount, TomcatStart.INTERNAL_PATH));*/
//
//		// 注册servlet
//		tomcat.addServlet("/access", "demoServlet", new T());
//		// servlet mapping
//		myCtx.addServletMappingDecoded("/demo.do", "demoServlet");
		tomcat.start();
		tomcat.getServer().await();
	}



	public static void main(String[] args) throws LifecycleException, ServletException {

		SpringBoot内嵌Tomcat.run();
	}

}
