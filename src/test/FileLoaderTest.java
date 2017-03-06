package test;

import es.interfaces.IFileLoader;
import game.carte.Case;
import org.junit.Test;

import java.io.IOException;

public class FileLoaderTest {

	@Test
	public void testMapLoad() throws IOException {

		Case[][] testTab = IFileLoader.loadCarteFile("./ressources/map/test.gameres");
		for (int j = 0; j < testTab[0].length; ++j) {
			// Remplacé par un foreach par l'ide
			for (Case[] aTestTab : testTab) {
				System.out.print(aTestTab[j].toString() + " - ");
			}
			System.out.print('\n');
		}

	}

}
