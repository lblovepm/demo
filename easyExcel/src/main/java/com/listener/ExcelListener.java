package com.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.model.ExcelPropertyUserModel;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/7/12 15:22
 */
public class ExcelListener extends AnalysisEventListener<ExcelPropertyUserModel> {

    public void invoke(ExcelPropertyUserModel excelPropertyUserModel, AnalysisContext analysisContext) {
        System.out.println(excelPropertyUserModel.getName());
    }

    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
