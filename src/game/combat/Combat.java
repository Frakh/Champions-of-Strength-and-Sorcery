package game.combat;

import game.Heros;
import game.IUnite;

public class Combat {
	
	public static final int HAUTEURTERRAIN = 13;
	public static final int LARGEURTERRAIN = 20;
	public CaseCombat[][] terrainCombat;
	Heros armee1;
	Heros armee2;
	IUnite[] ArmeeGauche;
	IUnite[] ArmeeDroite;

	
	public Combat (Heros h1, Heros h2){
		terrainCombat = new CaseCombat[HAUTEURTERRAIN][LARGEURTERRAIN];
		armee1=h1;
		armee2=h2;
		ArmeeGauche = new IUnite[7];
		ArmeeDroite = new IUnite[7];
	}
	
	public void initialiserCombat(){
		int nbTroupes1=0;
		int nbTroupes2=0;
		for (int i=0; i<armee1.armee.length; i++){
			if (armee1.armee[i]!=null){
				nbTroupes1++;
				ArmeeGauche[i]=armee1.armee[i];
				boostStats (ArmeeGauche[i], armee1.getAttaque(), armee1.getDefense(), armee1.getChance(), armee1.getMoral());
			}
			
		}
		for (int i=0; i<armee2.armee.length; i++){
			if (armee2.armee[i]!=null){
				nbTroupes1++;
				ArmeeDroite[i]=armee1.armee[i];
				boostStats (ArmeeDroite[i], armee2.getAttaque(), armee2.getDefense(), armee2.getChance(), armee2.getMoral());
			}
			
		}
		
		placerTroupes(nbTroupes1, nbTroupes2);
		

	}

	 private void boostStats(IUnite unit, int attaque, int defense, int moral, int chance){
		 unit.setAttaque(unit.getAttaque()+attaque);
		 unit.setDefense(unit.getDefense()+defense);
		 unit.setMoral(unit.getMoral()+moral);
		 unit.setChance(unit.getChance()+chance);
	 }
	
