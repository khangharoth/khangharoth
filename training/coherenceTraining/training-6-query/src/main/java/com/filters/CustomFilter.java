package com.filters;

import com.domain.Trade;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.util.Filter;

import java.io.IOException;

public class CustomFilter implements Filter,PortableObject {
    @Override
    public boolean evaluate(Object o) {
        Trade trade= (Trade) o;
        return trade.getInstrumentType().startsWith("C");
    }

    @Override
    public void readExternal(PofReader pofReader) throws IOException {
    }

    @Override
    public void writeExternal(PofWriter pofWriter) throws IOException {
    }
}
