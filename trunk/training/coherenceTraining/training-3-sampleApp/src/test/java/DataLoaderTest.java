import com.domain.Portfolio;
import com.domain.Trade;
import com.domain.TradeKey;
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

public class DataLoaderTest {

    private NamedCache tradeCache;
    private NamedCache portfolioCache;

    private ClusterMemberGroup memberGroup = null;

    @BeforeClass
    public void setUp() {
        memberGroup = ClusterMemberGroupUtils.newBuilder().
                setStorageEnabledCount(2).buildAndConfigureForStorageDisabledClient();

        tradeCache = CacheFactory.getCache("TradeCache");
        portfolioCache = CacheFactory.getCache("PortfolioCache");

    }


    @Test
    public void shouldLoadTrades() throws IOException {
        Map<TradeKey, Trade> trades = DataLoader.loadTradeData("trades.csv");
        tradeCache.putAll(trades);

        Assert.assertEquals(trades.size(), tradeCache.keySet().size());

    }

    @Test
    public void shouldLoadPortfolios() throws IOException {
        Map<Integer, Portfolio> portfolioMap = DataLoader.loadPortfolioData("portfolio.csv");
        portfolioCache.putAll(portfolioMap);

        Assert.assertEquals(portfolioMap.size(), portfolioCache.keySet().size());

    }

    @AfterClass
    public void tearDown() {
        ClusterMemberGroupUtils.shutdownCacheFactoryThenClusterMemberGroups(memberGroup);

    }


}
