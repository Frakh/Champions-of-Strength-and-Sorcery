package test;

import es.entree.Souris;
import es.netclasses.Evenement;
import es.netclasses.NetQueueEvenement;
import es.netclasses.NetworkInterface;
import es.netclasses.evenements.CombatEvenement;
import es.netclasses.evenements.JeuEvenement;
import es.sortie.FrameManager;
import es.sortie.composants.CombatLayer;
import game.Heros;
import game.Unite;
import game.carte.CaseDejaPriseException;
import game.combat.Combat;
import org.junit.Test;
import utilitaire.Position;
import utilitaire.Vector2i;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CombatTest {

	/**
	 * Ceci est le test qui permet d essayer le systeme de combat au tour par tour
	 * @throws IOException 
	 * @throws Throwable
	 */
 /* @Test
  public void UniteTest() throws Throwable{  //teste la creation de heros, d'unites, et l'initialisation du combat
 	System.out.println("Attention: cette interface n'est l� que pour tester le systeme de combat au tour par tour, une interface plus agreable et des graphismes seront rajoutes ulterieurement.");
 	System.out.println("Legende:");
 	System.out.println("");
 	System.out.println("Premier schema: (repr�sente la carte)");
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
	*/



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
	//}

/*
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
 	Souris souris = Souris.getInstance(fm);
 	fm.addMouseListener(souris);
 	fm.setDimensions(1280,720);
 	fm.setSpriteDim(32,32);
 	fm.setPositionToFollow(new Position(0,0));

 	CombatLayer cl = new CombatLayer(fm,c, souris);

 	fm.init(cl);
 	fm.setFrameRateLimit(60);

 	fm.setSpriteDim(64,55);
 	Thread.sleep(600000);

  }
*/

	

  @Test
  public void testScreenCombat() throws CaseDejaPriseException, InterruptedException, IOException {
	FrameManager fm = new FrameManager();
	Souris souris = Souris.getInstance(fm);
	 
	System.out.println("Demarrage du truc reseau");
	NetworkInterface.bind("127.0.0.1", 9001);
	System.out.println("Attente");
	Evenement evenement = null;	
	Thread.sleep(5000);
	evenement = NetQueueEvenement.getEvenement(Evenement.COMBAT_EVENT);
	Combat c = null;
	if (evenement==null) {
		Heros heros = new Heros();
		heros.addTroupe(new Unite(12, 50), 1);
		Heros Mechaaaaaaaaant = new Heros();
		Mechaaaaaaaaant.addTroupe(new Unite(11, 30), 0);
		Mechaaaaaaaaant.addTroupe(new Unite(13, 5), 4);
		c = new Combat(heros, Mechaaaaaaaaant, 10);
		c.initialiserCombat();
		NetworkInterface.send(new JeuEvenement(CombatEvenement.DEBUT_COMBAT, c.toStringMap()));
	 	fm.addMouseListener(souris);
	 	fm.setDimensions(1280,720);
	 	fm.setSpriteDim(32,32);
	 	fm.setPositionToFollow(new Position(0,0));
	 	CombatLayer cl = new CombatLayer(fm, c, souris);
	 	fm.init(cl);
	 	fm.setFrameRateLimit(60);
	 	fm.setSpriteDim(64,55);
		c.fight(true, -1, -1, souris);
		
	}
	else{
		c = new Combat(((CombatEvenement) evenement).getMaj());
	 	fm.addMouseListener(souris);
	 	fm.setDimensions(1280,720);
	 	fm.setSpriteDim(32,32);
	 	fm.setPositionToFollow(new Position(0,0));
	 	CombatLayer cl = new CombatLayer(fm, c, souris);
	 	fm.init(cl);
	 	fm.setFrameRateLimit(60);
	 	fm.setSpriteDim(64,55);
		c.fight(false, -1, -1, souris);
	}
  }
	/*
  @Test
	public void testRejoindrePartie() throws IOException, InterruptedException {
		NetworkInterface.bind("172.19.47.220",9001);
		while(true){
			JeuEvenement jev= (JeuEvenement) NetQueueEvenement.getEvenement(Evenement.GAME_ID);
			if (jev!=null)
				System.out.println(jev);
			Thread.sleep(16);
		}
		
		
		/*NetworkInterface.send(new JeuEvenement(JeuEvenement.GAME_LIST,""));
		Evenement ev=null;
		Thread.sleep(413);
		while(ev==null){
			ev= NetQueueEvenement.getEvenement(Evenement.GAME_ID);
		}
		JeuEvenement jev=(JeuEvenement) ev;
		System.out.println(jev.getDetail());
		NetworkInterface.send(new JeuEvenement(JeuEvenement.JOIN_GAME,"0"));
	}
*/
  
  
/*
	@Test
	public void testDecod() throws CaseDejaPriseException, FileNotFoundException, InterruptedException {

		System.out.println("TestCode");
		Heros heros = new Heros();
		heros.addTroupe(new Unite(11, 30), 1);
		Heros Mechaaaaaaaaant = new Heros();

		Mechaaaaaaaaant.addTroupe(new Unite(11, 50), 0);
		Mechaaaaaaaaant.addTroupe(new Unite(11, 75), 4);
		Combat c = new Combat(heros, Mechaaaaaaaaant, 10);
		c.initialiserCombat();
		c.teleporterTroupe(new Vector2i(0,6), new Vector2i(2,6));
		String aaa = c.toStringMap();
		System.out.println(aaa);
		System.out.println("");
		Combat b = new Combat(heros, Mechaaaaaaaaant, 10);
		b.initialiserCombat();
		b.majMap(aaa);
		System.out.println(b.toString());

	}
*/
}

