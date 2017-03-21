package es.entree;

import utilitaire.Vector2i;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * Classe permettant d'utiliser la classe de souris
 * Les methodes a utiliser sont celles qui ne retournent pas void
 *
 * Cette classe ne peut servir uniquement par simple instanciation, elle a besoin d'être ajouté en tant que
 * MouseListener, MouseMotionListener, MouseWheelListener dans un composant graphique.
 */
public class Souris extends MouseAdapter {

	private Vector2i framePosition;
	private int usedButton;
	private int mouseWheelPosition;
	private double highResMouseWheelPosition;

	private int lastWheelPos;
	private double highResLastWheelPos;

	public Souris() {
		framePosition = new Vector2i(0,0);
		usedButton = MouseEvent.NOBUTTON;
		mouseWheelPosition = 0;
		highResMouseWheelPosition = 0.0;
		lastWheelPos = 0;
		highResLastWheelPos = 0;
	}

	/**
	 * Methode pour obtenire la position sur l'écran
	 * @return la position sur l'écran
	 */
	public Vector2i getFramePosition() {
		return framePosition;
	}

	/**
	 * Retourne le bouton de la souris utilisé.
	 * MouseEvent.NOBUTTON = 0 = pas de bouton
	 * MouseEvent.BUTTON1 = 1 = clic gauche
	 * MouseEvent.BUTTON2 = 2 = clic molette
	 * MouseEvent.BUTTON3 = 3 = clic droit
	 * @return le code de bouton de souris
	 */
	public int getUsedButton() {
		return usedButton;
	}

	/**
	 * Methode d'obtention de la position de la molette. Position de départ = 0
	 * Supporte les souris à molettes "high resolution"
	 * @return la position de la molette précise
	 */
	public double getHighResMouseWheelPosition() {
		return highResMouseWheelPosition;
	}

	/**
	 * Methode d'obtention de la position de la molette. Position de départ
	 * @return la position de la molette
	 */
	public int getMouseWheelPosition() {
		return mouseWheelPosition;
	}

	/**
	 * Retourne la différence de "click" entre cet appel de méthode et l'appel de methode précédent cet appel
	 * @return la difference de "click"
	 */
	public double getHighResClickDifference() {
		double diff = this.highResMouseWheelPosition - this.highResLastWheelPos;
		this.highResLastWheelPos = this.highResMouseWheelPosition;
		return diff;
	}

	/**
	 * Retourne la différence de "click" entre cet appel de methode et l'appele de methode précédent cet appel
	 * @return la différence de "click"
	 */
	public int getClickDifference() {
		int diff = this.mouseWheelPosition - this.lastWheelPos;
		this.lastWheelPos = this.mouseWheelPosition;
		return diff;
	}

	public String toString() {
		return "Position on screen : x=" + this.framePosition.x + ", y=" + this.framePosition.y + '\n' +
				"   --   Bouton appuyé : " + this.usedButton + '\n' + "   --   Position Molette : " +
				this.mouseWheelPosition + '\n' + "   --   Position Molette High Res : " + highResMouseWheelPosition;
	}

	@Override
	public void mouseMoved(final MouseEvent e) {
		this.framePosition = new Vector2i(e.getX(), e.getY());
	}

	@Override
	public void mouseDragged(final MouseEvent e) {
		this.framePosition = new Vector2i(e.getX(), e.getY());
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		this.usedButton = e.getButton();
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		this.usedButton = MouseEvent.NOBUTTON;
	}

	@Override
	public void mouseWheelMoved(final MouseWheelEvent e) {
		this.mouseWheelPosition += e.getWheelRotation();
		this.highResMouseWheelPosition += e.getPreciseWheelRotation();
	}
}
