package com.spring2aop.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configurable
@ComponentScan("com.spring2aop.*")
@EnableAspectJAutoProxy//开启动态代理
//@EnableTransactionManagement//开启事物管理器支持
public class AopConfig {


	/**
	 * 配置事务管理器注解
	 *
	 * DataSourceTransactionManager 表示基于数据源的事务管理器
	 * @param dataSource
	 * @return
	 */
//	@Bean
//	public PlatformTransactionManager platformTransactionManager(DataSource dataSource){
//
//		return new DataSourceTransactionManager(dataSource);
//	}
}
