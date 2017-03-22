package test;

import es.interfaces.IFileLoader;
import game.carte.Case;
import org.junit.Test;
import utilitaire.ConteneurGeneric;

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

	@Test
	public void readFile() throws IOException {
		System.out.println(IFileLoader.fullyReadFile("./ressources/map/nexttest.gameres"));
	}

	@Test
	public void testGetFileMapLineNumb() throws IOException {
		String fc = IFileLoader.fullyReadFile("./ressources/map/nexttest.gameres");
		System.out.println(IFileLoader.getFileMapLines(fc)[1]);
	}

	@Test
	public void testDecompMap() throws IOException {
		String[][] datazs = IFileLoader.getFileMapMatrix(IFileLoader.getFileMapLines(IFileLoader.fullyReadFile("./ressources/map/nexttest.gameres")));
		for (int i = 0; i < datazs[0].length; ++i) {
			for (int j = 0; j < datazs.length; ++j) {
				System.out.print(datazs[j][i]);
			}
			System.out.print('\n');
		}
	}

	@Test
	public void conteneurGenericTest() {
		ConteneurGeneric cg = new ConteneurGeneric();
		cg.add_set_Attribut("test", 16);
		System.out.println(cg.getAttribut("test"));
	}
}
