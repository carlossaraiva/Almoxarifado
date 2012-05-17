package almoxarifado.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import almoxarifado.classes.Produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JProduto extends JPanel implements ActionListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;
	
	private ArrayList <Produto> produtos = new ArrayList<Produto>();
	private DefaultListModel modelProdutos = new DefaultListModel();
	
	private JTextField txtNomeProduto;
	private JTextField txtMarca;
	private JTextField textField;	
	private JList listProduto;	
	private JButton btnAdicionar;
	private JButton btnLimpar;
	private JButton btnExcluir;
	
	private JLabel lblNomeProduto;
	private JLabel lblMarca;
	private JScrollPane scrollPane;
	
	//Construtores
	public JProduto() {		
		setPainel();		
	}		
	
	public JProduto(ArrayList<Produto> produtos){		
		setPainel();
		this.produtos = produtos;		
		setModelProdutos();
	}
	
	//Setters e getters
	
	public void setModelProdutos(){
		for(Produto p : produtos){
			modelProdutos.addElement(p.getNome());
		}	
	}
	
	private void setPainel(){
		setLayout(null);		
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(10, 372, 89, 23);
		add(btnAdicionar);
		btnAdicionar.addActionListener(this);
				
		txtNomeProduto = new JTextField();
		txtNomeProduto.setBounds(10, 64, 220, 20);
		add(txtNomeProduto);
		txtNomeProduto.setColumns(10);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(10, 116, 220, 20);
		add(txtMarca);
		txtMarca.setColumns(10);
				
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(429, 360, 89, 23);
		btnExcluir.addActionListener(this);
		btnExcluir.setEnabled(false);
		add(btnExcluir);	
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(this);
		btnLimpar.setBounds(123, 372, 89, 23);
					
		lblNomeProduto = new JLabel("Nome do produto:");
		lblNomeProduto.setBounds(10, 39, 89, 14);
				
		lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(10, 95, 46, 14);
				
		textField = new JTextField();
		textField.setBounds(429, 64, 189, 20);
		
		textField.setColumns(10);
		
		add(lblNomeProduto);
		add(lblMarca);
		add(textField);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(429, 94, 189, 253);
		add(scrollPane);
		
		listProduto= new JList(modelProdutos);
		scrollPane.setViewportView(listProduto);
		listProduto.addListSelectionListener(this);
		add(btnLimpar);
	
	}
	
	public ArrayList<Produto> getProdutos(){
		return this.produtos;
	}
	
	//Listeners
	public void valueChanged(ListSelectionEvent e){
		
		System.out.println(listProduto.getSelectedIndex());
		
	    if (e.getValueIsAdjusting() == false) {

	        if (listProduto.getSelectedIndex() == -1) {
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
			Produto p = new Produto();		
			p.setNome(txtNomeProduto.getText());			
			modelProdutos.addElement(p.getNome());
			p.setMarca(txtMarca.getText());
			produtos.add(p);						
			txtNomeProduto.setText(null);
			txtMarca.setText(null);			
		}
		
		if(e.getSource() == btnExcluir){
			produtos.remove(listProduto.getSelectedIndex());
			modelProdutos.remove(listProduto.getSelectedIndex());			
		}
		
		showArray();
	}
	
	//debugem
	public void showArray(){
	
		for(Produto p: produtos){			
			System.out.println(p.getNome() + p.getMarca());
					
		}
	}
}