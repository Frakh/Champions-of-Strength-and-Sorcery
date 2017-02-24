package game.combat;

public class CaseCombat {
	boolean franchissable;
	IUnit unit;
	
	public CaseCombat(){
		franchissable = true;
	}
	public boolean getFranchissable(){
		return franchissable;
	}
	public IUnit getUnit() {
		return unit;
	}
	public void setUnit(IUnit troupe) {
		this.unit = troupe;
	}
	//ceci est un message de test pour savoir si je peux oush et pull, donc vous allez probablement être flood de commits/push/pulls. =et c'est bien fait pour vous.
	
}
