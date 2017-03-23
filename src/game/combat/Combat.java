package game.combat;

import game.Heros;
import utilitaire.Vector2i;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Combat {

	public static final int HAUTEURTERRAIN = 13;
	public static final int LARGEURTERRAIN = 20;
	public CaseCombat[][] terrainCombat;// ATTENTION, [LARGEUR][HAUTEUR]
	Heros armee1;
	Heros armee2;
	IUnit[] ArmeeGauche;
	IUnit[] ArmeeDroite;
	private Vector2i[] coordTroupes;
	private int[][] tableauPlacement = {  // Tableau des placement des troupes
			{6},
			{4,8},
			{2,6,10},
			{1,4,8,11},
			{0,3,6,9,12},
			{0,2,5,7,10,12},
			{0,2,4,6,8,10,12}
	};	
	 
	public Vector2i getCoordTroupes(int i){
		return coordTroupes[i];
	}
	
	public Combat(Heros h1, Heros h2, int typeterrain) throws FileNotFoundException {
		typeterrain+=(int)Math.random()*2;						//POUR LE TEST, A ENLEVER PLUS TARD
		terrainCombat = new CaseCombat[LARGEURTERRAIN][HAUTEURTERRAIN];
		for(int i=0; i<LARGEURTERRAIN;i++)
			for(int j=0; j<HAUTEURTERRAIN;j++)
				terrainCombat[i][j]=new CaseCombat();
		String nomFichierSource = "C:\\Users\\Ereshkigal\\git\\Champions-of-Strength-and-Sorcery2\\assets\\data\\combat\\terrain" +typeterrain + ".txt";
		Scanner sc = new Scanner (new FileInputStream(new File(nomFichierSource)));
		String straingue=sc.nextLine();
		sc.close();
		for(int j=0; j<HAUTEURTERRAIN;j++){
			for(int i=0; i<LARGEURTERRAIN;i++){
				if (straingue.charAt(0)=='x'){
					terrainCombat[i][j].setFranchissable(false);
				}
				straingue = straingue.substring(1,straingue.length());
			}
		}
				
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
			if (armee1.armee[i].getNombre()!=0){
				ArmeeGauche[nbTroupes1] = new Unit (armee1.getArmee()[i].getIdUnite(),armee1.getArmee()[i].getNombre()) ;
				ArmeeGauche[nbTroupes1].setArmeeGauche(true);
				nbTroupes1++;
			}
		}
		for (int i = 0; i < armee2.armee.length; i++) {
			if (armee2.armee[i].getNombre()!=0) {

				ArmeeDroite[nbTroupes2] = new Unit (armee2.armee[i].getIdUnite(),armee2.armee[i].getNombre()) ;
				ArmeeDroite[nbTroupes2].setArmeeGauche(false);
				nbTroupes2++;
			}
		}
		placerTroupeV2(nbTroupes1, nbTroupes2);
		fight();
	}
	
	private void boostStats(IUnit unit, int attaque, int defense, int moral, int chance) { //augmente les stats de unit
		unit.setAttaque(unit.getAttaque() + attaque);
		unit.setDefense(unit.getDefense() + defense);
		unit.setMoral(unit.getMoral() + moral);
		unit.setChance(unit.getChance() + chance);
	}

	private void placerTroupeV2(int nbt1, int nbt2) {
		if (nbt1 < 1 || nbt1 > 7 || nbt2 < 1 || nbt2 > 7)
			throw new IllegalArgumentException("Arguments invalides : nombre de troupes 1 = " + nbt1 + "  nombre de troupes 2 = " + nbt2 );

		for (int i =0; i < nbt1; ++i) {
			terrainCombat[0][tableauPlacement[nbt1-1][i]].setUnit(ArmeeGauche[i]);
			coordTroupes[i].set(0,tableauPlacement[nbt1-1][i]);
		}

		for (int i = 0; i < nbt2; ++i) {
			terrainCombat[LARGEURTERRAIN-1][tableauPlacement[nbt2-1][i]].setUnit(ArmeeDroite[i]);
			coordTroupes[i+7].set(LARGEURTERRAIN-1,tableauPlacement[nbt2-1][i]);
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
	
	private boolean deplacement1Case(int hauteur, int largeur, int hauteurVoulue, int largeurVoulue){//indique si un d�placement est l�gal
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
				}
			}
			terrainCombat[arrivee.getX()][arrivee.getY()].setUnit(terrainCombat[depart.getX()][depart.getY()].getUnit());
			terrainCombat[depart.getX()][depart.getY()].setUnit(null);
		}
	}
	
	public boolean[][] pathfinding(int coordUniteL, int coordUniteH){ // renvoie un tableau de bools, repr�sentant les cases accessibles par un monstre qui marche par terre
		// case non visit�e = null
		// case accessible = true
		// case � acc�der = false
		boolean volant = false;
		// On ne compare pas les string avec == bordel
		if (Objects.equals(terrainCombat[coordUniteL][coordUniteH].getUnit().getPouvoir(), "Volant")) volant = true;
		int k;
		int l;
		boolean trucARetourner[][] = new boolean[LARGEURTERRAIN][HAUTEURTERRAIN];
		int truc[][]=new int[LARGEURTERRAIN][HAUTEURTERRAIN]; // ai besoin de truc pour avoir faux, vrai et � rendre vrai
		for(int i=0; i<LARGEURTERRAIN; i++){
			for (int j=0; j<HAUTEURTERRAIN; j++){
				trucARetourner[i][j]=false;
				truc[i][j]=0;
			}
		}
		trucARetourner[coordUniteL][coordUniteH]=true;
		truc[coordUniteL][coordUniteH]=1;
		int mouvement = terrainCombat[coordUniteL][coordUniteH].getUnit().getMouvement();
		while (mouvement !=0){//while for for if if while if while if. bon app�tit bien s�r.
			//trouver les cases � acc�der
			for(int i=0; i<LARGEURTERRAIN; i++){
				for (int j=0; j<HAUTEURTERRAIN; j++){
					if (truc[i][j] == 1){
						k=i-1;
						if (k<0) {k=0;}
						while (k <= i+1 && k<LARGEURTERRAIN){//double boucle while parce que for me faisait sortir du tableau
							l=j-1;
							if (l<0) {l=0;}
							while (l <= j+1 && l<HAUTEURTERRAIN){	
								if (deplacement1Case(j,i,l,k) && truc[k][l] != 1){
									truc[k][l]=2; // chang� juste apr�s, pour �viter que les true cr��s ici fassent n'importe quoi dans la boucle
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
						if (volant || (terrainCombat[i][j].getFranchissable() && terrainCombat[i][j].getUnit() == null) )
							truc[i][j]=1;
					}
				}
			}
			trucARetourner[coordUniteL][coordUniteH]=false;
			mouvement--;
		}
		if (volant){ // volant: pas de d�tection des collisions dans la boucle, amis on enl�ve les cases inaccessibles � la fin
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
		
	private void finCombat(int gaucheVainqueur){ // finit le combat. [gauchevainqueur:0=match nul; 1=gauche gagne; 2=gauche perd]
		int compteur=0;
		for(int i=0;i<6; i++){ // enleve les troupes des 2 heros
			armee1.armee[i].setUnite(null, null);
			armee2.armee[i].setUnite(null, null);
		}
		if (gaucheVainqueur ==1){
			// Remplac� par un foreach par l'ide
			for (Vector2i coordTroupe : coordTroupes) { // prend les troupes vivantes sur le terrain et les rend au h�ros vainqueur
				if (coordTroupe.getX() != -1)
					if (terrainCombat[coordTroupe.getX()][coordTroupe.getY()].getUnit().getArmeeGauche()) {
						armee1.armee[compteur].setUnite(
								terrainCombat[coordTroupe.getX()][coordTroupe.getY()].getUnit().getId(),
								terrainCombat[coordTroupe.getX()][coordTroupe.getY()].getUnit().getNombre());
						compteur++;
					}
			}
			//envoyer signal de disparition du heros/mob droite
			
		}
		else if(gaucheVainqueur == 2){
			// Remplac� par un foreach par l'ide
			for (Vector2i coordTroupe : coordTroupes) { // prend les troupes vivantes sur le terrain et les rend au h�ros vainqueur
				if (coordTroupe.getX() != -1)
					if (!terrainCombat[coordTroupe.getX()][coordTroupe.getY()].getUnit().getArmeeGauche()) {
						armee2.armee[compteur].setUnite(
								terrainCombat[coordTroupe.getX()][coordTroupe.getY()]
										.getUnit().getId(),
								terrainCombat[coordTroupe.getX()][coordTroupe.getY()]
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
			throw new IllegalArgumentException("pas de vainqueur du match, pas de signal de match nul");
		}
	} 
	
	private boolean armeeMorte(){ //indique si le combat doit se terminer
		boolean a=true;
		for (int i=0; i<7; i++){
			if (coordTroupes[i].getX()>=0) {//si la troupe existe (coordtroupe est instancie a -1)
				if(!terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getMort())//et qu'elle est vivante
					a=false;//alors la premiere armee esiste toujours
				}
		}
		if (a) return a;
		for (int i=7; i<14; i++){
			if (coordTroupes[i].getX()>=0) {//pareil pour la deuxiemeS
				if(!terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getMort())
					a=false;
			}
		}
		return a;
	}
	
	public void fight(){ //bah, l'endroit on on fait se taper des gens avec d'autres
		int CestSonTour=-1;
		int initMin=1000;	// pour plus tard: les detecteurs de combat: 
		String ordre = "";		//unite cac: unite ennemie.deplacement1case()=true
		int coordx=-1;			//unite distance: peut toujours taper
		int coordy=-1;
		int taper=-1;
		int idJoueurEnCours=-1;
		boolean ordreFini=false;
		Scanner sc = new Scanner(System.in);
		while(!armeeMorte()){
			for(int i =0; i<coordTroupes.length; i++){//choisir l'unit� dont c'est le tour, mettre son id dans CestSonTour
				if (coordTroupes[i].getX()>=0)
					if (terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getInitiative()<initMin && !terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getAJoue()){
						initMin=terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getInitiative();//si l'unite a l'initiative la plus elevee et n'a pas encore joue...
						CestSonTour=i;
					}
			}
			if (CestSonTour!=-1){
				if (terrainCombat[coordTroupes[CestSonTour].getX()][coordTroupes[CestSonTour].getY()].getUnit().getArmeeGauche()) idJoueurEnCours=1;
				else idJoueurEnCours=0;
				System.out.println(toString());
				System.out.println(toStringPathFinding(new Vector2i(coordTroupes[CestSonTour].getX(),coordTroupes[CestSonTour].getY())));
				
				
				if (idJoueurEnCours==1) {//oui, faudra changer l'interface, merci captain obvious
					System.out.println("Tour du joueur Gauche \nQue voulez-vous faire? (tapez 1 pour la liste des commandes)\n C'est au tour de l'unite numero "+CestSonTour);
					while (!ordreFini) {
						ordre = sc.nextLine();
						if (ordre.equals("1")) {
							System.out.println("d: deplace la troupe dont c'est le tour");
							System.out.println("a: deplace la troupe dont c'est le tour et attaque une unite au corps a corps");
						}
						if (ordre.equals("d")) {
							System.out.println("tapez les coordonnes du deplacement separees d'un espace");
							coordx=sc.nextInt();
							coordy=sc.nextInt();
							if (pathfinding(coordTroupes[CestSonTour].getX(), coordTroupes[CestSonTour].getY())[coordx][coordy]) {
								teleporterTroupe(new Vector2i(coordTroupes[CestSonTour].getX(), coordTroupes[CestSonTour].getY()), new Vector2i(coordx, coordy));
								ordreFini=true;
								idJoueurEnCours=-1;
								terrainCombat[coordTroupes[CestSonTour].getX()][coordTroupes[CestSonTour].getY()].getUnit().setAJoue(true);
							}
							else {
								System.out.println("ce deplacement est impossible");
								ordre="";
							}

						}
						if (ordre.equals("a")){
							System.out.println("tapez les coordonnes du deplacement separees d'un espace");
							coordx=sc.nextInt();
							coordy=sc.nextInt();
							System.out.println("tapez le numero de l'unite a attaquer");
							taper=sc.nextInt();
							if (pathfinding(coordTroupes[CestSonTour].getX(), coordTroupes[CestSonTour].getY())[coordx][coordy] && deplacement1Case(coordx, coordy,coordTroupes[taper].getX(), coordTroupes[taper].getY())) {
								teleporterTroupe(new Vector2i(coordTroupes[CestSonTour].getX(), coordTroupes[CestSonTour].getY()), new Vector2i(coordx, coordy));
								terrainCombat[coordTroupes[CestSonTour].getX()][ coordTroupes[CestSonTour].getY()].getUnit().combattre(terrainCombat[coordTroupes[taper].getX()][ coordTroupes[taper].getY()].getUnit());
								ordreFini=true;
								idJoueurEnCours=-1;
								terrainCombat[coordTroupes[CestSonTour].getX()][coordTroupes[CestSonTour].getY()].getUnit().setAJoue(true);
							}
							else {
								System.out.println("cet ordre est impossible");
								ordre="";
							}
						}
					}
					ordreFini=false;
				}
				if (idJoueurEnCours==0) {//oui, faudra changer l'interface, merci captain obvious
					System.out.println("Tour du joueur Droite \nQue voulez-vous faire? (tapez 1 pour la liste des commandes)\n C'est au tour de l'unite numero "+CestSonTour);
					while (!ordreFini) { 
						ordre = sc.nextLine();
						if (ordre.equals("1")) {
							System.out.println("d: deplace la troupe dont c'est le tour");
							System.out.println("a: deplace la troupe dont c'est le tour et attaque une unite au corps a corps");
						}
						if (ordre.equals("d")) {
							System.out.println("tapez les coordonnes du deplacement separees d'un espace");
							coordx=sc.nextInt();
							coordy=sc.nextInt();
							if (pathfinding(coordTroupes[CestSonTour].getX(), coordTroupes[CestSonTour].getY())[coordx][coordy]) {
								teleporterTroupe(new Vector2i(coordTroupes[CestSonTour].getX(), coordTroupes[CestSonTour].getY()), new Vector2i(coordx, coordy));
								ordreFini=true;
								idJoueurEnCours=-1;
								terrainCombat[coordTroupes[CestSonTour].getX()][coordTroupes[CestSonTour].getY()].getUnit().setAJoue(true);
							}
							else {
								System.out.println("ce deplacement est impossible");
								ordre="";
							}

						}
						if (ordre.equals("a")){
							System.out.println("tapez les coordonnes du deplacement separees d'un espace");
							coordx=sc.nextInt();
							coordy=sc.nextInt();
							System.out.println("tapez le numero de l'unite a attaquer");
							taper=sc.nextInt();
							if (pathfinding(coordTroupes[CestSonTour].getX(), coordTroupes[CestSonTour].getY())[coordx][coordy] && deplacement1Case(coordx, coordy,coordTroupes[taper].getX(), coordTroupes[taper].getY())) {
								teleporterTroupe(new Vector2i(coordTroupes[CestSonTour].getX(), coordTroupes[CestSonTour].getY()), new Vector2i(coordx, coordy));
								terrainCombat[coordTroupes[CestSonTour].getX()][ coordTroupes[CestSonTour].getY()].getUnit().combattre(terrainCombat[coordTroupes[taper].getX()][ coordTroupes[taper].getY()].getUnit());
								ordreFini=true;
								idJoueurEnCours=-1;
								terrainCombat[coordTroupes[CestSonTour].getX()][coordTroupes[CestSonTour].getY()].getUnit().setAJoue(true);
							}
							else {
								System.out.println("cet ordre est impossible");
								ordre="";
							}
						}
					}
					ordreFini=false;
				}
				
				
				//donner la main au joueur pour lui faire faire des trucs
				//attendre le signal et faire en fonction
			}
			else{
				for (int i = 0; i < coordTroupes.length; i++) {//fin d'un tour de jeu: les unites regagnent leur riposte et leur droit de jouer
					if (coordTroupes[i].getX()>=0) {//si la troupe existe (coordtroupe est instancie a -1)
						terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().setAJoue(false);
					}
				}
			}
			CestSonTour=-1;
			initMin=1000;
		}
		for (int i =0; i<coordTroupes.length; i++){//regarder quelle arm�e est morte
			if (coordTroupes[i].getX()!=-1){
				if (!terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getMort()){
					if(i<7)
						finCombat(1);
					else
						finCombat(2);
				}
			}
		}
		sc.close();
		finCombat(0);
	}

	public String toString(){
		String st="";
		st+="tableau de combat\n";
		
		for(int j = 0; j < HAUTEURTERRAIN; j++){
			if (j%2 == 1) st+= " ";
			for(int i = 0; i < LARGEURTERRAIN; i++){
				if ((terrainCombat[i][j].getUnit()!=null)){
					for(int k=0;k<coordTroupes.length;k++){
						if (coordTroupes[k].getX()==i&&coordTroupes[k].getY()==j)
							st+=k+" ";
					}
				}
				else if (terrainCombat[i][j].franchissable)
					st+="o ";
				else
					st+="x ";
			}
			st+="\n";
		}
		st+="\nEmplacement des troupes\n";
		for (int i = 0; i < coordTroupes.length; i++){
			if (coordTroupes[i].getX()!=-1){
				st+= "unite "+i+": ";
				st+=terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getNombre()+" ";
				st+=terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getDescription()+"\n";
			}
		}
		return st;
	}
	public String toStringPathFinding(Vector2i unit){
		String st="";
		st+="tableau de pathfinding\n";
		
		boolean[][] pf = pathfinding(unit.getX(), unit.getY());
		
		for(int j = 0; j < HAUTEURTERRAIN; j++){
			if (j%2 == 1) st+= " ";
			for(int i = 0; i < LARGEURTERRAIN; i++){
				if (pf[i][j])
					st+="o ";
				else
					st+="x ";
			}
			st+="\n";
		}
		return st;
	}
}