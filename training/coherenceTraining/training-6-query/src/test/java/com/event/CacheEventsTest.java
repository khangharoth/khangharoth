package com.event;

import com.domain.DataLoader;
import com.domain.Trade;
import com.domain.TradeKey;
import com.events.TradeCacheListener;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.QueryHelper;
import org.littlegrid.ClusterMemberGroup;
import org.littlegrid.ClusterMemberGroupUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheEventsTest {
    private ClusterMemberGroup memberGroup = null;
    private NamedCache tradeCache;

    @BeforeClass
    public void setUp() throws IOException {
        memberGroup = ClusterMemberGroupUtils.newBuilder().
                setStorageEnabledCount(2).buildAndConfigureForStorageDisabledClient();

        tradeCache = CacheFactory.getCache("TradeCache");

    }

    @Test
    public void shouldListenToEvents() throws Exception {
        TradeCacheListener tradeCacheListener = new TradeCacheListener();
        tradeCache.addMapListener(tradeCacheListener);


        Map<TradeKey, Trade> trades = DataLoader.loadTradeData("trades.csv");
        tradeCache.putAll(trades);


    }


    @AfterClass
    public void tearDown() {
        ClusterMemberGroupUtils.shutdownCacheFactoryThenClusterMemberGroups(memberGroup);

    }
}
