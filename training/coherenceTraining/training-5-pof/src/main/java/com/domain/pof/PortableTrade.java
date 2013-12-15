package com.domain.pof;

import com.domain.TradeKey;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

import java.io.IOException;

public class PortableTrade implements PortableObject {
    private PortableTradeKey tradeKey;

    private String portfolio;

    private String instrumentType;

    private String counterParty;

    public PortableTrade(PortableTradeKey tradeKey) {
        this.tradeKey = tradeKey;
    }

    public PortableTradeKey getTradeKey() {
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
                this.tradeKey= (PortableTradeKey) pofReader.readObject(0);
    }

    @Override
    public void writeExternal(PofWriter pofWriter) throws IOException {
              pofWriter.writeObject(0,tradeKey);
    }
}
