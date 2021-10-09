package inventario;

public class Lote {
	
	private String fechaVencimiento;
	private String fechaRecibido;
	private int precioCompra;
	private int precioVenta;
	private int unidades;
	private int unidadesRestantes;
	private Producto productoAsociado;
	private boolean loteEliminado;
	
	public Lote(String fechaRecibido, String fechaVencimiento, int precioCompra, int precioVenta,
			int unidades, boolean loteEliminado, int unidadesRestantes) {
		super();
		this.fechaRecibido = fechaRecibido;
		this.fechaVencimiento = fechaVencimiento;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.unidades = unidades;
		this.loteEliminado = loteEliminado;
		this.unidadesRestantes = unidadesRestantes;
	}
	
	@Override
	public String toString() {
		String loteStr = "";
		loteStr+=this.fechaRecibido+"\t  ";
		loteStr+=this.fechaVencimiento+"\t\t  ";
		loteStr+=this.precioCompra+"\t\t\t  ";
		loteStr+=this.precioVenta+"\t\t\t  ";
		loteStr+=this.unidades+"\t\t\t  ";
		loteStr+=this.unidadesRestantes+"\t\t\t  ";
		loteStr+=this.loteEliminado+"\t\t\t\t  ";
		loteStr+=this.productoAsociado.getNombre()+"\t\t\t";
		return loteStr;
	}
	
	public String toFileLine() {
		String fileLine="";
		fileLine+=this.fechaRecibido+",";
		fileLine+=this.fechaVencimiento+",";
		fileLine+=this.precioCompra+",";
		fileLine+=this.precioVenta+",";
		fileLine+=this.unidades+",";
		fileLine+=this.productoAsociado.getCodigoBarras()+",";
		fileLine+=this.loteEliminado+",";
		fileLine+=this.unidadesRestantes;
		return fileLine;
	}
	
	boolean equals(Lote otroLote) {
		if (this.toString().equals(otroLote.toString())) {
			return true;
		}
		return false;
	}
	
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getFechaRecibido() {
		return fechaRecibido;
	}
	public void setFechaRecibido(String fechaRecibido) {
		this.fechaRecibido = fechaRecibido;
	}
	public int getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(int precioCompra) {
		this.precioCompra = precioCompra;
	}
	public int getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(int precioVenta) {
		this.precioVenta = precioVenta;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	public Producto getProductoAsociado() {
		return productoAsociado;
	}
	public void setProductoAsociado(Producto productoAsociado) {
		this.productoAsociado = productoAsociado;
	}

	public boolean isLoteEliminado() {
		return loteEliminado;
	}

	public void setLoteEliminado(boolean loteEliminado) {
		this.loteEliminado = loteEliminado;
	}

	public int getUnidadesRestantes() {
		return unidadesRestantes;
	}

	public void setUnidadesRestantes(int unidadesRestantes) {
		this.unidadesRestantes = unidadesRestantes;
	}
}
