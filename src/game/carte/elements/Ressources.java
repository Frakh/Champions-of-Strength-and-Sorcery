package game.carte.elements;

import game.Heros;
import game.carte.IElement;

public enum Ressources implements IElement {
	OR("OR.png"),
	BOIS("BOIS.png"),
	MINERAI("MINERAI.png"),
	MERCURE("MERCURE.png"),
	SOUFFRE("SOUFFRE.png"),
	CRISTAUX("CRISTAUX.png"),
	GEMMES("GEMMES.png"),
	COFFRE("");

	private String sprite;
	
	Ressources(String spritePath){
		this.sprite = spritePath;
	}
	
	@Override
	public String getImage() {
		return "./assets/img/"+sprite;
	}

	@Override
	public void interagir(Heros oui) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String decrire() {
		// TODO Auto-generated method stub
		return null;
	}

}
