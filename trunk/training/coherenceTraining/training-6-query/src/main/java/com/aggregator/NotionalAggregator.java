package com.aggregator;


import com.domain.Trade;
import com.tangosol.io.pof.PofContext;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.io.pof.reflect.PofValue;
import com.tangosol.io.pof.reflect.PofValueParser;
import com.tangosol.util.BinaryEntry;
import com.tangosol.util.InvocableMap;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

public class NotionalAggregator implements InvocableMap.ParallelAwareAggregator, PortableObject {

    public NotionalAggregator() {
    }

    @Override
    public Object aggregate(Set entriesOnOneNode) {
        Long result = 0L;

        for (Object entry : entriesOnOneNode) {
            BinaryEntry binaryEntry = (BinaryEntry) entry;

            PofContext context = (PofContext) binaryEntry.getSerializer();

            PofValue pofValue = PofValueParser.parse(binaryEntry.getBinaryValue(), context);

            Trade trade = (Trade) pofValue.getValue();

            result = result + trade.getNotional();


        }

        return result;
    }

    @Override
    public Object aggregateResults(Collection nodeResults) {
        Long finalResult = 0L;
        for (Object nodeResult : nodeResults) {
            finalResult = finalResult + (Long) nodeResult;
        }

        return finalResult;
    }


    @Override
    public InvocableMap.EntryAggregator getParallelAggregator() {
        return this;
    }

    @Override
    public void readExternal(PofReader pofReader) throws IOException {

    }

    @Override
    public void writeExternal(PofWriter pofWriter) throws IOException {
    }
}
