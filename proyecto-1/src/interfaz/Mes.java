package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class Mes extends JPanel implements ActionListener {
	

	public Mes(PanelGraficoCliente padre, String mes, Integer dias, HashMap<Integer,Integer> dataCompras) {
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(120, 120));
		
		JLabel jlabel = new JLabel("<html><B>" + mes + "</B></html>");
		jlabel.setHorizontalAlignment(JLabel.CENTER);
		jlabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		add(jlabel, BorderLayout.NORTH);
		
		JPanel p = new JPanel();
		GridLayout meses = new GridLayout(5,7);
		 
		p.setLayout(meses);
		
		double maximo = 0;
		double minimo = Double.POSITIVE_INFINITY;
		
		for (int totalDia : dataCompras.values()) {
			if (totalDia>maximo) {
				maximo=totalDia;
			}
			if (totalDia<minimo) {
				minimo=totalDia;
			}
		}
		
		for (int i = 0;i<dias;i++) {
			JButton button = new JButton();
			int dia = i+1;
			button.setToolTipText(Integer.toString(dia));
			button.setBackground(Color.WHITE);
			
			
			if(dataCompras.containsKey(dia)){
        		int totalDia = dataCompras.get(dia);
        		double porcentaje = map(totalDia,minimo,maximo,0,1);
        		button.setBackground(new Color(0, (int)(193 - (115*porcentaje)), (int)(255 - (152*porcentaje))));
        	}
			
			button.setBorder(BorderFactory.createLineBorder(Color.black, 1,true));
			button.setOpaque(true);
			p.add(button);
		};
		
		
		add(p, BorderLayout.CENTER);
		
		Border blackline = BorderFactory.createLineBorder(Color.black, 1, true);
		
		setBorder(blackline);
	}
	
	double map(double x, double in_min, double in_max, double out_min, double out_max)
	{
	  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
