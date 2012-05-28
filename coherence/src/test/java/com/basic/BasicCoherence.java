package com.basic;


import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class BasicCoherence {


    public static void main(String[] args) {
        NamedCache cache = CacheFactory.getCache("Trade");

        cache.put("1", "a");

        System.out.println(cache.get("1"));


    }
}
