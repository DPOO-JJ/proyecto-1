package processing;

import java.util.Date;

public class PuntosExtra extends Promocion{

	public PuntosExtra(String tipoPromocion, Date fechaInicial, Date fechaFinal, int idProducto, int multiplicador) {
		super(tipoPromocion, fechaInicial, fechaFinal);
		this.idProducto = idProducto;
		this.multiplicador = multiplicador;
	}

	private int idProducto;
	private int multiplicador;

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getMultiplicador() {
		return multiplicador;
	}

	public void setMultiplicador(int multiplicador) {
		this.multiplicador = multiplicador;
	}
}
