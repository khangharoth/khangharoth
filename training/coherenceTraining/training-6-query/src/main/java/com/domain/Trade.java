package com.domain;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

import java.io.IOException;

public class Trade implements PortableObject {
    private TradeKey tradeKey;

    private String portfolio;

    private String instrumentType;

    private String counterParty;

    public Trade() {
    }

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
    public void readExternal(PofReader pofReader) throws IOException {
        this.tradeKey = (TradeKey) pofReader.readObject(0);
        this.portfolio = pofReader.readString(1);
        this.instrumentType = pofReader.readString(2);
        this.counterParty = pofReader.readString(3);
    }

    @Override
    public void writeExternal(PofWriter pofWriter) throws IOException {
        pofWriter.writeObject(0, tradeKey);
        pofWriter.writeString(1,portfolio);
        pofWriter.writeString(2,instrumentType);
        pofWriter.writeString(3,counterParty);
    }
}
