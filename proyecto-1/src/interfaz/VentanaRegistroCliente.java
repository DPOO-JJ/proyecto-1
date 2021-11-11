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
public class VentanaRegistroCliente extends JFrame implements ActionListener {
	private JTextField cedula;
	private JTextField nombres;
	private JTextField apellidos;
	private JTextField edad;
	private JTextField pronombres;
	private JTextField situacionLaboral;
	private VentanaSistemaPOS padre;
	
	public VentanaRegistroCliente(VentanaSistemaPOS padre){
		this.padre = padre;
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(7,1));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
		
		JLabel ced = new JLabel("Cédula:");
		panel.add(ced);
		
		cedula = new JTextField();
		panel.add(cedula);
		
		JLabel nom = new JLabel("Nombres:");
		panel.add(nom);
		
		nombres = new JTextField();
		panel.add(nombres);
		
		JLabel ape = new JLabel("Apellidos:");
		panel.add(ape);
		
	    apellidos = new JTextField();
		panel.add(apellidos);
		
		JLabel ed = new JLabel("Edad:");
		panel.add(ed);
		
		edad = new JTextField();
		panel.add(edad);
		
		JLabel pron = new JLabel("Pronombres:");
		panel.add(pron);
		
		pronombres = new JTextField();
		panel.add(pronombres);
		
		JLabel sit = new JLabel("Situación Laboral:");
		panel.add(sit);
		
		situacionLaboral = new JTextField();
		panel.add(situacionLaboral);
		
		panel.add(new JPanel());
		
		
		JPanel panelAceptar = new JPanel();
		panelAceptar.setLayout(new BorderLayout());
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setSize(30,15);
		panelAceptar.add(aceptar, BorderLayout.EAST);
		panel.add(panelAceptar);
		
		add(panel);
		
		pack();
		setTitle("Registro de cliente");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setResizable(false);
		setVisible(true);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		padre.anadirCliente(
				Integer.parseInt(cedula.getText()),
				nombres.getText(), 
				apellidos.getText(),  
				Integer.parseInt(edad.getText()), 
				pronombres.getText(), 
				situacionLaboral.getText());
		dispose();
	}
}
