package game.carte;

public enum Case implements es.interfaces.ISpriteDrawable{

	// Convention : les elements enum sont ecris en majuscule

	HERBE("",1.0),
	TERRE("",1.0),
	LAVE("",1.0),
	SOUTERRAIN("",1.0),
	ROCHE("",0.0),
	TUNDRA("",1.25),
	SABLE("",1.5),
	NEIGE("",1.5),
	MARAIS("",1.75),
	EAU("",0.0);

	private String sprite;
	private double mvtCost = 0;
	
	Case(String spritePath, double cost){
		this.sprite = spritePath;
		this.mvtCost = cost;
	}
	
	public double getMvtCost() {
		return mvtCost;
	}
	
	@Override
	public String getImage() {
		// Permet de ne pas retaper "assets/img/" dans le CTOR de l'enum
		return "./assets/img/"+sprite;
	}
}