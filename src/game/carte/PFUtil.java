package game.carte;

public class PFUtil {
	int coord;
	boolean event;
	
	
	public PFUtil(){
		coord=-1;
		event=false;
	}
	
	public int getCoord() {
		return coord;
	}
	public void setCoord(int coord) {
		this.coord = coord;
	}
	public boolean isEvent() {
		return event;
	}
	public void setEvent(boolean event) {
		this.event = event;
	}
}
