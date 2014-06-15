package com.adhoc;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.Invocable;
import com.tangosol.net.InvocationService;
import com.tangosol.net.NamedCache;
import org.littlegrid.ClusterMemberGroup;
import org.littlegrid.ClusterMemberGroupUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class InvocableTester {

    private ClusterMemberGroup memberGroup = null;
    private NamedCache tradeCache;

    @BeforeClass
    public void setUp() throws IOException {
        memberGroup = ClusterMemberGroupUtils.newBuilder().
                setStorageEnabledCount(2).buildAndConfigureForStorageDisabledClient();

        tradeCache = CacheFactory.getCache("TradeCache");

    }

    @Test
    public void shouldBeAbleToRunGroovyAdHocCodeOnGrid() {
        String script = "(1..10).sum()";
        Invocable invocable = new GroovyScriptInvocable(script);
        InvocationService service = (InvocationService)
                CacheFactory.getService("InvocationService");
        Object result = service.query(invocable, null);
        System.out.println("");
    }

    @AfterClass
    public void tearDown() {
        ClusterMemberGroupUtils.shutdownCacheFactoryThenClusterMemberGroups(memberGroup);

    }
}
