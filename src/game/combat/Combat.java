package game.combat;

import game.Heros;
import game.IUnite;
import utilitaire.IPosition;

public class Combat {

	public static final int HAUTEURTERRAIN = 13;
	public static final int LARGEURTERRAIN = 20;
	public CaseCombat[][] terrainCombat;// ATTENTION, [LARGEUR][HAUTEUR]
	Heros armee1;
	Heros armee2;
	IUnite[] ArmeeGauche;
	IUnite[] ArmeeDroite;
	IPosition[] coordTroupes;


	public Combat(Heros h1, Heros h2) {
		terrainCombat = new CaseCombat[LARGEURTERRAIN][HAUTEURTERRAIN];
		armee1 = h1;
		armee2 = h2;
		ArmeeGauche = new IUnite[7];
		ArmeeDroite = new IUnite[7];
		coordTroupes= new IPosition[14];
	}

	public void initialiserCombat() {
		int nbTroupes1 = 0;
		int nbTroupes2 = 0;
		for (int i = 0; i < armee1.armee.length; i++) {
			if (armee1.armee[i] != null) {
				nbTroupes1++;
				ArmeeGauche[i] = armee1.armee[i];
				armee1.armee[i].setArmeeGauche(true);
			}
		}
		for (int i = 0; i < armee2.armee.length; i++) {
			if (armee2.armee[i] != null) {
				nbTroupes1++;
				ArmeeDroite[i] = armee1.armee[i];
				armee2.armee[i].setArmeeGauche(false);
			}
		}
		placerTroupes(nbTroupes1, nbTroupes2);
	}
	
	
	
	
	
	private void boostStats(IUnite unit, int attaque, int defense, int moral, int chance) { //augmente les stats de unit
		unit.setAttaque(unit.getAttaque() + attaque);
		unit.setDefense(unit.getDefense() + defense);
		unit.setMoral(unit.getMoral() + moral);
		unit.setChance(unit.getChance() + chance);
	}

