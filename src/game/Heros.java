package game;

import java.util.ArrayList;

public class Heros {
	
	public IUnite[] armee;
	private final static int NBUNITES = 7;
	private ICapacite[] capacites;
	private final static int NBCAPACITES = 10;
	private int attaque;
	private int defense;
	private int magieMin;
	private int magieMax;
	private int puissanceMagie;
	private int connaissance;
	
	private int moral;
	private int chance;
	private int experience;
	private double ptDeplacement;
	
	private IArtefact armeDroite;
	private IArtefact armeGauche;
	private IArtefact armure;
	private IArtefact botte;
	private ArrayList<IArtefact> sac;
	
	
	public Heros() {
		armee = new IUnite[NBUNITES];
		capacites = new ICapacite[NBCAPACITES];
	}
	
	
	public IArtefact getArmeDroite() {
		return armeDroite;
	}


	public void setArmeDroite(IArtefact armeDroite) {
		this.armeDroite = armeDroite;
	}


	public IArtefact getArmeGauche() {
		return armeGauche;
	}


	public void setArmeGauche(IArtefact armeGauche) {
		this.armeGauche = armeGauche;
	}


	public IArtefact getArmure() {
		return armure;
	}


	public void setArmure(IArtefact armure) {
		this.armure = armure;
	}


	public IArtefact getBotte() {
		return botte;
	}


	public void setBotte(IArtefact botte) {
		this.botte = botte;
	}


	public ArrayList<IArtefact> getSac() {
		return sac;
	}


	public void setSac(ArrayList<IArtefact> sac) {
		this.sac = sac;
	}
	
	public void addSac(IArtefact artefact) {
		sac.add(artefact);
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


	public int getMagieMin() {
		return magieMin;
	}


	public void setMagieMin(int magieMin) {
		this.magieMin = magieMin;
	}


	public int getMagieMax() {
		return magieMax;
	}


	public void setMagieMax(int magieMax) {
		this.magieMax = magieMax;
	}


	public int getPuissanceMagie() {
		return puissanceMagie;
	}


	public void setPuissanceMagie(int puissanceMagie) {
		this.puissanceMagie = puissanceMagie;
	}


	public int getConnaissance() {
		return connaissance;
	}


	public void setConnaissance(int connaissance) {
		this.connaissance = connaissance;
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


	public int getExperience() {
		return experience;
	}


	public void setExperience(int experience) {
		this.experience = experience;
	}


	public double getPtDeplacement() {
		return ptDeplacement;
	}


	public void setPtDeplacement(double ptDeplacement) {
		this.ptDeplacement = ptDeplacement;
	}

}
