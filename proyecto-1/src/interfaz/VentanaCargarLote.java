package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
public class VentanaCargarLote extends JFrame implements ActionListener{
	
	private JTextField nombreArchivo;
	private VentanaInventario padre;
	
	public VentanaCargarLote(VentanaInventario padre) {
		
		this.padre = padre;
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5,1));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 0, 100));
		
		JLabel cat = new JLabel("Ingrese el nombre del archivo: ");
		cat.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		cat.setHorizontalAlignment(JLabel.LEFT);
		panel.add(cat);
		
		nombreArchivo = new JTextField();
		panel.add(nombreArchivo);
		
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
		
		setTitle("Cargar lote");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setResizable(false);
		setVisible(true);
		
	}
	
	public String getNombreArchivo()
	{
		return nombreArchivo.getText();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		this.padre.cargarLote(getNombreArchivo());
		dispose();
	}
}
