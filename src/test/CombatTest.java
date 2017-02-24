package test;

import game.combat.Combat;
import org.junit.Test;
import game.Heros;
import game.Unite;
import game.carte.CaseDejaPriseException;

import static game.combat.Combat.HAUTEURTERRAIN;

public class CombatTest {

	/**
	 * Ceci est le test qui affiche les index ou seront placé les unités, pour les nombres de 1 à 7
	 * @throws Throwable 
	 */
	@Test
	public void UniteTest() throws Throwable{  //teste la cr�ation de h�ros, d'unit�s, et l'initialisation du combat
		Heros Heros = new Heros();
		Heros.addTroupe(new Unite(11, 30), 1);
		Heros Mechaaaaant = new Heros();
		Mechaaaaant.addTroupe(new Unite(11, 50), 0);
		Mechaaaaant.addTroupe(new Unite(11, 75), 4);
		Combat c = new Combat(Heros, Mechaaaaant);
		c.initialiserCombat();
		System.out.println(c.toString());
	}
	
}
