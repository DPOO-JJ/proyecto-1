package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MenuButton extends JPanel implements ActionListener{

	private PanelOpciones padre;

	public MenuButton(PanelOpciones padre, String label, String image) {
		this.padre = padre;
		
		setLayout(new BorderLayout());
		
		ImageIcon fondo = cargarImagen("./data/iconos/"+image);
		
		JButton button = new JButton(fondo);
		button.setActionCommand(label);
		button.addActionListener(this);
		add(button, BorderLayout.CENTER);
		
		JLabel jlabel = new JLabel("<html><B>" + label + "</B></html>");
		jlabel.setHorizontalAlignment(JLabel.CENTER);
		jlabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		add(jlabel, BorderLayout.SOUTH);
		
		Border blackline = BorderFactory.createLineBorder(Color.black, 2, true);
		setBorder(blackline);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String boton = e.getActionCommand();
		padre.ejecutarOpcion(boton);

	}
	
	private static ImageIcon cargarImagen(String nombreArchivo)
	{
		ImageIcon icon = new ImageIcon(nombreArchivo);
		Image imagen = icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
		ImageIcon ajustada = new ImageIcon(imagen);
		return ajustada;
	}
}
