package com;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class CacheServer {

    private static final Object lock=new Object();

    public static void main(String[] args) throws InterruptedException {
        Config cfg = new Config();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);


       synchronized (lock){
             lock.wait();
       }
    }
}
