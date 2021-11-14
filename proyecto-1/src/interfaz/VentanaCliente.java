package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import processing.Cliente;

@SuppressWarnings("serial")
public class VentanaCliente extends JFrame implements ActionListener, PanelPopup {

	private VentanaSistemaPOS padre;

	public VentanaCliente(VentanaSistemaPOS padre, Cliente cliente)
	{
		this.padre = padre;
		
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7,1));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
		
		JLabel ced = new JLabel("Cédula:");
		panel.add(ced);
		
		JLabel cedula = new JLabel(String.valueOf(cliente.getCedula()));
		panel.add(cedula);
		
		JLabel nom = new JLabel("Nombres:");
		panel.add(nom);
		
		JLabel nombres = new JLabel(cliente.getNombres());
		panel.add(nombres);
		
		JLabel ape = new JLabel("Apellidos:");
		panel.add(ape);
		
		JLabel apellidos = new JLabel(cliente.getApellidos());
		panel.add(apellidos);
		
		JLabel ed = new JLabel("Edad:");
		panel.add(ed);
		
		JLabel edad = new JLabel(String.valueOf(cliente.getEdad()));
		panel.add(edad);
		
		JLabel pron = new JLabel("Pronombres:");
		panel.add(pron);
		
		JLabel pronombres = new JLabel(cliente.getIdenGenero());
		panel.add(pronombres);
		
		JLabel sit = new JLabel("Situación Laboral:");
		panel.add(sit);
		
		JLabel situacionLaboral = new JLabel(cliente.getsituacionLaboral());
		panel.add(situacionLaboral);
		
		add(panel, BorderLayout.NORTH);
		
		
		PanelGraficoCliente grafico = new PanelGraficoCliente(this, (Integer) cliente.getCedula());
		add(grafico, BorderLayout.SOUTH);
		
		pack();
		setTitle(cliente.getNombres() + ' ' + cliente.getApellidos());
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
