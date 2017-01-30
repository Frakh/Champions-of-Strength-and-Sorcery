package game;

public interface IUnite {
	public int getMoral();
	public void setMoral(int moral);
	public int getChance();
	public void setChance(int chance);
	public int getNombre();
	public void setNombre(int nombre);
	public void setAttaque(int attaque);
	public void setDefense(int defense);
	public void setDegatsMin(int degatsMin);
	public void setDegatsMax(int degatsMax);
	public void setPvMax(int pvMax);
	public void setInitiative(int initiative);
	public void setMouvement(int mouvement);
	public void setPortee(int portee);
	public void setMunitions(int munitions);
	public int getAttaque();
	public int getDefense();
	public int getDegatsMin();
	public int getDegatsMax();
	public int getPvMax();
	public int getPvActuels();
	public void setPvActuels(int pvAct);
	public int getInitiative();
	public int getMouvement();
	public boolean isDistance();
	public int getPortee();
	public int getMunitions();
}
