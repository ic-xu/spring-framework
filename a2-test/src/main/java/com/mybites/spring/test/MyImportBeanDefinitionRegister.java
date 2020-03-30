package com.mybites.spring.test;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.annotation.Annotation;

public class MyImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

			MergedAnnotation<Annotation> annotationMergedAnnotation =
					importingClassMetadata.getAnnotations().get("com.mybites.spring.test.MyScan");
		/**
		 * 拿到@MyScan注解的值,也就是扫描的路径，然后循环加入 registry 中
		 */
		String value = (String)annotationMergedAnnotation.getValue("value").get();


		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(MyFactoryBean.class);
		AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
		//注入属性
		beanDefinition.getPropertyValues().add("mapperClass","com.mybites.spring.mapper.UserMapper");
		registry.registerBeanDefinition("xxx",beanDefinition);
	}
}
