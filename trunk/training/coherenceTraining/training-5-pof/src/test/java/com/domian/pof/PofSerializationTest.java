package com.domian.pof;

import com.domain.pof.PortableTrade;
import com.domain.pof.PortableTradeKey;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import org.littlegrid.ClusterMemberGroup;
import org.littlegrid.ClusterMemberGroupUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PofSerializationTest {
    private ClusterMemberGroup memberGroup = null;

    @BeforeClass
    public void setUp() {
        memberGroup = ClusterMemberGroupUtils.newBuilder().
                setStorageEnabledCount(2).buildAndConfigureForStorageDisabledClient();

    }

    @Test
    public void playWithPortableTrade(){
        NamedCache tradeCache = CacheFactory.getCache("TradeCache");

        PortableTradeKey key=new PortableTradeKey(100,1);
        PortableTrade trade=new PortableTrade(key);

        tradeCache.put(key,trade);
    }


    @AfterClass
    public void tearDown() {
        ClusterMemberGroupUtils.shutdownCacheFactoryThenClusterMemberGroups(memberGroup);

    }
}
