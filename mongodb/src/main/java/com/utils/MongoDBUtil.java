package com.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/1 11:05
 */
public class MongoDBUtil {

    private static MongoDatabase mongoDatabase = null;

    private final static String DB_NAME = "test";

    public static MongoDatabase getMongoDatabase(){
        if(null == mongoDatabase){
            MongoClient mongoClient = new MongoClient("localhost",27017);
            mongoDatabase = mongoClient.getDatabase(DB_NAME);
        }
        return mongoDatabase;
    }
}
