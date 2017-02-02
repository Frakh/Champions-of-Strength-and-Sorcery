package es.interfaces;

public interface IController {

	int NUMBER_MAX_OF_KEY_BINDING = 256;

	void setActionMap(int actionCode, int keyCode);

	boolean isActionned(int actionCode);

	boolean isActionned(int ... actionCode);

	boolean isJustPress(int actionCode);

}
