package interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import processing.FileManager;

@SuppressWarnings("serial")
public class PanelProductos extends JPanel implements PanelOpciones{
	
	private PanelOpciones padre;

	public PanelProductos(PanelOpciones padre) {
		this.padre = padre;
		
		GridLayout layout = new GridLayout(4,4);
		layout.setHgap(20);
		layout.setVgap(20);
		
		JPanel panel = new JPanel();
		panel.setLayout(layout);
		panel.setPreferredSize(new Dimension(680,900));
		
		HashMap<Integer,ArrayList<String>> hm = FileManager.cargarImagenes();
		
		for (ArrayList<String> dataProducto : hm.values()) {
			ProductoButton lote = new ProductoButton(this, dataProducto);
			panel.add(lote);
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
