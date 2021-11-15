package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class ProductoButton extends JPanel implements ActionListener{

	private PanelOpciones padre;

	public ProductoButton(PanelOpciones padre, ArrayList<String> dataProducto) {
		this.padre = padre;
		
		setLayout(new BorderLayout());
		
		ImageIcon fondo = cargarImagen("./data/productos/"+dataProducto.get(2));
		
		String titulo = dataProducto.get(1);
		
		JButton button = new JButton(fondo);
		button.setActionCommand(dataProducto.get(0));
		button.addActionListener(this);
		add(button, BorderLayout.CENTER);
		
		JLabel jlabel = new JLabel("<html><B>" + titulo + "</B></html>");
		jlabel.setHorizontalAlignment(JLabel.CENTER);
		jlabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		add(jlabel, BorderLayout.SOUTH);
		
		Border blackline = BorderFactory.createLineBorder(Color.black, 2, true);
		setBorder(blackline);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String codigoProducto = e.getActionCommand();
		padre.ejecutarOpcion(codigoProducto);

	}
	
	private static ImageIcon cargarImagen(String nombreArchivo)
	{
		ImageIcon icon = new ImageIcon(nombreArchivo);
		Image imagen = icon.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
		ImageIcon ajustada = new ImageIcon(imagen);
		return ajustada;
	}
}
