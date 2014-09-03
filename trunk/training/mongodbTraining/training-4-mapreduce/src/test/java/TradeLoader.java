import com.mongodb.BasicDBObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raj on 03/09/14.
 */
public class TradeLoader {

    private static final List<BasicDBObject> trades = new ArrayList<BasicDBObject>();

    private static BasicDBObject newTrade(Long tradeId, Integer version, String instrumentType, String sourceBook,
                                          Long amount, String counterParty) {
        BasicDBObject basicDBObject = new BasicDBObject("tradeId", tradeId);
        basicDBObject.append("version", version);
        basicDBObject.append("instrumentType", instrumentType);
        basicDBObject.append("sourceBook", sourceBook);
        basicDBObject.append("amount", amount);
        basicDBObject.append("counterParty", counterParty);
        return basicDBObject;
    }

    private static void addTrade(Long tradeId, Integer version, String instrumentType, String sourceBook,
                                 Long amount, String counterParty) {
        trades.add(newTrade(tradeId, version, instrumentType, sourceBook, amount, counterParty));
    }

    private static void load() {
        addTrade(1L, 1, "IRS", "SB-1", 11000L, "CP-1");
        addTrade(2L, 1, "IRS", "SB-1", 11000L, "CP-1");
        addTrade(3L, 1, "IRS", "SB-2", 11000L, "CP-1");
        addTrade(4L, 1, "IRS", "SB-1", 11000L, "CP-1");
        addTrade(5L, 1, "IRS", "SB-3", 11000L, "CP-1");
        addTrade(6L, 1, "IRS", "SB-3", 11000L, "CP-1");
        addTrade(7L, 1, "IRS", "SB-1", 11000L, "CP-1");
        addTrade(8L, 1, "IRS", "SB-3", 11000L, "CP-1");
        addTrade(9L, 1, "IRS", "SB-2", 11000L, "CP-1");
        addTrade(10L, 1, "IRS", "SB-2", 11000L, "CP-1");
        addTrade(12L, 1, "IRS", "SB-2", 11000L, "CP-1");
        addTrade(13L, 1, "IRS", "SB-1", 11000L, "CP-1");
    }

    public static List<BasicDBObject> trades() {
        load();
        return trades;
    }

}
