package game.artefacts;

import game.Heros;
import game.IArtefact;

public class Artefact implements IArtefact {

	private String type;
	private Heros heros;
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

	public Artefact(Heros heros, String type, int attaque, int defense, int magieMin, int magieMax, int puissanceMagie, int connaissance, int moral, int chance, int experience, double ptDeplacement) {
		this.heros = heros;
		this.type = type;
		this.attaque = attaque;
		this.defense = defense;
		this.magieMin = magieMin;
		this.magieMax = magieMax;
		this.puissanceMagie = puissanceMagie;
		this.connaissance = connaissance;
		this.moral = moral;
		this.chance = chance;
		this.experience = experience;
		this.ptDeplacement= ptDeplacement;
	}

	@Override
	public void equiper() {
		if(type.equals("arme")) {
			if(heros.getArmeDroite() != null)
				heros.getArmeDroite().retirer();
			heros.setArmeDroite(this);
		}
		heros.setAttaque(heros.getAttaque()+ attaque);
		heros.setDefense(heros.getAttaque()+ defense);
		heros.setMagieMin(heros.getAttaque()+ magieMin);
		heros.setMagieMax(heros.getAttaque()+ magieMax);
		heros.setPuissanceMagie(heros.getAttaque()+ puissanceMagie);
		heros.setConnaissance(heros.getAttaque()+ connaissance);
		heros.setMoral(heros.getAttaque()+ moral);
		heros.setChance(heros.getAttaque()+ chance);
		heros.setExperience(heros.getAttaque()+ experience);
		heros.setPtDeplacement(heros.getAttaque()+ ptDeplacement);
	}

	@Override
	public void retirer() {
		// TODO Auto-generated method stub

	}
}