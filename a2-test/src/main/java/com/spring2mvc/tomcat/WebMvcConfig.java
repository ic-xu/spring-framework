package com.spring2mvc.tomcat;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;

@Configurable
@ComponentScan("com.spring2mvc.*")
//@EnableAspectJAutoProxy//开启动态代理
public class WebMvcConfig {


}
