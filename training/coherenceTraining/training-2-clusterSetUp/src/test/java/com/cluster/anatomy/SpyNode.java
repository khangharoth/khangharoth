package com.cluster.anatomy;

import com.domain.Trade;
import com.domain.TradeKey;
import com.tangosol.coherence.component.util.SafeService;
import com.tangosol.coherence.component.util.safeService.safeCacheService.SafeDistributedCacheService;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.Cluster;
import com.tangosol.net.Member;
import com.tangosol.net.NamedCache;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

public class SpyNode {

    private static Cluster cluster;
    private Trade trade;

    @BeforeClass
    public void setUp() {
        System.setProperty("tangosol.coherence.distributed.localstorage", "false");

        trade = trade();
        NamedCache tradeCache = CacheFactory.getCache("TradeCache");
        tradeCache.put(trade.getTradeKey(), trade);

        cluster = CacheFactory.getCluster();
    }

    @Test
    public void clusterServiceInfo() {
        SafeService service = (SafeService) cluster.getService("Cluster");

        System.out.println(service);
    }

    @Test
    public void distributedServiceInfo() {
        SafeDistributedCacheService service = (SafeDistributedCacheService) cluster.getService("DistributedCache");

        Set<Member> members = service.getOwnershipEnabledMembers();

        for (Member member : members) {
            System.out.println(member);
        }

    }

    @Test
    public void shouldGetKeyOwner() {
        SafeDistributedCacheService service = (SafeDistributedCacheService) cluster.getService("DistributedCache");

        Member owner = service.getKeyOwner(trade.getTradeKey());

        System.out.println(owner);
    }

    private Trade trade() {
        Trade trade = new Trade(new TradeKey(100, 1));
        trade.setCounterParty("TheBank");
        trade.setInstrumentType("IRS");
        trade.setPortfolio("My-BANK-SWAP");
        return trade;
    }


}
