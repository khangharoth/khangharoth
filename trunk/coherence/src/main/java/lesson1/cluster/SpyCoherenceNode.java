package lesson1.cluster;


import com.tangosol.coherence.component.util.safeService.safeCacheService.SafeDistributedCacheService;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.Cluster;
import com.tangosol.net.Member;
import com.tangosol.net.NamedCache;
import com.tangosol.net.partition.PartitionSet;

import java.util.Set;

public class SpyCoherenceNode {

    /*Cluster,Management,DistributedCache */

    public static void main(String[] args) throws Exception {

        Cluster cluster = CacheFactory.getCluster();
        NamedCache cache = CacheFactory.getCache("Trade");


        distributedServiceInfo(cluster);

    }

    private static void distributedServiceInfo(Cluster cluster) {
        SafeDistributedCacheService service = (SafeDistributedCacheService) cluster.getService("DistributedCache");
        memberInfo(service);
    }

    private static void memberInfo(SafeDistributedCacheService service) {
        Set<Member> members = service.getOwnershipEnabledMembers();
        for (Member member : members) {
            PartitionSet set = service.getOwnedPartitions(member);
            System.out.println(member + " having partitions " + set);
        }
    }
}
