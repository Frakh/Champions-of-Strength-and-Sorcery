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
	public void UniteTest() throws Throwable{
		Heros Heros = new Heros();
		Heros.addTroupe(new Unite( 11, 30), 0);
		Heros Mechaaaaant = new Heros();
		Heros.addTroupe(new Unite(21, 50), 0);
		Combat c = new Combat(Heros, Mechaaaaant);
		c.initialiserCombat();
	}
	
	
	public void Combat(){
		
	}
	
}
