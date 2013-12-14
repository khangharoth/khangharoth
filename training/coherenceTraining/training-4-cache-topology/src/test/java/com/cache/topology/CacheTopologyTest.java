package test.java.com.cache.topology;

import com.DataLoader;
import com.domain.Portfolio;
import com.domain.Trade;
import com.domain.TradeKey;
import com.tangosol.coherence.component.util.safeService.safeCacheService.SafeDistributedCacheService;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import org.littlegrid.ClusterMemberGroup;
import org.littlegrid.ClusterMemberGroupUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class CacheTopologyTest {

    private ClusterMemberGroup memberGroup = null;

    @BeforeClass
    public void setUp() {
        memberGroup = ClusterMemberGroupUtils.newBuilder().
                setStorageEnabledCount(2).buildAndConfigureForStorageDisabledClient();

//
//        portfolioCache = CacheFactory.getCache("PortfolioCache");

    }

    @Test
    public void shouldHaveTradeCacheAsPartitioned() throws IOException {
        NamedCache tradeCache = CacheFactory.getCache("TradeCache");
        Map<TradeKey, Trade> trades = DataLoader.loadTradeData("trades.csv");
        tradeCache.putAll(trades);

        Assert.assertEquals(SafeDistributedCacheService.class, tradeCache.getCacheService().getClass());
    }

    @Test
    public void shouldHavePortfolioCacheAsPartitioned() throws IOException {
        NamedCache portfolioCache = CacheFactory.getCache("PortfolioCache");
        Map<Integer, Portfolio> portfolios = DataLoader.loadPortfolioData("portfolio.csv");
        portfolioCache.putAll(portfolios);

        Assert.assertEquals(SafeDistributedCacheService.class, portfolioCache.getCacheService().getClass());
    }

    @AfterClass
    public void tearDown() {
        ClusterMemberGroupUtils.shutdownCacheFactoryThenClusterMemberGroups(memberGroup);

    }
}
