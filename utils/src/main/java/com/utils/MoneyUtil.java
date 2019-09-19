package com.utils;

import org.springframework.validation.BindingResult;

import java.math.BigDecimal;

/**
 * 金额元分之间转换工具类
 * 
 * @author Mr.LB
 *
 */
public class MoneyUtil {
	
	//金额为分的格式  
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";
    
    //金额为元的格式
    public static final String CURRENCY_YUAN_REGEX = "(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?";   
        
    /**   
     * 将分为单位的转换为元 (除以100)
     *    
     * @param amount   
     * @return   
     * @throws Exception    
     */    
    public static String changeFenToYuan(String amount) throws Exception{
        if(!amount.matches(CURRENCY_FEN_REGEX)) {    
            throw new Exception("金额格式有误");    
        }    
        return BigDecimal.valueOf(Integer.valueOf(amount)).divide(new BigDecimal(100)).toString();    
    }   
    
    /**   
     * 将分为单位的转换为元 (除以100)
     *    
     * @param amount   
     * @return   
     * @throws Exception    
     */    
    public static String changeFenToYuan(BigDecimal amount) throws Exception{    
        if(!String.valueOf(amount).matches(CURRENCY_FEN_REGEX)) {    
            throw new Exception("金额格式有误");    
        }    
        return amount.divide(new BigDecimal(100)).toString();    
    }
        
    /**    
     * 将元为单位的转换为分 (乘以100)
     *    
     * @param amount   
     * @return   
     * @throws Exception 
     */    
    public static BigDecimal changeYuanToFen(BigDecimal amount) throws Exception{
    	if(!String.valueOf(amount).matches(CURRENCY_YUAN_REGEX)){
    		throw new Exception("金额格式有误");
    	}
        return amount.multiply(new BigDecimal(100));
    }
    
    /**    
     * 将元为单位的转换为分 (乘以100)
     *    
     * @param amount   
     * @return   
     * @throws Exception 
     */    
    public static BigDecimal changeYuanToFen(String amount) throws Exception{   
    	if(!amount.matches(CURRENCY_YUAN_REGEX)){
    		throw new Exception("金额格式有误");
    	}
        return BigDecimal.valueOf(Double.valueOf(amount)).multiply(new BigDecimal(100));
    }
        
    public static void main(String[] args) throws Exception {

    }

}
