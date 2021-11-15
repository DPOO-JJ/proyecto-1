package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JPanel;

import processing.FileManager;


@SuppressWarnings("serial")
public class PanelGraficoCliente extends JPanel implements ActionListener {
	
	private VentanaCliente padre;

    public PanelGraficoCliente(VentanaCliente padre, Integer cedula){  
    	this.padre = padre;
    	
    	GridLayout layout = new GridLayout(2,6);
    	layout.setHgap(10);
		layout.setVgap(10);
    	setLayout(layout);
    	//setPreferredSize(new Dimension(750, 120));
    	
    	int anio = Calendar.getInstance().get(Calendar.YEAR);
    	
    	ArrayList<ArrayList<String>> enero = FileManager.guardarInfoMes("1", String.valueOf(cedula)); // dice los dias de enero que tienen compras
    	ArrayList<ArrayList<String>> febrero = FileManager.guardarInfoMes("2", String.valueOf(cedula));
    	ArrayList<ArrayList<String>> marzo = FileManager.guardarInfoMes("3", String.valueOf(cedula));
    	ArrayList<ArrayList<String>> abril = FileManager.guardarInfoMes("4", String.valueOf(cedula));
    	ArrayList<ArrayList<String>> mayo = FileManager.guardarInfoMes("5", String.valueOf(cedula));
    	ArrayList<ArrayList<String>> junio = FileManager.guardarInfoMes("6", String.valueOf(cedula));
    	ArrayList<ArrayList<String>> julio = FileManager.guardarInfoMes("7", String.valueOf(cedula));
    	ArrayList<ArrayList<String>> agosto = FileManager.guardarInfoMes("8", String.valueOf(cedula));
    	ArrayList<ArrayList<String>> septiembre = FileManager.guardarInfoMes("9", String.valueOf(cedula));
    	ArrayList<ArrayList<String>> octubre = FileManager.guardarInfoMes("10", String.valueOf(cedula));
    	ArrayList<ArrayList<String>> noviembre = FileManager.guardarInfoMes("11", String.valueOf(cedula));
    	ArrayList<ArrayList<String>> diciembre = FileManager.guardarInfoMes("12", String.valueOf(cedula));
    	
    	add(new Mes(this,"Enero",YearMonth.of(anio, 1).lengthOfMonth(), enero));
    	add(new Mes(this,"Febrero",YearMonth.of(anio, 2).lengthOfMonth(), febrero));
    	add(new Mes(this,"Marzo",YearMonth.of(anio, 3).lengthOfMonth(), marzo));
    	add(new Mes(this,"Abril",YearMonth.of(anio, 4).lengthOfMonth(), abril));
    	add(new Mes(this,"Mayo",YearMonth.of(anio, 5).lengthOfMonth(), mayo));
    	add(new Mes(this,"Junio",YearMonth.of(anio, 6).lengthOfMonth(),junio));
    	add(new Mes(this,"Julio",YearMonth.of(anio, 7).lengthOfMonth(),julio));
    	add(new Mes(this,"Agosto",YearMonth.of(anio, 8).lengthOfMonth(),agosto));
    	add(new Mes(this,"Septiembre",YearMonth.of(anio, 9).lengthOfMonth(),septiembre));
    	add(new Mes(this,"Octubre",YearMonth.of(anio, 10).lengthOfMonth(),octubre));
    	add(new Mes(this,"Noviembre",YearMonth.of(anio, 11).lengthOfMonth(),noviembre));
    	add(new Mes(this,"Diciembre",YearMonth.of(anio, 12).lengthOfMonth(),diciembre));
    	
      }



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}  
    
}
