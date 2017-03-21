package utilitaire;

public class Vector2i {
	public int x;
	public int y;
	 
	public Vector2i(int a, int b){
		x=a;
		y=b;
	}

	public Vector2i(Vector2i v) {
		this.x = v.x;
		this.y = v.y;
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
