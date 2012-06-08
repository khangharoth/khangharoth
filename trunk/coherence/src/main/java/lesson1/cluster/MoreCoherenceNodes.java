package lesson1.cluster;


import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class MoreCoherenceNodes {


    public static void main(String[] args) throws Exception {
        NamedCache cache = CacheFactory.getCache("Trade");

        while (true) {
            Thread.sleep(10000);
        }
    }
}
