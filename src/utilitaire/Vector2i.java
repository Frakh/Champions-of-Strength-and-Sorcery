package utilitaire;

/**
 * Classe Vector2i
 * Stocke 2 entiers capable d'être modifié instantanément
 * Inspiré de la classe sf::Vector2 dans la bibliothèque SFML
 */
public class Vector2i {

	//Demander si mettre volatile != faire des conneries
	public /*volatile*/ int x;
	public /*volatile*/ int y;
	 
	public Vector2i(int a, int b){
		x=a;
		y=b;
	}

	public Vector2i(Vector2i v) {
		this.x = v.x;
		this.y = v.y;
	}

	//Code boilerplate
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// Methode boilerplate
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public IPosition toPosition() {
		return new Position(x, y);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Vector2i vector2i = (Vector2i) o;

		return x == vector2i.x && y == vector2i.y;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = Short.MAX_VALUE * result + y;
		return result;
	}
}
