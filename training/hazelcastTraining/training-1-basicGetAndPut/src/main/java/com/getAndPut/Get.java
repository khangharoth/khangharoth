package com.getAndPut;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;



import java.util.Map;

public class Get {
    public static void main(String[] args) {
        Config cfg = new Config();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
        Map mapCustomers = instance.getMap("TradeCache");


        System.out.println("Customer with key A: " + mapCustomers.get("A"));
    }
}
