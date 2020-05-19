package com.spring2aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class TestAspects {

	public TestAspects(){
		System.out.println("fff");
	}

	@Pointcut("execution(public String com.spring2aop.test.TestServer.list())") // the pointcut expression
	private void anyOldTransfer() {} // the pointcut signature

	/**
	 * 返回通知
	 * joinPoint 参数一定要出现在第一位
	 */
	@AfterReturning(value = "com.spring2aop.aspect.TestAspects.anyOldTransfer()",returning = "result")
	public void methonReturing(JoinPoint joinPoint,String result) {
		System.err.println("-----"+joinPoint.getSignature().getName()+"--------执行切面--"+result+"-------methonReturing---------");
	}

	/**
	 * 前置通知
	 */
	@Before("com.spring2aop.aspect.TestAspects.anyOldTransfer()")
	public void methonStart() {
		System.err.println("-------------执行切面---------------methonStart---");
	}

	/**
	 * 后置通知,无论方法正常结束还是异常结束都调用
	 */
	@After("com.spring2aop.aspect.TestAspects.anyOldTransfer()")
	public void methonEnd() {
		System.err.println("-------------执行切面----------------methonEnd--");
	}

	/**
	 * 异常通知
	 */
	@AfterThrowing(value = "com.spring2aop.aspect.TestAspects.anyOldTransfer()",throwing = "e")
	public void methonException(Exception e) {
		System.err.println("-------------执行切面---------methonException----"+e.getMessage()+"-----");
	}


	/**
	 * 环绕通知,需要手动推进方法执行，相当于手动的编写状态。
	 */
	@Around("com.spring2aop.aspect.TestAspects.anyOldTransfer()")
	public void methonAronud(JoinPoint joinPoint) throws Throwable {
		System.err.println("-------------执行切面---------------methonAronud-1-");
		((MethodInvocationProceedingJoinPoint)joinPoint).proceed(joinPoint.getArgs());

		System.err.println("-------------执行切面---------------methonAronud--2-");
	}


}
