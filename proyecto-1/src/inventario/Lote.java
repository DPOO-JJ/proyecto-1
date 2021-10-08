package inventario;

public class Lote {
	
	private String fechaVencimiento;
	private String fechaRecibido;
	private int precioCompra;
	private boolean ventaPorMedida;
	private int precioVenta;
	private int unidades;
	private Producto productoAsociado;
	
	public Lote(String fechaVencimiento, String fechaRecibido, int precioCompra, boolean ventaPorMedida, int precioVenta,
			int unidades) {
		super();
		this.fechaVencimiento = fechaVencimiento;
		this.fechaRecibido = fechaRecibido;
		this.precioCompra = precioCompra;
		this.ventaPorMedida = ventaPorMedida;
		this.precioVenta = precioVenta;
		this.unidades = unidades;
		this.productoAsociado = productoAsociado;
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
	public boolean getVentaPorMedida() {
		return ventaPorMedida;
	}
	public void setVentaPorMedida(boolean ventaPorMedida) {
		this.ventaPorMedida = ventaPorMedida;
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
}
