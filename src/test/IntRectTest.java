package test;

import org.junit.Test;
import utilitaire.IntRect;
import utilitaire.Vector2i;

import static org.junit.Assert.assertEquals;

public class IntRectTest {

	@Test
	public void contains() throws Exception {

		IntRect ir = new IntRect(20,20,50,10);
		assertEquals(false, ir.contains(new Vector2i(20,20)));
		assertEquals(false, ir.contains(new Vector2i(35,35)));
		assertEquals(true, ir.contains(new Vector2i(65, 25)));
	}

}