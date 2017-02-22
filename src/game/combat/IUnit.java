package game.combat;

public interface IUnit {
	public int getId();
	public boolean getArmeeGauche();
	public void setArmeeGauche(boolean armeeGauche);
	public int getNombre();
	public void setNombre(int nombre);
	public int getAttaque();
	public void setAttaque(int attaque);
	public int getDefense();
	public void setDefense(int defense);
	public int getMoral();
	public void setMoral(int moral);
	public int getChance();
	public void setChance(int chance);
	public int getPvMax();
	public void setPvMax(int pvMax);
	public int getPvAct();
	public void setPvAct(int pvAct);
	public int getInitiative();
	public void setInitiative(int initiative);
	public int getMouvement();
	public void setMouvement(int mouvement);
	public int getDegatMin();
	public void setDegatMin(int degatMin);
	public int getDegatMax();
	public void setDegatMax(int degatMax);
	public void combattre(IUnit u);
	public void mourir();
}