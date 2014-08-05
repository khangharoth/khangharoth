package com;

import com.domain.Portfolio;
import com.domain.Trade;
import com.domain.TradeKey;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class CacheTopologyTest {


    private HazelcastInstance instance;

    @Test
    public void shouldHaveTradeCacheAsPartitioned() throws IOException {
        Map tradeCache = instance.getMap("TradeCache");
        Map<TradeKey, Trade> trades = DataLoader.loadTradeData("trades.csv");
        tradeCache.putAll(trades);

//        Assert.assertEquals(SafeDistributedCacheService.class, tradeCache.getCacheService().getClass());
    }

    @Test
    public void shouldHavePortfolioCacheAsPartitioned() throws IOException {
        Map portfolioCache = instance.getMap("PortfolioCache");
        Map<Integer, Portfolio> portfolios = DataLoader.loadPortfolioData("portfolio.csv");
        portfolioCache.putAll(portfolios);

//        Assert.assertEquals(SafeDistributedCacheService.class, portfolioCache.getCacheService().getClass());
    }
    @Test
    public void shouldHaveStaticCacheAsReplicated() throws IOException {
        Map portfolioCache = instance.getMap("StaticCache");
        Map<Integer, Portfolio> portfolios = DataLoader.loadPortfolioData("portfolio.csv");
        portfolioCache.putAll(portfolios);
    }

    @BeforeClass
    public void setUp() {
        Config cfg = new Config();
        instance = Hazelcast.newHazelcastInstance(cfg);
        Map mapCustomers = instance.getMap("TradeCache");

    }

}