	 private void placerTroupes(int nbTroupes1, int nbTroupes2){
		 if (nbTroupes1==1){
			 terrainCombat[6][0].setUnit(ArmeeGauche[0]);//.......i.......
	 }
		 if (nbTroupes1==2){
			 terrainCombat[4][0].setUnit(ArmeeGauche[0]);
			 terrainCombat[8][0].setUnit(ArmeeGauche[1]);//....i...i....
			 
	 }
		 if (nbTroupes1==3){
			 terrainCombat[3][0].setUnit(ArmeeGauche[0]);//...i..i..i...
			 terrainCombat[6][0].setUnit(ArmeeGauche[1]);
			 terrainCombat[9][0].setUnit(ArmeeGauche[2]);
			 
	 }
		 if (nbTroupes1==4){
			 terrainCombat[1][0].setUnit(ArmeeGauche[0]);//.i..i...i..i.
			 terrainCombat[4][0].setUnit(ArmeeGauche[1]);
			 terrainCombat[8][0].setUnit(ArmeeGauche[2]);
			 terrainCombat[11][0].setUnit(ArmeeGauche[3]);
			 
	 }
		 if (nbTroupes1==5){
			 terrainCombat[1][0].setUnit(ArmeeGauche[0]);//.i..i.i.i..i.
			 terrainCombat[4][0].setUnit(ArmeeGauche[1]);
			 terrainCombat[6][0].setUnit(ArmeeGauche[2]);
			 terrainCombat[8][0].setUnit(ArmeeGauche[3]);
			 terrainCombat[11][0].setUnit(ArmeeGauche[4]);
			 
	 }
		 if (nbTroupes1==6){
			 terrainCombat[1][0].setUnit(ArmeeGauche[0]);//.i.i.i.i.i.i.
			 terrainCombat[3][0].setUnit(ArmeeGauche[1]);
			 terrainCombat[5][0].setUnit(ArmeeGauche[2]);
			 terrainCombat[7][0].setUnit(ArmeeGauche[3]);
			 terrainCombat[9][0].setUnit(ArmeeGauche[4]);
			 terrainCombat[11][0].setUnit(ArmeeGauche[5]);
	 }
		 if (nbTroupes1==7){
			 terrainCombat[0][0].setUnit(ArmeeGauche[0]);//i.i.i.i.i.i.i
			 terrainCombat[2][0].setUnit(ArmeeGauche[1]);
			 terrainCombat[4][0].setUnit(ArmeeGauche[2]);
			 terrainCombat[6][0].setUnit(ArmeeGauche[3]);
			 terrainCombat[8][0].setUnit(ArmeeGauche[4]);
			 terrainCombat[10][0].setUnit(ArmeeGauche[5]);
			 terrainCombat[12][0].setUnit(ArmeeGauche[6]);
	 }
		 if (nbTroupes2==1){
			 terrainCombat[6][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);//.......i.......
	 }
		 if (nbTroupes2==2){
			 terrainCombat[4][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			 terrainCombat[8][LARGEURTERRAIN-1].setUnit(ArmeeDroite[1]);//....i...i....
			 
	 }
		 if (nbTroupes2==3){
			 terrainCombat[3][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);//...i..i..i...
			 terrainCombat[6][LARGEURTERRAIN-1].setUnit(ArmeeDroite[1]);
			 terrainCombat[9][LARGEURTERRAIN-1].setUnit(ArmeeDroite[2]);
			 
	 }
		 if (nbTroupes2==4){
			 terrainCombat[1][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);//.i..i...i..i.
			 terrainCombat[4][LARGEURTERRAIN-1].setUnit(ArmeeDroite[1]);
			 terrainCombat[8][LARGEURTERRAIN-1].setUnit(ArmeeDroite[2]);
			 terrainCombat[11][LARGEURTERRAIN-1].setUnit(ArmeeDroite[3]);
			 
	 }
		 if (nbTroupes2==5){
			 terrainCombat[1][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);//.i..i.i.i..i.
			 terrainCombat[4][LARGEURTERRAIN-1].setUnit(ArmeeDroite[1]);
			 terrainCombat[6][LARGEURTERRAIN-1].setUnit(ArmeeDroite[2]);
			 terrainCombat[8][LARGEURTERRAIN-1].setUnit(ArmeeDroite[3]);
			 terrainCombat[11][LARGEURTERRAIN-1].setUnit(ArmeeDroite[4]);
			 
	 }
		 if (nbTroupes2==6){
			 terrainCombat[1][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);//.i.i.i.i.i.i.
			 terrainCombat[3][LARGEURTERRAIN-1].setUnit(ArmeeDroite[1]);
			 terrainCombat[5][LARGEURTERRAIN-1].setUnit(ArmeeDroite[2]);
			 terrainCombat[7][LARGEURTERRAIN-1].setUnit(ArmeeDroite[3]);
			 terrainCombat[9][LARGEURTERRAIN-1].setUnit(ArmeeDroite[4]);
			 terrainCombat[11][LARGEURTERRAIN-1].setUnit(ArmeeDroite[5]);
	 }
		 if (nbTroupes2==7){
			 terrainCombat[0][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);//i.i.i.i.i.i.i
			 terrainCombat[2][LARGEURTERRAIN-1].setUnit(ArmeeDroite[1]);
			 terrainCombat[4][LARGEURTERRAIN-1].setUnit(ArmeeDroite[2]);
			 terrainCombat[6][LARGEURTERRAIN-1].setUnit(ArmeeDroite[3]);
			 terrainCombat[8][LARGEURTERRAIN-1].setUnit(ArmeeDroite[4]);
			 terrainCombat[10][LARGEURTERRAIN-1].setUnit(ArmeeDroite[5]);
			 terrainCombat[12][LARGEURTERRAIN-1].setUnit(ArmeeDroite[6]);
	 }

}	 
	 
}
