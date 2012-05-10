package almoxarifado.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import almoxarifado.classes.base.Fornecedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JFornecedor extends JPanel implements ActionListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;
	
	private ArrayList <Fornecedor> fornecedores = new ArrayList<Fornecedor>();
	private DefaultListModel modelFornecedores = new DefaultListModel();
	
	private JTextField txtRzSocial;
	private JTextField txtCNPJ;
	private JTextField textField;	
	private JList listFornecedor;	
	private JButton btnAdicionar;
	private JButton btnLimpar;
	private JButton btnExcluir;
	private JScrollPane scrollPane;
	
	//Construtores
	public JFornecedor() {		
		setPainel();		
	}		
	
	public JFornecedor(ArrayList<Fornecedor> fornecedores){		
		setPainel();
		this.fornecedores = fornecedores;		
		setModelFornecedores();
	}
	
	//Getters e setters
	public void setModelFornecedores(){
		for(Fornecedor f : fornecedores){
			modelFornecedores.addElement(f.getRzSocial());
		}	
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
		
		add(lblNomeCompleto);
		add(lblRegistro);
		add(textField);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(429, 94, 189, 253);
		add(scrollPane);
		
		listFornecedor = new JList(modelFornecedores);
		scrollPane.setViewportView(listFornecedor);
		listFornecedor.addListSelectionListener(this);
		add(btnLimpar);
	
	}
	
	public ArrayList<Fornecedor> getFornecedores(){
		return this.fornecedores;
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
			Fornecedor f = new Fornecedor();		
			f.setRzSocial(txtRzSocial.getText());			
			modelFornecedores.addElement(f.getRzSocial());
			f.setCNPJ(txtCNPJ.getText());
			fornecedores.add(f);						
			txtRzSocial.setText(null);
			txtCNPJ.setText(null);			
		}
		
		if(e.getSource() == btnExcluir){
			fornecedores.remove(listFornecedor.getSelectedIndex());
			modelFornecedores.remove(listFornecedor.getSelectedIndex());			
		}
		
		showArray();
	}
	
	//debugagem
	public void showArray(){
	
		for(Fornecedor f: fornecedores){			
			System.out.println(f.getRzSocial() + f.getCNPJ());
					
		}
	}
}
