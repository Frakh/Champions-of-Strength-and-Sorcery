package test;

import game.combat.Combat;
import utilitaire.Vector2i;

import org.junit.Test;
import game.Heros;
import game.Unite;
import game.carte.CaseDejaPriseException;

import static game.combat.Combat.HAUTEURTERRAIN;

public class CombatTest {

	/**
	 * Ceci est le test qui affiche les index ou seront placÃ© les unitÃ©s, pour les nombres de 1 Ã  7
	 * @throws Throwable 
	 */
	@Test
	public void UniteTest() throws Throwable{  //teste la création de héros, d'unités, et l'initialisation du combat
		Heros Heros = new Heros();
		Heros.addTroupe(new Unite(11, 30), 1);
		Heros Mechaaaaant = new Heros();
		Mechaaaaant.addTroupe(new Unite(11, 50), 0);
		Mechaaaaant.addTroupe(new Unite(11, 75), 4);
		Combat c = new Combat(Heros, Mechaaaaant);
		c.initialiserCombat();
		System.out.println(c.toString());
		System.out.println(c.toStringPathFinding(new Vector2i(0,6)));
		System.out.println("\n\nDeplacement du gobelin 0 de 3 cases en avant:\n");
		assert (c.pathfinding(0,6)[3][6]);
		c.teleporterTroupe(new Vector2i(0,6),new Vector2i(3,6));
		System.out.println(c.toString());
		System.out.println(c.toStringPathFinding(new Vector2i(3,6)));
		Vector2i degats=c.terrainCombat[3][6].getUnit().getDegatsEffectues(c.terrainCombat[19][4].getUnit());
		System.out.println("le gobelin 0 attaque le gobelin 7");
		System.out.println("il devrait faire entre "+degats.getX()+" et "+degats.getY()+ " degats. Un gobelin a "+ c.terrainCombat[3][6].getUnit().getPvMax()+" PV.");
		c.terrainCombat[3][6].getUnit().combattre(c.terrainCombat[19][4].getUnit());
		System.out.println(c.toString());
	}  
	
}
