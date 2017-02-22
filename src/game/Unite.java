package game;

public class Unite {
	Integer idUnite;
	Integer nombre;
	
	public Unite(int id, int nombre){
		idUnite = id;
		this.nombre = nombre;
	}
	
	public void setUnite(Integer id, Integer nombre){
		idUnite = id;
		this.nombre = nombre;
	}
	public int getIdUnite() {
		return idUnite;
	}
	public void setIdUnit(int idUnite) {
		this.idUnite = idUnite;
	}
	public int getNombre() {
		return nombre;
	}
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
}
