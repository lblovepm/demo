package com.common;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

/**
 * 对返回的数据进行封装
 * @author Mr.LB
 * @date 2019-09-20
 */
public class ResultResponse {

    //成功响应的返回码
    private final static String SUCCESS = "success";

    //失败响应的返回码
    private final static String FAIL = "fail";

    //异常或错误响应的返回码
    private final static String ERROR = "error";

    /**
     * 成功响应
     * @param result
     * @return
     */
    public static JSONObject successResponse(Object result){
        JSONObject resultJson = new JSONObject();

        if(result instanceof Page){
            //列表分页(配合BootstrapTable:$('#tableId').bootstrapTable()需要total和rows两个字段)
            Page resultPage = (Page) result;
            if (null == resultPage || resultPage.size() == 0) {
                resultJson.put("total", 0);
                resultJson.put("rows", new Page());
            } else {
                resultJson.put("total", resultPage.getTotal());
                resultJson.put("rows", resultPage);
            }
        }else{
            //非列表分页
            resultJson.put("code",SUCCESS);
            resultJson.put("result",result);
        }
        return resultJson;
    }

    /**
     * 失败响应
     * @param result
     * @return
     */
    public static JSONObject failResponse(Object result){
        JSONObject resultJson = new JSONObject();

        resultJson.put("code",FAIL);
        resultJson.put("result",result);

        return resultJson;
    }

    /**
     * 错误或异常响应
     * @param errorMsg
     * @return
     */
    public static JSONObject errorResponse(Object errorMsg){
        JSONObject resultJson = new JSONObject();

        resultJson.put("code",ERROR);
        resultJson.put("result",errorMsg);

        return resultJson;
    }
}
