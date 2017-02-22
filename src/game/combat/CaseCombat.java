<<<<<<< HEAD
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
=======
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
	
	
}
>>>>>>> branch 'master' of https://github.com/Frakh/Champions-of-Strength-and-Sorcery.git
