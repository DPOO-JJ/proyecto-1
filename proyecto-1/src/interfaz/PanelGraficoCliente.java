package interfaz;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.YearMonth;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class PanelGraficoCliente extends JPanel implements ActionListener {
	
	private JButton[][] botones;
	private VentanaCliente padre;

    public PanelGraficoCliente(VentanaCliente padre, Integer cedula) {  
    	this.padre = padre;
    	
    	GridLayout layout = new GridLayout(2,6);
    	layout.setHgap(10);
		layout.setVgap(10);
    	setLayout(layout);
    	//setPreferredSize(new Dimension(750, 120));
    	
    	int anio = Calendar.getInstance().get(Calendar.YEAR);
    	
    	add(new Mes(this,"Enero",YearMonth.of(anio, 1).lengthOfMonth()));
    	add(new Mes(this,"Febrero",YearMonth.of(anio, 2).lengthOfMonth()));
    	add(new Mes(this,"Marzo",YearMonth.of(anio, 3).lengthOfMonth()));
    	add(new Mes(this,"Abril",YearMonth.of(anio, 4).lengthOfMonth()));
    	add(new Mes(this,"Mayo",YearMonth.of(anio, 5).lengthOfMonth()));
    	add(new Mes(this,"Junio",YearMonth.of(anio, 6).lengthOfMonth()));
    	add(new Mes(this,"Julio",YearMonth.of(anio, 7).lengthOfMonth()));
    	add(new Mes(this,"Agosto",YearMonth.of(anio, 8).lengthOfMonth()));
    	add(new Mes(this,"Septiembre",YearMonth.of(anio, 9).lengthOfMonth()));
    	add(new Mes(this,"Octubre",YearMonth.of(anio, 10).lengthOfMonth()));
    	add(new Mes(this,"Noviembre",YearMonth.of(anio, 11).lengthOfMonth()));
    	add(new Mes(this,"Diciembre",YearMonth.of(anio, 12).lengthOfMonth()));
    	
      }



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}  
    
}
