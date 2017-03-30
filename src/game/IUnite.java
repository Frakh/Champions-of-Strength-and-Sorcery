package game;

public interface IUnite {
	int getMoral();
	void setMoral(int moral);
	int getChance();
	void setChance(int chance);
	int getNombre();
	void setNombre(int nombre);
	void setAttaque(int attaque);
	void setDefense(int defense);
	void setDegatsMin(int degatsMin);
	void setDegatsMax(int degatsMax);
	void setPvMax(int pvMax);
	void setInitiative(int initiative);
	void setMouvement(int mouvement);
	void setPortee(int portee);
	void setMunitions(int munitions);
	int getAttaque();
	int getDefense();
	int getDegatsMin();
	int getDegatsMax();
	int getPvMax();
	int getPvActuels();
	void setPvActuels(int pvAct);
	int getInitiative();
	int getMouvement();
	boolean isDistance();
	int getPortee();
	int getMunitions();
}
