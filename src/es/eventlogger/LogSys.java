package es.eventlogger;

import java.io.*;

/**
 * Created on 27/02/2017.
 */
public class LogSys {

	public static final String logfilename = "./log/log.txt";

	private static BufferedWriter bw = null;

	private static boolean init() {
		File f = new File(logfilename);
		if (!f.exists()) {
			return false;
		}
		try {
			FileOutputStream fos = new FileOutputStream(f);
			bw = new BufferedWriter(new OutputStreamWriter(fos));
		} catch (FileNotFoundException e) {
			return false;
		}
		return true;
	}

	public boolean log(String str) {
		try {
			bw.write(str);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean log(Throwable t) {
		String thrMsg = t.getMessage();
		StringBuilder builder = new StringBuilder(1024 + thrMsg.length());
		builder.append(t.getClass().getName()).append("  --- MESSAGE  :  ").append(thrMsg);
		StackTraceElement[] ste = t.getStackTrace();
		builder.append("   --- STACKTRACE  :  ");
		for (StackTraceElement s : ste) {
			builder.append(s.toString());
		}
		return log(builder.toString());
	}

	public boolean log(Throwable t, String str) {
		boolean b1 = log(t);
		boolean b2 = log(str);
		return b1 && b2;
	}

	public boolean log(String str, Throwable t) {
		boolean b2 = log(str);
		boolean b1 = log(t);
		return b1 && b2;
	}
}
