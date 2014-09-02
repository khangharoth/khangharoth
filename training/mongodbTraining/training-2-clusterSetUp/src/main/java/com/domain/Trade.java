package com.domain;

import com.mongodb.BasicDBObject;

/**
 * Created by raj on 02/09/14.
 */
public class Trade extends BasicDBObject {

    public Trade(TradeKey tradeKey, String counterParty, String instrumentType, String portfolio) {
        super();
        append("tradeId", tradeKey.getId());
        append("version", tradeKey.getVersion());
        append("counterParty", counterParty);
        append("instrumentType", instrumentType);
        append("portfolio", portfolio);
    }

    public static Trade newTrade(TradeKey tradeKey, String counterParty, String instrumentType, String portfolio){
        return new Trade(tradeKey,counterParty,instrumentType,portfolio);
    }
}
