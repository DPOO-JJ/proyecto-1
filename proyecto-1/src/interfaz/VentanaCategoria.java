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
public class VentanaCategoria extends JFrame implements ActionListener{
	
	private JTextField nombreCategoria;
	private JTextField nombreSuperCategoria;
	private VentanaInventario padre;
	
	public VentanaCategoria(VentanaInventario padre) {
		
		this.padre = padre;
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5,1));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 35, 100));
		
		JLabel cat = new JLabel("Ingrese el nombre de la categoría: ");
		cat.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		cat.setHorizontalAlignment(JLabel.LEFT);
		panel.add(cat);
		
		nombreCategoria = new JTextField();
		panel.add(nombreCategoria);
		
		JLabel superCat = new JLabel("Ingrese el nombre de la súper categoría a la que pertenece: ");
		superCat.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		superCat.setHorizontalAlignment(JLabel.LEFT);
		panel.add(superCat);
		
		nombreSuperCategoria = new JTextField();
		panel.add(nombreSuperCategoria);
		
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
		
		setTitle("Añadir categoría");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setResizable(false);
		setVisible(true);
		
	}
	
	public String getNombreCategoria()
	{
		return nombreCategoria.getText();
	}
	
	public String getNombreSuperCategoria()
	{
		return nombreSuperCategoria.getText();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.padre.anadirCategoria(getNombreCategoria(),getNombreSuperCategoria());
		dispose();
	}
}