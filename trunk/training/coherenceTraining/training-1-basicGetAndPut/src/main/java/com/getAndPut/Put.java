package com.getAndPut;


import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class Put {

    public static void main(String[] args) {

        NamedCache namedCache = CacheFactory.getCache("TradeCache");

        namedCache.put("A", "1");
    }
}
