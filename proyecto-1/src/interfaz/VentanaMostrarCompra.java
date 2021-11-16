package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import processing.Cliente;
import processing.Compra;
import processing.Producto;

@SuppressWarnings("serial")
public class VentanaMostrarCompra extends JFrame implements ActionListener, PanelOpciones{
	
	private VentanaSistemaPOS padre;
	private PanelProductos pProductos;
	
	private int productoSeleccionado;
	private Cliente cliente;
	
	public static final String ACEPTAR = "Aceptar";
	public static final String VOLVER = "Volver";
	
	public VentanaMostrarCompra(VentanaSistemaPOS padre, Cliente cliente, Compra compra, ArrayList<Producto> productos) {
		
		this.padre = padre;
		this.cliente = cliente;
		
		this.productoSeleccionado = 1;
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 35, 100));
		
		//label
//		JLabel jlabel = new JLabel("Seleccione el producto");
//		jlabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
//		jlabel.setHorizontalAlignment(JLabel.LEFT);
//		panel.add(jlabel,BorderLayout.NORTH);
		
		//productos
		pProductos = new PanelProductos(this, productos);
		pProductos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		panel.add(pProductos, BorderLayout.CENTER);
		
		//botones
		JPanel p = new JPanel();
		BorderLayout bl = new BorderLayout();
		p.setLayout(bl);

		JLabel jlabel = new JLabel("Total: "+compra.getTotal());
		jlabel.setHorizontalAlignment(JLabel.LEFT);
		panel.add(jlabel,BorderLayout.NORTH);
		
		jlabel = new JLabel("Puntos para "+cliente.getApellidos()+": "+compra.getPuntos());
		jlabel.setHorizontalAlignment(JLabel.LEFT);
		panel.add(jlabel,BorderLayout.SOUTH);
		
		
		JButton button = new JButton(ACEPTAR);
		button.setActionCommand(ACEPTAR);
		button.addActionListener(this);
		p.add(button, BorderLayout.EAST);
		
		button = new JButton(VOLVER);
		button.setActionCommand(VOLVER);
		button.addActionListener(this);
		p.add(button, BorderLayout.WEST);
		
		panel.add(p,BorderLayout.SOUTH);
		
		add(panel);
		
		pack();
		
		setTitle("Carrito de compras");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setResizable(false);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String boton = e.getActionCommand();
		
		if (boton.equals(ACEPTAR))
		{
			this.padre.finalizarCompra(cliente);
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
