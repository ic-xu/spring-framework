package com.aop.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configurable
@ComponentScan("com.aop.*")
@EnableAspectJAutoProxy//开启动态代理
@EnableTransactionManagement//开启注解（开启之前必须配置事务管理器）
public class AopConfig {


	/**
	 * 配置事务管理器注解
	 *
	 * DataSourceTransactionManager 表示基于数据源的事务管理器
	 * @param dataSource
	 * @return
	 */
	@Bean
	public PlatformTransactionManager platformTransactionManager(DataSource dataSource){

		return new DataSourceTransactionManager(dataSource);
	}
}
