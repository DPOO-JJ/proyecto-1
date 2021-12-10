package processing;

import java.util.Date;

public abstract class Promocion {

	public Date fechaInicial;
	public Date fechaFinal;
	
	public Promocion(Date fechaInicial, Date fechaFinal) {
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
	}
	
	public Date getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	public boolean estaVigente() {
		boolean result = false;
		Date date = new Date();
		if (date.after(fechaInicial) && date.before(fechaFinal)) {
			result = true;
		}
		return result;
	}
	
}
