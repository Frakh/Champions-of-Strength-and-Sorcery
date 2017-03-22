package game.carte.elements;

public enum Heros implements es.interfaces.ISpriteDrawable{
	// Convention : les elements enum sont ecris en majuscule
	
	JESUISLECAC("heros.jpg"),
	JESUISLEMAGIE("ennemi1.jpg"),
	SAINTTAPIR("sainttapir.png");

	private String sprite;
	
	Heros(String spritePath){
		this.sprite = spritePath;
	}
	
	@Override
	public String getImage() {
		return "./assets/img/"+sprite;
	}

}
