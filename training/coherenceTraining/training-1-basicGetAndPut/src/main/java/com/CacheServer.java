package com;


import com.tangosol.net.DefaultCacheServer;

public class CacheServer {

    private static final Object lock=new Object();

    public static void main(String[] args) throws Exception {

        DefaultCacheServer.startDaemon();

        synchronized (lock)   {
            lock.wait();
        }
    }
}
