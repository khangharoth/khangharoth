package com.getAndPut;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class Get {
    public static void main(String[] args) {

        NamedCache namedCache = CacheFactory.getCache("TradeCache");


        System.out.println(namedCache.get("A"));
    }
}
