package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import processing.Lote;

@SuppressWarnings("serial")
public class VentanaLotes extends JFrame implements ActionListener, PanelPopup{
	
	private AplicacionInventario padre;
	private PanelProductos pProductos;
	
	ArrayList<Lote> lotesFiltrados;
	JList<String> lotes;
	
	public VentanaLotes(AplicacionInventario padre, ArrayList<Lote> lotesFiltrados) {
		
		this.padre = padre;
		this.lotesFiltrados = lotesFiltrados;
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 35, 100));
		
		//label
//		JLabel jlabel = new JLabel("Fecha recibido \t| Fecha de Vencimiento \t| Precio de Compra \t|"
//	    		+ " Precio de Venta \t| Unidades originales \t|"
//	    		+ " Unidades restantes \t| ¿El lote fue eliminado? \t| Producto del lote");
//		jlabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
//		jlabel.setHorizontalAlignment(JLabel.LEFT);
//		panel.add(jlabel,BorderLayout.NORTH);
		
		//lista
		
		lotes=new JList<String>();
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)lotes.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.LEFT);  
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		
		int i = 1;
		
		for (Lote lote: lotesFiltrados) {
			modelo.addElement("Lote No."+Integer.toString(i)+", Unidades restantes: "+lote.getUnidadesRestantes()
			+", Fecha de Vencimiento "+lote.getFechaVencimiento()+", Precio de venta:"+ lote.getPrecioVenta()
			+", ¿Ha sido eliminado? "+(lote.isLoteEliminado()?"Si":"No"));
			i++;
		}

		lotes.setModel(modelo);
		
		JScrollPane scrollLista = new JScrollPane();
		scrollLista.setBounds(20, 120,220, 80);
		scrollLista.setViewportView(lotes);
		
		panel.add(scrollLista, BorderLayout.CENTER);
		
		//botones
		JPanel p = new JPanel();
		BorderLayout bl = new BorderLayout();
		p.setLayout(bl);
		
		JButton button = new JButton("Eliminar");
		button.addActionListener(this);
		p.add(button, BorderLayout.EAST);
		
		panel.add(p,BorderLayout.SOUTH);
		
		add(panel);
		
		pack();
		
		setTitle("Eliminar lote");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setResizable(false);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int selectedIndex = lotes.getSelectedIndex();
		if (selectedIndex == -1) {
			new VentanaError(this,"Eliminar lote","Seleccione un lote a eliminar.");
		}
		else {
			new VentanaExitosa(this,"Eliminar lote","¿Está seguro de que quiere eliminar el lote "+(selectedIndex+1)+"?");
		}
	}

	@Override
	public void aceptar(String titulo, boolean aceptada) {
		if (aceptada) {
			this.padre.eliminarLote(this.lotesFiltrados,lotes.getSelectedIndex());
		}
		dispose();
		
	}

	@Override
	public void error(String titulo) {
		// TODO Auto-generated method stub
		
	}
}
