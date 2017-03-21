package es.eventlogger;

import java.io.*;

/**
 * Created on 27/02/2017.
 */
public class LogSys {

	public static final String logfilename = "./log.txt";

	private static BufferedWriter bw = null;

	static {
		if (!init()) {
			throw new RuntimeException("LogSys initialization failed");
		}
	}

	private static boolean init() {
		File f = new File(logfilename);
		if (!f.exists()) {
			try {
				if (!f.createNewFile())
					return false;
			} catch (IOException e) {

				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		try {
			FileOutputStream fos = new FileOutputStream(f);
			bw = new BufferedWriter(new OutputStreamWriter(fos));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
//			return false;
		}
		return true;
	}

	public static boolean log(String str) {
		try {
			bw.write(str);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean log(Throwable t) {
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

	public static boolean log(Throwable t, String str) {
		boolean b1 = log(t);
		boolean b2 = log(str);
		return b1 && b2;
	}

	public static boolean log(String str, Throwable t) {
		boolean b2 = log(str);
		boolean b1 = log(t);
		return b1 && b2;
	}

	public static void exit() {
		try {
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
