package com.domain.lite;

import com.tangosol.io.ExternalizableLite;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LiteTrade implements ExternalizableLite {
    private LiteTradeKey tradeKey;

    private String portfolio;

    private String instrumentType;

    private String counterParty;

    public LiteTrade(LiteTradeKey tradeKey) {
        this.tradeKey = tradeKey;
    }

    public LiteTradeKey getTradeKey() {
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
    public void readExternal(DataInput dataInput) throws IOException {

    }

    @Override
    public void writeExternal(DataOutput dataOutput) throws IOException {

    }
}
