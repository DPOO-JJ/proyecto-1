package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Mes extends JPanel implements ActionListener {
	
	private PanelGraficoCliente padre;
	private JButton[][] botones;

	public Mes(PanelGraficoCliente padre, String mes, Integer dias) {
		this.padre = padre;
		setLayout(new BorderLayout());
		JLabel mesLabel = new JLabel(mes);
		add(mesLabel);
		JPanel panel = new JPanel();
		botones = new JButton[4][8];
		
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				botones[i][j] = new JButton(); // TODO que muestre el numero
				botones[i][j].setActionCommand("" + i + "_" + j);
				botones[i][j].addActionListener(this);
				panel.add(botones[i][j]);
			}
		}
		add(panel);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
