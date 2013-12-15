package com;

import com.domain.Portfolio;
import com.domain.Trade;
import com.domain.TradeKey;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import org.littlegrid.ClusterMemberGroupUtils;

import java.io.IOException;
import java.util.Map;

public class Topology {

    public static void main(String[] args) throws Exception {
        ClusterMemberGroupUtils.newBuilder().
                setStorageEnabledCount(3).buildAndConfigureForStorageDisabledClient();


        NamedCache tradeCache = CacheFactory.getCache("TradeCache");
        Map<TradeKey, Trade> trades = DataLoader.loadTradeData("trades.csv");
        tradeCache.putAll(trades);

        NamedCache portfolioCache = CacheFactory.getCache("StaticCache");
        Map<Integer, Portfolio> portfolios = DataLoader.loadPortfolioData("portfolio.csv");
        portfolioCache.putAll(portfolios);


        while(true){
            Thread.sleep(10000);
        }
    }
}
