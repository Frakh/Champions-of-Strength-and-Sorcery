<<<<<<< HEAD
package game.combat;

import java.io.FileNotFoundException;
import game.Heros;
import utilitaire.IPosition;

public class Combat {

	public static final int HAUTEURTERRAIN = 13;
	public static final int LARGEURTERRAIN = 20;
	public CaseCombat[][] terrainCombat;// ATTENTION, [LARGEUR][HAUTEUR]
	Heros armee1;
	Heros armee2;
	IUnit[] ArmeeGauche;
	IUnit[] ArmeeDroite;
	IPosition[] coordTroupes;
	private int[][] tableauPlacement = {  // Tableau des placement des troupes
			{6},
			{4,8},
			{2,6,10},
			{1,4,8,11},
			{0,3,6,9,12},
			{0,2,5,7,10,12},
			{0,2,4,6,8,10,12}
	};	
	
	public Combat(Heros h1, Heros h2) {
		terrainCombat = new CaseCombat[LARGEURTERRAIN][HAUTEURTERRAIN];
		armee1 = h1;
		armee2 = h2;
		ArmeeGauche = new IUnit[7];
		ArmeeDroite = new IUnit[7];
		coordTroupes= new IPosition[14];
	}

	public void initialiserCombat() throws FileNotFoundException {
		int nbTroupes1 = 0;
		int nbTroupes2 = 0;
		for (int i = 0; i < armee1.armee.length; i++) {
			if (armee1.armee[i] != null){
				nbTroupes1++;
				ArmeeGauche[i] = new Unit (armee1.getArmee()[i].getIdUnite(),armee1.getArmee()[i].getIdUnite()) ;
				ArmeeGauche[i].setArmeeGauche(true);
			}
		}
		for (int i = 0; i < armee2.armee.length; i++) {
			if (armee2.armee[i] != null) {
				nbTroupes1++;
				ArmeeDroite[i] = new Unit (armee2.armee[i].getIdUnite(),armee2.armee[i].getIdUnite()) ;
				ArmeeDroite[i].setArmeeGauche(false);
			}
		}
		placerTroupes(nbTroupes1, nbTroupes2);
	}
	
	
	
	private void boostStats(IUnit unit, int attaque, int defense, int moral, int chance) { //augmente les stats de unit
		unit.setAttaque(unit.getAttaque() + attaque);
		unit.setDefense(unit.getDefense() + defense);
		unit.setMoral(unit.getMoral() + moral);
		unit.setChance(unit.getChance() + chance);
	}

