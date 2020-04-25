package com.spring2ext.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 *第一、 BeanPostProcessor :bean 的后之处理器，在bean的初始化前后进行拦截工作
 *
 *第二、 BeanFactoryPostProcessor ：BeanFactory的后置处理器，在beanFactory标准初始化之后调用，
 * 		就是所有的beanDefinition都已经保存加载但是还没有生成bean的时候
 *
 *第三、BeanDefinitionRegistryPostProcessor extend BeanFactoryPostProcessor。
 * 		BeanDefinitionRegistryPostProcessor 在所有beanDefinition 将要被加载的时候执行
 *
 *第四、ApplicationListener 监听容器中事件驱动模型的开发，那么我们需要关心的是事件的发布和监听，基于事件开发，步骤如下：
 * 		1、编写某一个监听器事件（ApplicationEvent及其子类）
 * 		2、把监听器加入到容器
 * 		3、只要容器中相关事件发布（使用ApplicationContext.publishEvent(Event event)方法发布），就能够监听到该事件
 * 			我们可以自定义事件多播器，并传入异步执行器让其异步执行。开发中可以在方法上使用@EventListener 可以代替实现接口的
 * 			方式实现接口的监听工作。
 * 			@EventListener	原理： EventListener 是利用 EventListenerMethodProcessor 后置处理器完成的，
 * 			而 	EventListenerMethodProcessor implements SmartInitializingSingleton ，而 SmartInitializingSingleton
 * 			中唯一的 afterSingletonsInstantiated 方法，afterSingletonsInstantiated 会在所有的单是列bean 完成之后触发
 * 		SmartInitializingSingleton 原理：在创建单实例bean 的时候，使用getBean（...）方法，获取所有创建好的bean 对象，然后
 * 		判断是不是 继承 SmartInitializingSingleton，如果是的话，就执行它的 afterSingletonsInstantiated(...)方法。
 *
 *
 *
 *后置处理器的执行顺序:
 *  BeanDefinitionRegistryPostProcessor -> BeanFactoryPostProcessor
 *  	-> BeanPostProcessor -> SmartInitializingSingleton -> ApplicationListener
 */
@ComponentScan("com.spring2ext.*")
@Configuration
public class ExtConfig {
}
