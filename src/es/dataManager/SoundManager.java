package es.dataManager;

import es.exception.MediaNonTrouveException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Cette classe est mal conçue, a revoir
public class SoundManager {

	// La map de fichier sonores
	private static Map<String, Media> soundMap;

	static {
		soundMap = new ConcurrentHashMap<>();
	}

	/**
	 * Permet d'obtenir un média
	 * @param url l'url du media
	 * @return le média
	 * @throws MediaNonTrouveException dans le cas ou le média n'est pas trouvé
	 * @throws IllegalArgumentException dans le cas ou l'argument est null
	 */
	private static Media getMedia(String url) throws MediaNonTrouveException, IllegalArgumentException {
		if (url==null) {
			throw new IllegalArgumentException("L'URL est nulle");
		}
		if (!soundMap.containsKey(url)) {
			if (!mediaLoad(url))
				throw new MediaNonTrouveException("File not found ! : " + url);
		}
		Media m = soundMap.get(url);
		if (m==null)
			throw new MediaNonTrouveException("M EST NULL");
		return m;
	}

	/**
	 * Methode de chargement de media
	 * @param url : l'url du fichier
	 * @return : true si le fichier est chargé
	 */
	private static boolean mediaLoad(String url) {
		Media tempMedia = new Media(Paths.get(url).toUri().toString());
		soundMap.put(url, tempMedia);
		return true;
	}

	/**
	 * Methode permettant de jouer le media
	 * @param url l'url a jouer
	 */
	public static void playMedia(String url) {
		Media strack = getMedia(url);
		MediaPlayer mediaPlayer = new MediaPlayer(strack);
		mediaPlayer.play();
	}

	/**
	 * Methode permettant de stopper la musique
	 * @param url : l'url
	 */
	public static void stopMedia(String url) {

	}

}
