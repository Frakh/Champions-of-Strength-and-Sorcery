package game.combat;

import utilitaire.Vector2i;

public interface IUnit {
	int getId();
	boolean getArmeeGauche();
	void setArmeeGauche(boolean armeeGauche);
	int getNombre();
	void setNombre(int nombre);
	int getAttaque();
	void setAttaque(int attaque);
	int getDefense();
	void setDefense(int defense);
	int getMoral();
	void setMoral(int moral);
	int getChance();
	void setChance(int chance);
	int getPvMax();
	void setPvMax(int pvMax);
	int getPvAct();
	void setPvAct(int pvAct);
	int getInitiative();
	void setInitiative(int initiative);
	int getMouvement();
	void setMouvement(int mouvement);
	int getDegatMin();
	void setDegatMin(int degatMin);
	int getDegatMax();
	void setDegatMax(int degatMax);
	String getDescription();
	String getPouvoir();
	boolean getAJoue();//renvoie si l'unite a deja joue
	void setAJoue(boolean b);
	boolean getRiposte();//renvoie si l'unite a deja joue
	void setRiposte(boolean b);
	boolean getMort();
	void setMort(boolean b);
	void combattre(IUnit u);
	Vector2i getDegatsEffectues(IUnit unit);
	void subirDegats(int degatsEffectues);
}
