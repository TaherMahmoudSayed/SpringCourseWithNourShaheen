package com.book.booksproject.Aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
@Slf4j
public class MeasurementAspect {
    @Pointcut(value = "execution(* com.book.booksproject.repository..*(..))")
    public void loggingRepositoryAdvice(){}
    @Pointcut(value = "execution(* com.book.booksproject.service..*(..))")
    public void loggingServiceAdvice(){}
    @Pointcut(value = "execution(* com.book.booksproject.controller..*(..))")
    public void loggingControllerAdvice(){}

    @Before(value = "loggingRepositoryAdvice()||loggingServiceAdvice()||loggingControllerAdvice()")
    public void normalLoggingAdviceImpl(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().toShortString();

		log.info("====>  Method Name is >> {}" , methodName );

		Object [] args = joinPoint.getArgs();

		for (Object arg : args) {

			log.info("===> argument >> {}" , arg);
		}
    }
    @AfterThrowing(value = "loggingRepositoryAdvice()||loggingServiceAdvice()||loggingControllerAdvice()"
                    ,throwing = "ex")
    public void exceptionLoggingAdvice(JoinPoint joinPoint,Exception ex){
        log.error("error====>"+joinPoint.getSignature().toShortString());
        log.error("error====>"+ex.getMessage());

    }
}
