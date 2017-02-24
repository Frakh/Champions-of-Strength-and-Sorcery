package utilitaire;

public class Vector2i {
	int x;
	int y;
	 
	public Vector2i(int a, int b){
		x=a;
		y=b;
	}
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
