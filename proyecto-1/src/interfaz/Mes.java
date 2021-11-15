package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class Mes extends JPanel implements ActionListener {
	
	private PanelGraficoCliente padre;

	public Mes(PanelGraficoCliente padre, String mes, Integer dias, ArrayList<ArrayList<String>> comprasOcurrencias) {
		this.padre = padre;
		
		//TODO COLOREs
		
		Color verde1 = new Color(153, 255, 0);
		Color verde2 = new Color(183, 255, 74);
		Color verde3 = new Color(211, 255, 145);
		Color verde4 = new Color(228, 255, 189);
		Color verde5 = new Color(242, 255, 224);
		
		
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
			for (int j = 0;j<comprasOcurrencias.size();j++)
			{
				if (i == Integer.parseInt(comprasOcurrencias.get(j).get(0)))
				{
					if (Integer.parseInt(comprasOcurrencias.get(j).get(1))>100000)
					{
						button.setBackground(verde1);
					}
					else if (Integer.parseInt(comprasOcurrencias.get(j).get(1))>75000)
					{
						button.setBackground(verde2);
					}
					else if (Integer.parseInt(comprasOcurrencias.get(j).get(1))>50000)
					{
						button.setBackground(verde3);
					}
					else if (Integer.parseInt(comprasOcurrencias.get(j).get(1))>25000)
					{
						button.setBackground(verde4);
					}
					else
					{
						button.setBackground(verde5);
					}
				}
			}
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
