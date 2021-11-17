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
		
		//Datos
		BorderLayout bl = new BorderLayout();
		JPanel p = new JPanel();
		p.setLayout(bl);
		
		JLabel jlabel = new JLabel("<html><B>Total:</B> "+compra.getTotal()+"</html>");
		jlabel.setHorizontalAlignment(JLabel.LEFT);
		p.add(jlabel,BorderLayout.NORTH);
		
		jlabel = new JLabel("<html><B>Puntos para "+cliente.getApellidos()+":</B> "+compra.getPuntos()+"</html>");
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
		
		p.add(pp,BorderLayout.EAST);
		
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
