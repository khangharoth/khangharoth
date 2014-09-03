package com.getAndPut;

import com.MongoDBClient;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

/**
 * Created by raj on 30/08/14.
 */
public class Get {

    public static void main(String[] args) {
        DBCollection coll = MongoDBClient.getDB().getCollection("TradeCache");

        BasicDBObject query = new BasicDBObject("A", "1");

        DBCursor cursor = coll.find(query);

        try {
            while(cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        } finally {
            cursor.close();
        }

    }
}
