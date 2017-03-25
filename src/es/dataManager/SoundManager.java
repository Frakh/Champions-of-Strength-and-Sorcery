package es.dataManager;

import es.exception.MediaNonTrouveException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//UNSTABLE CLASS DO NOT USE
public class SoundManager {

	// La map de fichier sonores
	private static Map<String, Media> soundMap;

	// Joueur de media en cours
	private static MediaPlayer mediaPlayer = null;

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
		MediaPlayer mPlayer = new MediaPlayer(strack);
		if (mediaPlayer!=null) {
			stopMedia();
		}
		mediaPlayer = mPlayer;
		mediaPlayer.play();
	}

	/**
	 * Permet de mettre en pause le média.
	 * Utiliser resumeMedia pour le remettre en route
	 */
	public static void pauseMedia() {
		mediaPlayer.pause();
	}

	/**
	 * Permet de continuer a jouer la musique
	 */
	public static void resumeMedia() {
		mediaPlayer.play();
	}

	/**
	 * Methode permettant de stopper la musique
	 */
	public static void stopMedia() {
		mediaPlayer.stop();
		mediaPlayer.dispose();
		mediaPlayer = null;
	}

}
