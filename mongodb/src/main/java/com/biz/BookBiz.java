package com.biz;

import com.BookEntity;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/1 9:51
 */

@Component
public class BookBiz {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存数据
     * @param book
     * @return
     */
    public void saveObj(BookEntity book){
        mongoTemplate.save(book);
    }

    /**
     * 查询所有
     * @return
     */
    public List<BookEntity> findAllObj(){
        return mongoTemplate.findAll(BookEntity.class);
    }

    /**
     * 根据id查询
     * @param bookId
     * @return
     */
    public List<BookEntity> findObjByBookId(Integer bookId){
        Query query = new Query();
        query.addCriteria(Criteria.where("bookId").is(bookId));
        return mongoTemplate.find(query,BookEntity.class);
    }

    /**
     * 根据书名查询
     * @param bookName
     * @return
     */
    public List<BookEntity> findObjByBookName(String bookName){
        Query query = new Query();
        query.addCriteria(Criteria.where("bookName").is(bookName));
        return mongoTemplate.find(query,BookEntity.class);
    }

    /**
     * 更新数据
     * @param book
     */
    public long updateObj(BookEntity book){
        Query query = new Query();
        query.addCriteria(Criteria.where("bookId").is(book.getBookId()));

        Update updateBook = new Update().set("bookName",book.getBookName());
        UpdateResult updateResult = mongoTemplate.updateFirst(query,updateBook,BookEntity.class);
        return updateResult.getModifiedCount();
    }

    /**
     * 删除数据
     */
    public long delete(BookEntity book){
        DeleteResult deleteResult = mongoTemplate.remove(book);
        return deleteResult.getDeletedCount();
    }
}
