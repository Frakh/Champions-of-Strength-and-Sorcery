package game.combat;

import java.io.FileNotFoundException;

import game.Heros;
import utilitaire.IPosition;
import utilitaire.Vector2i;

public class Combat {

	public static final int HAUTEURTERRAIN = 13;
	public static final int LARGEURTERRAIN = 20;
	public CaseCombat[][] terrainCombat;// ATTENTION, [LARGEUR][HAUTEUR]
	Heros armee1;
	Heros armee2;
	IUnit[] ArmeeGauche;
	IUnit[] ArmeeDroite;
	Vector2i[] coordTroupes;
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
		for(int i=0; i<LARGEURTERRAIN;i++)
			for(int j=0; j<HAUTEURTERRAIN;j++)
				terrainCombat[i][j]=new CaseCombat();
		armee1 = h1;
		armee2 = h2;
		ArmeeGauche = new IUnit[7];
		ArmeeDroite = new IUnit[7];
		coordTroupes= new Vector2i[14];
		for(int j=0; j<14;j++)
			coordTroupes[j]= new Vector2i(-1,-1);
	}

	public void initialiserCombat() throws FileNotFoundException {
		int nbTroupes1 = 0;
		int nbTroupes2 = 0;
		for (int i = 0; i < armee1.armee.length; i++) {
			if (armee1.armee[i] != null){
				ArmeeGauche[nbTroupes1] = new Unit (armee1.getArmee()[i].getIdUnite(),armee1.getArmee()[i].getNombre()) ;
				ArmeeGauche[nbTroupes1].setArmeeGauche(true);
				nbTroupes1++;
			}
		}
		for (int i = 0; i < armee2.armee.length; i++) {
			if (armee2.armee[i] != null) {

				ArmeeDroite[nbTroupes2] = new Unit (armee2.armee[i].getIdUnite(),armee2.armee[i].getNombre()) ;
				ArmeeDroite[nbTroupes2].setArmeeGauche(false);
				nbTroupes2++;
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
				terrainCombat[0][6].setUnit(ArmeeGauche[0]);//------i------
				coordTroupes[0].set(0,6);
				break;
			case 2:
				terrainCombat[0][4].setUnit(ArmeeGauche[0]);//----i---i----
				terrainCombat[0][8].setUnit(ArmeeGauche[01]);
				coordTroupes[0].set(0,4);
				coordTroupes[1].set(0,8);
				break;
			case 3:
				terrainCombat[0][2].setUnit(ArmeeGauche[0]);//--i---i---i--
				terrainCombat[0][6].setUnit(ArmeeGauche[01]);
				terrainCombat[0][10].setUnit(ArmeeGauche[02]);
				coordTroupes[0].set(0,2);
				coordTroupes[1].set(0,6);
				coordTroupes[2].set(0,10);
				break;
			case 4:
				terrainCombat[0][1].setUnit(ArmeeGauche[0]);//-i--i---i--i-
				terrainCombat[0][4].setUnit(ArmeeGauche[01]);
				terrainCombat[0][8].setUnit(ArmeeGauche[02]);
				terrainCombat[0][11].setUnit(ArmeeGauche[03]);
				coordTroupes[0].set(0,1);
				coordTroupes[1].set(0,4);
				coordTroupes[2].set(0,8);				
				coordTroupes[3].set(0,11);

				break;
			case 5:
				terrainCombat[0][0].setUnit(ArmeeGauche[0]);//i--i--i--i--i
				terrainCombat[0][3].setUnit(ArmeeGauche[01]);
				terrainCombat[0][6].setUnit(ArmeeGauche[02]);
				terrainCombat[0][9].setUnit(ArmeeGauche[03]);
				terrainCombat[0][12].setUnit(ArmeeGauche[04]);
				coordTroupes[0].set(0, 0);
				coordTroupes[1].set(0,3);
				coordTroupes[2].set(0,6);				
				coordTroupes[3].set(0,9);				
				coordTroupes[4].set(0,12);
				break;
			case 6:
				terrainCombat[0][0].setUnit(ArmeeGauche[0]);//i-i--i-i--i-i
				terrainCombat[0][2].setUnit(ArmeeGauche[01]);
				terrainCombat[0][5].setUnit(ArmeeGauche[02]);
				terrainCombat[0][7].setUnit(ArmeeGauche[03]);
				terrainCombat[0][10].setUnit(ArmeeGauche[04]);
				terrainCombat[0][12].setUnit(ArmeeGauche[05]);
				coordTroupes[0].set(0, 0);
				coordTroupes[1].set(0,2);
				coordTroupes[2].set(0,5);				
				coordTroupes[3].set(0,7);				
				coordTroupes[4].set(0,10);
				coordTroupes[5].set(0,12);
				break;
			case 7:
				terrainCombat[0][0].setUnit(ArmeeGauche[0]);//i-i-i-i-i-i-i
				terrainCombat[0][2].setUnit(ArmeeGauche[01]);
				terrainCombat[0][4].setUnit(ArmeeGauche[02]);
				terrainCombat[0][6].setUnit(ArmeeGauche[03]);
				terrainCombat[0][8].setUnit(ArmeeGauche[04]);
				terrainCombat[0][10].setUnit(ArmeeGauche[05]);
				terrainCombat[0][12].setUnit(ArmeeGauche[06]);
				coordTroupes[0].set(0, 0);
				coordTroupes[1].set(0,2);
				coordTroupes[2].set(0,4);				
				coordTroupes[3].set(0,6);				
				coordTroupes[4].set(0,8);
				coordTroupes[5].set(0,10);
				coordTroupes[6].set(0,12);
				break;
			default:
				throw new IllegalArgumentException("tableau de troupes vide ou invalide");
		}
		switch (nbTroupes2){
		case 1:
			terrainCombat[LARGEURTERRAIN-1][6].setUnit(ArmeeDroite[0]);//------i------
			coordTroupes[7].set(LARGEURTERRAIN-1,6);
			break;
		case 2:
			terrainCombat[LARGEURTERRAIN-1][4].setUnit(ArmeeDroite[0]);//----i---i----
			terrainCombat[LARGEURTERRAIN-1][8].setUnit(ArmeeDroite[01]);
			coordTroupes[7].set(LARGEURTERRAIN-1,4);
			coordTroupes[8].set(LARGEURTERRAIN-1,8);
			break;
		case 3:
			terrainCombat[LARGEURTERRAIN-1][2].setUnit(ArmeeDroite[0]);//--i---i---i--
			terrainCombat[LARGEURTERRAIN-1][6].setUnit(ArmeeDroite[01]);
			terrainCombat[LARGEURTERRAIN-1][10].setUnit(ArmeeDroite[02]);
			coordTroupes[7].set(LARGEURTERRAIN-1,2);
			coordTroupes[8].set(LARGEURTERRAIN-1,6);
			coordTroupes[9].set(LARGEURTERRAIN-1,10);
			break;
		case 4:
			terrainCombat[LARGEURTERRAIN-1][1].setUnit(ArmeeDroite[0]);//-i--i---i--i-
			terrainCombat[LARGEURTERRAIN-1][4].setUnit(ArmeeDroite[01]);
			terrainCombat[LARGEURTERRAIN-1][8].setUnit(ArmeeDroite[02]);
			terrainCombat[LARGEURTERRAIN-1][11].setUnit(ArmeeDroite[03]);
			coordTroupes[7].set(LARGEURTERRAIN-1,1);
			coordTroupes[8].set(LARGEURTERRAIN-1,4);
			coordTroupes[9].set(LARGEURTERRAIN-1,8);				
			coordTroupes[10].set(LARGEURTERRAIN-1,10);
			break;
		case 5:
			terrainCombat[LARGEURTERRAIN-1][0].setUnit(ArmeeDroite[0]);//i--i--i--i--i
			terrainCombat[LARGEURTERRAIN-1][3].setUnit(ArmeeDroite[01]);
			terrainCombat[LARGEURTERRAIN-1][6].setUnit(ArmeeDroite[02]);
			terrainCombat[LARGEURTERRAIN-1][9].setUnit(ArmeeDroite[03]);
			terrainCombat[LARGEURTERRAIN-1][12].setUnit(ArmeeDroite[04]);
			coordTroupes[7].set(LARGEURTERRAIN-1,0);
			coordTroupes[8].set(LARGEURTERRAIN-1,3);
			coordTroupes[9].set(LARGEURTERRAIN-1,6);				
			coordTroupes[10].set(LARGEURTERRAIN-1,9);				
			coordTroupes[11].set(LARGEURTERRAIN-1,12);
			break;
		case 6:
			terrainCombat[LARGEURTERRAIN-1][0].setUnit(ArmeeDroite[0]);//i-i--i-i--i-i
			terrainCombat[LARGEURTERRAIN-1][2].setUnit(ArmeeDroite[01]);
			terrainCombat[LARGEURTERRAIN-1][5].setUnit(ArmeeDroite[02]);
			terrainCombat[LARGEURTERRAIN-1][7].setUnit(ArmeeDroite[03]);
			terrainCombat[LARGEURTERRAIN-1][10].setUnit(ArmeeDroite[04]);
			terrainCombat[LARGEURTERRAIN-1][12].setUnit(ArmeeDroite[05]);
			coordTroupes[7].set(LARGEURTERRAIN-1,0);
			coordTroupes[8].set(LARGEURTERRAIN-1,2);
			coordTroupes[9].set(LARGEURTERRAIN-1,5);				
			coordTroupes[10].set(LARGEURTERRAIN-1,7);				
			coordTroupes[11].set(LARGEURTERRAIN-1,10);
			coordTroupes[12].set(LARGEURTERRAIN-1,12);
			break;
		case 7:
			terrainCombat[LARGEURTERRAIN-1][0].setUnit(ArmeeDroite[0]);//i-i-i-i-i-i-i
			terrainCombat[LARGEURTERRAIN-1][2].setUnit(ArmeeDroite[01]);
			terrainCombat[LARGEURTERRAIN-1][4].setUnit(ArmeeDroite[02]);
			terrainCombat[LARGEURTERRAIN-1][6].setUnit(ArmeeDroite[03]);
			terrainCombat[LARGEURTERRAIN-1][8].setUnit(ArmeeDroite[04]);
			terrainCombat[LARGEURTERRAIN-1][10].setUnit(ArmeeDroite[05]);
			terrainCombat[LARGEURTERRAIN-1][12].setUnit(ArmeeDroite[06]);
			coordTroupes[7].set(LARGEURTERRAIN-1,0);
			coordTroupes[8].set(LARGEURTERRAIN-1,2);
			coordTroupes[9].set(LARGEURTERRAIN-1,4);				
			coordTroupes[10].set(LARGEURTERRAIN-1,6);				
			coordTroupes[11].set(LARGEURTERRAIN-1,8);
			coordTroupes[12].set(LARGEURTERRAIN-1,10);
			coordTroupes[13].set(LARGEURTERRAIN-1,12);
			break;
		default:
			throw new IllegalArgumentException("tableau de troupes vide ou invalide");
		}

		for (int i=0; i<coordTroupes.length; i++){

			if (i<7){
				if (coordTroupes[i].getX()!=-1){ // case de coords vide{
					boostStats (terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit(), armee1.getAttaque(), armee1.getDefense(), armee1.getChance(), armee1.getMoral());
				}
			}
			else{
				if (coordTroupes[i].getX()!=-1)
					boostStats (terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit(), armee2.getAttaque(), armee2.getDefense(), armee2.getChance(), armee2.getMoral());
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
	
	public void teleporterTroupe(Vector2i depart, Vector2i arrivee){ //teleporte la troupe de la case depart vers la case arrivee
		if (terrainCombat[arrivee.getX()][arrivee.getY()].getFranchissable() && terrainCombat[arrivee.getX()][arrivee.getY()].getUnit()==null){
			for(int i=0; i<coordTroupes.length;i++){
				if(coordTroupes[i].getX()==depart.getX() && coordTroupes[i].getY()==depart.getY()){
					coordTroupes[i]=arrivee;
					System.out.println("blblllll");
				}
				System.out.println("blb");
			}
			terrainCombat[arrivee.getX()][arrivee.getY()].setUnit(terrainCombat[depart.getX()][depart.getY()].getUnit());
			terrainCombat[depart.getX()][depart.getY()].setUnit(null);
		}
	}
	
	private boolean[][] pathfinding(int coordUniteL, int coordUniteH, boolean volant){ // renvoie un tableau de bools, représentant les cases accessibles par un monstre qui marche par terre
		// case non visitée = null
		// case accessible = true
		// case à accéder = false
		int k;
		int l;
		boolean trucARetourner[][] = new boolean[LARGEURTERRAIN][HAUTEURTERRAIN];
		int truc[][]=new int[LARGEURTERRAIN][HAUTEURTERRAIN]; // ai besoin de truc pour avoir faux, vrai et à rendre vrai
		for(int i=0; i<LARGEURTERRAIN; i++){
			for (int j=0; j<HAUTEURTERRAIN; j++){
				trucARetourner[i][j]=false;
				truc[i][j]=0;
			}
		}
		trucARetourner[coordUniteL][coordUniteH]=true;
		truc[coordUniteL][coordUniteH]=1;
		int mouvement = terrainCombat[coordUniteL][coordUniteH].getUnit().getMouvement();
		System.out.println("mv="+mouvement);
		while (mouvement !=0){//while for for if for for if if. bon appétit bien sûr.
			//trouver les cases à accéder
			for(int i=0; i<LARGEURTERRAIN; i++){
				for (int j=0; j<HAUTEURTERRAIN; j++){
					if (truc[i][j] == 1){
						k=i-1;
						if (k<0) {k=0;}
						while (k <= i+1){//double boucle while parce que for me faisait sortir du tableau
							l=j-1;
							if (l<0) {l=0;}
							while (l <= j+1){	
								if (deplacement1Case(j,i,l,k) && truc[k][l] != 1){
											truc[k][l]=2; // changé juste après, pour éviter que les true créés ici fassent n'importe quoi dans la boucle
								}
								l++;
							}
							k++;
						}
					}
				}
			}
			for(int i=0; i<LARGEURTERRAIN; i++){
				for (int j=0; j<HAUTEURTERRAIN; j++){
					if (truc[i][j]==2){
						if (volant == true || (terrainCombat[i][j].getFranchissable() && terrainCombat[i][j].getUnit() == null) )
							truc[i][j]=1;
					}
				}
			}
			mouvement--;
		}
		if (volant){ // volant: pas de détection des collisions dans la boucle, amis on enlève les cases inaccessibles à la fin
			for(int i=0; i<LARGEURTERRAIN; i++){
				for (int j=0; j<HAUTEURTERRAIN; j++){
					if (truc[i][j]==0 || !(terrainCombat[i][j].getFranchissable() && terrainCombat[i][j].getUnit() == null) ){
						truc[i][j] = 0;
					}
				}
			}
		}
		
		truc[coordUniteL][coordUniteH]=0;
		for(int i=0; i<LARGEURTERRAIN; i++){
			for (int j=0; j<HAUTEURTERRAIN; j++){
				if (truc[i][j]==1){
					trucARetourner[i][j]=true;
				}
			}
		}
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
		return true;
	}
	
	private void tourCombat(){ //a faire
		while(!armeeMorte()){
			
		}
	}

	public String toString(){
		String st="";
		st+="tableau de combat\n";
		
		for(int j = 0; j < HAUTEURTERRAIN; j++){
			for(int i = 0; i < LARGEURTERRAIN; i++){
				if ((terrainCombat[i][j].getUnit()!=null)){
					for(int k=0;k<coordTroupes.length;k++){
						if (coordTroupes[k].getX()==i&&coordTroupes[k].getY()==j)
							st+=k;
					}
				}
				else if (terrainCombat[i][j].franchissable)
					st+="o";
				else
					st+="x";
			}
			st+="\n";
		}
		st+="\nEmplacement des troupes\n";
		for (int i = 0; i < coordTroupes.length; i++){
			if (coordTroupes[i].getX()!=-1){
				System.out.println(coordTroupes[i].getY());
				st+= "unite "+i+": ";
				System.out.println(coordTroupes[i].getX()+ "  "+coordTroupes[i].getY());
				st+=terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getNombre()+" ";
				st+=terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getDescription()+"\n";
			}
		}
		return st;
	}
	public String toStringPathFinding(Vector2i unit){
		String st="";
		st+="tableau de pathfinding\n";
		
		boolean[][] pf = pathfinding(unit.getX(), unit.getY(), false);
		
		for(int j = 0; j < HAUTEURTERRAIN; j++){
			for(int i = 0; i < LARGEURTERRAIN; i++){
				if (pf[i][j])
					st+="o";
				else
					st+="x";
			}
			st+="\n";
		}
		return st;
	}
}