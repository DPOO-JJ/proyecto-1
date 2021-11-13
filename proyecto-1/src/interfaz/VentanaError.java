package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class VentanaError extends JFrame implements ActionListener{
	
	private PanelPopup padre;
	private String titulo;
	
	public VentanaError(PanelPopup padre, String titulo, String aviso) {
		
		this.padre = padre;
		this.titulo = titulo;
		
		JPanel panel = new JPanel();
		BorderLayout bl = new BorderLayout();
		panel.setLayout(bl);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 80, 13, 80));
		
		JPanel p = new JPanel();
		BoxLayout gl = new BoxLayout(p, BoxLayout.Y_AXIS);
		p.setLayout(gl);
		
		p.add(Box.createVerticalGlue());
		
		JLabel error = new JLabel("<html><B>Error</B></html>");
		error.setForeground(Color.red);
		p.add(error);
		
		JLabel cat = new JLabel(aviso);
		cat.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		cat.setHorizontalAlignment(JLabel.LEFT);
		
		p.add(cat);
		
		panel.add(p, BorderLayout.CENTER);
		
		
		p.add(Box.createVerticalGlue());
		
		p = new JPanel();
		gl = new BoxLayout(p, BoxLayout.Y_AXIS);
		p.setLayout(gl);
		
		p.add(Box.createVerticalGlue());
		
		try {
			BufferedImage myPicture = ImageIO.read(new File("./data/iconos/warning.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			p.add(picLabel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setSize(30,15);
		aceptar.setAlignmentX(JButton.CENTER_ALIGNMENT);
		p.add(aceptar);
		
		p.add(Box.createVerticalGlue());
		
		panel.add(p,BorderLayout.EAST);
		
		add(panel);
		
		pack();
		
		setTitle(titulo);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setResizable(false);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.padre.error(titulo);
		dispose();
		//this.padre.anadirCategoria(getNombreCategoria(),getNombreSuperCategoria());		
	}
}
