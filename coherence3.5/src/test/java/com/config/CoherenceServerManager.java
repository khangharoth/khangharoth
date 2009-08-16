/**
 * 
 */
package com.config;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
/**
 * @author Elope
 */
public class CoherenceServerManager
{
	protected static final String			HPC_MULTICAST_ADDRESS_1	= "239.255.12.30";
	protected static final String			CLUSTER_NAME			= "com.test.demo";
	private static final String				CLUSTER_PORT			= "1234";
	private static Collection<Process>		processes				= new ArrayList<Process>();
	private static Collection<ConsoleWritter>	gobblers				= new ArrayList<ConsoleWritter>();
	
	public Process startCoherenceOuOfProcess(String pathToConfig) {
        return startCoherenceOuOfProcess(pathToConfig, "", "");
    }

    public Process startCoherenceOuOfProcess(String pathToConfig, String classPathAdditions, String propertiesAdditions){
    	return startProcess(getCommand(pathToConfig, classPathAdditions, propertiesAdditions));
    }

	private String getCommand(String pathToConfig, String classPathAdditions, String propertiesAdditions) {
		return "java" +
                " -Dtangosol.coherence.ttl=" + 0 + " " +
                "-Dtangosol.coherence.clusterport=" + CLUSTER_PORT + " " +
                "-Dtangosol.coherence.localport=14000" + " " +
                "-Dtangosol.coherence.log.level=" + 9 + " " +
                "-Dtangosol.coherence.cluster=" + CLUSTER_NAME + " " +
//                "-Dtangosol.coherence.clusteraddress=" + HPC_MULTICAST_ADDRESS_1 + " " +
                "-Dtangosol.coherence.cacheconfig=" + pathToConfig +" "+
                "-Dtangosol.pof.config=" + "pof-config.xml" 
                + " " +propertiesAdditions + " " +
                "-cp " + System.getProperty("java.class.path") + 
                classPathAdditions + " " +
                "com.tangosol.net.DefaultCacheServer";
	}

    public Process startProcess(String command)
	{
		System.out.println( "Running " + command );
		try
		{
			Process process = Runtime.getRuntime().exec( command );
			startLogging( process );
			checkForSuccesfulStart( command, process );
			Thread.sleep( 8000 );
			checkForSuccesfulStart( command, process );

			System.out.println( "Coherence was started" );
			processes.add( process );
			return process;
		}
		catch( Exception e )
		{
			System.out.println(e.getMessage());
          e.printStackTrace();
		}
        return null;
	}

    private void startLogging(Process process) throws FileNotFoundException {
        ConsoleWritter gobbler = new ConsoleWritter(process);
        gobbler.gobble();
    }

    protected void checkForSuccesfulStart(String command, Process process) throws InterruptedException {
        try {
            process.exitValue();
            throw new RuntimeException("Cache server is not running " + command);
        } catch (Exception hopedFor) {
        }
    }

	public Collection<Process> runningProcesses() {
		return processes;
	}

	public void killOpenCoherenceProcesses() {
        for (Process process : processes) {
            System.out.println("killing process " + process);
            process.destroy();
            while (true) {
                try {
                    process.exitValue();
                    break;
                } catch (IllegalThreadStateException mustStillBeRunning) {
                }
            }
        }
        processes.clear();
        for (ConsoleWritter gobbler : gobblers) {
            System.out.println("killing gobbler " + gobbler);
            gobbler.stop();
        }
        gobblers.clear();
    }
}
