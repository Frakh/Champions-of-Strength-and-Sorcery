package game.combat;
import game.IUnite;

public class CaseCombat {
	boolean franchissable;
	IUnite unit;
	
	public CaseCombat(){
		franchissable = true;
	}
	public boolean getFranchissable(){
		return franchissable;
	}
	public IUnite getUnit() {
		return unit;
	}

	public void setUnit(IUnite armeeGauche) {
		this.unit = armeeGauche;
	}
	
	
}
