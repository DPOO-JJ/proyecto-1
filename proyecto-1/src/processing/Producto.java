package processing;

public class Producto {
	
	private int codigoBarras;
	private String nombre;
	private int numUnidadesMedida;
	private String unidadMedida;
	private Categoria categoria;
	private String almacenamiento;
	private boolean empacado;
	
	public Producto(int codigoBarras, String nombre, int numUnidadesMedida, String unidadMedida,
			String almacenamiento, boolean empacado) {
		super();
		this.codigoBarras = codigoBarras;
		this.nombre = nombre;
		this.numUnidadesMedida = numUnidadesMedida;
		this.unidadMedida = unidadMedida;
		this.almacenamiento = almacenamiento;
		this.empacado = empacado;
	}
	
	public Producto() {}
	
	boolean equals(Producto otroProducto) {
		if (this.codigoBarras==otroProducto.getCodigoBarras()) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String loteStr = "";
		loteStr+=this.codigoBarras+"\t";
		loteStr+=this.nombre+"\t";
		loteStr+=this.numUnidadesMedida+"\t";
		loteStr+=this.unidadMedida+"\t";
		loteStr+=this.categoria.getNombre()+"\t";
		loteStr+=this.almacenamiento+"\t";
		loteStr+=this.empacado+"\t";
		return loteStr;
	}
	
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
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
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
