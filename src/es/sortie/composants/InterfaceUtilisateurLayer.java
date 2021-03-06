package es.sortie.composants;

import es.dataManager.ImageManager;
import es.sortie.ImageConteneur;
import utilitaire.IntRect;
import utilitaire.Vector2i;

import java.awt.*;
import java.util.*;

/**
 * Classe permettant de contenir les informations par rapport a l'UI
 */
public class InterfaceUtilisateurLayer extends AbstractBufferComposant {

	//La position en pixels des images sur la carte
	private final java.util.List<ImageConteneur> imgConteneur = new ArrayList<>();
	//Les strings a ajouter, appelera la methode toString
	private final Map<Vector2i, Object> iObjectMap = new HashMap<>();

	/**
	 * Constructeur de l'UILayer
	 */
	public InterfaceUtilisateurLayer() {
		super(null, null);
	}

	/**
	 * Permet de vider les cartes
	 */
	public void emptyContainers() {
		imgConteneur.clear();
	}

	/**
	 * Permet d'ajouter une image sur l'ecran de l'ui
	 * @param ic : le conteneur d'image
	 */
	public void ajouterImageUI(ImageConteneur ic) {
		synchronized (imgConteneur) {
			imgConteneur.add(ic);
			imgConteneur.sort(ImageConteneur::compareTo);
		}
	}

	/**
	 * Permet de retirer une image du layer de l'ui
	 * @param ic : l'image en question
	 */
	public void retirerImageUI(ImageConteneur ic) {
		synchronized (imgConteneur) {
			this.imgConteneur.remove(ic);
		}
	}

	/**
	 * Permet d'ajouter une chaine a écrire sur l'écran :
	 * @param v : la position en pixel
	 * @param o : l'objet ( methode toString appelé )
	 */
	public void ajouterDonnesUI(Vector2i v, Object o) {
		iObjectMap.put(v, o);
	}

	/**
	 * Methode dessinant tous les objets servant a l'utilisateur
	 * @param g : l'objet graphique
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		synchronized (imgConteneur) {
			for (ImageConteneur ic : imgConteneur) {
				IntRect ir = ic.getImageDrawingArea();
				g2.drawImage(
						ImageManager.getImage(ic.getImagePath()),
						ir.x, ir.y, ir.width, ir.height,
						this
				);
				++AntiTearBuffer.RENDERED_IMAGES;
			}
		}

		Color c = g2.getColor();
		g2.setColor(Color.BLACK);
		synchronized (iObjectMap) {
			//Memory Unoptimized
			Set<Vector2i> vector2is = iObjectMap.keySet();
			for (Vector2i vi : vector2is) {
				g2.drawString(iObjectMap.get(vi).toString(), vi.x, vi.y);
			}
		}
		g2.setColor(c);
	}

	//Voir la javadoc de la super classe abstraite
	@Override
	void dessiner(final Graphics g) {
		paintComponent(g);
	}

	public String toString() {
		return "Taille liste : " + this.imgConteneur.size();
	}
}
