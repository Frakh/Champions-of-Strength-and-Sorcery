package game.carte.elements;

import es.interfaces.ISpriteDrawable;

public enum Ressources implements ISpriteDrawable {
	OR("OR.png"),
	BOIS("BOIS.png"),
	MINERAI("MINERAI.png"),
	MERCURE("MERCURE.png"),
	SOUFFRE("SOUFFRE.png"),
	CRISTAUX("CRISTAUX.png"),
	GEMMES("GEMMES.png");

	private String sprite;
	
	Ressources(String spritePath){
		this.sprite = spritePath;
	}
	
	@Override
	public String getImage() {
		return "./assets/img/"+sprite;
	}

}
