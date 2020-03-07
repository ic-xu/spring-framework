/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.annotation;

import java.util.function.Supplier;

import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * Standalone application context, accepting <em>component classes</em> as input &mdash;
 * in particular {@link Configuration @Configuration}-annotated classes, but also plain
 * {@link org.springframework.stereotype.Component @Component} types and JSR-330 compliant
 * classes using {@code javax.inject} annotations.
 *
 * <p>Allows for registering classes one by one using {@link #register(Class...)}
 * as well as for classpath scanning using {@link #scan(String...)}.
 * 允许使用{@link #register（Class ...）} 一对一地注册类，以及使用{@link #scan（String ...）}进行类路径扫描。
 *
 * <p>In case of multiple {@code @Configuration} classes, {@link Bean @Bean} methods
 * defined in later classes will override those defined in earlier classes. This can
 * be leveraged to deliberately override certain bean definitions via an extra
 * {@code @Configuration} class.
 * <p>
 * 如果有多个{@code @Configuration}类，则以后的类中定义的{@link Bean @Bean}
 * 方法*将覆盖先前的类中定义的方法。
 * 可以通过一个额外的{@code @Configuration}类来故意重写某些bean的定义。
 *
 * <p>See {@link Configuration @Configuration}'s javadoc for usage examples.
 * <p>
 * <p>
 * 主要类或接口说明：
 * <p>
 * GenericApplicationContext: 通用应用上下文，内部持有一个DefaultListableBeanFactory实例，
 * 这个类实现了BeanDefinitionRegistry接口，
 * 可以在它身上使用任意的bean definition读取器。
 * 典型的使用案例是：通过BeanFactoryRegistry接口注册bean definitions，
 * 然后调用refresh()方法来初始化那些带有应用上下文语义
 * （org.springframework.context.ApplicationContextAware）的bean，
 * 自动探测org.springframework.beans.factory.config.BeanFactoryPostProcessor等。
 * <p>
 * <p>
 * BeanDefinitionRegistry: 用于持有像RootBeanDefinition和 ChildBeanDefinition实例的bean definitions的注册表接口。
 * DefaultListableBeanFactory实现了这个接口，因此可以通过相应的方法向beanFactory里面注册bean。
 * GenericApplicationContext内置一个DefaultListableBeanFactory实例，
 * 它对这个接口的实现实际上是通过调用这个实例的相应方法实现的。
 * <p>
 * <p>
 * AbstractApplicationContext : ApplicationContext接口的抽象实现，没有强制规定配置的存储类型，仅仅实现了通用的上下文功能。
 * 这个实现用到了模板方法设计模式，需要具体的子类来实现其抽象方法。
 * 自动通过registerBeanPostProcessors()方法注册BeanFactoryPostProcessor,
 * BeanPostProcessor和ApplicationListener的实例用来探测bean factory里的特殊bean——对比1分析
 *
 * <p>
 * AnnotationConfigRegistry: 注解配置注册表。
 * 用于注解配置应用上下文的通用接口，拥有一个注册配置类和扫描配置类的方法。
 *
 * @author Juergen Hoeller
 * @author Chris Beams
 * @see #register
 * @see #scan
 * @see AnnotatedBeanDefinitionReader
 * @see ClassPathBeanDefinitionScanner
 * @see org.springframework.context.support.GenericXmlApplicationContext
 * @since 3.0
 */
public class AnnotationConfigApplicationContext extends GenericApplicationContext implements AnnotationConfigRegistry {


	/**
	 * AnnotatedBeanDefinitionReader——BeanDefinition解析器用来解析带注解的bean
	 * ClassPathBeanDefinitionScanner——bean的扫描器 用来扫描类
	 *
	 * 注册解析传入的配置类（使用类配置的方式进行解析）
	 * 调用容器的refresh方法初始化容器
	 * 这里我们用的是最后一种构造函数，即传入一个包路径。
	 *
	 */

	private final AnnotatedBeanDefinitionReader reader;

	private final ClassPathBeanDefinitionScanner scanner;


	/**
	 * Create a new AnnotationConfigApplicationContext that needs to be populated
	 * through {@link #register} calls and then manually {@linkplain #refresh refreshed}.
	 * <p>
	 * 创建一个新的AnnotationConfigApplicationContext，
	 * 它需要通过{@link #register}调用进行填充，
	 * 然后手动{@linkplain #refresh refreshed}。
	 * 也就是
	 * 默认构造函数，初始化一个空容器，容器不包含任何 Bean 信息，需要在稍后通过调用其register()
	 * 方法注册配置类，并调用refresh()方法刷新容器，触发容器对注解Bean的载入、解析和注册过程
	 */
	public AnnotationConfigApplicationContext() {
		this.reader = new AnnotatedBeanDefinitionReader(this);
		this.scanner = new ClassPathBeanDefinitionScanner(this);
	}

	/**
	 * Create a new AnnotationConfigApplicationContext with the given DefaultListableBeanFactory.
	 *
	 * @param beanFactory the DefaultListableBeanFactory instance to use for this context
	 */
	public AnnotationConfigApplicationContext(DefaultListableBeanFactory beanFactory) {
		super(beanFactory);
		this.reader = new AnnotatedBeanDefinitionReader(this);
		this.scanner = new ClassPathBeanDefinitionScanner(this);
	}

	/**
	 * Create a new AnnotationConfigApplicationContext, deriving bean definitions
	 * from the given component classes and automatically refreshing the context.
	 * <p>
	 * 创建一个新的AnnotationConfigApplicationContext，最常用的构造函数，
	 * 通过将涉及到的配置类传递给该构造函数，
	 * 以实现将相应配置类中的Bean自动注册到容器中
	 * <p>
	 * ①、给定的组件类派生bean定义，
	 * ②、自动刷新上下文。
	 *
	 * @param componentClasses one or more component classes &mdash; for example,
	 *                         {@link Configuration @Configuration} classes
	 */
	public AnnotationConfigApplicationContext(Class<?>... componentClasses) {

		/**
		 * 第一步：调用无参构造函数，初始化AnnotatedBeanDefinitionReader和 ClassPathBeanDefinitionScanner
		 * 第二步：
		 * 第三步：
		 */
		this();

		register(componentClasses);
		refresh();
	}

	/**
	 * Create a new AnnotationConfigApplicationContext, scanning for components
	 * in the given packages, registering bean definitions for those components,
	 * and automatically refreshing the context.
	 *
	 * @param basePackages the packages to scan for component classes
	 */
	public AnnotationConfigApplicationContext(String... basePackages) {
		this();
		//扫描包、注册bean
		scan(basePackages);
		refresh();
	}


	/**
	 * Propagate the given custom {@code Environment} to the underlying
	 * {@link AnnotatedBeanDefinitionReader} and {@link ClassPathBeanDefinitionScanner}.
	 */
	@Override
	public void setEnvironment(ConfigurableEnvironment environment) {
		super.setEnvironment(environment);
		this.reader.setEnvironment(environment);
		this.scanner.setEnvironment(environment);
	}

	/**
	 * Provide a custom {@link BeanNameGenerator} for use with {@link AnnotatedBeanDefinitionReader}
	 * and/or {@link ClassPathBeanDefinitionScanner}, if any.
	 * <p>Default is {@link AnnotationBeanNameGenerator}.
	 * <p>Any call to this method must occur prior to calls to {@link #register(Class...)}
	 * and/or {@link #scan(String...)}.
	 *
	 * @see AnnotatedBeanDefinitionReader#setBeanNameGenerator
	 * @see ClassPathBeanDefinitionScanner#setBeanNameGenerator
	 * @see AnnotationBeanNameGenerator
	 * @see FullyQualifiedAnnotationBeanNameGenerator
	 */
	public void setBeanNameGenerator(BeanNameGenerator beanNameGenerator) {
		this.reader.setBeanNameGenerator(beanNameGenerator);
		this.scanner.setBeanNameGenerator(beanNameGenerator);
		getBeanFactory().registerSingleton(
				AnnotationConfigUtils.CONFIGURATION_BEAN_NAME_GENERATOR, beanNameGenerator);
	}

	/**
	 * Set the {@link ScopeMetadataResolver} to use for registered component classes.
	 * <p>The default is an {@link AnnotationScopeMetadataResolver}.
	 * <p>Any call to this method must occur prior to calls to {@link #register(Class...)}
	 * and/or {@link #scan(String...)}.
	 */
	public void setScopeMetadataResolver(ScopeMetadataResolver scopeMetadataResolver) {
		this.reader.setScopeMetadataResolver(scopeMetadataResolver);
		this.scanner.setScopeMetadataResolver(scopeMetadataResolver);
	}


	//---------------------------------------------------------------------
	// Implementation of AnnotationConfigRegistry
	//---------------------------------------------------------------------

	/**
	 * Register one or more component classes to be processed.
	 * <p>Note that {@link #refresh()} must be called in order for the context
	 * to fully process the new classes.
	 *
	 * @param componentClasses one or more component classes &mdash; for example,
	 *                         {@link Configuration @Configuration} classes
	 * @see #scan(String...)
	 * @see #refresh()
	 */
	@Override
	public void register(Class<?>... componentClasses) {
		Assert.notEmpty(componentClasses, "At least one component class must be specified");
		this.reader.register(componentClasses);
	}

	/**
	 * Perform a scan within the specified base packages.
	 * <p>Note that {@link #refresh()} must be called in order for the context
	 * to fully process the new classes.
	 *
	 * @param basePackages the packages to scan for component classes
	 * @see #register(Class...)
	 * @see #refresh()
	 */
	@Override
	public void scan(String... basePackages) {
		Assert.notEmpty(basePackages, "At least one base package must be specified");
		this.scanner.scan(basePackages);
	}


	//---------------------------------------------------------------------
	// Adapt superclass registerBean calls to AnnotatedBeanDefinitionReader
	//---------------------------------------------------------------------

	@Override
	public <T> void registerBean(@Nullable String beanName, Class<T> beanClass,
								 @Nullable Supplier<T> supplier, BeanDefinitionCustomizer... customizers) {

		this.reader.registerBean(beanClass, beanName, supplier, customizers);
	}

}
