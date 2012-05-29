package com.basic;


import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicCoherence {

    @Test
    public void shouldDoBasicPutAndGet() {
        NamedCache cache = CacheFactory.getCache("Trade");

        cache.put("1", "a");

        Assert.assertEquals(cache.get("1"), "a");
    }

}
