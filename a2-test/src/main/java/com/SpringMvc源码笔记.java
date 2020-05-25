package com;

import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

/**
 *
 * springMvc相关设计模式：
 * 		1.适配器模式（适配器模式分为组合和继承两种实现）：
 * 			继承方式：继承被适配的类和实现适配器借接口
 * 			组合方式：实现适配器接口并包含被适配实现类的引用
 * 		2.模板方法模式：就是定义一个抽象类，把相同的实现代码在抽象类中实现，不同的地方抽象出来让其分别在子类中去实现
 * 		3.策略模式：不同策略的实现，他有一个抽象的策略接口，然后在客户端制定需要实现的策略即可
 * 		4.组合模式：
 * 		5.代理模式：
 *
 *
 *
 *
 * 去除ｘｍｌ原理：
 *		在servlet3.0 规范中，规定启动的时候使用WEB-INF/service/目录下的　
 *		javax.servlet.ServletContainerInitializer文件中编写的类的全路径名字，如在SpringWeb中的配置如下：
 *			org.springframework.web.SpringServletContainerInitializer
 *		多个就使用多行表示。它会默认启动类的　onStartup(..) 方法,并装载　@HandlesTypes(WebApplicationInitializer.class)
 *		注解中实现的接口的子类，那么在其实现中，会添加 DispatcherServlet 对象，其基本逻辑如下：
 *
 *			1.扫描项目 ->
 *			2、	拿到所有添加了 @Controllor 的类 ->
 *			3、	判断方法是不是添加了 @RequestMapping 注解->
 *			4、	把注解的值当作key，把method 当作 value 存入map集合中 ->
 *			5、	根据用户发送的请求拦截用户请求的uri，然后使用uri请求的值当作key 去map 中获取对应的 method 执行。
 *
 *   定义一个handle 有两种类型三种实现方式，两种类型分别是 BeanName类型和@Controllor，他们的体现表现为底层使用两种不同方式的HandleMapping
 *   	去查找，三种实现分别是 实现HttpRequestHandle、实现Controllor 和添加 @Controllor注解（体现在HandleAdapter 有三种实现），
 *
 *
 */
public class SpringMvc源码笔记 {
	/**
	 *springMVC中重要的组建
	 */

	/** 继承httpServlet*/
	FrameworkServlet frameworkServlet;

	/** 处理http请求的中央派发器实现类*/
	DispatcherServlet dispatcherServlet;

	/** 拦截器顶层的接口，如果开发拦截器则需要实现这个接口 */
	HandlerInterceptor handlerInterceptor;

	/** 拦截器适配器抽象类，这个主要是适配jdk8之前还没有default 关键字而开发的类，里面没有业务逻辑，全部都是
	 *  HandlerInterceptor的空实现 */
	HandlerInterceptorAdapter handlerInterceptorAdapter;

	/** 处理请求到解析器的映射关系 */
	HandlerMapping handlerMapping;

	/** 最终执行方法的详细信息 */
	HandlerMethod handlerMethod;

	/** 控制器适配器，因为有多种不同的控制器实现方式，所以有多种解析方式，
	 * 那么这时候需要适配以前的接口，所以有了适配器模式 */
	HandlerAdapter handlerAdapter;

	/** 封装了方法的方法参数的元信息，如是不是添加了@PathVariable 等 */
	MethodParameter methodParameter;

	/** 对请求方法参数的解析*/
	HandlerMethodArgumentResolver handlerMethodArgumentResolver;

	/** 这个其实是包含 HandlerMethodArgumentResolver 接口的 组合模式，它封装了
	 *  HandlerMethodArgumentResolver 的实现类，然后对它们遍历的作用 */
	HandlerMethodArgumentResolverComposite handlerMethodArgumentResolverComposite;

	/** 对应了SpringMvc中 @RequestBody 和 @ResposeBody */
	RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor;

}
