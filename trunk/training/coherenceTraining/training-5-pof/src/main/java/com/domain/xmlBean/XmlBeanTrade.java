package com.domain.xmlBean;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.run.xml.XmlBean;

import java.io.IOException;

public class XmlBeanTrade extends XmlBean {
    private XmlBeanTradeKey tradeKey;

    private String portfolio;

    private String instrumentType;

    private String counterParty;

    public XmlBeanTrade(XmlBeanTradeKey tradeKey) {
        this.tradeKey = tradeKey;
    }

    public XmlBeanTradeKey getTradeKey() {
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
}
