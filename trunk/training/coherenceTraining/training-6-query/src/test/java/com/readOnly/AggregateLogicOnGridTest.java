package com.readOnly;

import com.domain.DataLoader;
import com.domain.Trade;
import com.domain.TradeKey;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.aggregator.LongMax;
import com.tangosol.util.filter.AlwaysFilter;
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

    @BeforeClass
    public void setUp() {
        memberGroup = ClusterMemberGroupUtils.newBuilder().
                setStorageEnabledCount(2).buildAndConfigureForStorageDisabledClient();

    }

    @Test
    public void shouldGetHighestTradeId() throws IOException {
        Map<TradeKey, Trade> trades = DataLoader.loadTradeData("trades.csv");
        NamedCache tradeCache = CacheFactory.getCache("TradeCache");
        tradeCache.putAll(trades);


        Object highestId = tradeCache.aggregate(AlwaysFilter.INSTANCE, new LongMax("getTradeKey.getTradeId"));

        Assert.assertEquals(highestId, 2L);


    }

    @AfterClass
    public void tearDown() {
        ClusterMemberGroupUtils.shutdownCacheFactoryThenClusterMemberGroups(memberGroup);

    }
}
