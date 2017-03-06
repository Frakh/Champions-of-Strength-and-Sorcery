package game.carte;

public enum Case implements es.interfaces.ISpriteDrawable{

	// Convention : les elements enum sont ecris en majuscule

	HERBE("HERBE.png",100),
	TERRE("TERRE.png",100),
	LAVE("LAVE.png",100),
	SOUTERRAIN("SOUTERRAIN.png",100),
	ROCHE("ROCHE.png", 0),
	TUNDRA("TUNDRA.png",125),
	SABLE("SABLE.png",150),
	NEIGE("NEIGE.png",150),
	MARAIS("",175),
	EAU("",0);

	private String sprite;
	private int mvtCost = 0;
	
	Case(String spritePath, int cost){
		this.sprite = spritePath;
		this.mvtCost = cost;
	}
	
	public int getMvtCost() {
		return mvtCost;
	}
	
	@Override
	public String getImage() {
		// Permet de ne pas retaper "assets/img/" dans le CTOR de l'enum
		return "./assets/img/"+sprite;
	}
}