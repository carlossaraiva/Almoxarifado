package almoxarifado.classes.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class JEntrada extends JFrame {

	private JPanel contentPane;
	private JTextField txtQtde;


	public JEntrada() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 337, 181);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox cboProduto = new JComboBox();
		cboProduto.setBounds(100, 11, 150, 20);
		contentPane.add(cboProduto);
		
		JComboBox cboFornecedor = new JComboBox();
		cboFornecedor.setBounds(100, 39, 150, 20);
		contentPane.add(cboFornecedor);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(100, 114, 89, 23);
		contentPane.add(btnNewButton);
		
		txtQtde = new JTextField();
		txtQtde.setBounds(100, 70, 86, 20);
		contentPane.add(txtQtde);
		txtQtde.setColumns(10);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setBounds(10, 14, 46, 14);
		contentPane.add(lblProduto);
		
		JLabel lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setBounds(10, 42, 61, 14);
		contentPane.add(lblFornecedor);
		
		JLabel lblQtde = new JLabel("Qtde.:");
		lblQtde.setBounds(10, 73, 46, 14);
		contentPane.add(lblQtde);
		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
}
