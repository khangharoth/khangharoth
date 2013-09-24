package com;

import com.domain.Trade;
import com.domain.TradeKey;
import com.hazelcast.config.Config;
import com.hazelcast.core.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;

public class SpyNode {
    private Trade trade;
    private HazelcastInstance instance;

    @BeforeClass
    public void setUp() {
        trade = trade();
        Config cfg = new Config();
        instance = Hazelcast.newHazelcastInstance(cfg);

        Map<TradeKey, Trade> tradeCache = instance.getMap("TradeCache");
        tradeCache.put(trade.getTradeKey(), trade);

    }

    @Test
    public void shouldGetClusterMembers() {
        Set<Member> members = instance.getCluster().getMembers();
        for (Member member : members) {
            System.out.println(member);
        }

    }

    @Test
    public void shouldGetKeyPartition() {
        Partition partitionKeyBelongsTo = instance.getPartitionService().getPartition(trade.getTradeKey());

        System.out.println(partitionKeyBelongsTo.getPartitionId());
    }

    @Test
    public void shouldGetKeyOwner() {
        Partition partitionKeyBelongsTo = instance.getPartitionService().getPartition(trade.getTradeKey());

        System.out.println(partitionKeyBelongsTo.getOwner());
    }

    private Trade trade() {
        Trade trade = new Trade(new TradeKey(100, 1));
        trade.setCounterParty("TheBank");
        trade.setInstrumentType("IRS");
        trade.setPortfolio("My-BANK-SWAP");
        return trade;
    }

}
