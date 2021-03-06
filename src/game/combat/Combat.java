package game.combat;

import es.entree.Souris;
import es.netclasses.Evenement;
import es.netclasses.NetQueueEvenement;
import es.netclasses.NetworkInterface;
import es.netclasses.evenements.CombatEvenement;
import es.sortie.composants.CombatLayer;
import game.Heros;
import utilitaire.Vector2i;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Combat {

	public static final int HAUTEURTERRAIN = 13;//1280*720
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

	public Combat(String truc) throws FileNotFoundException{
		armee1 = null;
		armee2 = null;
		int cpt = 1000;
		int x = -1;
		int y = -1;
		terrainCombat = new CaseCombat[LARGEURTERRAIN][HAUTEURTERRAIN];
		coordTroupes = new Vector2i[14];
		
		
		for(int i = 0; i < coordTroupes.length ; i++){
			coordTroupes[i] = new Vector2i(-1, -1);
		}
		
		for(int i=0; i<LARGEURTERRAIN;i++)
			for(int j=0; j<HAUTEURTERRAIN;j++)
				terrainCombat[i][j]=new CaseCombat();
		
		coordTroupes= new Vector2i[14];
		for(int j=0; j<14;j++)
			coordTroupes[j]= new Vector2i(-1,-1);
		
		for(int i=0; i<LARGEURTERRAIN;i++)
			for(int j=0; j<HAUTEURTERRAIN;j++)
				terrainCombat[i][j]=new CaseCombat();
		
		
		coordTroupes[0]=new Vector2i(0,6);
		terrainCombat[0][6].setUnit(new Unit(12, 50));
		coordTroupes[7]=new Vector2i(19,4);
		terrainCombat[19][4].setUnit(new Unit(11, 30));
		coordTroupes[8]=new Vector2i(19,8);
		terrainCombat[19][8].setUnit(new Unit(13, 5));
		
		this.majMap(truc);
	}
	
	public Combat(Heros h1, Heros h2, int typeterrain) throws FileNotFoundException { 		   //POUR LE TEST, A ENLEVER PLUS TARD
		terrainCombat = new CaseCombat[LARGEURTERRAIN][HAUTEURTERRAIN];
		for(int i=0; i<LARGEURTERRAIN;i++)
			for(int j=0; j<HAUTEURTERRAIN;j++)
				terrainCombat[i][j]=new CaseCombat();
		String nomFichierSource = "./assets/data/combat/terrain" +typeterrain + ".txt";
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
		while (mouvement !=0){//while for for if if while if while if. bon appetit bien sur.
			//trouver les cases a acceder
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
									truc[k][l]=2; // chang� juste apres, pour eviter que les true crees ici fassent n'importe quoi dans la boucle
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

		}
		else if(gaucheVainqueur == 2){
			// Remplace par un foreach par l'ide
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

	public String toStringMap(){
		String st="";
		for(int j = 0; j < HAUTEURTERRAIN; j++){
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
		}
		for (int i = 0; i < coordTroupes.length; i++){
			if (coordTroupes[i].getX()!=-1){
				st+=i+" ";
				st+=terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getNombre()+" ";
				st+=terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getPvAct()+" ";
			}
		}
		return st;

	}

	private int choixTourUnit(){
		int initMin=1000;
		int CestSonTour=-1;
		for(int i =0; i<coordTroupes.length; i++){//choisir l'unite dont c'est le tour, mettre son id dans CestSonTour
			if (coordTroupes[i].getX()>=0)
				if (terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getInitiative()<initMin && !terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getAJoue()){
					initMin=terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getInitiative();//si l'unite a l'initiative la plus elevee et n'a pas encore joue...
					CestSonTour=i;
				}
		}
		if (CestSonTour == -1) {
			for (int i = 0; i < coordTroupes.length; i++) {//fin d'un tour de jeu: les unites regagnent leur riposte et leur droit de jouer
				if (coordTroupes[i].getX()>=0) {//si la troupe existe on lui enleve son aJoue
					terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().setAJoue(false);
				}
			}
			for(int i =0; i<coordTroupes.length; i++){//..et on rechoisit la bonne unit
				if (coordTroupes[i].getX()>=0)
					if (terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getInitiative()<initMin && !terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getAJoue()){
						initMin=terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getInitiative();//si l'unite a l'initiative la plus elevee et n'a pas encore joue...
						CestSonTour=i;
					}
			}
		}

		return CestSonTour;
	}
		
	public void majMap(String m) {
		CaseCombat[][] t = new CaseCombat[LARGEURTERRAIN][HAUTEURTERRAIN];

		//sert a retirer les gens morts de l'index
		int[] flush = new int[14];
		for(int i=0; i<flush.length; i++) {
			flush[i]=-1;
		}

		//creer t
		for(int i=0; i<LARGEURTERRAIN;i++) {
			for(int j=0; j<HAUTEURTERRAIN;j++) {
				t[i][j]=new CaseCombat();
			}
		}
		//mettre la nouvelle carte dans t
		for(int j=0; j<HAUTEURTERRAIN;j++){
			for(int i=0; i<LARGEURTERRAIN;i++){
				if (m.charAt(0)!=' '){ 		   //efface les blancs entre les caracteres
					if (m.charAt(0)=='x'){ 	   //si la case est un obstacle
						t[i][j].setFranchissable(false);
					}
					else if (Character.isDigit((m.charAt(0))) && !Character.isDigit((m.charAt(1)))) {    //si la case est un nombre a  1 chiffre
						t[i][j].setUnit(terrainCombat[coordTroupes[Character.getNumericValue(m.charAt(0))].getX()][coordTroupes[Character.getNumericValue(m.charAt(0))].getY()].getUnit());
						coordTroupes[Character.getNumericValue(m.charAt(0))].set(i, j); 		   //mettre a jour coordTroupes
						flush[Character.getNumericValue(m.charAt(0))]=1;
					}
					else if (Character.isDigit((m.charAt(0))) && Character.isDigit((m.charAt(1)))) {    //si la case est un nombre a  2 chiffres (fourbe!!)
						t[i][j].setUnit(terrainCombat[coordTroupes[Integer.parseInt(String.valueOf(m.charAt(0))+m.charAt(1))].getX()][coordTroupes[Integer.parseInt(String.valueOf(m.charAt(0))+m.charAt(1))].getY()].getUnit());
						coordTroupes[Integer.parseInt(String.valueOf(m.charAt(0))+m.charAt(1))].set(i, j);
						flush[Integer.parseInt(String.valueOf(m.charAt(0))+m.charAt(1))]=1;
						m = m.substring(1,m.length()); //parce qu'il faut virer 2 caracteres au final
					}
					else if (m.charAt(0)!='o')
						System.out.println("majMap plante");
				}
				else
					i--;
				m = m.substring(1,m.length());
			}
			
	}
		//flush contient 1 sur toutes les unites encore vivantes, 61 sur les autres
		for(int i=0; i<flush.length; i++) {
			if (flush[i]==-1) {
				coordTroupes[i].set(-1, -1);
			}
		}

		//t devient la nouvelle map officielle
		for(int i=0; i<HAUTEURTERRAIN;i++){
			for(int j=0; j<LARGEURTERRAIN;j++){
				terrainCombat[j][i]=t[j][i];
			}
		}
		//les donnees sont de la forme "indexUnit nombre pv"
		//on met a jour les units avec ces donnees
		String tt="";  //sert de String-tampon
		int cpt=0; //permet de pas bugger sur les nombres a plusieurs chiffres
		int unit=0;
		int nb=-1;
		int pv=-1;
		while (m.length() !=0) {

			//isoler l'id unit
			while (m.length() !=0 && !Character.isDigit((m.charAt(0))))    //virer les non-chiffres
				m = m.substring(1,m.length());
			if (m.length() !=0) {    //si il y a un blanc a la fin du toString, la fonction plante sans ce if
				while (Character.isDigit((m.charAt(cpt))))
					cpt++;
				for(int i=0; i<cpt; i++) {
					tt+=String.valueOf(m.charAt(0));
					m = m.substring(1,m.length());
				}
				unit = Integer.parseInt(tt);
				cpt=0;
				tt="";
				m = m.substring(1,m.length());

				//isoler le nombre
				while (!Character.isDigit((m.charAt(0))))
					m = m.substring(1,m.length());
				while (Character.isDigit((m.charAt(cpt))))
					cpt++;
				for(int i=0; i<cpt; i++) {
					tt+=String.valueOf(m.charAt(0));
					m = m.substring(1,m.length());
				}
				nb = Integer.parseInt(tt);
				cpt=0;
				tt="";

				//isoler les pv
				while (!Character.isDigit((m.charAt(0))))
					m = m.substring(1,m.length());
				while (Character.isDigit((m.charAt(cpt))))
					cpt++;
				for(int i=0; i<cpt; i++) {
					tt+=String.valueOf(m.charAt(0));
					m = m.substring(1,m.length());
				}
				pv = Integer.parseInt(tt);
				cpt=0;
				tt="";
				//mettre a jour terraincombat avec les donnees isolees
				terrainCombat[coordTroupes[unit].getX()][coordTroupes[unit].getY()].getUnit().setNombre(nb);
				terrainCombat[coordTroupes[unit].getX()][coordTroupes[unit].getY()].getUnit().setPvAct(pv);
				unit=-1;
				nb=-1;
				pv=-1;
			}
		}
	}	
	
	public void fight(boolean attaque, int idH1, int idH2, Souris s) throws IOException, InterruptedException{ //bah, l'endroit on on fait se taper des gens avec d'autres
		boolean blbl=false;
		int k;
		int l;
		int CestSonTour=-1;
		
		Vector2i sel = new Vector2i(-1,-1);
		Evenement eventEnCours = null; //new CombatEvenement(Evenement.COMBAT_EVENT, "");
		if (attaque){ 		   //si le joueur enclenche le combat, il envoie les donn꦳ de combat ࡳon adversaire
			eventEnCours=new CombatEvenement(CombatEvenement.DEBUT_COMBAT , toStringMap());
			System.out.println(eventEnCours.getId());
			NetworkInterface.send(eventEnCours);
			eventEnCours=null;
		}
		while(!armeeMorte()){
			CestSonTour = choixTourUnit();//cestsontour = id de la troupe qui doit jouer
			System.out.println("tour de l'unit "+CestSonTour);
			if ((attaque && CestSonTour < 7) || !attaque && CestSonTour > 6) { 	//si attaque=true, alors ce joueur alance le combat
				//et possede donc les 7 premiers slots du tableau.
				//Sinon il possede les 7 derniers.
				//faire une action
				while (!blbl) {
					if (CombatLayer.getCaseSourisPosition()  != null)
						System.out.print("");
					Thread.sleep(30);
					if (s.getUniqueUsedButton()==1) {
						
						sel.set(CombatLayer.getCaseSourisPosition().getX(), CombatLayer.getCaseSourisPosition().getY());
						//si le pathfinding est d'accord, le trupe se balade
						System.out.println("clic detecte");
						if (pathfinding(coordTroupes[CestSonTour].getX(),coordTroupes[CestSonTour].getY())[sel.getX()][sel.getY()]) {
							teleporterTroupe(new Vector2i (coordTroupes[CestSonTour].getX(),coordTroupes[CestSonTour].getY()), new Vector2i(sel.getX(), sel.getY()));
							blbl=true;
							//si elle arrive pr�s d'un ou plusieurs ennemis, elle les tape
							if (attaque) {
								for (int i = 7; i <14;i++) {
								
									if (deplacement1Case (coordTroupes[i].getX(),coordTroupes[i].getY(), sel.getX(), sel.getY())){
										System.out.println("taper");
										terrainCombat[sel.getX()][sel.getY()].getUnit().combattre(terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit());
										System.out.println(terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getNombre());
										if (terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getMort()) {
											System.out.println("mouru");
											terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].setUnit(null);
											coordTroupes[i]=new Vector2i(-1, -1);
										}
									}
								}
							}
							else {
								for (int i = 0; i <7;i++) {
									
									if (deplacement1Case (sel.getX(), sel.getY(), coordTroupes[i].getX(),coordTroupes[i].getY())){
										System.out.println("taper");
										terrainCombat[sel.getX()][sel.getY()].getUnit().combattre(terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit());
										System.out.println(terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getNombre());
										if (terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].getUnit().getMort()) {
											System.out.println("mouru");
											terrainCombat[coordTroupes[i].getX()][coordTroupes[i].getY()].setUnit(null);
											coordTroupes[i]=new Vector2i(-1, -1);
										}
									}
								}
							}
						}
						// si elle agi, on le set sinon elle va rejouer a l'infini
					}
				}
				blbl = false;
				//envoyer au serveur la map mise a jour
				eventEnCours = new CombatEvenement(Evenement.COMBAT_EVENT, toStringMap());
				NetworkInterface.send(eventEnCours);
				eventEnCours=null;
			}
			else {
				//attendre une mise a jour de la map
				System.out.println("en attente...");
				while (eventEnCours == null) {
					eventEnCours=NetQueueEvenement.getEvenement(Evenement.COMBAT_EVENT);
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {}
					if (eventEnCours!=null) {
						majMap(((CombatEvenement) eventEnCours).getMaj());
						}
				}
				System.out.println("map recue");
			}
			terrainCombat[coordTroupes[CestSonTour].getX()][coordTroupes[CestSonTour].getY()].getUnit().setAJoue(true);
			eventEnCours=null;
		}
		for (int i =0; i<coordTroupes.length; i++){//regarder quelle armee est morte
			if (coordTroupes[i].getX()!=-1){
				if (i<7) {
					finCombat(1);
					if(attaque) {
						//NetworkInterface.send(new(/signal de idH1 a gagne/));
					}
				}
				else {
					finCombat(2);
					if(attaque) {
						//NetworkInterface.send(new(/signal de idH2 a gagne/));
					}
				}
			}
		}
	}

}