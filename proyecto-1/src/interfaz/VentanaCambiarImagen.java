package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import processing.Producto;

@SuppressWarnings("serial")
public class VentanaCambiarImagen extends JFrame implements ActionListener, PanelPopup{
	
	private JTextField nombreImagen;
	private VentanaInventario padre;
	private ArrayList<String> dataProducto;
	
	public VentanaCambiarImagen(VentanaInventario padre, ArrayList<String> dataProducto) {
		
		this.padre = padre;
		this.dataProducto = dataProducto;
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,1));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 35, 100));
		
		JLabel cat = new JLabel("Ingrese el nombre de la imagen (incluyendo la extensión): ");
		cat.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		cat.setHorizontalAlignment(JLabel.LEFT);
		panel.add(cat);
		
		nombreImagen = new JTextField();
		panel.add(nombreImagen);
		
		JPanel p = new JPanel();
		BorderLayout bl = new BorderLayout();
		
		p.setLayout(bl);
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setSize(30,15);
		p.add(aceptar, BorderLayout.EAST);
		panel.add(p);
		
		add(panel);
		
		pack();
		
		setTitle("Modificar imagen");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setResizable(false);
		setVisible(true);
		
	}
	
	public String getNombreImagen()
	{
		return nombreImagen.getText();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String imagePath = getNombreImagen();

		if (imagePath.endsWith(".png") || imagePath.endsWith(".jpg") || imagePath.endsWith(".jpeg")) {
			
			
			File f = new File("./data/productos/"+imagePath);
			if (f.exists()) {
				new VentanaExitosa(this,"Modificar imagen","¿Está seguro de que quiere cambiar la imagen de "+dataProducto.get(1)+"?");
			}
			else {
				new VentanaExitosa(this,"Modificar imagen","¿Está seguro de que quiere cambiar la imagen de "+dataProducto.get(1)+" a pesar de que esta no se encuentre en el directorio?");
			}
		}
		else if (imagePath.equals("")){
			new VentanaError(this,"Modificar imagen","Cadena de texto vacía.");
		}
		else {
			new VentanaError(this,"Modificar imagen","La imagen tiene que ser de formato .png, .jpg o .jpeg");
		}
	}

	@Override
	public void aceptar(String titulo, boolean aceptada) {
		if (aceptada) {
			this.padre.modificarImagen(dataProducto,getNombreImagen());
		}
		dispose();
	}

	@Override
	public void error(String titulo) {
		// TODO Auto-generated method stub
		
	}
}
