package com.aop.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class TestAspects {

//	com.xyz.myapp.dao.*.*(..)
	@Pointcut("execution(* com.aop.test.*.*(..))") // the pointcut expression
	private void anyOldTransfer() {} // the pointcut signature


	@After("com.aop.aspect.TestAspects.anyOldTransfer()")
	public void doAccessCheck() {
		System.err.println("-------------执行切面------------------");
	}
}
