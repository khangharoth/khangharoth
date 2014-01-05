package com.query;

import com.aggregator.NotionalAggregator;
import com.domain.DataLoader;
import com.domain.Trade;
import com.domain.TradeKey;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Filter;
import com.tangosol.util.aggregator.LongMax;
import com.tangosol.util.extractor.PofExtractor;
import com.tangosol.util.filter.AlwaysFilter;
import com.tangosol.util.filter.EqualsFilter;
import org.littlegrid.ClusterMemberGroup;
import org.littlegrid.ClusterMemberGroupUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class AggregateLogicOnGridTest {
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
    public void shouldGetHighestTradeId() throws IOException {
        Object highestId = tradeCache.aggregate(AlwaysFilter.INSTANCE, new LongMax("getTradeKey.getTradeId"));
        Assert.assertEquals(highestId, 3L);


    }

    @Test
    public void shouldReturnSumOfNotionalsForIRS() {
        PofExtractor extractor = new PofExtractor(String.class, 2);
        Filter filter = new EqualsFilter(extractor, "IRS");

        Object notionalSum = tradeCache.aggregate(filter, new NotionalAggregator());

        Assert.assertEquals(notionalSum, 1700L);
    }

    @AfterClass
    public void tearDown() {
        ClusterMemberGroupUtils.shutdownCacheFactoryThenClusterMemberGroups(memberGroup);

    }
}
