package utilitaire;

import java.util.HashMap;
import java.util.Map;

public class ConteneurGeneric {

	private Map<String, Object> attr_map;

	public ConteneurGeneric() {
		attr_map = new HashMap<>();
	}

	public ConteneurGeneric(ConteneurGeneric cg) {
		this.attr_map = new HashMap<>(cg.attr_map);
	}

	public void add_set_Attribut(String nom, Object valeur) {
		attr_map.put(nom, valeur);
	}

	public Object getAttribut(String nom) {
		return attr_map.get(nom);
	}

}
