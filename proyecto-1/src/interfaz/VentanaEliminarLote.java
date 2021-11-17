package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VentanaEliminarLote extends JFrame implements ActionListener, IOpciones{
	
	private AplicacionInventario padre;
	private PanelProductos pProductos;
	
	private int productoSeleccionado;
	
	public VentanaEliminarLote(AplicacionInventario padre) {
		
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
		pProductos = new PanelProductos(this, null);
		pProductos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		panel.add(pProductos, BorderLayout.CENTER);
		
		//botones
		JPanel p = new JPanel();
		BorderLayout bl = new BorderLayout();
		p.setLayout(bl);
		
		JButton button = new JButton("Aceptar");
		button.addActionListener(this);
		p.add(button, BorderLayout.EAST);
		
		panel.add(p,BorderLayout.SOUTH);
		
		add(panel);
		
		pack();
		
		setTitle("Eliminar lote");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setResizable(false);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.padre.lanzarVentanaEliminar(productoSeleccionado);
		dispose();
	}

	@Override
	public void ejecutarOpcion(String opcion) {
		this.productoSeleccionado = Integer.parseInt(opcion);
	}
}
