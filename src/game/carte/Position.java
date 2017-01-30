package game;

import game.carte;

public class Position implements IPosition {

	private double x;	// X coordinnate
	private double y;	// Y coordinate
	private boolean is_const;	// Is (not?) constant property

	/**
	 * Default Basic Constructor
	 */
	public Position() {
		x=0;
		y=0;
		is_const = false;
	}

	/**Copy Constructor
	 * @param p : Position object which will be copied
	 */
	public Position(Position p) {
		x = p.x;
		y = p.y;
		is_const = p.is_const;
	}

	/**
	 * CTor avec IPosition en param
	 * @param p l'IPosition a copier
	 */
	public Position(IPosition p) {
		x = p.getX();
		y = p.getY();
	}

	/**Constructor with parameters of build
	 * @param x : x coordinate
	 * @param y : y coordinate
	 */
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
		is_const = false;
	}

	/**Cosntructor with more parameter of build
	 * @param x : x coordinate
	 * @param y : y coordinate
	 * @param is_c : is constant ( or not ) parameter
	 */
	public Position(double x, double y, boolean is_c) {
		this(x, y);
		is_const = is_c;
	}

	/**Set method to set values, constant field won't be copied
	 * @param p : position, source of values that will be copied
	 */
	public void set(IPosition p) {
		set(p.getX(), p.getY());
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	/**Set mthod to set values
	 * @param x : x value
	 * @param y : y value
	 */
	public void set(double x, double y) {
		if (is_const) {
			throw new IllegalStateException("Class : Position - Method : set(int, int);\n"
					+ "Method is supposed to change values of the object, while is declared as constant");
		}
		this.x = x;
		this.y = y;
	}

	/**Move method, "move" the position
	 * @param x : x value, will be moved Object.x + x
	 * @param y : y value, will be moved Object.y + y
	 */
	public void move(double x, double y) {
		set(this.x + x, this.y + y);
	}

	/**Move method
	 * @param p : Position, source of values that will be copied
	 */
	public void move(IPosition p) {
		set(x+p.getX(), y+p.getY());
	}

	/**Deplace method, create new Position object with the data ( parameters and current object )
	 * Current object won't be modified
	 * @param x : x value
	 * @param y : y value
	 * @return : new Position, that will have x and y coordinate added with the object one.
	 */
	public Position deplace(double x, double y) {
		return new Position(this.x+x, this.y+y);
	}

	/**Deplace method, None of the object ( this and p references ) will be modified
	 * @param p : position
	 * @return : new Position, that have the values of the current, and p position added.
	 */
	public Position deplace(IPosition p) {
		return deplace(p.getX(), p.getY());
	}

	/**Method computing distance between 2 position
	 * @param p : position
	 * @return : double value;
	 */
	public double distanceBetween(IPosition p) {
		return Math.sqrt((p.getX()-x)*(p.getX()-x) + (p.getY()-y)*(p.getY()-y));
	}

	@Override
	public IPosition roundByDefault() {
		x = (int)x;
		y = (int)y;
		return this;
	}

	@Override
	public IPosition copy() {
		return new Position(this);
	}

	public String toString() {
		return "Class : Position\nValues : x = " + x + " - y = " + y;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Position position = (Position) o;

		return x == position.x && y == position.y;

	}

	@Override
	public int hashCode() {
		return (int) (31 * x + y);
	}
}
