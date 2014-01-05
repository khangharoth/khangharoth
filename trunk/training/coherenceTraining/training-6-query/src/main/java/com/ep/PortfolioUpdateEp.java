package com.ep;

import com.domain.Trade;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.util.InvocableMap;
import com.tangosol.util.processor.AbstractProcessor;

import java.io.IOException;


public class PortfolioUpdateEp extends AbstractProcessor implements PortableObject {
    private String newValue;

    public PortfolioUpdateEp() {
    }

    public PortfolioUpdateEp(String newValue) {
        this.newValue = newValue;
    }

    @Override
    public Object process(InvocableMap.Entry entry) {
        Trade trade= (Trade) entry.getValue();

        trade.setInstrumentType(newValue);
        entry.setValue(trade) ;
        return trade;
    }

    @Override
    public void readExternal(PofReader pofReader) throws IOException {
        newValue=pofReader.readString(0);

    }

    @Override
    public void writeExternal(PofWriter pofWriter) throws IOException {
          pofWriter.writeString(0,newValue);
    }
}
