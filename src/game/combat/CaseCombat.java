package game.combat;

public class CaseCombat {

	boolean franchissable;
	IUnit unit;
	
	public CaseCombat(){
		franchissable = true;
		unit=null;
	}
	public boolean getFranchissable(){
		return franchissable;
	}
	public void setFranchissable( boolean a){
		franchissable=a;
	}
	public IUnit getUnit() {
		return unit;
	}
	public void setUnit(IUnit troupe) {
		this.unit = troupe;
	}
	
}
