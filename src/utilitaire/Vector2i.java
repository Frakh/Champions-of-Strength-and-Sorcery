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

	// 4 methodes boilerplate
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

	public IPosition toPosition() {
		return new Position(x, y);
	}
}
