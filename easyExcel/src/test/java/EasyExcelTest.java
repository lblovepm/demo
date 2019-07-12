import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.listener.ExcelListener;
import com.model.ExcelPropertyUserModel;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/7/12 15:26
 */
public class EasyExcelTest {

    private final String excelFilePath = "D:\\test.xlsx";

    /**导入Excel*/
    @Test
    public void testRead() throws Exception{
        File file = new File(excelFilePath);
        if(!file.exists()){
            file.createNewFile();
        }
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        try {
            // 解析每行结果在listener中处理
            AnalysisEventListener listener = new ExcelListener();

            ExcelReader excelReader = new ExcelReader(inputStream, null, listener);

            /**
             * Sheet 构造函数参数
             * sheetNo  是第几个sheet  默认从1开始
             * headLineMun 表头占几行
             * 导入excel的实体地址
             */
            excelReader.read(new Sheet(1, 2, ExcelPropertyUserModel.class));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private List<ExcelPropertyUserModel> getData(){
        List<ExcelPropertyUserModel> data = new ArrayList<>();
        ExcelPropertyUserModel userModel = new ExcelPropertyUserModel();
        userModel.setName("张大伟");
        userModel.setAge(27);
        userModel.setRemarks("不错哦");
        userModel.setSex("男");
        data.add(userModel);

        userModel = new ExcelPropertyUserModel();
        userModel.setName("小熊猫");
        userModel.setAge(29);
        userModel.setRemarks("国宝啊");
        userModel.setSex("女");

        data.add(userModel);

        return data;
    }

    /**
     * 导出 excel
     * @throws FileNotFoundException
     */
    @Test
    public void testWrite() throws FileNotFoundException {
        File file = new File(excelFilePath);

        OutputStream out = new FileOutputStream(file);
        try {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet1 = new Sheet(1, 0,ExcelPropertyUserModel.class);
            writer.write(getData(), sheet1);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
