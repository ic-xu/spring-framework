package com.springboot;

/**
 * @Target java元注解，用于标志注解作用的对象
 * @Retention java元注解，用于标识注解作用的生命周期
 * @Documented javadoc
 * @Inherited 修饰自定义注解可被继承
 *
 *@SpringBootConfiguration
 * @EnableAutoConfiguration
 * @ComponentScan(
 *                excludeFilters = {@Filter(
 *                type = FilterType.CUSTOM,
 * 				classes = {TypeExcludeFilter.class}
 * 		), @Filter(
 * 				type = FilterType.CUSTOM,
 * 				classes = {AutoConfigurationExcludeFilter.class}
 * 		)}
 * )
 *public @interface SpringBootApplication {
 * 			//默认使用CGLIB 代理 为true保证生成的bean是单列的，而false就不可以，这也是@Compunet 和@Configruation的区别
 *         boolean proxyBeanMethods() default true;
 *}
 *
 *
 *
 *
 */

public class Springboot自动装配原理 {
}
