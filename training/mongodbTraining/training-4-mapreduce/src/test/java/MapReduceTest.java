import com.MongoDBClient;
import com.mongodb.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by raj on 03/09/14.
 */
public class MapReduceTest {

    private MongoClient mongoDBClient = MongoDBClient.getConnection();

    @BeforeClass
    public void init() {
        DBCollection collection = mongoDBClient.getDB("test").getCollection("tradeCache");
        //clean db
        collection.getDB().dropDatabase();
        for (BasicDBObject basicDBObject : TradeLoader.trades()) {
            collection.insert(basicDBObject);
        }

    }


    private String map() {
        return new String("function() { emit(this.sourceBook, this.amount); };");
    }

    private String reduce() {
        return new String("function(sourceBook, amount) { return Array.sum(amount); };");
    }

    @Test
    public void runMapReduceOnCollectionSumAmountBySourceBook() {

        DBCollection collection = mongoDBClient.getDB("test").getCollection("tradeCache");
        MapReduceCommand cmd = new MapReduceCommand(collection, map(), reduce(), null, MapReduceCommand.OutputType.INLINE, null);
        MapReduceOutput out = collection.mapReduce(cmd);

        for (DBObject o : out.results()) {
            System.out.println(o.toString());

        }
    }

}
