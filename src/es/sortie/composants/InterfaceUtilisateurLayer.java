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

	/**
	 * Constructeur de l'UILayer
	 */
	public InterfaceUtilisateurLayer() {
		super(null, null);
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
