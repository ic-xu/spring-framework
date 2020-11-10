package com.spring2ext.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;


/**
 * 这个处理器在beanDefinition  之后执行，在是实例化(new xxx())之前执行。
 * 用来处理beanDefinition信息的。
 *
 *
 */
public class MyFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}
}
