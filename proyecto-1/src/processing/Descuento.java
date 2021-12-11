package processing;

import java.util.Date;

public class Descuento extends Promocion{
	
	private int idProducto;
	private float descuento;
	
	public Descuento(String tipoPromocion, Date fechaInicial, Date fechaFinal, int idProducto, float descuento) {
		super(tipoPromocion, fechaInicial, fechaFinal);
		this.idProducto = idProducto;
		this.descuento = descuento;
	}
	
	public float getDescuento() {
		return descuento;
	}
	
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
}
