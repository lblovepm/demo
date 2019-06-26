package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.biz.BookBiz;
import com.BookEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/1 10:41
 */

@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookBiz bookBiz;

    @RequestMapping("/save")
    public JSONObject saveBook(BookEntity bookEntity){
        bookBiz.saveObj(bookEntity);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",null);
        return jsonObject;
    }

    @RequestMapping("/findall")
    public JSONObject findAll(){
        List<BookEntity> result = bookBiz.findAllObj();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",result);
        return jsonObject;
    }
}
