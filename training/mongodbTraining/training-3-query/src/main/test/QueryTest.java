import com.MongoDBClient;
import com.mongodb.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class QueryTest {

    private static final MongoClient mongoDBClient = MongoDBClient.getConnection();

    @BeforeClass
    public void init() throws Exception {
        DBCollection collection = getCollection();
        collection.getDB().dropDatabase();
        loadTrade(collection, 1L, 0);
        loadTrade(collection, 2L, 0);
        loadTrade(collection, 3L, 0);
        loadTrade(collection, 4L, 0);
        loadTrade(collection, 5L, 5);
        loadTrade(collection, 6L, 0);
        loadTrade(collection, 7L, 0);
        loadTrade(collection, 8L, 2);
        loadTrade(collection, 9L, 0);
        loadTrade(collection, 10L, 0);

    }

    private BasicDBObject newTrade(Long tradeId, Integer version) {
        BasicDBObject basicDBObject = new BasicDBObject("tradeId", tradeId);
        basicDBObject.append("version", version);
        return basicDBObject;
    }

    private void loadTrade(DBCollection cache, Long tradeId, Integer version) {
        cache.insert(newTrade(tradeId, version));
    }

    private int countResult(DBCursor cursor) {
        int i = 0;
        while (cursor.hasNext()) {
            cursor.next();
            i++;
        }

        return i;
    }

    private void assertTradeVersion(DBObject object, Integer version) {
        Assert.assertEquals(object.get("version"), version);
    }

    @Test
    public void shouldGetFirstEntry() {
        DBCollection collection = getCollection();
        DBObject dbObject = collection.findOne();
        Assert.assertEquals(dbObject.toMap().get("tradeId"), 1L);
        Assert.assertEquals(dbObject.toMap().get("version"), 0);
    }

    private static DBCollection getCollection() {
        return mongoDBClient.getDB("test").getCollection("tradeCache");
    }

    @Test
    public void shouldGetAllEntry() {
        DBCollection collection = getCollection();
        Assert.assertEquals(countResult(collection.find()), 10);
    }

    @Test
    public void shouldGetTradeWithVersion5() {
        DBCollection collection = getCollection();
        BasicDBObject query = new BasicDBObject("version", 5);
        DBCursor cursor = collection.find(query);


        DBObject dbObject = cursor.next();
        assertTradeVersion(dbObject, 5);
    }

    @Test
    public void shouldQueryLessThanGreaterThan() {
        DBCollection collection = getCollection();
        DBObject query = QueryBuilder.start("tradeId").greaterThan(3L).lessThan(7l).get();
        Assert.assertEquals(countResult(collection.find(query)), 3);
    }


    @Test
    public void shouldQueryIn() {
        DBCollection collection = getCollection();
        BasicDBObject inQuery = new BasicDBObject();
        List<Long> list = new ArrayList<Long>();
        list.add(2L);
        list.add(4L);
        list.add(5L);
        inQuery.put("tradeId", new BasicDBObject("$in", list));
        Assert.assertEquals(countResult(collection.find(inQuery)), 3);
    }

    @Test
    public void shouldQueryLogicalAnd() {

        DBCollection collection = getCollection();
        BasicDBObject andQuery = new BasicDBObject();

        List<BasicDBObject> andConstraints = new ArrayList<BasicDBObject>();
        andConstraints.add(new BasicDBObject("tradeId", 8L));
        andConstraints.add(new BasicDBObject("version", 2));

        andQuery.put("$and", andConstraints);

        Assert.assertEquals(countResult(collection.find(andQuery)), 1);

    }


}