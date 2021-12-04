package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import processing.Cliente;
import processing.Compra;
import processing.Producto;

@SuppressWarnings("serial")
public class VentanaMostrarCompra extends JFrame implements ActionListener, IOpciones{
	
	private AplicacionSistemaPOS padre;
	private PanelProductos pProductos;
	
	@SuppressWarnings("unused")
	private int productoSeleccionado;
	private Cliente cliente;
	public JTextField pts;
	
	public static final String ACEPTAR = "Aceptar";
	public static final String VOLVER = "Volver";
	
	public VentanaMostrarCompra(AplicacionSistemaPOS padre, Cliente cliente, Compra compra, ArrayList<Producto> productos) {
		
		this.padre = padre;
		this.cliente = cliente;
		
		this.productoSeleccionado = 1;
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 35, 100));
		
		//productos
		pProductos = new PanelProductos(this, productos);
		pProductos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		panel.add(pProductos, BorderLayout.CENTER);
		
		//Datos
		BorderLayout bl = new BorderLayout();
		JPanel p = new JPanel();
		p.setLayout(bl);
		
		JLabel jlabel = new JLabel("<html><B>Total:</B> "+compra.getTotal()+"</html>");
		jlabel.setHorizontalAlignment(JLabel.LEFT);
		p.add(jlabel,BorderLayout.NORTH);
		
		if (cliente != null) {
			jlabel = new JLabel("<html><B>Puntos para "+cliente.getApellidos()+":</B> "+compra.getPuntos()+"</html>");
		}
		else {
			jlabel = new JLabel("<html><B>Cliente no registrado</B></html>");
		}
		
		jlabel.setHorizontalAlignment(JLabel.LEFT);
		p.add(jlabel,BorderLayout.WEST);
		
		//botones
		JPanel pp = new JPanel();
		bl = new BorderLayout();
		pp.setLayout(bl);
		
		JButton button = new JButton(ACEPTAR);
		button.setActionCommand(ACEPTAR);
		button.addActionListener(this);
		pp.add(button, BorderLayout.EAST);
		
		button = new JButton(VOLVER);
		button.setActionCommand(VOLVER);
		button.addActionListener(this);
		pp.add(button, BorderLayout.WEST);
		
		JPanel panelPuntos = new JPanel();
		
		JLabel cuantosPuntos = new JLabel("Puntos para utilizar en esta compra:");
		pts = new JTextField("0");
		pts.setPreferredSize(new Dimension(120, 30));
		
		panelPuntos.add(cuantosPuntos);
		panelPuntos.add(pts);

		pp.add(panelPuntos, BorderLayout.NORTH);
		
		p.add(pp,BorderLayout.EAST);
		
		panel.add(p,BorderLayout.SOUTH);
		
		
		add(panel);
		
		pack();
		
		setTitle("Carrito de compras");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); 
		setResizable(false);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String boton = e.getActionCommand();
		
		if (boton.equals(ACEPTAR))
		{
			//TODO Agregar la opción de método de pago con puntos
			try {
				this.padre.finalizarCompra(cliente, Integer.parseInt(pts.getText()));
			}
			catch (NumberFormatException e1)
			{
				new VentanaError(padre, "Error", "El número de puntos es incorrecto");
				e1.printStackTrace();
			}
			catch (Exception e2)
			{
				new VentanaError(padre, "Error", "");
				e2.printStackTrace();
			}
			
		}
		else if (boton.equals(VOLVER)) {
			this.padre.lanzarVentanaAñadirProducto(cliente);
		}
		dispose();
	}

	@Override
	public void ejecutarOpcion(String opcion) {
		this.productoSeleccionado = Integer.parseInt(opcion);
	}
}
