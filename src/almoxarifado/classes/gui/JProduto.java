package almoxarifado.classes.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import almoxarifado.classes.logico.Produto;

public class JProduto extends JPanel implements ActionListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;
	
	private ArrayList <Produto> produtos = new ArrayList<Produto>();
	private DefaultListModel<String> modelProdutos = new DefaultListModel<String>();
	private JTextField txtNomeProduto;
	private JTextField txtMarca;
	private JTextField textField;	
	private JList<String> listProduto;	
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

		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				modelProdutos.clear();
				Produto pro = new Produto();
				ResultSet result = pro.buscaProduto(textField.getText());
				try
				{
					while(result.next())
					{
						modelProdutos.addElement(result.getString("forNome"));
					}
				}
				catch(SQLException teste)
				{
					System.out.println("Erro"+teste.getErrorCode());
				}
			}
		});
		textField.setBounds(429, 64, 189, 20);
		
		textField.setColumns(10);
		
		add(lblNomeProduto);
		add(lblMarca);
		add(textField);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(429, 94, 189, 253);
		add(scrollPane);
		
		listProduto= new JList<String>(modelProdutos);
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
	    if (e.getSource()==listProduto){
	    	txtNomeProduto.setText((String)listProduto.getSelectedValue());
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