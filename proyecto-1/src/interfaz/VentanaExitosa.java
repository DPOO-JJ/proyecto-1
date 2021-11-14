package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class VentanaExitosa extends JFrame implements ActionListener{
	
	private PanelPopup padre;
	private String titulo;
	
	public VentanaExitosa(PanelPopup padre, String titulo, String aviso) {
		
		this.padre = padre;
		this.titulo = titulo;
		
		JPanel panel = new JPanel();
		GridLayout gl = new GridLayout(2,1);
		gl.setVgap(15);
		panel.setLayout(gl);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 100, 13, 100));
		
		JLabel cat = new JLabel(aviso);
		cat.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		cat.setHorizontalAlignment(JLabel.LEFT);
		panel.add(cat);
		
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
		
		setTitle(titulo);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setResizable(false);
		setVisible(true);
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				padre.aceptar(titulo, false);
			}
		});
		
	}
	
	public void cerrar() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.padre.aceptar(titulo, true);
		dispose();		
	}
}
