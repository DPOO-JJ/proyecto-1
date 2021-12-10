package processing;

import java.util.Date;

public class Combo extends Promocion{

	private int idCombo;
	private String nombreCombo;
	private float descuento;
	private int[] idProductos;
	
	public Combo(Date fechaInicial, Date fechaFinal, int idCombo, String nombreCombo, float descuento,
			String idProductos) {
		super(fechaInicial, fechaFinal);
		this.idCombo = idCombo;
		this.nombreCombo = nombreCombo;
		this.descuento = descuento;
		
		String[] productos = idProductos.split("-");
		
		this.idProductos = new int[productos.length] ;
		
		int i = 0;
		for (String producto: productos ) {
			this.idProductos[i] = Integer.parseInt(producto);
			i++;
		}
	}
	
	public int getIdCombo() {
		return idCombo;
	}
	public void setIdCombo(int idCombo) {
		this.idCombo = idCombo;
	}
	public String getNombreCombo() {
		return nombreCombo;
	}
	public void setNombreCombo(String nombreCombo) {
		this.nombreCombo = nombreCombo;
	}
	public float getDescuento() {
		return descuento;
	}
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	public int[] getIdProductos() {
		return idProductos;
	}
	public void setIdProductos(int[] idProductos) {
		this.idProductos = idProductos;
	}
}
