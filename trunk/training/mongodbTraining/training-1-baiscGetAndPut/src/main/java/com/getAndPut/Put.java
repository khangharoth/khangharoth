package com.getAndPut;

import com.MongoDBClient;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

/**
 * Created by raj on 30/08/14.
 */
public class Put {
    public static void main(String[] args) {
        DBCollection coll = MongoDBClient.getDB().getCollection("TradeCache");
        BasicDBObject doc = new BasicDBObject("A", "1")
                .append("type", "trade");
        coll.insert(doc);
    }
}
