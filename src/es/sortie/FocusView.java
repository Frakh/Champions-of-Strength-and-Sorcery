package es.sortie;


import utilitaire.IPosition;

/*
Classe contenant des informations par rapport au point a afficher

CETTE CLASSE NE DEVRAIT PAS ÊTRE UTILISE EN DEHORS DU PACKAGE es
 */
public class FocusView {

	// Position a suivre. Est censé être une référence vers la position qui peut être modifié
	private IPosition followPos;
	// Le frame manager
	private FrameManager frameManager;

	/**
	 * Permet de construire le focus view avec le frame manager
	 * @param frame
	 */
	FocusView(FrameManager frame) {
		this.frameManager = frame;
	}

	/**
	 * Constructeur avec le manager et la position a suivre
	 * @param manager : le frame manager
	 * @param ip la position a suivre
	 */
	FocusView(FrameManager manager, IPosition ip) {
		this(manager);
		this.followPos = ip;
	}

	/**
	 * Donne la quantité de pixel sur le plan horizontal a déplacer pour placer l'image au bon endroit
	 * @return la quantité de pixel sur le plan horizontal a déplacer pour placer l'image au bon endroit
	 */
	public int getXDeplacement() {
		return (int) ((frameManager.getWidth()/2)-(frameManager.getSpriteWidth()*this.followPos.getX()));
	}

	/**
	 * Donne la quantité de pixel sur le plan vertical a déplacer pour placer l'image au bon endroit
	 * @return la quantité de pixel sur le plan vertical a déplacer pour placer l'image au bon endroit
	 */
	public int getYDeplacement() {
		return (int) ((frameManager.getHeight()/2)-(frameManager.getSpriteHeigt()*this.followPos.getY()));
	}

	/**
	 * Retourne une copie de la positin a suivre
	 * @return la copie de la position a suivre
	 */
	public IPosition getCentralPos() {
		return this.followPos.copy();
	}

	public void setPositionToFollow(final IPosition positionToFollow) {
		this.followPos = positionToFollow;
	}
}
