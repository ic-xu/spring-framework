package com;

/**
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
}
