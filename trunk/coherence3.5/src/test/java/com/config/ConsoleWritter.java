package com.config;

import java.io.*;


public class ConsoleWritter {
	private boolean gobbleStream;
	private final Process process;

	public ConsoleWritter(Process process) throws FileNotFoundException {
		this.process = process;
	}

	public void gobble() {
		System.out.println("Attaching logging to process "+process);
		gobbleStream = true;
		new Gobbler(process.getInputStream(), System.out).start();
		new Gobbler(process.getErrorStream(), System.err).start();
	}

	public void stop() {
		gobbleStream = false;
	}

	private class Gobbler extends Thread {
		InputStream streamToGobble;
		PrintStream streamToSendTo;

		Gobbler(InputStream streamToGobble, PrintStream streamToSendTo) {
			this.streamToGobble = streamToGobble;
			this.streamToSendTo = streamToSendTo;
			setDaemon(true);
		}

		public void run() {
			while (gobbleStream) {
				BufferedReader stream = new BufferedReader(new InputStreamReader(streamToGobble));
				try {
					String s = stream.readLine();
					if (s != null) {
						s = "Proc"+process.hashCode() + " " + s;
						streamToSendTo.println(s);
					}
					if (!stillAlive()) {
						System.out.println("Process exited ("+process.toString()+")");
						break;
					}
				} catch (IOException e) {
					e.printStackTrace();
					gobbleStream = false;
				}
			}
		}

		private boolean stillAlive() {
			try {
				process.exitValue();
			} catch (Exception e) {
				return true;
			}
			return false;
		}
	}

}
