package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class Mes extends JPanel implements ActionListener {
	
	private PanelGraficoCliente padre;
	private JButton[][] botones;

	public Mes(PanelGraficoCliente padre, String mes, Integer dias) {
		this.padre = padre;
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(120, 120));
		
		JLabel jlabel = new JLabel("<html><B>" + mes + "</B></html>");
		jlabel.setHorizontalAlignment(JLabel.CENTER);
		jlabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		add(jlabel, BorderLayout.NORTH);
		
		JPanel p = new JPanel();
		GridLayout meses = new GridLayout(5,7);
		
		p.setLayout(meses);
		
		for (int i = 0;i<dias;i++) {
			JButton button = new JButton();
			button.setToolTipText(Integer.toString(i+1));
			button.setBackground(Color.WHITE);
			button.setBorder(BorderFactory.createLineBorder(Color.black, 1,true));
			button.setOpaque(true);
			p.add(button);
		};
		add(p, BorderLayout.CENTER);
		
		Border blackline = BorderFactory.createLineBorder(Color.black, 1, true);
		
		setBorder(blackline);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
