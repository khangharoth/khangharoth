package lesson1.cluster;


import com.tangosol.coherence.component.util.safeService.safeCacheService.SafeDistributedCacheService;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.Cluster;

import java.util.Enumeration;

public class SpyCoherenceNode {

    /*
   Cluster
   Management
   DistributedCache


    */

    public static void main(String[] args) throws Exception {

        Cluster cluster = CacheFactory.getCluster();


        SafeDistributedCacheService service = (SafeDistributedCacheService) cluster.getService("DistributedCache");

        Enumeration serviceNames = cluster.getServiceNames();

        while (serviceNames.hasMoreElements()) {
            System.out.println(serviceNames.nextElement());
        }
    }
}
