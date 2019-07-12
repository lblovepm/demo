package com.annotation;

/**
 * @author Mr.LB
 * @description: 日志输出注解
 * @date 2019/7/11 14:10
 */
public @interface LogOutput {

    String[] params() default {};;
}
