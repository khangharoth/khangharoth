package com.query;

import com.domain.DataLoader;
import com.domain.Trade;
import com.domain.TradeKey;
import com.filters.CustomFilter;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Filter;
import com.tangosol.util.extractor.PofExtractor;
import com.tangosol.util.extractor.ReflectionExtractor;
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

public class GridQueryTest {

    private ClusterMemberGroup memberGroup = null;

    @BeforeClass
    public void setUp() {
        memberGroup = ClusterMemberGroupUtils.newBuilder().
                setStorageEnabledCount(2).buildAndConfigureForStorageDisabledClient();

    }

    @Test
    public void shouldQueryUsingCustomFilter() throws IOException {
        Map<TradeKey, Trade> trades = DataLoader.loadTradeData("trades.csv");
        NamedCache tradeCache = CacheFactory.getCache("TradeCache");
        tradeCache.putAll(trades);

        Filter filter = new CustomFilter();


        Set<TradeKey> result = tradeCache.keySet(filter);

        Assert.assertEquals(result.size(), 1);

        Trade tradeReturned = (Trade) tradeCache.get(result.iterator().next());
        Assert.assertEquals(tradeReturned.getInstrumentType(), "CDS");


    }


    @Test
    public void shouldQueryUsingEqualsFilter() throws IOException {
        Map<TradeKey, Trade> trades = DataLoader.loadTradeData("trades.csv");
        NamedCache tradeCache = CacheFactory.getCache("TradeCache");
        tradeCache.putAll(trades);

        ReflectionExtractor extractor = new ReflectionExtractor("getInstrumentType");
        Filter filter = new EqualsFilter(extractor, "IRS");


        Set<TradeKey> result = tradeCache.keySet(filter);

        Assert.assertEquals(result.size(), 1);

        Trade tradeReturned = (Trade) tradeCache.get(result.iterator().next());
        Assert.assertEquals(tradeReturned.getInstrumentType(), "IRS");


    }

    @Test
    public void shouldQueryUsingEqualsFilterWithPof() throws IOException {
        Map<TradeKey, Trade> trades = DataLoader.loadTradeData("trades.csv");
        NamedCache tradeCache = CacheFactory.getCache("TradeCache");
        tradeCache.putAll(trades);

        PofExtractor extractor = new PofExtractor(String.class, 2);
        Filter filter = new EqualsFilter(extractor, "IRS");


        Set<TradeKey> result = tradeCache.keySet(filter);

        Assert.assertEquals(result.size(), 1);

        Trade tradeReturned = (Trade) tradeCache.get(result.iterator().next());
        Assert.assertEquals(tradeReturned.getInstrumentType(), "IRS");


    }


    @AfterClass
    public void tearDown() {
        ClusterMemberGroupUtils.shutdownCacheFactoryThenClusterMemberGroups(memberGroup);

    }
}
