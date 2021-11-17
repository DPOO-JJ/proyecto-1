package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class VentanaNuevaCompra extends JFrame implements ActionListener, IPopup{
	
	private AplicacionSistemaPOS padre;
	private JTextField cedula;

	public VentanaNuevaCompra(AplicacionSistemaPOS padre)
	{
		this.padre = padre;
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,1));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 35, 100));
		
		JLabel cat = new JLabel("Ingrese la cédula del cliente: ");
		cat.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		cat.setHorizontalAlignment(JLabel.LEFT);
		panel.add(cat);
		
		cedula = new JTextField();
		panel.add(cedula);
		
		JPanel p = new JPanel();
		BorderLayout bl = new BorderLayout();
		
		p.setLayout(bl);
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		p.add(aceptar, BorderLayout.EAST);
		panel.add(p);
		
		add(panel);
		
		pack();
		setTitle("Nueva compra");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setResizable(false);
		setVisible(true);
	}


	@Override
	public void aceptar(String titulo, boolean aceptada) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String titulo) {
		// TODO Auto-generated method stub
		
	}
	
	public String getCedula()
	{
		return cedula.getText();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		String cedula = getCedula();
		
		if (cedula.length()>0) {
			if (isNumeric(cedula)) {
				padre.nuevaCompra(Integer.parseInt(cedula));
				dispose();
			}
			else {
				new VentanaError(this,"Nueva compra","Insertó algo que no es un número.");
			}
		}
		else{
			new VentanaError(this,"Nueva compra","El campo está vacío.");
		}
	}
	
	public static boolean isNumeric(String str) { 
		try { 
			Double.parseDouble(str);  
			return true;
		} catch(NumberFormatException e){  
			return false;  
		}  
	}

}
