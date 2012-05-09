package Codigo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class JFornecedor extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	private ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
	
	private JTextField txtRzSocial;
	private JTextField txtTelefone;
	private JTextField txtEndereco;
	private JButton btnAdicionar;

	public JFornecedor() {
		setLayout(null);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(43, 304, 89, 23);
		add(btnAdicionar);
		
		txtRzSocial = new JTextField();
		txtRzSocial.setBounds(43, 51, 86, 20);
		add(txtRzSocial);
		txtRzSocial.setColumns(10);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(43, 195, 86, 20);
		add(txtTelefone);
		txtTelefone.setColumns(10);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(43, 124, 86, 20);
		add(txtEndereco);
		txtEndereco.setColumns(10);
		
		JLabel lblRzSocial = new JLabel("Raz\u00E3o Social:");
		lblRzSocial.setBounds(43, 21, 86, 14);
		add(lblRzSocial);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setBounds(43, 99, 46, 14);
		add(lblEndereo);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(43, 170, 46, 14);
		add(lblTelefone);

	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdicionar){
			Fornecedor f = new Fornecedor();		
			f.setNome(txtRzSocial.getText());						
			f.setEndereco(txtEndereco.getText());
			f.setTelefone(txtTelefone.getText());
			fornecedores.add(f);			
			txtRzSocial.setText(null);
			txtEndereco.setText(null);
			txtTelefone.setText(null);
		}
	}

	public ArrayList<Fornecedor> getFornecedores(){
		return this.fornecedores;
	}
 }