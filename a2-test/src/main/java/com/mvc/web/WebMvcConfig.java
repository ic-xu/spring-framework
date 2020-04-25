package com.mvc.web;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configurable
@ComponentScan("com.mvc.*")
//@EnableAspectJAutoProxy//开启动态代理
public class WebMvcConfig {


}
