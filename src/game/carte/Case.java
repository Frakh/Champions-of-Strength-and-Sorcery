package game.carte;

public enum Case implements es.interfaces.ISpriteDrawable{

	// Convention : les elements enum sont ecris en majuscule

	HERBE("HERBE.png",1.0),
	TERRE("TERRE.png",1.0),
	LAVE("LAVE.png",1.0),
	SOUTERRAIN("SOUTERRAIN.png",1.0),
	ROCHE("ROCHE.png",0.0),
	TUNDRA("TUNDRA.png",1.25),
	SABLE("SABLE.png",1.5),
	NEIGE("NEIGE.png",1.5),
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