package es.interfaces;

public interface IPosition {

	/**
	 * Getter sur X
	 * @return la valeur de x
	 */
	double getX();

	/**
	 * Getter sur Y
	 * @return return la valeur de y
	 */

	double getY();
	/**
	 * Methode pour parametrer la position
	 * @param x la position x
	 * @param y la position y
	 */
	void set(double x, double y);

	/**
	 * Methode pour parametrer la position
	 * @param p la position a copier
	 */
	void set(IPosition p);

	/**
	 * Bouge l'objet de +x et +y cases
	 * @param x le nombre qui sera additionné a x
	 * @param y le nombre qui sera additionné a y
	 */
	void move(double x, double y);

	/**
	 * Bouge l'objet utilisant la position
	 * @param p l'objet a copier
	 */
	void move(IPosition p);

	/**
	 * Retourne une nouvelle position avec un decalage de +x et +y cases
	 * @param x le nombre de case a déplacer horizontalement
	 * @param y le nombre de case a déplacer vertialement
	 * @return la position
	 */
	IPosition deplace(double x, double y);

	/**
	 * Retourne une nouvelle position avec un décalage par rapport a cette position
	 * @param position la position
	 * @return une nouvelle position
	 */
	IPosition deplace(IPosition position);

	/**
	 * Retourne une position entre les 2 position
	 * @param p l'autre position
	 * @return la nouvelle position
	 */
	double distanceBetween(IPosition p);

	IPosition roundByDefault();

	IPosition copy();

}
