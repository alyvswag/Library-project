package com.example.libraryproject.aop;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.model.dto.response.base.BaseResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.libraryproject.service..*Service.*(..))")
    public void logMethodParameters(JoinPoint joinPoint) {
        printMethodName(joinPoint);
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            System.err.println("Method parameters: " + Arrays.toString(args));
        } else {
            System.err.println("Method called with no parameters.");
        }
    }

    private synchronized static void printMethodName(JoinPoint joinPoint) {
        System.out.println("--Method called: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(* com.example.libraryproject.service..*Service.*(..))", returning = "result")
    public void logMethodReturn(JoinPoint joinPoint, Object result) {
        if (result != null) {
            System.err.println("Method " + joinPoint.getSignature().getName() + " returned: " + BaseResponse.success(result));
        } else {
            System.err.println("Method " + joinPoint.getSignature().getName() + " returned: "+ BaseResponse.success());
        }
    }

    @Around("execution(* com.example.libraryproject.service..*Service.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            long startTime = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            System.err.println("Method " + joinPoint.getSignature().getName() + " executed in " + (endTime - startTime) + "ms");
            return result;
        }catch (BaseException ex){
            System.err.println("Error: "+BaseResponse.error(ex));
            throw ex;
        }
    }

}

