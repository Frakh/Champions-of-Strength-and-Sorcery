package test;

import es.dataManager.SoundManager;
import org.junit.Test;

public class TestMP3 {

	@Test
	public void testMusique() throws InterruptedException {
		SoundManager.playMedia("./assets/musics/main_jingle.mp3");
		Thread.sleep(4000);
		SoundManager.pauseMedia();
	}
}
