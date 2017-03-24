package test;

import es.sortie.FrameManager;
import es.sortie.composants.CombatLayer;
import game.Heros;
import game.Unite;
import game.carte.CaseDejaPriseException;
import game.combat.Combat;
import org.junit.Test;
import utilitaire.Position;

import java.io.FileNotFoundException;

public class CombatTest {

	/**
	 * Ceci est le test qui permet d essayer le systeme de combat au tour par tour
	 * @throws Throwable 
	 */
	@Test
	public void UniteTest() throws Throwable{  //teste la creation de heros, d'unites, et l'initialisation du combat
		System.out.println("Attention: cette interface n'est là que pour tester le systeme de combat au tour par tour, une interface plus agreable et des graphismes seront rajoutes ulterieurement.");
		System.out.println("Legende:");
		System.out.println("");
		System.out.println("Premier schema: (représente la carte)");
		System.out.println("o: case libre");
		System.out.println("x: case avec un obstacle (infranchissable)");
		System.out.println("chiffre: emplacement de l'unite correspondante au chiffre");
		System.out.println("Deuxieme schema: (represente le pathfinding)");
		System.out.println("o: case ou l'unite dont c'est le tour peut aller");
		System.out.println("x: case ou l'unite dont c'est le tour est, ou ne peut pas acceder.");
		Heros Heros = new Heros();
		Heros.addTroupe(new Unite(11, 30), 1);
		Heros Mechaaaaaaaaant = new Heros();
		Mechaaaaaaaaant.addTroupe(new Unite(11, 50), 0);
		Mechaaaaaaaaant.addTroupe(new Unite(11, 75), 4);
		Combat c = new Combat(Heros, Mechaaaaaaaaant, 10);
		c.initialiserCombat();
		c.fight();
		
		
		
		
		/*System.out.println(c.toString());
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
		System.out.println(c.toString());*/
		
		//MapTest testDispAndMove
	}  


	@Test
	public void testScreenCombat() throws CaseDejaPriseException, FileNotFoundException, InterruptedException {

		System.out.println("TestJFRAME");
		Heros heros = new Heros();
		heros.addTroupe(new Unite(11, 30), 1);
		Heros Mechaaaaaaaaant = new Heros();
		Mechaaaaaaaaant.addTroupe(new Unite(11, 50), 0);
		Mechaaaaaaaaant.addTroupe(new Unite(11, 75), 4);
		Combat c = new Combat(heros, Mechaaaaaaaaant, 10);
		c.initialiserCombat();

		FrameManager fm = new FrameManager();
		fm.setDimensions(1280,720);
		fm.setSpriteDim(64,55);
		fm.setPositionToFollow(new Position(0,0));

		CombatLayer cl = new CombatLayer(fm,c);

		fm.init(cl);

		while (true) {
			fm.repaint();
			Thread.sleep(5000);
		}

	}
}
