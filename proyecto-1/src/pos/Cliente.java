package pos;

public class Cliente {
	
	
	private int cedula;
	private String nombres;
	private String apellidos;
	private int edad;
	private String idenGenero;
	private String situacionLaboraL; 
	public int puntos;
	
	
	public Cliente(int cedula, String nombres, String apellidos, int edad, String idenGenero, String situacionLaboraL,
			int puntos) {
		super();
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.edad = edad;
		this.idenGenero = idenGenero;
		this.situacionLaboraL = situacionLaboraL;
		this.puntos = puntos;
	}
	
	
	public int getCedula() {
		return cedula;
	}
	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getIdenGenero() {
		return idenGenero;
	}
	public void setIdenGenero(String idenGenero) {
		this.idenGenero = idenGenero;
	}
	public String getSituacionLaboraL() {
		return situacionLaboraL;
	}
	public void setSituacionLaboraL(String situacionLaboraL) {
		this.situacionLaboraL = situacionLaboraL;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}


}
