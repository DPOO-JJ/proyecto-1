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
import javax.swing.JScrollPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class VentanaDetalles extends JFrame implements ActionListener, PanelOpciones{
	
	private VentanaInventario padre;
	private PanelProductos pProductos;
	
	private int productoSeleccionado;
	
	public static final String MODIFICAR_IMAGEN = "Modificar imagen";
	public static final String REVISAR_DETALLES = "Revisar detalles";
	
	public VentanaDetalles(VentanaInventario padre) {
		
		this.padre = padre;
		
		this.productoSeleccionado = 1;
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 35, 100));
		
		//label
		JLabel jlabel = new JLabel("Seleccione el producto");
		jlabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		jlabel.setHorizontalAlignment(JLabel.LEFT);
		panel.add(jlabel,BorderLayout.NORTH);
		
		//productos
		pProductos = new PanelProductos(this);
		pProductos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		panel.add(pProductos, BorderLayout.CENTER);
		
		//botones
		JPanel p = new JPanel();
		BorderLayout bl = new BorderLayout();
		p.setLayout(bl);
		
		JButton button = new JButton(MODIFICAR_IMAGEN);
		button.setActionCommand(MODIFICAR_IMAGEN);
		button.addActionListener(this);
		p.add(button, BorderLayout.EAST);
		
		button = new JButton(REVISAR_DETALLES);
		button.setActionCommand(REVISAR_DETALLES);
		button.addActionListener(this);
		p.add(button, BorderLayout.WEST);
		
		panel.add(p,BorderLayout.SOUTH);
		
		add(panel);
		
		pack();
		
		setTitle("Detalles producto");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setResizable(false);
		setVisible(true);
		
	}
	
	public void productoPresionado(int codigoDeBarras) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String boton = e.getActionCommand();
		if(boton.equals(MODIFICAR_IMAGEN)) {
			this.padre.lanzarVentanaImagen(productoSeleccionado);
		}
		else if(boton.equals(REVISAR_DETALLES)) {
			this.padre.revisarDetallesProducto(productoSeleccionado);
		}
		dispose();
	}

	@Override
	public void ejecutarOpcion(String opcion) {
		this.productoSeleccionado = Integer.parseInt(opcion);
	}
}
