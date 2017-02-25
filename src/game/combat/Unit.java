package game.combat;
//test
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import utilitaire.Vector2i;

public class Unit implements IUnit {
	int id;
	boolean armeeGauche;
	int nombre;
	int attaque;
	int defense;
	int moral;
	int chance;
	int pvMax;
	int pvAct;
	int initiative;
	int mouvement;
	int degatMin;
	int degatMax;
	boolean AJoue;
	boolean riposte;
	boolean mort;
	String description;
	String pouvoir;
	
	public Unit(int id, int nombre) throws FileNotFoundException{
		this.id=id;
		String nomFichierSource = "C:\\Users\\Ereshkigal\\git\\Champions-of-Strength-and-Sorcery\\assets\\unit\\descriptionUnit" +id + ".txt";
		Scanner sc = new Scanner (new FileInputStream(new File(nomFichierSource)));
		this.attaque=Integer.parseInt(sc.nextLine());
		this.defense=Integer.parseInt(sc.nextLine());
		this.pvMax=Integer.parseInt(sc.nextLine());
		this.degatMin=Integer.parseInt(sc.nextLine());
		this.degatMax=Integer.parseInt(sc.nextLine());
		this.initiative=Integer.parseInt(sc.nextLine());
		this.mouvement=Integer.parseInt(sc.nextLine());
		this.description=sc.nextLine();
		this.pouvoir=sc.nextLine();
		sc.close();
		this.nombre = nombre;
		this.riposte=true;
		this.mort=false;
		this.pvAct=pvMax;
	}
	
	
	public boolean getAJoue(){
		return AJoue;
	}
	public void setAJoue(boolean a){
		AJoue=a;
	}
	public String getPouvoir(){
		return this.pouvoir;
	}
	public boolean getArmeeGauche() {
		return armeeGauche;
	}

	public void setArmeeGauche(boolean armeeGauche) {
		this.armeeGauche = armeeGauche;
	}

	public int getNombre() {
		return nombre;
	}

	public String getDescription(){
		return this.description;
	}
	
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public int getAttaque() {
		return attaque;
	}

	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getMoral() {
		return moral;
	}

	public void setMoral(int moral) {
		this.moral = moral;
	}

	public int getChance() {
		return chance;
	}

	public void setChance(int chance) {
		this.chance = chance;
	}

	public int getPvMax() {
		return pvMax;
	}

	public void setPvMax(int pvMax) {
		this.pvMax = pvMax;
	}

	public int getPvAct() {
		return pvAct;
	}

	public void setPvAct(int pvAct) {
		this.pvAct = pvAct;
	}

	public int getInitiative() {
		return initiative;
	}

	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}

	public int getMouvement() {
		return mouvement;
	}

	public void setMouvement(int mouvement) {
		this.mouvement = mouvement;
	}

	public int getDegatMin() {
		return degatMin;
	}

	public void setDegatMin(int degatMin) {
		this.degatMin = degatMin;
	}

	public int getDegatMax() {
		return degatMax;
	}

	public void setDegatMax(int degatMax) {
		this.degatMax = degatMax;
	}
	public int getId() {
		return id;
	}
	
	
	public Vector2i getDegatsEffectues(IUnit u){
		double degatsMin = this.getDegatMin() * this.getNombre();
		double degatsMax = this.getDegatMax() * this.getNombre();
		int multiplicateur = this.getAttaque() - u.getDefense();
		while (multiplicateur > 0){
			degatsMin += degatsMin*1.05;
			degatsMax += degatsMax*1.05;
			multiplicateur--;
		}		
		while (multiplicateur < 0){
			degatsMin += degatsMin*0.95;
			degatsMax += degatsMax*0.95;
			multiplicateur++;
		}
		return new Vector2i((int) degatsMin, (int) degatsMax);		
	}
	
	public void combattre (IUnit u){
		Vector2i degatsMinMax = getDegatsEffectues(u);
		int degatsEffectues = (int) (Math.random()*(degatsMinMax.getY() - degatsMinMax.getX())+degatsMinMax.getX()); 
		
		if (degatsEffectues <=0) //minimun de dégats parce que wouallah
				degatsEffectues = 1;
		u.subirDegats(degatsEffectues);
	}
	
	public void subirDegats(int degatsRecus){
		while(this.getNombre()!=0 && degatsRecus!=0){
			if (degatsRecus>=this.pvAct){
				degatsRecus-=pvAct;
				this.setNombre(this.getNombre()-1);
				if (this.getNombre()!=0){
					this.pvAct=this.pvMax;
				}
				else
					this.pvAct=0;
			}
			else{
				this.pvAct-=degatsRecus;
				degatsRecus=0;
			}
		}
	}
	 
	public void setMort(boolean a){
		this.mort=a;
	}
	

	public boolean getRiposte() {
		return riposte;
	}

	public void setRiposte(boolean b) {
		riposte=b;
	}

	public boolean getMort() {
		return mort;
	}

}
