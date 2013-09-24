package com.domain;

import java.io.Serializable;

public class TradeKey implements Serializable{

    private Integer tradeId;

    private Integer version;

    public TradeKey(Integer tradeId, Integer version) {
        this.tradeId = tradeId;
        this.version = version;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public Integer getVersion() {
        return version;
    }
}
