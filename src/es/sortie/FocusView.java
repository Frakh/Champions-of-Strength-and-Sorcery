package es.sortie;


public class FocusView {

	private double lengthShift, heightShift;
	private double blockMultiplier;
	private boolean applyBlockMultiplier;

	public static final double DEF_LENGTH_SHIFT = 0, DEF_HEIGHT_SHIFT = 0, DEF_MULTIPLIER = 1;
	public static final boolean DEF_BLOCK_MULTIPLIER_APPLICATION = false;

	public FocusView() {
		lengthShift = DEF_LENGTH_SHIFT;
		heightShift = DEF_HEIGHT_SHIFT;
		blockMultiplier = DEF_MULTIPLIER;
		applyBlockMultiplier = DEF_BLOCK_MULTIPLIER_APPLICATION;
	}

}
