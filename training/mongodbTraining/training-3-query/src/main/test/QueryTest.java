import com.MongoDBClient;
import com.mongodb.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class QueryTest {

    private static final MongoClient mongoDBClient = MongoDBClient.getConnection();

    @Before
    public void init() throws Exception {
        DBCollection collection = mongoDBClient.getDB("test").getCollection("tradeCache");

        loadTade(collection, 1L, 0);
        loadTade(collection, 2L, 0);
        loadTade(collection, 3L, 0);
        loadTade(collection, 4L, 0);
        loadTade(collection, 5L, 5);
        loadTade(collection, 6L, 0);
        loadTade(collection, 7L, 0);
        loadTade(collection, 8L, 2);
        loadTade(collection, 9L, 0);
        loadTade(collection, 10L, 0);

    }

    private BasicDBObject newTrade(Long tradeId, Integer version) {
        BasicDBObject basicDBObject = new BasicDBObject("tradeId", tradeId);
        basicDBObject.append("version", version);
        return basicDBObject;
    }

    private void loadTade(DBCollection cache, Long tradeId, Integer version) {
        cache.save(newTrade(tradeId, version));
    }

    private int countResult(DBCursor cursor) {
        int i = 0;
        while (cursor.hasNext()) {
            i++;
        }

        return i;
    }

    private void assertTradeVersion(DBObject object, Integer version) {
        Assert.assertEquals(object.get("version"), version);
    }

    @Test
    public void shouldGetFirstEntry() {
        DBCollection collection = mongoDBClient.getDB("test").getCollection("tradeCache");
        DBObject dbObject = collection.findOne();
        Assert.assertEquals(dbObject.toMap().get("tradeId"), equals(1L));
        Assert.assertEquals(dbObject.toMap().get("version"), equals(0));
    }

    @Test
    public void shouldGetAllEntry() {
        DBCollection collection = mongoDBClient.getDB("test").getCollection("tradeCache");
        Assert.assertEquals(countResult(collection.find()), 10);
    }

    @Test
    public void shouldGetTradeWithVersion5() {
        DBCollection collection = mongoDBClient.getDB("test").getCollection("tradeCache");
        BasicDBObject query = new BasicDBObject("version", 5);
        DBCursor cursor = collection.find(query);


        DBObject dbObject = cursor.next();
        assertTradeVersion(dbObject, 5);
    }

    @Test
    public void shouldQueryLessThanGreaterThan() {
        DBCollection collection = mongoDBClient.getDB("test").getCollection("tradeCache");
        BasicDBObject query = new BasicDBObject("tradeId", new BasicDBObject("$lt", 7).append("k", new BasicDBObject("$gt", 3)));
        Assert.assertEquals(countResult(collection.find(query)), 3);
    }

}