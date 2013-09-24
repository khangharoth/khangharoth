package com;

import com.tangosol.net.DefaultCacheServer;

public class CacheServer {
    private static final Object lock = new Object();

    public static void main(String[] args) throws Exception {
        CacheServer.startAStorageEnabledNode();
    }

    public static void startAStorageEnabledNode() throws InterruptedException {
        System.setProperty("tangosol.coherence.distributed.localstorage", "true");
        startServer();
    }

    private static void startServer() throws InterruptedException {
        DefaultCacheServer.startDaemon();

        synchronized (lock) {
            lock.wait();
        }
    }

    public static void startAStorageDisabledNode() throws InterruptedException {
        System.setProperty("tangosol.coherence.distributed.localstorage", "false");
        startServer();
    }
}
