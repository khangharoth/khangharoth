package com;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

/**
 * Created by raj on 30/08/14.
 */
public class MongoDBClient {

    public static MongoClient getConnection() {
        MongoClient mongoClient = null;

        try {
            mongoClient = new MongoClient();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't connect to mongodb server.");

        }
        return mongoClient;
    }

    public static DB getDB() {
        return getConnection().getDB("test");
    }

}
