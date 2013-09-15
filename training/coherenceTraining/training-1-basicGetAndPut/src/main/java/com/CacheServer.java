package com;


import com.tangosol.net.DefaultCacheServer;

public class CacheServer {

    public static void main(String[] args) throws Exception {

        DefaultCacheServer.startDaemon();

        while (true) {
            Thread.sleep(1000 * 10);
        }
    }
}
