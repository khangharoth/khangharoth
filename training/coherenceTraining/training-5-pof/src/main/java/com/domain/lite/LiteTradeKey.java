package com.domain.lite;

import com.tangosol.io.ExternalizableLite;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LiteTradeKey implements ExternalizableLite {
    private Integer tradeId;

    private Integer version;

    public LiteTradeKey() {
    }

    public LiteTradeKey(Integer tradeId, Integer version) {
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
    public void readExternal(DataInput in) throws IOException {
            tradeId= in.readInt();
            tradeId= in.readInt();
    }

    @Override
    public void writeExternal(DataOutput out) throws IOException {
             out.writeInt(tradeId);
             out.writeInt(version);
    }
}
