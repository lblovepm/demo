package com;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 图书实体类
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/1 9:50
 */

@Setter
@Getter
public class BookEntity implements Serializable {

    /**
     * 书的id
     */
    private Integer bookId;

    /**
     * 书名
     */
    private String bookName;

    /**
     * 价格
     */
    private BigDecimal bookPrice;

    /**
     * 作者
     */
    private String author;

    /**
     * 简介
     */
    private String desc;

    /**
     * 出版社
     */
    private String publishHouse;

    /**
     * 发布日期
     */
    private Date publishDate;
}
