package almoxarifado.classes.gui;
import almoxarifado.classes.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import almoxarifado.classes.logico.*;
import java.awt.Component;

import java.awt.Cursor;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.basic.BasicTreeUI;

public class JProduto extends JPanel implements ActionListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;


	private DefaultListModel modelProdutos = new DefaultListModel();
	private DefaultListModel buscaProdutos = new DefaultListModel();
	
	private JTextField txtNomeProduto;
        private JTextField txtPreco;
	private JTextField txtMarca;
        private JTextField txtQtdMinima;
        private JTextField txtQtdAtual;
        private JTextField txtQtdMaxima;
	private JTextField textField;	
	private JList listProduto;	
	private JButton btnAdicionar;
	private JButton btnLimpar;
	private JButton btnExcluir;
	private JButton btnAlterar;
        
	private JScrollPane scrollPane;
        
        private JComboBox<String> cboProduto;
        private JComboBox<String> cboFornecedor;
	
	//Metodo que atuliza no banco os resultados
	public void atualizaProduto(String nome)
	{
            //Limpo o objeto
            modelProdutos.clear();
            //Instancio objeto
            Produto pro = new Produto();            
            //Executo uma busca sem nenhum valor para retornar tudo que existir no banco e guardo em uma variavel
            ResultSet result = pro.buscaProduto(""+nome+"");
            try
            {
                    while(result.next())
                    {
                            //Exponho o elemento no objeto selecionando so a coluna nome que o interessante neste caso
                            modelProdutos.addElement(result.getString("proNome"));
                    }
            }
            catch(SQLException sqlex)
            {
                    //Se ocorrer um erro este apare�era nesta excessao
                    JOptionPane.showMessageDialog(null, "Erro "+sqlex.getLocalizedMessage()+".");
            }
	}	
	//Construtores
	public JProduto() {		
		setPainel();		
	}
	//Setters e getters
	
	private void setPainel(){
		setLayout(null);		
		
                atualizaProduto("");
                
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(10, 372, 89, 23);
		add(btnAdicionar);
		btnAdicionar.addActionListener(this);
				
		txtNomeProduto = new JTextField();
		txtNomeProduto.setBounds(10, 24, 170, 20);
		add(txtNomeProduto);
		txtNomeProduto.setColumns(10);
                
                txtPreco = new JTextField();
		txtPreco.setBounds(10, 76, 170, 20);
		add(txtPreco);
		txtPreco.setColumns(10);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(10, 134, 170, 20);
		add(txtMarca);
		txtMarca.setColumns(10);
                
                txtQtdMinima = new JTextField();
		txtQtdMinima.setBounds(10, 194, 170, 20);
		add(txtQtdMinima);
		txtQtdMinima.setColumns(10);
                
                txtQtdAtual = new JTextField();
		txtQtdAtual.setBounds(10, 254, 170, 20);
		add(txtQtdAtual);
		txtQtdAtual.setColumns(10);
                
                txtQtdMaxima = new JTextField();
		txtQtdMaxima.setBounds(10, 314, 170, 20);
		add(txtQtdMaxima);
		txtQtdMaxima.setColumns(10);
                
                JLabel lblNomeProduto = new JLabel("Nome do produto:");
		lblNomeProduto.setBounds(10, 05, 89, 14);
                add(lblNomeProduto);
				
		JLabel lblPreco = new JLabel("Preço:");
		lblPreco.setBounds(10, 55, 46, 14);
                add(lblPreco);
                
                JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(10, 105, 46, 14);
                add(lblMarca);
                
                JLabel lblQtdMinima = new JLabel("Quantidade miníma deste produto:");
		lblQtdMinima.setBounds(10, 165, 250, 14);
                add(lblQtdMinima);
                
                JLabel lblQtdAtual = new JLabel("Quantidade atual deste produto:");
		lblQtdAtual.setBounds(10, 225, 200, 14);
                add(lblQtdAtual);
                
                JLabel lblQtdMaxima = new JLabel("Quantidade máxima deste produto:");
		lblQtdMaxima.setBounds(10, 285, 250, 14);
                add(lblQtdMaxima);
				
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(429, 360, 89, 23);
		btnExcluir.addActionListener(this);
		btnExcluir.setEnabled(false);
		add(btnExcluir);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(529, 360, 89, 23);
		btnAlterar.addActionListener(this);
		btnAlterar.setEnabled(false);
		add(btnAlterar);	
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(this);
		btnLimpar.setBounds(123, 372, 89, 23);
				
		textField = new JTextField();
		//Quando digitado algo no txt o evento e disparado es por sua vez chama outro
		textField.addKeyListener
		(
				new KeyAdapter() 
				{	
					@Override
					public void keyReleased(KeyEvent arg0) 
					{
						atualizaProduto(textField.getText());
					}
				}
		);
		textField.setBounds(429, 64, 189, 20);
		textField.setColumns(10);		
		add(textField);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(429, 94, 189, 253);
		add(scrollPane);
                
                JLabel catProduto = new JLabel("Categoria deste produto:");
                catProduto.setBounds(200, 05, 150, 14);
                add(catProduto);
                
                cboProduto = new JComboBox<String>();
		cboProduto.setBounds(200, 23, 132, 20);
		add(cboProduto);
                cboProduto.addMouseListener
                (
                    new MouseAdapter()
                    {
                        @Override
                        public void mouseReleased(MouseEvent arg0)
                        {
                            cboProduto.removeAllItems();
                            Categoria cat = new Categoria();
                            ResultSet res = cat.buscaCategoria("");
                            try
                            {
                                while(res.next())
                                {
                                        //Exponho o elemento no objeto selecionando so a coluna nome que o interessante neste caso
                                        cboProduto.addItem(res.getString("catNome"));
                                }
                            }catch(SQLException sqlex)
                            {
                                System.out.println("Erro na atualização da categoria");
                            }
                        }
                   }
                );
                
                
                JLabel forNome = new JLabel("Nome do fornecedor:");
                forNome.setBounds(200, 55, 150, 14);
                add(forNome);
                
                cboFornecedor = new JComboBox<String>();
		cboFornecedor.setBounds(200, 76, 132, 20);
		add(cboFornecedor);
                cboFornecedor.addMouseListener
                (
                    new MouseAdapter()
                    {
                        @Override
                        public void mouseReleased(MouseEvent arg0)
                        {
                            cboFornecedor.removeAllItems();
                            Fornecedor forn = new Fornecedor();
                            ResultSet re = forn.buscaFornecedor("");
            
                            try
                            {
                                while(re.next())
                                {
                                        //Exponho o elemento no objeto selecionando so a coluna nome que o interessante neste caso
                                        cboFornecedor.addItem(re.getString("forNome"));
                                }
                            }catch(SQLException sqlex)
                            {
                                System.out.println("Erro na atualização da categoria");
                            }
                        }
                   }
		);
		listProduto = new JList(modelProdutos);
		scrollPane.setViewportView(listProduto);
		listProduto.addListSelectionListener(this);
		add(btnLimpar);
	}
	
	//Listeners
	public void valueChanged(ListSelectionEvent e){
		
            
	    if (e.getValueIsAdjusting() == false) {

	        if (listProduto.getSelectedIndex() == -1) {
	        //No selection, disable fire button.
	            btnExcluir.setEnabled(false);
	            btnAlterar.setEnabled(false);

	        } else {
	        //Selection, enable the fire button.
	            btnExcluir.setEnabled(true);
	            btnAlterar.setEnabled(true);
	        }		    
	    }		
	    if (e.getSource() == listProduto){
	    	String esc = (String)listProduto.getSelectedValue();
	    	Produto pro = new Produto();
	    	ResultSet result = pro.buscaProduto(esc);
	    	try
	    	{
	    		while(result.next()){
	    			txtNomeProduto.setText(result.getString("proNome"));
                                txtPreco.setText(result.getString("proPreco"));
                                txtMarca.setText(result.getString("proMarca"));
                                txtQtdMinima.setText(result.getString("proQtdMinima"));
                                txtQtdAtual.setText(result.getString("proQtdAtua"));
                                txtQtdMaxima.setText(result.getString("proQtdMaxima"));
	    		}		
	    	}
	    	catch(SQLException sqlex)
	    	{
	    		JOptionPane.showMessageDialog(null, "Erro "+sqlex.getLocalizedMessage()+".");
	    	}
	    }
	}
        
	public void actionPerformed(ActionEvent e) {
		
                
		if (e.getSource() == btnAdicionar)
		{
			if(txtNomeProduto.getText().contentEquals(""))
			{
				JOptionPane.showMessageDialog(null, "Não pode ser deixar campos vazios.");
				txtNomeProduto.setCursor(null);
			}
			else
			{
				String nom=""; 
                                int nom1=0;
                                int nom2=0;
				Produto pro = new Produto(txtNomeProduto.getText(), txtPreco.getText(), txtMarca.getText(), txtQtdMinima.getText(), txtQtdAtual.getText(), txtQtdMaxima.getText());
				ResultSet result = pro.buscaProduto(txtNomeProduto.getText());
				try
				{
					while(result.next())
					{
						nom = result.getString("proNome");
					}
				}
				catch(SQLException sqlex)
				{
					JOptionPane.showMessageDialog(null, "Erro "+sqlex.getLocalizedMessage()+".");
				}
				
				if(nom.contentEquals(txtNomeProduto.getText()))
				{
					JOptionPane.showMessageDialog(null,"Já existe produto com este nome.");
				}
				else
				{
                                    Categoria cat = new Categoria();
                                    Fornecedor forn = new Fornecedor();
                                    ResultSet res = cat.buscaCategoria((String)cboProduto.getSelectedItem());
                                    ResultSet res1 = forn.buscaFornecedor((String)cboFornecedor.getSelectedItem());
                                    try
                                    {
                                        while(res.next())
                                        {
                                            nom1 = res.getInt("catCodigo");
                                        }
                                        while(res1.next())
                                        {
                                            nom2 = res1.getInt("forCodigo");
                                        }
                                    }
                                    catch(SQLException sqlex)
                                    {
                                        JOptionPane.showMessageDialog(null, "Erro "+sqlex.getLocalizedMessage()+".");
                                    }

                                    pro.insereProduto(nom1,nom2);
                                    atualizaProduto("");
                                    JOptionPane.showMessageDialog(null,"Produto adicionado com sucesso!");
                                    txtNomeProduto.setText(null);
                                    txtPreco.setText(null);
                                    txtMarca.setText(null);
                                    txtQtdMinima.setText(null);
                                    txtQtdAtual.setText(null);
                                    txtQtdMaxima.setText(null);
				}
			}
		}		
		if(e.getSource() == btnExcluir){
			/*Mensagem de segurança para ver se o usuario tem certeza que deseja deletar*/
                        int esc = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja deletar este produto ?");
                        /*Verifico a escolha se afirmativa*/
                        if(esc == 0)
                        {
                            int catCodigo = 0;
                            Produto fun = new Produto();
                            /*Verifico se o usuario que esta no text existe no banco*/
                            ResultSet result = fun.buscaProduto(txtNomeProduto.getText());
                            try
                            {
                                while(result.next())
                                {
                                    /*Se existir armazeno seu codigo*/
                                    catCodigo = result.getInt("proCodigo");
                                }
                            } 
                            catch(SQLException ex)
                            {
                                Logger.getLogger(JCategoria.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            /*Verifico se existe algo para se deletar*/
                            if(catCodigo != 0)
                            {
                                /*Se existir ele deleta o valor apartir do valor do seu codigo primario*/
                                fun.excluirProduto(catCodigo);
                                /*Atualizo o list*/
                                atualizaProduto("");
                                /*limpo o text*/
                                txtNomeProduto.setText(null);
                                txtPreco.setText(null);
                                txtMarca.setText(null);
                                txtQtdMinima.setText(null);
                                txtQtdAtual.setText(null);
                                txtQtdMaxima.setText(null);                            
                            }else
                            {
                                JOptionPane.showMessageDialog(null,"Não existe esta categoria.");
                            }
                        }
                        else
                        {
                            /*Atualizo o list*/
                            atualizaProduto("");
                        }
		}
		if(e.getSource() == btnAlterar){
			int esc = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja alterar este funcionario?");
                        if(esc == 0)
                        {
                            int proCodigo = 0;
                            int forCodigo = 0;
                            int catCodigo = 0;
                            Produto pro = new Produto(txtNomeProduto.getText(), txtPreco.getText(), txtMarca.getText(), txtQtdMinima.getText(), txtQtdAtual.getText(), txtQtdMaxima.getText());
                            Fornecedor forn = new Fornecedor();
                            Categoria cat = new Categoria();
                            /*Guardo a escolha do usuario na variavel esco*/
                            String esco = (String)listProduto.getSelectedValue();
                            /*Verifico se o usuario que esta no text existe no banco com a escolha do usuario*/
                            ResultSet result = pro.buscaProduto(esco);
                            ResultSet res = cat.buscaCategoria((String)cboProduto.getSelectedItem());
                            ResultSet re = forn.buscaFornecedor((String)cboFornecedor.getSelectedItem());
                            try
                            {
                                while(result.next())
                                {
                                    proCodigo = result.getInt("proCodigo");
                                }
                                while(re.next())
                                {
                                    forCodigo = re.getInt("forCodigo");
                                }
                                while(res.next())
                                {
                                    catCodigo = res.getInt("catCodigo");
                                }
                            }
                            catch(SQLException sqlex)
                            {
                                JOptionPane.showMessageDialog(null, "Erro "+sqlex.getLocalizedMessage()+".");
                            }
                            /*Verifico se existe algo para se deletar*/
                            if(proCodigo != 0)
                            {
                                /*Se existir a categoria será alterada apartir do codigo desta*/
                                pro.alterarProduto(proCodigo, catCodigo, forCodigo);
                                /*Atualizo já alterada*/
                                atualizaProduto("");
                                /*limpo o campo text*/
                                txtNomeProduto.setText(null);
                                txtPreco.setText(null);
                                txtMarca.setText(null);
                                txtQtdMinima.setText(null);
                                txtQtdAtual.setText(null);
                                txtQtdMaxima.setText(null);	
                            }
                        }
                        else
                        {
                            /*Atualizo o campo text*/
                            atualizaProduto("");
                        }
		}
		if(e.getSource() == btnLimpar){
                    txtNomeProduto.setText(null);
                    txtPreco.setText(null);
                    txtMarca.setText(null);
                    txtQtdMinima.setText(null);
                    txtQtdAtual.setText(null);
                    txtQtdMaxima.setText(null);	
		}
	}
}