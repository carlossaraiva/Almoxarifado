package almoxarifado.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;

public class JEstoque extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tab;
	private JPanel painelEstoque;
	private JFrame caixaEntrada;
	private JEntrada entrada;
	
	public JEstoque() {
		setLayout(null);
		painelEstoque = new JPanel();
				
		tab = new JTabbedPane(JTabbedPane.TOP);
		tab.setBounds(0, 0, 667, 476);
		tab.addTab("Estoque", painelEstoque);
		painelEstoque.setLayout(null);
		
		JLabel lblDatahora = new JLabel("Data/Hora:");
		lblDatahora.setBounds(21, 32, 59, 14);
		painelEstoque.add(lblDatahora);
		
		JLabel lbldataHoraAqui = new JLabel("(Data HOra Aqui");
		lbldataHoraAqui.setBounds(90, 32, 79, 14);
		painelEstoque.add(lbldataHoraAqui);
		
		JButton btnNovaEntrada = new JButton("Nova Entrada");
		btnNovaEntrada.addActionListener(this);
		
		btnNovaEntrada.setBounds(23, 380, 113, 23);
		painelEstoque.add(btnNovaEntrada);
		
		JButton btnNovaSada = new JButton("Nova Sa\u00EDda");
		btnNovaSada.setBounds(146, 380, 105, 23);
		painelEstoque.add(btnNovaSada);
		
		JLabel lblltimasEntradas = new JLabel("\u00DAltimas entradas:");
		lblltimasEntradas.setBounds(21, 98, 96, 14);
		painelEstoque.add(lblltimasEntradas);
		
		JLabel lblltimasSadas = new JLabel("\u00DAltimas sa\u00EDdas:");
		lblltimasSadas.setBounds(21, 169, 96, 14);
		painelEstoque.add(lblltimasSadas);
		
		JLabel lblProdutoEmEstoque = new JLabel("Produto em Estoque M\u00EDnimo");
		lblProdutoEmEstoque.setBounds(21, 232, 148, 14);
		painelEstoque.add(lblProdutoEmEstoque);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(21, 150, 631, 14);
		painelEstoque.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(21, 73, 631, 14);
		painelEstoque.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(21, 207, 631, 14);
		painelEstoque.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(21, 281, 631, 14);
		painelEstoque.add(separator_3);
		add(tab);

		entrada = new JEntrada();
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		entrada.setVisible(true);
	}
}
