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
import processing.Cliente;


@SuppressWarnings("serial")
public class VentanaCompra extends JFrame implements ActionListener, PanelPopup{
	
	private VentanaSistemaPOS padre;
	private JTextField codigo;
	private JTextField peso;
	private Cliente cliente;

	public VentanaCompra (VentanaSistemaPOS padre, Cliente cliente) 
	{
		this.padre = padre;
		this.cliente = cliente;
		pack();
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5,1));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 0, 100));
		
		JLabel txt = new JLabel("Ingrese el código del producto:");
		panel.add(txt);
		
		codigo = new JTextField();
		panel.add(codigo);
		
		JLabel txt2 = new JLabel("Ingrese la cantidad. Si es por peso digite el gramaje:");
		panel.add(txt2);
		
		peso = new JTextField();
		panel.add(peso);
		
		JPanel panelAceptar = new JPanel();
		panelAceptar.setLayout(new BorderLayout());
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setSize(30,15);
		aceptar.setActionCommand("Aceptar");
		panelAceptar.add(aceptar, BorderLayout.EAST);
		
		JButton finalizar = new JButton("Finalizar");
		aceptar.addActionListener(this);
		aceptar.setSize(30,15);
		aceptar.setActionCommand("Finalizar");
		panelAceptar.add(finalizar, BorderLayout.WEST);
		
		panel.add(panelAceptar);
		
		add(panel);
		
		pack();
		setTitle("Nueva Compra");
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
		
		if (e.getActionCommand() == "Aceptar")
		{
			padre.hacerCompra(Integer.parseInt(codigo.getText()), Integer.parseInt(peso.getText()), cliente);
			
		}
		else
		{
			new VentanaExitosa(this, "Compra Finalizada", "La compra ha sido hecha con éxito");
		}
		
	}

}
