package interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import processing.FileManager;
import processing.Producto;

@SuppressWarnings("serial")
public class PanelProductos extends JPanel implements PanelOpciones{
	
	private PanelOpciones padre;

	public PanelProductos(PanelOpciones padre, ArrayList<Producto> productos) {
		this.padre = padre;
		
		GridLayout layout = new GridLayout(4,4);
		layout.setHgap(20);
		layout.setVgap(20);
		
		JPanel panel = new JPanel();
		panel.setLayout(layout);
		panel.setPreferredSize(new Dimension(680,900));
		
		HashMap<Integer,ArrayList<String>> hm = FileManager.cargarImagenes();
		
		if (productos == null) {
			for (ArrayList<String> dataProducto : hm.values()) {
				ProductoButton boton = new ProductoButton(this, dataProducto);
				panel.add(boton);
			}
		}
		else {
			for(Producto producto:productos) {
				int codigoBarras = producto.getCodigoBarras();
				
				for (ArrayList<String> dataProducto : hm.values()) {
					if (codigoBarras == Integer.parseInt(dataProducto.get(0))) {
						ProductoButton boton = new ProductoButton(this, dataProducto);
						panel.add(boton);
						break;
					}
				}
			}
		}
		
		
		JScrollPane scrollFrame = new JScrollPane(panel);
		panel.setAutoscrolls(true);
		scrollFrame.setPreferredSize(new Dimension(700,300));
		
		add(scrollFrame);
	}

	@Override
	public void ejecutarOpcion(String opcion) {
		this.padre.ejecutarOpcion(opcion);
	}

}
