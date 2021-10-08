package inventario;

public class Producto {
	
	private int codigoBarras;
	private String nombre;
	private int numUnidadesMedida;
	private String unidadMedida;
	private String categoria;
	private String almacenamiento;
	private boolean empacado;
	
	public Producto(int codigoBarras, String nombre, int numUnidadesMedida, String unidadMedida, String categoria,
			String almacenamiento, boolean empacado) {
		super();
		this.codigoBarras = codigoBarras;
		this.nombre = nombre;
		this.numUnidadesMedida = numUnidadesMedida;
		this.unidadMedida = unidadMedida;
		this.categoria = categoria;
		this.almacenamiento = almacenamiento;
		this.empacado = empacado;
	}
	
	public Producto() {}
	
	public int getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(int codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumUnidadesMedida() {
		return numUnidadesMedida;
	}
	public void setNumUnidadesMedida(int numUnidadesMedida) {
		this.numUnidadesMedida = numUnidadesMedida;
	}
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getAlmacenamiento() {
		return almacenamiento;
	}
	public void setAlmacenamiento(String almacenamiento) {
		this.almacenamiento = almacenamiento;
	}
	public boolean isEmpacado() {
		return empacado;
	}
	public void setEmpacado(boolean empacado) {
		this.empacado = empacado;
	}

}
