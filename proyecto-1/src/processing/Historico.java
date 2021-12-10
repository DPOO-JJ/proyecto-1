package processing;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Historico {
	private HashMap<Date,Integer> datos = new HashMap<Date,Integer>();
	private int idProducto;
	
	public Historico(int idProducto) {
		this.idProducto = idProducto;
	}
	
	public HashMap<Date, Integer> getDatos() {
		return datos;
	}

	public void setDatos(HashMap<Date, Integer> historia) {
		this.datos = historia;
	}
	
	public void cargarDato(String fecha, int cantidad) {
		
		Date dFecha = null;
		
	    try {
			dFecha=new SimpleDateFormat("dd/MM/yy").parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		datos.put(dFecha,cantidad);
	}
	
	public ArrayList<Object> obtenerUltimaFecha() {
		ArrayList<Object> par = new ArrayList<Object>();
		Date ultimaFecha = null;
		int ultimaCantidad = 0;
		
		try {
			//esta fecha es para tener una fecha muy lejana
			ultimaFecha = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1900");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Map.Entry<Date, Integer> entry : datos.entrySet()) {
			if (entry.getKey().after(ultimaFecha)) {
				ultimaFecha = entry.getKey();
				ultimaCantidad = entry.getValue();
			}
		}
		
		par.add(ultimaFecha);
		par.add(ultimaCantidad);
		
		return par;
	}
	
	public void actualizarCantidad(int cambioEnUnidades) {
		ArrayList<Object> datoUltimaFecha = this.obtenerUltimaFecha();
		int cantidad = (int)datoUltimaFecha.get(1);
		int nuevaCantidad = cantidad+cambioEnUnidades;
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yy");

		Date fechaHoy = null;
		try {
			fechaHoy = formatter.parse(formatter.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String sFechaHoy = formatter.format(fechaHoy);
		
		if (datos.containsKey(fechaHoy)) {
			String oldFileLine = sFechaHoy+","+this.idProducto+","+cantidad;
			String newFileLine = sFechaHoy+","+this.idProducto+","+nuevaCantidad;
			FileManager.cambiarLineaArchivo("data/histproductos.csv",oldFileLine,newFileLine);
		}
		else {
			String line = sFechaHoy+","+this.idProducto+","+nuevaCantidad;
			FileManager.addLineToCSV("data/histproductos.csv",line);
		}
		
		datos.put(fechaHoy,nuevaCantidad);
	}
}
