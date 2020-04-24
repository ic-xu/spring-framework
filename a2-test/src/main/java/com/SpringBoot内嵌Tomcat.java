package com;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
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
	String webappDirLocation = "src/main/webapp/";
	Tomcat tomcat = new Tomcat();
	tomcat.setPort(9090);
	StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
	ctx.setReloadable(false);
	File additionWebInfClasses = new File("target/classes");
	WebResourceRoot resources = new StandardRoot(ctx);
	resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(),
			"/"));
	tomcat.start();
	tomcat.getServer().await();
	}
}
