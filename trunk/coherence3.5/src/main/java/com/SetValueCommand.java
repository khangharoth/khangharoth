package com;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.oracle.coherence.patterns.command.Command;
import com.oracle.coherence.patterns.command.ExecutionEnvironment;
import com.tangosol.io.ExternalizableLite;
import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

@SuppressWarnings({ "serial", "unchecked" })
public class SetValueCommand<T> implements Command,
		ExternalizableLite, PortableObject {



	private final Timer timer = new Timer();
	private final int milliSec = 1;

	public SetValueCommand() {
	}

	
	
	public void execute(ExecutionEnvironment executionEnvironment) {
		start();
	}

	private void start() {
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				NamedCache clusterTimeCache = CacheFactory.getCache("ClusterTime_Update");
				clusterTimeCache.put("ASDF", clusterTimeCache.getCacheService().getCluster().getTimeMillis());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},0,milliSec);
	}

	public void readExternal(DataInput in) throws IOException {
//		this.value = (T) ExternalizableHelper.readObject(in);
	}

	public void writeExternal(DataOutput out) throws IOException {
//		ExternalizableHelper.writeObject(out, value);
	}

	public void readExternal(PofReader reader) throws IOException {
//		this.value = (T) reader.readObject(0);
	}

	public void writeExternal(PofWriter writer) throws IOException {
//		writer.writeObject(0, value);
	}
}
