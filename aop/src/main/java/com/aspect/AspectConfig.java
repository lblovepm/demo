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
            **/
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
     * 环绕增强
     * @param proceedingJoinPoint
     */
    @Around("@annotation(com.annotation.LogOutput)")
    public int logOutputAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("================Around-Start==================");

        Object[] argsArr = proceedingJoinPoint.getArgs();
        for (Object object : argsArr){
            System.out.println(object);
        }

        //TODO  proceedingJoinPoint.proceed() 可以使目标方法执行，如果没有添加，则@Before失效
        proceedingJoinPoint.proceed(argsArr);

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

    /**
     * 后置增强
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
     * 最终增强
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

}
