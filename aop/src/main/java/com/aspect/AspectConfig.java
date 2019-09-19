package com.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.SourceLocation;
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
//    @Before("@annotation(com.annotation.LogOutput)")
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
    @Around("execution(* com.controller.*.*(..))")
    public JSONObject logOutputAround(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("================Around-Start==================");

        //TODO 参数
//        Object[] argsArr = proceedingJoinPoint.getArgs();
//        for (Object object : argsArr){
//            System.out.println("参数--------------->"+object);
//        }
//
//        String kind = proceedingJoinPoint.getKind();
//        System.out.println("kind--------------->"+kind);
//
//        Signature signature = proceedingJoinPoint.getSignature();
//        System.out.println("signature--------------->"+signature.getName());
//
//        SourceLocation sourceLocation = proceedingJoinPoint.getSourceLocation();
//        System.out.println("sourceLocation--------------------->"+sourceLocation);
//
//        //TODO  proceedingJoinPoint.proceed() 可以使目标方法执行，如果没有添加，则@Before失效
//        proceedingJoinPoint.proceed(argsArr);

//        System.out.println("================Around-End==================");

        try{
            return (JSONObject) proceedingJoinPoint.proceed();
        }catch (Throwable e){
            JSONObject errorJson = new JSONObject();
            errorJson.put("flag","error");
            errorJson.put("message",e.getMessage());
            return errorJson;
        }
    }

    /**
     * 异常增强
     * @AfterThrowing中的value和pointcut是差不多的含义,pointcut可以和@PointCut注解配合使用
     * @AfterThrowing只能捕捉到方法体内的异常,方法参数效验(eg：@Valid或@Validated)产生的异常无法捕捉
     * @param ex
     */
//    @AfterThrowing(value = "@annotation(com.annotation.LogOutput)",throwing = "ex")
    @AfterThrowing(pointcut = "execution(* com.service.impl.*.*(..))",throwing = "ex")
    public int logOutputAfterThrowing(Throwable ex){
        System.out.println("================AfterThrowing-Start==================");

        System.out.println(ex.getMessage());

        System.out.println("================AfterThrowing-End==================");

        return 1111;
    }

    /**
     * 后置增强
     * @param joinPoint
     */
//    @AfterReturning("@annotation(com.annotation.LogOutput)")
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
//    @After("@annotation(com.annotation.LogOutput)")
    public void logOutputAfter(JoinPoint joinPoint){
        System.out.println("================After-Start==================");

        Object[] argsArr = joinPoint.getArgs();
        for (Object object : argsArr){
            System.out.println(object);
        }

        System.out.println("================After-End==================");
    }

}
