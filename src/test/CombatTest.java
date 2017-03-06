package test;

import game.combat.Combat;
import utilitaire.Vector2i;

import org.junit.Test;
import game.Heros;
import game.Unite;

public class CombatTest {

	/**
	 * Ceci est le test qui affiche les index ou seront placé les unités, pour les nombres de 1 à 7
	 * @throws Throwable 
	 */
	@Test
	public void UniteTest() throws Throwable{  //teste la cr�ation de h�ros, d'unit�s, et l'initialisation du combat
		Heros Heros = new Heros();
		Heros.addTroupe(new Unite(11, 30), 1);
		Heros Mechaaaaaaaaant = new Heros();
		Mechaaaaaaaaant.addTroupe(new Unite(11, 50), 0);
		Mechaaaaaaaaant.addTroupe(new Unite(11, 75), 4);
		Combat c = new Combat(Heros, Mechaaaaaaaaant, 10);
		c.initialiserCombat();
		System.out.println(c.toString());
		System.out.println(c.toStringPathFinding(new Vector2i(0,6)));
		System.out.println("\n\nDeplacement du gobelin 0 de 3 cases en avant:\n");
		assert (c.pathfinding(0,6)[5][6]);
		c.teleporterTroupe(new Vector2i(0,6),new Vector2i(5,6));
		System.out.println(c.toString());
		System.out.println(c.toStringPathFinding(c.getCoordTroupes(0)));
		Vector2i degats=c.terrainCombat[c.getCoordTroupes(0).getX()][c.getCoordTroupes(0).getY()].getUnit().getDegatsEffectues(c.terrainCombat[c.getCoordTroupes(7).getX()][c.getCoordTroupes(7).getY()].getUnit());
		System.out.println("le gobelin 0 attaque le gobelin 7");
		System.out.println("il devrait faire entre "+degats.getX()+" et "+degats.getY()+ " degats. Un gobelin a "+ c.terrainCombat[c.getCoordTroupes(0).getX()][c.getCoordTroupes(0).getY()].getUnit().getPvMax()+" PV.");
		c.terrainCombat[c.getCoordTroupes(0).getX()][c.getCoordTroupes(0).getY()].getUnit().combattre(c.terrainCombat[c.getCoordTroupes(7).getX()][c.getCoordTroupes(7).getY()].getUnit());
		System.out.println(c.toString());
	}  
	
}
