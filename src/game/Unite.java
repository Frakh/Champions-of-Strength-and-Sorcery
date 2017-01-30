package game;

public class Unite {
	int moral;
	int chance;
	int nombre;
	int attaque;
	int defense;
	int degatsMin;
	int degatsMax;
	int pvMax;
	int pvActuels;
	int initiative;
	int mouvement;
	int portee;
	int munitions = 0;
	
	public Unite(int nb, int atk, int def, int degMin, int degMax, int pvMax, int init, int mov, int por, int mun){
		nombre = nb;
		attaque=atk;
		defense=def;
		degatsMin=degMin;
		degatsMax=degMax;
		this.pvMax=pvMax;
		pvActuels=this.pvMax;
		initiative = init;
		mouvement = mov;
		this.portee=por;
		munitions = mun;
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
	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void setDegatsMin(int degatsMin) {
		this.degatsMin = degatsMin;
	}

	public void setDegatsMax(int degatsMax) {
		this.degatsMax = degatsMax;
	}

	public void setPvMax(int pvMax) {
		this.pvMax = pvMax;
	}

	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}

	public void setMouvement(int mouvement) {
		this.mouvement = mouvement;
	}

	public void setPortee(int portee) {
		this.portee = portee;
	}

	public void setMunitions(int munitions) {
		this.munitions = munitions;
	}

	public int getAttaque() {
		return attaque;
	}
	public int getDefense() {
		return defense;
	}
	public int getDegatsMin() {
		return degatsMin;
	}
	public int getDegatsMax() {
		return degatsMax;
	}
	public int getPvMax() {
		return pvMax;
	}
	public int getPvActuels() {
		return pvActuels;
	}	
	public void setPvActuels(int pvAct) {
		if (pvAct< pvMax){
			this.pvActuels=pvAct;
		}
	}
	public int getInitiative() {
		return initiative;
	}
	public int getMouvement() {
		return mouvement;
	}
	public boolean isDistance() {
		return !(portee == 0);
	}
	public int getPortee() {
		return portee;
	}
	public int getMunitions() {
		return munitions;
	}

}
