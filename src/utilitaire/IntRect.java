package utilitaire;

public class IntRect {

	public int x, y, width, height;

	public IntRect(final int x, final int y, final int width, final int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public IntRect(Vector2i xy, Vector2i wh) {
		this(xy.x, xy.y, wh.x, wh.y);
	}

	public IntRect(Vector2i xy, int width, int height) {
		this(xy.x, xy.y, width, height);
	}

	public IntRect(int x, int y, Vector2i wh) {
		this(x, y, wh.x, wh.y);
	}

	public void setXY(Vector2i xy) {
		x = xy.x;
		y = xy.y;
	}

	public void setWidthHeight(Vector2i wh) {
		width = wh.x;
		height = wh.y;
	}

	public Vector2i[] asVectorArray() {
		return new Vector2i[]{getXY(), getWidthHeight()};
	}

	public Vector2i getXY() {
		return new Vector2i(x,y);
	}

	public Vector2i getWidthHeight() {
		return new Vector2i(width, height);
	}

	public Vector2i getTopRight() {
		return new Vector2i(x+width,y);
	}

	public Vector2i getBottomLeft() {
		return new Vector2i(x,y+height);
	}

	public Vector2i getBottomRight() {
		return new Vector2i(x+width, y+height);
	}

	public Vector2i[] getAllPoints() {
		return new Vector2i[] {
				getXY(),
				getTopRight(),
				getBottomLeft(),
				getBottomRight()
		};
	}

	public boolean contains(Vector2i v) {
		return v.x > x && v.x < x + width && v.y > y && v.y < y + height;
	}

	public boolean intersect(IntRect ir) {
		Vector2i[] irpoints = ir.getAllPoints();
		Vector2i[] thatpoints = ir.getAllPoints();
		for (Vector2i v : irpoints) {
			if (contains(v))
				return true;
		}
		for (Vector2i v : thatpoints) {
			if (ir.contains(v))
				return true;
		}
		return false;
	}
}
