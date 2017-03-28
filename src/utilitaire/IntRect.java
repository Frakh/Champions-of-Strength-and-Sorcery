package utilitaire;


public class IntRect {

	public int x,y,width,height;

	public IntRect(final int x, final int y, final int width, final int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean contains(Vector2i v) {
		return v.x > x && v.x < x+width && v.y > y && v.y < y+height;
	}
}
