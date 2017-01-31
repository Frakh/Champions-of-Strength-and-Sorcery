package game.carte;

public enum Case implements es.interfaces.ISpriteDrawable{
	Herbe("",1.0),
	Terre("",1.0),
	Lave("",1.0),
	Souterrain("",1.0),
	Roche("",0.0),
	Toundra("",1.25),
	Sable("",1.5),
	Neige("",1.5),
	Marais("",1.75),
	Eau("",0.0);

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