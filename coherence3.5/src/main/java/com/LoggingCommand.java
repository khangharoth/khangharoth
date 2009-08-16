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
import com.tangosol.util.ExternalizableHelper;

@SuppressWarnings({"unchecked", "serial"})
public class LoggingCommand implements Command, ExternalizableLite, PortableObject {

	private String message;

	private final Timer timer = new Timer();
	/**
	 * <p>The Coherence {@link CacheFactory} logging sensitivity.</p>
	 */
	private int severity;
	
	
	public LoggingCommand() {
	}
	
	
	public LoggingCommand(String message, int severity) {
		this.message = message;
		this.severity = severity;
	}

	
	public void execute(ExecutionEnvironment executionEnvironment) {
		logTime();
	}
	private void logTime() {
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				NamedCache clusterTimeCache=CacheFactory.getCache("ClusterTime_Update");
				System.out.println("In LoggingCommand @@@@@" +clusterTimeCache.get("ASDF"));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},0,1);
	}
	
	public void readExternal(DataInput in) throws IOException {
		this.message = ExternalizableHelper.readSafeUTF(in);
		this.severity = ExternalizableHelper.readInt(in);
	}
	
	
	public void writeExternal(DataOutput out) throws IOException {
		ExternalizableHelper.writeSafeUTF(out, message);
		ExternalizableHelper.writeInt(out, severity);
	}
	
	
	public void readExternal(PofReader reader) throws IOException {
		this.message = reader.readString(0);
		this.severity = reader.readInt(1);
	}
	
	
	public void writeExternal(PofWriter writer) throws IOException {
		writer.writeString(0, message);
		writer.writeInt(1, severity);
	}
	
	
	public String toString() {
		return String.format("LoggingCommand{%s, message=%s, severity=%d}", super.toString(), message, severity);
	}
}
