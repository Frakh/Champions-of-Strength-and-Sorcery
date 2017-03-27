package es.sortie.composants;

import es.dataManager.ImageManager;
import utilitaire.Vector2i;

import java.awt.*;
import java.util.Map;
import java.util.Set;

/**
 * Classe permettant de contenir les informations par rapport a l'UI
 * A terminé, pas complète
 */
public class InterfaceUtilisateurLayer extends AbstractBufferComposant {

	//La position en pixels des images sur la carte
	private Map<Vector2i, String> mapPositionImage;
	private Map<Vector2i, Object> donnes_pos_img;

	/**
	 * Constructeur de l'UILayer
	 */
	public InterfaceUtilisateurLayer() {
		super(null, null);
	}

	/**
	 * Permet de vider les cartes
	 */
	public void emptyMaps() {
		mapPositionImage.clear();
		donnes_pos_img.clear();
	}

	/**
	 * Permet d'ajouter une image sur l'ecran de l'ui
	 * @param v : la position en pixel
	 * @param s : l'adresse sur le disque de l'image
	 */
	public void ajouterImageUI(Vector2i v, String s) {
		mapPositionImage.put(v,s);
	}

	/**
	 * Permet d'ajouter une chaine a écrire sur l'écran :
	 * @param v : la position en pixel
	 * @param o : l'objet ( methode toString appelé )
	 */
	public void ajouterDonnesUI(Vector2i v, Object o) {
		donnes_pos_img.put(v, o);
	}

	/**
	 * Methode dessinant tous les objets servant a l'utilisateur
	 * @param g : l'objet graphique
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		// Pas optimisé au niveau mémoire
		Set<Vector2i> coorset = mapPositionImage.keySet();

		for (Vector2i vi : coorset) {
			g2.drawImage(
					ImageManager.getImage(mapPositionImage.get(vi)),
					vi.x,
					vi.y,
					this
			);
			++AntiTearBuffer.RENDERED_IMAGES;
		}
	}

	//Voir la javadoc de la super classe abstraite
	@Override
	void dessiner(final Graphics g) {

	}
}
