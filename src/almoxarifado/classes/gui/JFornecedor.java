package almoxarifado.classes.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import almoxarifado.classes.logico.Fornecedor;
import almoxarifado.classes.logico.Funcionario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JFornecedor extends JPanel implements ActionListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;
	

	private DefaultListModel<String> modelFornecedores = new DefaultListModel<String>();
	private Fornecedor f;
	
	private JTextField txtRzSocial;
	private JTextField txtCNPJ;
	private JTextField textField;	
	private JList<String> listFornecedor;	
	private JButton btnAdicionar;
	private JButton btnLimpar;
	private JButton btnExcluir;
	private JScrollPane scrollPane;
	
	//Construtores
	public JFornecedor() {		
		setPainel();		
	}		
	
	
	private void setPainel(){
		setLayout(null);		
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(10, 372, 89, 23);
		add(btnAdicionar);
		btnAdicionar.addActionListener(this);
				
		txtRzSocial = new JTextField();
		txtRzSocial.setBounds(10, 64, 220, 20);
		add(txtRzSocial);
		txtRzSocial.setColumns(10);
		
		txtCNPJ = new JTextField();
		txtCNPJ.setBounds(10, 116, 220, 20);
		add(txtCNPJ);
		txtCNPJ.setColumns(10);
				
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(429, 360, 89, 23);
		btnExcluir.addActionListener(this);
		btnExcluir.setEnabled(false);
		add(btnExcluir);	
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(this);
		btnLimpar.setBounds(123, 372, 89, 23);
					
		JLabel lblNomeCompleto = new JLabel("Nome completo:");
		lblNomeCompleto.setBounds(10, 39, 89, 14);
				
		JLabel lblRegistro = new JLabel("Registro:");
		lblRegistro.setBounds(10, 95, 46, 14);
				
		textField = new JTextField();
		textField.setBounds(429, 64, 189, 20);
		
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				modelFornecedores.clear();
				f = new Fornecedor();
				ResultSet result = f.buscaFornecedor(textField.getText());
				try
				{
					while(result.next())
					{
						modelFornecedores.addElement(result.getString("forNome"));
					}
				}
				catch(SQLException teste)
				{
					System.out.println("Erro"+teste.getErrorCode());
				}
			}
		});
		
		add(lblNomeCompleto);
		add(lblRegistro);
		add(textField);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(429, 94, 189, 253);
		add(scrollPane);
		
		listFornecedor = new JList<String>(modelFornecedores);
		scrollPane.setViewportView(listFornecedor);
		listFornecedor.addListSelectionListener(this);
		add(btnLimpar);
	
	}
	
	
	//Listeners
	public void valueChanged(ListSelectionEvent e){
		
		System.out.println(listFornecedor.getSelectedIndex());
		
	    if (e.getValueIsAdjusting() == false) {

	        if (listFornecedor.getSelectedIndex() == -1) {
	        //No selection, disable fire button.
	            btnExcluir.setEnabled(false);

	        } else {
	        //Selection, enable the fire button.
	            btnExcluir.setEnabled(true);
	        }		    
	    }		
	}
	
	public void actionPerformed(ActionEvent e) {
	
		
		if (e.getSource() == btnAdicionar){
			f = new Fornecedor(txtRzSocial.getText(),txtCNPJ.getText());
			f.insereFornecedor();
			txtRzSocial.setText(null);
			txtCNPJ.setText(null);			
		}
		
		if(e.getSource() == btnExcluir){
			f.excluirFornecedor(listFornecedor.getSelectedValue());
			
		
		}	
	}
}
