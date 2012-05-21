package almoxarifado.classes.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import almoxarifado.classes.logico.Produto;

public class JEntrada extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtQtde;
	private Produto p;
	private DefaultComboBoxModel<String> cboModelProdutos = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> cboModelFornecedores = new DefaultComboBoxModel<String>();
	private JComboBox<String> cboProduto;
	private JComboBox<String> cboFornecedor;
	


	public JEntrada() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(580, 350, 337, 181);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(100, 114, 89, 23);
		contentPane.add(btnAdicionar);
		btnAdicionar.addActionListener(this);
		
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
		
		cboProduto = new JComboBox<String>(cboModelProdutos);
		cboProduto.setBounds(100, 11, 132, 20);
		contentPane.add(cboProduto);
		
		cboFornecedor = new JComboBox<String>(cboModelFornecedores);
		cboFornecedor.setBounds(100, 39, 132, 20);
		contentPane.add(cboFornecedor);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		p.alterarProduto((String)cboModelProdutos.getSelectedItem(),Integer.parseInt(txtQtde.getText()));
		
		setVisible(false);
		
	}
	
	public void setCboModel(){
		cboModelProdutos.addElement("Doia");
	}
}
