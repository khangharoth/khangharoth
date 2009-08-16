package com.test.basicTest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.config.CoherenceServerManager;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class BasicCoherenceTest 
{
	private CoherenceServerManager coherenceServerManager=new CoherenceServerManager();

	@BeforeMethod
	public void beforeMethod(){
		System.setProperty( "tangosol.pof.config", "pof-config.xml" );
		System.setProperty( "tangosol.pof.enabled", "true" );
	}
	@Test
	public void basicTest(){
		
		coherenceServerManager.startCoherenceOuOfProcess( "cluster-with-extend-32000.xml" );
		
		System.setProperty( "tangosol.coherence.cacheconfig", "extend-connection-32000.xml" );
		NamedCache cache=CacheFactory.getCache( "LatestXYZ" );
		
		cache.put( "A", "B" );
		
		System.out.println(cache.get( "A" ));
	}
	
	@AfterMethod
	public void afterMethod(){
		coherenceServerManager.killOpenCoherenceProcesses();
	}
}
