package es.dataManager;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoundManager {

	private static Map<String, Media> soundMap;
	private static Map<String, MediaPlayer> playerMap;

	static {
		soundMap = new ConcurrentHashMap<>();
	}

	private static Media getMedia(String url) {
		if (url==null) {
			throw new IllegalArgumentException("L'URL est nulle");
		}
		if (!soundMap.containsKey(url)) {
			if (!mediaLoad(url))
				throw new RuntimeException("File not found ! : " + url);
		}
		Media m = soundMap.get(url);
		if (m==null)
			throw new RuntimeException("M EST NULL");
		return m;
	}

	private static boolean mediaLoad(String url) {
		Media tempMedia = new Media(Paths.get(url).toUri().toString());
		soundMap.put(url, tempMedia);
		return true;
	}

	public static void playMedia(String url) {
		Media strack = getMedia(url);
		MediaPlayer mediaPlayer = new MediaPlayer(strack);
		mediaPlayer.play();
		playerMap.put(url, mediaPlayer);
	}

	public static void stopMedia(String url) {
		playerMap.remove(url);
	}

}
