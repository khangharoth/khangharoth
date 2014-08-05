package com.domain.xmlBean;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.run.xml.XmlBean;

import java.io.IOException;

public class XmlBeanTradeKey extends XmlBean {
    private Integer tradeId;

    private Integer version;

    public XmlBeanTradeKey() {
    }

    public XmlBeanTradeKey(Integer tradeId, Integer version) {
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
