package com.test;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.utils.MongoDBUtil;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/1 11:09
 */
public class MongoDBTest {

    public static void main(String[] args) {
        MongoDatabase mongoDatabase = MongoDBUtil.getMongoDatabase();
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("lb");

        //insert
        Document document3 = new Document();
        document3.append("_id","234567890")
                .append("userName","LB先生3")
                .append("age",25)
                .append("userId",4);
        mongoCollection.insertOne(document3);

//        update
        Document updateFilter = new Document();
        updateFilter.append("userId",2);
//        BasicDBObject updateFilter = new BasicDBObject("_id",new ObjectId("5cf1f1898c1591238c785e63"));
        Document updateOption = new Document();
        updateOption.append("$set",new Document("age",2221)
                .append("userName","LB先生呢呢1")
                .append("desc","多么活泼可爱1"));
        UpdateResult updateResult = mongoCollection.updateOne(updateFilter,updateOption);
        System.out.println(updateResult.getModifiedCount());

//        query
        List<Document> documentList = new ArrayList<>();
        FindIterable<Document> findIterable = mongoCollection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        Document document = null;
        while (mongoCursor.hasNext()){
            document = mongoCursor.next();
            documentList.add(document);
        }

        //delete
        BasicDBObject filter = new BasicDBObject("_id",new ObjectId("5cf1f1898c1591238c785e63"));
        DeleteResult deleteResult = mongoCollection.deleteOne(filter);
        System.out.println(deleteResult.getDeletedCount());
    }
}
