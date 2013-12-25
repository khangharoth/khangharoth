package com;

import com.domain.DataLoader;
import com.domain.Trade;
import com.domain.TradeKey;
import com.tangosol.coherence.component.util.safeService.safeCacheService.SafeDistributedCacheService;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.extractor.PofExtractor;
import com.tangosol.util.extractor.ReflectionExtractor;
import com.tangosol.util.filter.EqualsFilter;
import org.littlegrid.ClusterMemberGroup;
import org.littlegrid.ClusterMemberGroupUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class DataGridQueryTest {

    private ClusterMemberGroup memberGroup = null;

    @BeforeClass
    public void setUp() {
        memberGroup = ClusterMemberGroupUtils.newBuilder().
                setStorageEnabledCount(2).buildAndConfigureForStorageDisabledClient();

    }

    @Test
    public void shouldQueryUsingEqualsFilter() throws IOException {
        Map<TradeKey, Trade> trades = DataLoader.loadTradeData("trades.csv");
        NamedCache tradeCache = CacheFactory.getCache("TradeCache");
        tradeCache.putAll(trades);

        ReflectionExtractor extractor = new ReflectionExtractor("getInstrumentType");
        EqualsFilter filter = new EqualsFilter(extractor, "IRS");


        Set result = tradeCache.keySet(filter);

        Assert.assertEquals(result.size(), 1);


    }
}
