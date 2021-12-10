package processing;

import java.util.Date;

public class Regalo extends Promocion{

	private int idProducto;
	private int cantidadRequerida;
	private int unidadesRegalo;
	
	public Regalo(Date fechaInicial, Date fechaFinal, int idProducto, int cantidadRequerida, int unidadesRegalo) {
		super(fechaInicial, fechaFinal);
		this.idProducto = idProducto;
		this.cantidadRequerida = cantidadRequerida;
		this.unidadesRegalo = unidadesRegalo;
	}

	public int getCantidadRequerida() {
		return cantidadRequerida;
	}
	public void setCantidadRequerida(int cantidadRequerida) {
		this.cantidadRequerida = cantidadRequerida;
	}
	public int getUnidadesRegalo() {
		return unidadesRegalo;
	}
	public void setUnidadesRegalo(int unidadesRegalo) {
		this.unidadesRegalo = unidadesRegalo;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
}