	private void placerTroupes(int nbTroupes1, int nbTroupes2) { // place les troupes de départ, les met dans le repertoire des coordonnees et les initialise aux stats des héros
		switch (nbTroupes1){
			case 1:
				terrainCombat[6][0].setUnit(ArmeeGauche[0]);//------i------
				coordTroupes[0].set(6, 0);
				break;
			case 2:
				terrainCombat[4][0].setUnit(ArmeeGauche[0]);//----i---i----
				terrainCombat[8][0].setUnit(ArmeeGauche[0]);
				coordTroupes[0].set(4, 0);
				coordTroupes[1].set(8, 0);
				break;
			case 3:
				terrainCombat[2][0].setUnit(ArmeeGauche[0]);//--i---i---i--
				terrainCombat[6][0].setUnit(ArmeeGauche[0]);
				terrainCombat[10][0].setUnit(ArmeeGauche[0]);
				coordTroupes[0].set(2, 0);
				coordTroupes[1].set(6, 0);
				coordTroupes[2].set(10, 0);
				break;
			case 4:
				terrainCombat[1][0].setUnit(ArmeeGauche[0]);//-i--i---i--i-
				terrainCombat[4][0].setUnit(ArmeeGauche[0]);
				terrainCombat[8][0].setUnit(ArmeeGauche[0]);
				terrainCombat[11][0].setUnit(ArmeeGauche[0]);
				coordTroupes[0].set(1, 0);
				coordTroupes[1].set(4, 0);
				coordTroupes[2].set(8, 0);				
				coordTroupes[3].set(11, 0);

				break;
			case 5:
				terrainCombat[0][0].setUnit(ArmeeGauche[0]);//i--i--i--i--i
				terrainCombat[3][0].setUnit(ArmeeGauche[0]);
				terrainCombat[6][0].setUnit(ArmeeGauche[0]);
				terrainCombat[9][0].setUnit(ArmeeGauche[0]);
				terrainCombat[12][0].setUnit(ArmeeGauche[0]);
				coordTroupes[0].set(0, 0);
				coordTroupes[1].set(3, 0);
				coordTroupes[2].set(6, 0);				
				coordTroupes[3].set(9, 0);				
				coordTroupes[4].set(12, 0);
				break;
			case 6:
				terrainCombat[0][0].setUnit(ArmeeGauche[0]);//i-i--i-i--i-i
				terrainCombat[2][0].setUnit(ArmeeGauche[0]);
				terrainCombat[5][0].setUnit(ArmeeGauche[0]);
				terrainCombat[7][0].setUnit(ArmeeGauche[0]);
				terrainCombat[10][0].setUnit(ArmeeGauche[0]);
				terrainCombat[12][0].setUnit(ArmeeGauche[0]);
				coordTroupes[0].set(0, 0);
				coordTroupes[1].set(2, 0);
				coordTroupes[2].set(5, 0);				
				coordTroupes[3].set(7, 0);				
				coordTroupes[4].set(10, 0);
				coordTroupes[5].set(12, 0);
				break;
			case 7:
				terrainCombat[0][0].setUnit(ArmeeGauche[0]);//i-i-i-i-i-i-i
				terrainCombat[2][0].setUnit(ArmeeGauche[0]);
				terrainCombat[4][0].setUnit(ArmeeGauche[0]);
				terrainCombat[6][0].setUnit(ArmeeGauche[0]);
				terrainCombat[8][0].setUnit(ArmeeGauche[0]);
				terrainCombat[10][0].setUnit(ArmeeGauche[0]);
				terrainCombat[12][0].setUnit(ArmeeGauche[0]);
				coordTroupes[0].set(0, 0);
				coordTroupes[1].set(2, 0);
				coordTroupes[2].set(4, 0);				
				coordTroupes[3].set(6, 0);				
				coordTroupes[4].set(8, 0);
				coordTroupes[5].set(10, 0);
				coordTroupes[6].set(12, 0);
				break;
			default:
				throw new IllegalArgumentException("tableau de troupes vide ou invalide");
		}
		switch (nbTroupes2){
		case 1:
			terrainCombat[6][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);//------i------
			coordTroupes[0].set(6, LARGEURTERRAIN-1);
			break;
		case 2:
			terrainCombat[4][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);//----i---i----
			terrainCombat[8][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			coordTroupes[0].set(4, LARGEURTERRAIN-1);
			coordTroupes[1].set(8, LARGEURTERRAIN-1);
			break;
		case 3:
			terrainCombat[2][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);//--i---i---i--
			terrainCombat[6][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			terrainCombat[10][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			coordTroupes[0].set(2, LARGEURTERRAIN-1);
			coordTroupes[1].set(6, LARGEURTERRAIN-1);
			coordTroupes[2].set(10, LARGEURTERRAIN-1);
			break;
		case 4:
			terrainCombat[1][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);//-i--i---i--i-
			terrainCombat[4][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			terrainCombat[8][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			terrainCombat[11][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			coordTroupes[0].set(1, LARGEURTERRAIN-1);
			coordTroupes[1].set(4, LARGEURTERRAIN-1);
			coordTroupes[2].set(8, LARGEURTERRAIN-1);				
			coordTroupes[3].set(11, LARGEURTERRAIN-1);
			break;
		case 5:
			terrainCombat[0][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);//i--i--i--i--i
			terrainCombat[3][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			terrainCombat[6][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			terrainCombat[9][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			terrainCombat[12][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			coordTroupes[0].set(0, LARGEURTERRAIN-1);
			coordTroupes[1].set(3, LARGEURTERRAIN-1);
			coordTroupes[2].set(6, LARGEURTERRAIN-1);				
			coordTroupes[3].set(9, LARGEURTERRAIN-1);				
			coordTroupes[4].set(12, LARGEURTERRAIN-1);
			break;
		case 6:
			terrainCombat[0][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);//i-i--i-i--i-i
			terrainCombat[2][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			terrainCombat[5][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			terrainCombat[7][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			terrainCombat[10][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			terrainCombat[12][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			coordTroupes[0].set(0, LARGEURTERRAIN-1);
			coordTroupes[1].set(2, LARGEURTERRAIN-1);
			coordTroupes[2].set(5, LARGEURTERRAIN-1);				
			coordTroupes[3].set(7, LARGEURTERRAIN-1);				
			coordTroupes[4].set(10, LARGEURTERRAIN-1);
			coordTroupes[5].set(12, LARGEURTERRAIN-1);
			break;
		case 7:
			terrainCombat[0][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);//i-i-i-i-i-i-i
			terrainCombat[2][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			terrainCombat[4][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			terrainCombat[6][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			terrainCombat[8][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			terrainCombat[10][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			terrainCombat[12][LARGEURTERRAIN-1].setUnit(ArmeeGauche[0]);
			coordTroupes[0].set(0, LARGEURTERRAIN-1);
			coordTroupes[1].set(2, LARGEURTERRAIN-1);
			coordTroupes[2].set(4, LARGEURTERRAIN-1);				
			coordTroupes[3].set(6, LARGEURTERRAIN-1);				
			coordTroupes[4].set(8, LARGEURTERRAIN-1);
			coordTroupes[5].set(10, LARGEURTERRAIN-1);
			coordTroupes[6].set(12, LARGEURTERRAIN-1);
			break;
		default:
			throw new IllegalArgumentException("tableau de troupes vide ou invalide");
		}
		for (int i=0; i<coordTroupes.length; i++){
			if (i<7){
				boostStats (terrainCombat[(int) coordTroupes[i].getX()][(int) coordTroupes[i].getY()].getUnit(), armee1.getAttaque(), armee1.getDefense(), armee1.getChance(), armee1.getMoral());
			}
			else{
				boostStats (terrainCombat[(int) coordTroupes[i].getX()][(int) coordTroupes[i].getY()].getUnit(), armee2.getAttaque(), armee2.getDefense(), armee2.getChance(), armee2.getMoral());
			}
		}
	}
	
	private void teleporterTroupe(CaseCombat depart, CaseCombat arrivee){ //teleporte la troupe de la case depart vers la case arrivee
		if (arrivee.getFranchissable() && arrivee.getUnit()==null){
			arrivee.setUnit(depart.getUnit());
			depart.setUnit(null);
		}
	}
	
	private void pathfinding(int queCEstSonTourALui){	
	}
	
	
	private boolean deplacement1Case(int hauteur, int largeur, int hauteurVoulue, int largeurVoulue){//indique si un déplacement est légal
		if (hauteur == hauteurVoulue){
			if (largeurVoulue==largeur+1 ||largeurVoulue == largeur-1){
				return false;
			}
		}
		
		if (hauteur%2 == 0){
			if (hauteur == (hauteurVoulue+1)||hauteur == (hauteurVoulue-1)){
				if (largeurVoulue == (largeur-1) || largeurVoulue == (largeur)){
					return true;
				}
			}

		}
		if (hauteur%2 == 1){
			if (hauteur == (hauteurVoulue+1)||hauteur == (hauteurVoulue-1)){
				if (largeurVoulue == (largeur+1) || largeurVoulue == (largeur)){
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	private void finCombat(int gaucheVainqueur){ // finit le combat. [gauchevainqueur=0:match nul; 1=gauche gagne; 2=gauche perd]
		int compteur=0;
		for(int i=0;i<6; i++){ // enleve les troupes du héros vainqueur
			armee1.armee[i].setUnite(null, null);
		}
		if (gaucheVainqueur ==1){
			for (int i=0; i<coordTroupes.length; i++){ // prend les troupes vivantes sur le terrain et les rend au héros vainqueur
				if (terrainCombat[(int) coordTroupes[i].getX()][(int) coordTroupes[i].getY()].getUnit().armeeGauche()){
					armee1.armee[compteur].setUnite(
							terrainCombat[(int) coordTroupes[i].getX()][(int) coordTroupes[i].getY()]
									.getUnit().getType(),
							terrainCombat[(int) coordTroupes[i].getX()][(int) coordTroupes[i].getY()]
									.getUnit().getNombre());
					compteur++;
				}
			}
			//envoyer signal de disparition du heros/mob droite
			
		}
		else if(gaucheVainqueur == 2){
			for (int i=0; i<coordTroupes.length; i++){ // prend les troupes vivantes sur le terrain et les rend au héros vainqueur
				if (!terrainCombat[(int) coordTroupes[i].getX()][(int) coordTroupes[i].getY()].getUnit().armeeGauche()){
					armee2.armee[compteur].setUnite(
							terrainCombat[(int) coordTroupes[i].getX()][(int) coordTroupes[i].getY()]
									.getUnit().getType(),
							terrainCombat[(int) coordTroupes[i].getX()][(int) coordTroupes[i].getY()]
									.getUnit().getNombre());
					compteur++;
				}
			}
			//envoyer signal de disparition du heros gauche
			
		}
		else if (gaucheVainqueur == 0){
			//envoyer signal de disparition des 2 combattants
		}
			
		else{
			throw new IllegalArgumentException("pas de vainqueur du match");
		}
	}
	
	
	
	
	
}
	


