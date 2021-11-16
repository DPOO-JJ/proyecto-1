package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
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
public class VentanaAnadirProducto extends JFrame implements ActionListener, PanelPopup{
	
	private VentanaSistemaPOS padre;
	private JTextField codigo;
	private JTextField peso;
	private Cliente cliente;
	private JLabel mensaje;
	
	public static final String AGREGAR = "Agregar";
	public static final String FINALIZAR = "Finalizar";

	public VentanaAnadirProducto (VentanaSistemaPOS padre, Cliente cliente) 
	{
		this.padre = padre;
		this.cliente = cliente;
		pack();
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6,1));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
		
		mensaje = new JLabel("Producto agregado.");
		mensaje.setForeground(Color.green);
		mensaje.setVisible(false);
		panel.add(mensaje);
		
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
		
		JButton aceptar = new JButton(AGREGAR);
		aceptar.addActionListener(this);
		aceptar.setSize(30,15);
		aceptar.setActionCommand(AGREGAR);
		panelAceptar.add(aceptar, BorderLayout.EAST);
		
		JButton finalizar = new JButton(FINALIZAR);
		finalizar.addActionListener(this);
		finalizar.setSize(30,15);
		finalizar.setActionCommand(FINALIZAR);
		panelAceptar.add(finalizar, BorderLayout.WEST);
		
		panel.add(panelAceptar);
		
		add(panel);
		
		pack();
		setTitle("Compra");
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
	
	public String getCodigo()
	{
		return codigo.getText();
	}
	
	public String getPeso()
	{
		return peso.getText();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String boton = e.getActionCommand();
		
		String codigo = getCodigo();
		String peso = getPeso();
		
		if (boton.equals(AGREGAR))
		{
			if (codigo.length()>0 && peso.length()>0) {
				if (isNumeric(codigo) && isNumeric(peso)) {
					int result = padre.anadirProducto(Integer.parseInt(codigo), Integer.parseInt(peso), cliente);
					
					mensaje.setVisible(true);
					
					if (result==0)
					{
						mensaje.setForeground(Color.green);
						mensaje.setText("Producto agregado.");
					}
					else if (result ==-1)
					{
						mensaje.setForeground(Color.red);
						mensaje.setText("El producto no fue encontrado.");
					}
					else if (result ==-2)
					{
						mensaje.setForeground(Color.red);
						mensaje.setText("No hay existencia del producto.");
					}
				}
				else {
					new VentanaError(this,"Compra","Insertó algo que no es un número.");
				}
			}
			else{
				new VentanaError(this,"Compra","El campo está vacío.");
			}
		}
		else if(boton.equals(FINALIZAR))
		{
			padre.mostrarCompra(this.cliente);
			dispose();
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
