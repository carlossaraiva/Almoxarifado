package Codigo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;

public class JProduto extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private ArrayList <Produto> produtos = new ArrayList<Produto>();
	private DefaultListModel modelProdutos = new DefaultListModel();
	
	private JTextField txtNome;
	private JTextField txtMarca;
	private JButton btnAdicionar;
	private JList listProdutos;

	
	public JProduto() {
		setLayout(null);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(10, 337, 89, 23);
		add(btnAdicionar);
		btnAdicionar.addActionListener(this);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 82, 258, 20);
		add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 57, 46, 14);
		add(lblNome);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(10, 157, 258, 20);
		add(txtMarca);
		txtMarca.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(10, 132, 46, 14);
		add(lblMarca);
		
		listProdutos = new JList(modelProdutos);
		listProdutos.setBounds(415, 84, 207, 295);
		add(listProdutos);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(425, 390, 89, 23);
		add(btnExcluir);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdicionar){
			Produto p = new Produto();		
			p.setNome(txtNome.getText());						
			p.setMarca(txtMarca.getText());
			produtos.add(p);			
			modelProdutos.addElement(txtNome.getText());
			txtNome.setText(null);
			txtMarca.setText(null);
		}
	}
	
	public ArrayList<Produto> getProdutos(){
		return produtos;
	}
}
