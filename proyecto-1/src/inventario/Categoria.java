package inventario;

public class Categoria {
	
	private String nombre;
	private Categoria superCategoria = null;
	
	public Categoria(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Categoria getSuperCategoria() {
		return superCategoria;
	}
	public void setSuperCategoria(Categoria superCategoria) {
		this.superCategoria = superCategoria;
	}
	
}
