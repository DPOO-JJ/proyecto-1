package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import processing.Producto;

@SuppressWarnings("serial")
public class VentanaProducto extends JFrame {

	public VentanaProducto(Producto producto, HashMap<String,Integer> desempeno, int unidadesRestantes)
	{
		
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,1));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 80));
		
		JLabel ganancias = new JLabel("Ganancias:");
		panel.add(ganancias);
		
		ganancias = new JLabel(String.valueOf(desempeno.get("ganancias"))+" pesos");
		panel.add(ganancias);
		
		JLabel perdidas = new JLabel("Perdidas:");
		panel.add(perdidas);
		
		perdidas = new JLabel(String.valueOf(desempeno.get("perdidas"))+" pesos");
		panel.add(perdidas);
		
		JLabel unidades = new JLabel("Unidades restantes:    ");
		panel.add(unidades);
		
		unidades = new JLabel(String.valueOf(unidadesRestantes));
		panel.add(unidades);
		
		JLabel gondola = new JLabel("Góndola:");
		panel.add(gondola);
		
		gondola = new JLabel(String.valueOf(producto.getAlmacenamiento()));
		panel.add(gondola);
		
		add(panel, BorderLayout.CENTER);
		
		if (producto.getHistorial().getDatos().size()>0) {
			PanelGraficoProducto grafica = new PanelGraficoProducto();
			
			DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			
			Random random = new Random();
		    float r = random.nextFloat();
		    float g = random.nextFloat();
		    float b = random.nextFloat();
		    Color randomColor = new Color(r, g, b);
		    
		    //se ordena el mapa
		    Map<Date, Integer> treeMap = new TreeMap<Date, Integer>(producto.getHistorial().getDatos());
			
			for (Map.Entry<Date, Integer> entry : treeMap.entrySet()) {
				String sFecha = formatter.format(entry.getKey());
				int cantidad = entry.getValue();
				grafica.anadirColumna(sFecha, cantidad, randomColor);
			}

	        grafica.layoutHistogram();
			
			add(grafica, BorderLayout.SOUTH);
		}
		
		pack();
		setTitle(producto.getNombre());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setResizable(false);
		setVisible(true);
	}
}
