package com.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/***************************Aspect中各种增强的执行顺序****************************************/
           /**
                try{
                       @Around
                       @Before
                    }catch(Exception e){
                       @AfterThrowing
                    }finally{
                       @After
                    }
                    @Around
                    @AfterReturning

              */
/***************************Aspect中各种增强的执行顺序****************************************/

/**
 * @author Mr.LB
 * @description: 切面配置
 * @date 2019/7/11 14:09
 */
@Component
@Aspect
public class AspectConfig {

    /**
     * 前置增强
     * @param joinPoint
     */
    @Before("@annotation(com.annotation.LogOutput)")
    public void logOutputBefore(JoinPoint joinPoint){
        System.out.println("================Before-Start==================");

        Object[] argsArr = joinPoint.getArgs();
        for (Object object : argsArr){
            System.out.println(object);
        }

        System.out.println("================Before-End==================");
    }

    /**
     * 后置增强
     * @param joinPoint
     */
    @After("@annotation(com.annotation.LogOutput)")
    public void logOutputAfter(JoinPoint joinPoint){
        System.out.println("================After-Start==================");

        Object[] argsArr = joinPoint.getArgs();
        for (Object object : argsArr){
            System.out.println(object);
        }

        System.out.println("================After-End==================");
    }

    /**
     * 最终增强
     * @param joinPoint
     */
    @AfterReturning("@annotation(com.annotation.LogOutput)")
    public void logOutputAfterReturning(JoinPoint joinPoint){
        System.out.println("================AfterReturning-Start==================");

        Object[] argsArr = joinPoint.getArgs();
        for (Object object : argsArr){
            System.out.println(object);
        }

        System.out.println("================AfterReturning-End==================");
    }

    /**
     * 环绕增强
     * @param joinPoint
     */
    @Around("@annotation(com.annotation.LogOutput)")
    public int logOutputAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("================Around-Start==================");

        Object[] argsArr = joinPoint.getArgs();
        for (Object object : argsArr){
            System.out.println(object);
        }
        joinPoint.proceed(argsArr);

        System.out.println("================Around-End==================");

        return 0;
    }

    /**
     * 异常增强
     * @param joinPoint
     */
    @AfterThrowing("@annotation(com.annotation.LogOutput)")
    public void logOutputAfterThrowing(JoinPoint joinPoint){
        System.out.println("================AfterThrowing-Start==================");

        Object[] argsArr = joinPoint.getArgs();
        for (Object object : argsArr){
            System.out.println(object);
        }

        System.out.println("================AfterThrowing-End==================");
    }

    public static void main(String[] args) {
        try {
//            System.out.println(1/0);
            System.out.println("1111111111111111111");
        }catch (Exception e){
            System.out.println("2222222222222222222");
        }finally {
            System.out.println("3333333333333333333");
        }
        System.out.println("4444444444444444444444");
//        return;
    }

}
