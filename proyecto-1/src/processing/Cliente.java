package processing;

import java.util.ArrayList;

public class Cliente {
	
	
	private int cedula;
	private String nombres;
	private String apellidos;
	private int edad;
	private String idenGenero;
	private String situacionLaboral; 
	public int puntos;
	public ArrayList<Compra> compras;
	
	
	public Cliente(int cedula, String nombres, String apellidos, int edad, String idenGenero, String situacionLaboral, int puntos) {
		super();
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.edad = edad;
		this.idenGenero = idenGenero;
		this.situacionLaboral = situacionLaboral;
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
	public String getsituacionLaboral() {
		return situacionLaboral;
	}
	public void setsituacionLaboral(String situacionLaboral) {
		this.situacionLaboral = situacionLaboral;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String lineCSV() 
	{
		String line = "";
		line += this.cedula + ",";
		line += this.nombres + ",";
		line += this.apellidos + ",";
		line += this.edad + ",";
		line += this.idenGenero + ",";
		line += this.situacionLaboral + ",";
		line += this.puntos;
		
		return line;
	}

}
