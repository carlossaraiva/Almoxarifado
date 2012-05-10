package almoxarifado.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JEstoque extends JPanel {

	private static final long serialVersionUID = 1L;

	public JEstoque() {
		setLayout(null);
		
		JButton btnGerarSada = new JButton("Gerar Sa\u00EDda");
		btnGerarSada.setBounds(44, 391, 117, 29);
		add(btnGerarSada);

	}
}
