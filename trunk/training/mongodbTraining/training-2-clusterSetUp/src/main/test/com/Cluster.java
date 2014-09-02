package com;

import com.mongodb.*;

import org.junit.Before;
import org.junit.Test;


public final class Cluster {

    private static final int[] _shardPorts = {27018, 27019};

    private static final MongoClient mongoDBClient = MongoDBClient.getConnection();

    @Before
    public void setupCluster() throws Exception {

        // Add the shards
        for (final int shardPort : _shardPorts) {
            final CommandResult result
                    = mongoDBClient.getDB("admin").command(new BasicDBObject("addshard", ("localhost:" + shardPort)));
            System.out.println(result);
        }

        // Sleep for a bit to wait for all the nodes to be intialized.
        Thread.sleep(5000);

        // Enable sharding on a collection.
        CommandResult result
                = mongoDBClient.getDB("admin").command(new BasicDBObject("enablesharding", "test"));
        System.out.println(result);

        final BasicDBObject shardKey = new BasicDBObject("tradeId", 1);
        shardKey.put("version", 1);

        final BasicDBObject cmd = new BasicDBObject("shardcollection", "test.TradeCache");
        cmd.put("key", shardKey);

        result = mongoDBClient.getDB("admin").command(cmd);
        System.out.println(result);
    }

    @Test
    public void testShards() throws Exception {

        final DBCollection shardCollection = mongoDBClient.getDB("test").getCollection("TradeCache");
        final BasicDBObject entry = new BasicDBObject("tradeId", 100L);

        entry.append("version", 1).append("counterParty", "TheBank")
                .append("instrumentType", "IRS").append("portfolio", "My-Bank-Swap");

        shardCollection.insert(entry);

    }

    @Test
    public void listShards() throws Exception {

        final DB adminDB = mongoDBClient.getDB("admin");
        CommandResult res = adminDB.command(new BasicDBObject("listshards ", 1));
        System.out.println(res);
    }

}
