package es.entree;

public class NetControllerJoueur extends ControlleurJoueur {


	@Override
	public boolean isActionned(final int actionCode) {
		return false;
	}

	@Override
	public boolean isJustPress(final int actionCode) {
		return false;
	}
}
