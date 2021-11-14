package interfaz;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class PanelGraficoCliente extends JPanel implements ActionListener {
	
	private JButton[][] botones;
	private VentanaCliente padre;

    public PanelGraficoCliente(VentanaCliente padre, Integer cedula) {  
    	this.padre = padre;
    	setLayout(new GridLayout(7,53));
    	setPreferredSize(new Dimension(750, 120));
    	
    	
      }



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}  
    
}
