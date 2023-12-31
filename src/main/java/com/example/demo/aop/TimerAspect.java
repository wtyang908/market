package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect//切面类
public class TimerAspect {

    @Around("execution(* com.example.demo.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp)throws Throwable{
        long start = System.currentTimeMillis();

        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("耗时"+(end-start));
        return result;
    }
}
