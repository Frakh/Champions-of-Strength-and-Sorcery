package es.sortie;


import utilitaire.IPosition;

public class FocusView {

	private IPosition followPos;
	private FrameManager frameManager;

	public FocusView(FrameManager frame) {
		this.frameManager = frame;
	}

	public FocusView(FrameManager manager, IPosition ip) {
		this(manager);
		this.followPos = ip;
	}

	public int getXDeplacement() {
		return (int) ((frameManager.getLength()/2)-(frameManager.getSpriteLength()*this.followPos.getX()));
	}

	public int getYDeplacement() {
		return (int) ((frameManager.getHeight()/2)-(frameManager.getSpriteHeigt()*this.followPos.getY()));
	}

	public IPosition getCentralPos() {
		return this.followPos.copy();
	}

}
