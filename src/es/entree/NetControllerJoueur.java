package es.entree;

public class NetControllerJoueur extends ControlleurJoueur {

	//Commenter comme ça au moins on sait à quoi c'est censé servir
	@Override
	public boolean isActionned(final int actionCode) {
		return false;
	}

	@Override
	public boolean isJustPress(final int actionCode) {
		return false;
	}
}
