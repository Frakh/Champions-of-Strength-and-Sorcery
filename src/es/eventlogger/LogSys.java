package es.eventlogger;

import java.io.*;

/**
 * Created on 27/02/2017.
 */
public class LogSys {

	// Nom du fichier dans lequel seront stocké les logs
	public static final String logfilename = "./log.txt";

	// Le buffered writer qui ecrira le log
	private static BufferedWriter bw = null;

	static {
		if (!init()) {
			throw new RuntimeException("LogSys initialization failed");
		}
	}

	/**
	 * Fonction d'initialisation de la classe
	 * @return un boolean pour savoir si oui ou non es.eventlogger.LogSys est initialisé
	 */
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

	/**
	 * Permet d'ajouter une ligne dans le fichier de log
	 * @param str : la chaine de charactère
	 * @return un boolean, true si ça s'est déroulé correctement, false sinon
	 */
	public static boolean log(String str) {
		try {
			bw.write(str+'\n');
			bw.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Permet de log un lancement de throwable
	 * @param t : le throwable
	 * @return true si le log s'est correctement déroulé
	 */
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

	/**
	 * Permet de stocker un lancement de throwable puis un message dans le log
	 * @param t le throwable lancé
	 * @param str le message a écrire
	 * @return true si les opérations d'écriture se sont bien passés
	 */
	public static boolean log(Throwable t, String str) {
		boolean b1 = log(t);
		boolean b2 = log(str);
		return b1 && b2;
	}

	/**
	 * Permet de stocker un lancement de throwable puis un message dans le fichier de log
	 * @param str : la chaine de charactère
	 * @param t : le throwable lancé
	 * @return true si les opérations se sont bien déroulés
	 */
	public static boolean log(String str, Throwable t) {
		boolean b2 = log(str);
		boolean b1 = log(t);
		return b1 && b2;
	}

	/**
	 * Permet de flush et de fermer le stream de sortie
	 */
	public static void exit() {
		try {
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
