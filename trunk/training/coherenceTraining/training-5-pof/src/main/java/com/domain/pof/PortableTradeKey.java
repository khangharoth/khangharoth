package com.domain.pof;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

import java.io.IOException;

public class PortableTradeKey implements PortableObject {
    private Integer tradeId;

    private Integer version;

    public PortableTradeKey() {
    }

    public PortableTradeKey(Integer tradeId, Integer version) {
        this.tradeId = tradeId;
        this.version = version;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public Integer getVersion() {
        return version;
    }

    @Override
    public void readExternal(PofReader pofReader) throws IOException {
        this.tradeId = pofReader.readInt(0);
        this.version = pofReader.readInt(1);

    }

    @Override
    public void writeExternal(PofWriter pofWriter) throws IOException {
        pofWriter.writeInt(0,tradeId);
        pofWriter.writeInt(1,version);
    }
}
