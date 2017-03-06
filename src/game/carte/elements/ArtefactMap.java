package game.carte.elements;

import game.Heros;
import game.artefacts.Artefact;
import game.carte.IElement;

public abstract class ArtefactMap implements IElement {
	private Artefact art; //l'artefact auquel l'élémnt de la carte correspond
	
	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void interagir(Heros oui) {
		//oui.ramasserArtefact(id);

	}

	@Override
	public abstract String decrire() ;

}
