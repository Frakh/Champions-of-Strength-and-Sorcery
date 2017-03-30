package test;

import es.interfaces.IFileLoader;
import org.junit.Test;
import utilitaire.ConteneurGeneric;

import java.io.IOException;

public class FileLoaderTest {

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
			for (String[] dataz : datazs) {
				System.out.print(dataz[i]);
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
