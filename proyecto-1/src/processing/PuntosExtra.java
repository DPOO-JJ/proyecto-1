package processing;

import java.util.Date;

public class PuntosExtra extends Promocion{

	public PuntosExtra(Date fechaInicial, Date fechaFinal, int idProducto) {
		super(fechaInicial, fechaFinal);
		this.idProducto = idProducto;
	}

	private int idProducto;

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
}
