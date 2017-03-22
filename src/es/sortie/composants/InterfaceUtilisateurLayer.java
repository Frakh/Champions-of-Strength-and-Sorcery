package es.sortie.composants;

import es.dataManager.ImageManager;
import utilitaire.Vector2i;

import java.awt.*;
import java.util.Map;
import java.util.Set;

public class InterfaceUtilisateurLayer extends AbstractBufferComposant {

	private Map<Vector2i, String> mapPositionImage;

	public InterfaceUtilisateurLayer() {
		super(null, null);
	}


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

	@Override
	void dessiner(final Graphics g) {

	}
}
