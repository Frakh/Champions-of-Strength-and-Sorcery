package test;

import org.junit.Test;

import static game.combat.Combat.HAUTEURTERRAIN;

public class CombatTest {

	/**
	 * Ceci est le test qui affiche les index ou seront placé les unités, pour les nombres de 1 à 7
	 */
	@Test
	public void positionnementFuncTest() {

		for (int j = 1; j <= 7; ++j) {
			int nbTroupes1 = j;
			System.out.println("------ TEST AVEC : " + j + " ------");
			double step = ((double) HAUTEURTERRAIN) / ((double) nbTroupes1 + 1);
			for (int i = 1; i <= nbTroupes1; ++i) {
				System.out.println((int) (i * step));
			}
		}
	}
}
