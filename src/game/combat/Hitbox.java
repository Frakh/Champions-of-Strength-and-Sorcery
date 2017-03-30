package game.combat;

import utilitaire.IntRect;
import utilitaire.Vector2i;

public class Hitbox extends IntRect{
	
	public Hitbox(final int x, final int y, final int width, final int height){
		super(x, y, width, height);
	}
	
	public boolean intersect(Hitbox h){
		boolean sale = false;
		
		
		
		for(int i = h.x; i <= h.x+h.width; i++)
			for(int j = h.y; j <= h.y+h.width; j++)
				sale |= contains(new Vector2i(i,j));
		return sale;
	}
}
