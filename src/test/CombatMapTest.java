package test;
import org.junit.Test;

import game.Heros;
import game.Unite;
import game.carte.elements.Ennemi;



public class CombatMapTest{
	
	@Test
	public void test() throws Throwable{
		Ennemi ennemi= new Ennemi(11,20);
		Heros heros = new Heros();
		heros.addTroupe(new Unite(11, 30), 1);
		ennemi.interagir(heros);
		
	}
	
}
