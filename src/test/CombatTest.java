package test;

import org.junit.Test;

import static game.combat.Combat.HAUTEURTERRAIN;

public class CombatTest {

	@Test
	public void positionnementFuncTest() {

		for (int j = 1; j <= 7; ++j) {
			int nbTroupes1 = j;
			double step = ((double) HAUTEURTERRAIN) / ((double) nbTroupes1 + 1);
			for (int i = 1; i <= nbTroupes1; ++i) {
				System.out.println((int) (i * step));
			}
			System.out.println("------");
		}
	}
}
