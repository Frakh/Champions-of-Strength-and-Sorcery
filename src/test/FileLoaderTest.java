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
			for (int i =0; i < testTab.length; ++i) {
				System.out.print(testTab[i][j].toString() + " - ");
			}
			System.out.print('\n');
		}

	}

}
