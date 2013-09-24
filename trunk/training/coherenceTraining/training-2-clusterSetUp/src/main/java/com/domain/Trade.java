package com.domain;

import java.io.Serializable;

public class Trade implements Serializable {

    private TradeKey tradeKey;

    private String portfolio;

    private String instrumentType;

    private String counterParty;

    public Trade(TradeKey tradeKey) {
        this.tradeKey = tradeKey;
    }

    public TradeKey getTradeKey() {
        return tradeKey;
    }

    public String getCounterParty() {
        return counterParty;
    }

    public void setCounterParty(String counterParty) {
        this.counterParty = counterParty;
    }

    public String getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
