package com.update;

import com.domain.DataLoader;
import com.domain.Trade;
import com.domain.TradeKey;
import com.ep.PortfolioUpdateEp;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Filter;
import com.tangosol.util.extractor.PofExtractor;
import com.tangosol.util.filter.EqualsFilter;
import org.littlegrid.ClusterMemberGroup;
import org.littlegrid.ClusterMemberGroupUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class EntryProcessorTest {

    private ClusterMemberGroup memberGroup = null;
    private NamedCache tradeCache;

    @BeforeClass
    public void setUp() throws IOException {
        memberGroup = ClusterMemberGroupUtils.newBuilder().
                setStorageEnabledCount(2).buildAndConfigureForStorageDisabledClient();

        Map<TradeKey, Trade> trades = DataLoader.loadTradeData("trades.csv");
        tradeCache = CacheFactory.getCache("TradeCache");
        tradeCache.putAll(trades);

    }

    @Test
    public void shouldUpdateNormallyToAGrid() {
        PofExtractor extractor = new PofExtractor(String.class, 2);
        Filter filter = new EqualsFilter(extractor, "IRS");

        Set<TradeKey> keys = tradeCache.keySet(filter);


        for (TradeKey key : keys) {

            if (tradeCache.lock(key, 5000)) {
                try {
                    Trade trade = (Trade) tradeCache.get(key);
                    trade.setInstrumentType("IRS-1");

                    tradeCache.put(trade.getTradeKey(), trade);
                } finally {
                    tradeCache.unlock(key);
                }

            } else {
                System.out.println(" not able to take look on key " + key);
            }
        }

        Filter newFilter = new EqualsFilter(extractor, "IRS-1");

        Assert.assertEquals(tradeCache.keySet(newFilter).size(), 2);

    }

    @Test
    public void shouldUpdateWithEp(){
        PofExtractor extractor = new PofExtractor(String.class, 2);
        Filter filter = new EqualsFilter(extractor, "IRS");

        PortfolioUpdateEp ep=new PortfolioUpdateEp("IRS-1");

        tradeCache.invokeAll(filter,ep);

        Filter newFilter = new EqualsFilter(extractor, "IRS-1");

        Assert.assertEquals(tradeCache.keySet(newFilter).size(), 2);


    }

    @AfterClass
    public void tearDown() {
        ClusterMemberGroupUtils.shutdownCacheFactoryThenClusterMemberGroups(memberGroup);

    }
}