	private void placerTroupeV2(int nbt1, int nbt2) {
		if (nbt1 < 1 || nbt1 > 7 || nbt2 < 1 || nbt2 > 7)
			throw new IllegalArgumentException("Arguments invalides : nbt1 = " + nbt1 + " - nbt2 = " + nbt2 );

		for (int i = 1; i <= nbt1; ++i) {
			terrainCombat[tableauPlacement[nbt1][i-1]][0].setUnit(ArmeeGauche[0]);
			coordTroupes[i-1].set(tableauPlacement[nbt1][i-1], 0);
		}

		for (int i = 1; i <= nbt2; ++i) {
			terrainCombat[tableauPlacement[nbt1][i-1]][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			coordTroupes[i-1].set(tableauPlacement[nbt1][i-1], LARGEURTERRAIN-1);
		}
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
			terrainCombat[6][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);//------i------
			coordTroupes[0].set(6, LARGEURTERRAIN-1);
			break;
		case 2:
			terrainCombat[4][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);//----i---i----
			terrainCombat[8][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			coordTroupes[0].set(4, LARGEURTERRAIN-1);
			coordTroupes[1].set(8, LARGEURTERRAIN-1);
			break;
		case 3:
			terrainCombat[2][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);//--i---i---i--
			terrainCombat[6][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			terrainCombat[10][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			coordTroupes[0].set(2, LARGEURTERRAIN-1);
			coordTroupes[1].set(6, LARGEURTERRAIN-1);
			coordTroupes[2].set(10, LARGEURTERRAIN-1);
			break;
		case 4:
			terrainCombat[1][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);//-i--i---i--i-
			terrainCombat[4][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			terrainCombat[8][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			terrainCombat[11][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			coordTroupes[0].set(1, LARGEURTERRAIN-1);
			coordTroupes[1].set(4, LARGEURTERRAIN-1);
			coordTroupes[2].set(8, LARGEURTERRAIN-1);				
			coordTroupes[3].set(11, LARGEURTERRAIN-1);
			break;
		case 5:
			terrainCombat[0][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);//i--i--i--i--i
			terrainCombat[3][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			terrainCombat[6][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			terrainCombat[9][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			terrainCombat[12][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			coordTroupes[0].set(0, LARGEURTERRAIN-1);
			coordTroupes[1].set(3, LARGEURTERRAIN-1);
			coordTroupes[2].set(6, LARGEURTERRAIN-1);				
			coordTroupes[3].set(9, LARGEURTERRAIN-1);				
			coordTroupes[4].set(12, LARGEURTERRAIN-1);
			break;
		case 6:
			terrainCombat[0][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);//i-i--i-i--i-i
			terrainCombat[2][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			terrainCombat[5][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			terrainCombat[7][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			terrainCombat[10][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			terrainCombat[12][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			coordTroupes[0].set(0, LARGEURTERRAIN-1);
			coordTroupes[1].set(2, LARGEURTERRAIN-1);
			coordTroupes[2].set(5, LARGEURTERRAIN-1);				
			coordTroupes[3].set(7, LARGEURTERRAIN-1);				
			coordTroupes[4].set(10, LARGEURTERRAIN-1);
			coordTroupes[5].set(12, LARGEURTERRAIN-1);
			break;
		case 7:
			terrainCombat[0][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);//i-i-i-i-i-i-i
			terrainCombat[2][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			terrainCombat[4][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			terrainCombat[6][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			terrainCombat[8][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			terrainCombat[10][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
			terrainCombat[12][LARGEURTERRAIN-1].setUnit(ArmeeDroite[0]);
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
	
	private boolean deplacement1Case(int hauteur, int largeur, int hauteurVoulue, int largeurVoulue){//indique si un déplacement est légal
		if (hauteur == hauteurVoulue){
			if (largeurVoulue==largeur+1 ||largeurVoulue == largeur-1){
				return true;
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
	
	private void teleporterTroupe(CaseCombat depart, CaseCombat arrivee){ //teleporte la troupe de la case depart vers la case arrivee
		if (arrivee.getFranchissable() && arrivee.getUnit()==null){
			arrivee.setUnit(depart.getUnit());
			depart.setUnit(null);
		}
	}
	
	private Boolean[][] pathfinding(int coordUniteL, int coordUniteH, boolean volant){ // renvoie un tableau de bools, représentant les cases accessibles par un monstre qui marche par terre
		// case non visitée = null
		// case accessible = true
		// case à accéder = false
		int k;
		int l;
		Boolean trucARetourner[][] = new Boolean[LARGEURTERRAIN][HAUTEURTERRAIN];
		trucARetourner[coordUniteL][coordUniteH]=true;
		int mouvement = terrainCombat[coordUniteL][coordUniteH].getUnit().getMouvement();
		while (mouvement !=0){//while for for if for for if if. bon appétit bien sûr.
			//trouver les cases à accéder
			for(int i=0; i<LARGEURTERRAIN; i++){
				for (int j=0; j<HAUTEURTERRAIN; j++){
					if (trucARetourner[i][j] == true){
						k=i-1;
						if (k<0) k=0;
						while (k<=LARGEURTERRAIN){//double boucle while parce que for me faisait sortir du tableau
							l=j-1;
							if (j<0) j=0;
							while (j <= HAUTEURTERRAIN){	
								if (deplacement1Case(j,i,l,k)){
									if (trucARetourner[k][l] != true){
											trucARetourner[k][l]=false; // changé juste après, pour éviter que les true créés ici fassent n'importe quoi dans la boucle
									}
								}
								j++;
							}
							k++;
						}
					}
				}
			}
			for(int i=0; i<LARGEURTERRAIN; i++){
				for (int j=0; j<HAUTEURTERRAIN; j++){
					if (trucARetourner[i][j]==false){
						if (volant == true || (terrainCombat[i][j].getFranchissable() && terrainCombat[i][j].getUnit() == null) )
							trucARetourner[i][j]=true;
					}
				}
			}
			mouvement--;
		}
		if (volant){ // volant: pas de détection des collisions dans la boucle, amis on enlève les cases inaccessibles à la fin
			for(int i=0; i<LARGEURTERRAIN; i++){
				for (int j=0; j<HAUTEURTERRAIN; j++){
					if (trucARetourner[i][j] && !(terrainCombat[i][j].getFranchissable() && terrainCombat[i][j].getUnit() == null) ){
						trucARetourner[i][j] = false;
					}
				}
			}
		}
		trucARetourner[coordUniteL][coordUniteH]=false;
		return trucARetourner;
	}
		
	private void finCombat(int gaucheVainqueur){ // finit le combat. [gauchevainqueur=0:match nul; 1=gauche gagne; 2=gauche perd]
		int compteur=0;
		for(int i=0;i<6; i++){ // enleve les troupes du héros vainqueur
			armee1.armee[i].setUnite(null, null);
		}
		if (gaucheVainqueur ==1){
			for (int i=0; i<coordTroupes.length; i++){ // prend les troupes vivantes sur le terrain et les rend au héros vainqueur
				if (terrainCombat[(int) coordTroupes[i].getX()][(int) coordTroupes[i].getY()].getUnit().getArmeeGauche()){
					armee1.armee[compteur].setUnite(
							terrainCombat[(int) coordTroupes[i].getX()][(int) coordTroupes[i].getY()]
									.getUnit().getId(),
							terrainCombat[(int) coordTroupes[i].getX()][(int) coordTroupes[i].getY()]
									.getUnit().getNombre());
					compteur++;
				}
			}
			//envoyer signal de disparition du heros/mob droite
			
		}
		else if(gaucheVainqueur == 2){
			for (int i=0; i<coordTroupes.length; i++){ // prend les troupes vivantes sur le terrain et les rend au héros vainqueur
				if (!terrainCombat[(int) coordTroupes[i].getX()][(int) coordTroupes[i].getY()].getUnit().getArmeeGauche()){
					armee2.armee[compteur].setUnite(
							terrainCombat[(int) coordTroupes[i].getX()][(int) coordTroupes[i].getY()]
									.getUnit().getId(),
							terrainCombat[(int) coordTroupes[i].getX()][(int) coordTroupes[i].getY()]
									.getUnit().getNombre());
					compteur++;
				}
			}
			//envoyer signal de disparition du heros gauche
			
		}
		else if (gaucheVainqueur == 0){
			//match nul
			//envoyer signal de disparition des 2 combattants
		}
			
		else{
			throw new IllegalArgumentException("pas de vainqueur du match");
		}
	}
	
	private boolean armeeMorte(){ //dit si un joueur a gagné
		//a faire
	}
	
	private void tourCombat(){ //a faire
		while(!armeeMorte){
			
		}
	}
	
	
}
>>>>>>> branch 'master' of https://github.com/Frakh/Champions-of-Strength-and-Sorcery.git
