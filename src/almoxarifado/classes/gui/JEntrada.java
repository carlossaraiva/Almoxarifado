package almoxarifado.classes.gui;

import almoxarifado.classes.logico.Conexao;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class JEntrada extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtQtde;
	private Produto p;
        private JButton btnAdicionar ;
        /**/
	private DefaultComboBoxModel<String> cboModelProdutos = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> cboModelFornecedores = new DefaultComboBoxModel<String>();
	private JComboBox<String> cboProduto;
	private JComboBox<String> cboFornecedor;
	
        public void atualizaCategoria(){
    
                String nome = "";
        		Produto pro = new Produto();
		//Executo uma busca sem nenhum valor para retornar tudo que existir no banco e guardo em uma variavel
		ResultSet result = pro.buscaProduto(""+nome+"");
		try
		{
			while(result.next())
			{
				//Exponho o elemento no objeto selecionando so a coluna nome que o interessante neste caso
				cboModelProdutos.addElement(result.getString("proNome"));
			}
		}
		catch(SQLException sqlex)
		{
			//Se ocorrer um erro este apare�era nesta excessao
			JOptionPane.showMessageDialog(null, "Erro "+sqlex.getLocalizedMessage()+".");
		}      
           }

	public JEntrada() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(580, 350, 337, 181);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 btnAdicionar = new JButton("Adicionar");
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
                setCboModel();
                            
	}


	@Override
	public void actionPerformed(ActionEvent e) {
            
         if(e.getSource() == btnAdicionar){
             int qtde = 0;
             Produto p = new Produto();
             int soma = 0;
             
             ResultSet resultado = p.buscaProduto((String)cboProduto.getSelectedItem());
            try {
                while(resultado.next()){
                    
                    qtde = resultado.getInt("proQtdAtua");
                    soma = qtde + Integer.parseInt(txtQtde.getText());
                    
                   // Produto pe = new Produto(resultado.getString("usuNome"), (String)resultado.getInt("usuPreco"), resultado.getString("usuMarca"), (String)resultado.getInt("usuQtdMinima"), toString(soma), (String)resultado.getInt("Maxima"));
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(JEntrada.class.getName()).log(Level.SEVERE, null, ex);
            }
            
             //p.alterarProduto(, qtde, qtde);
             
         }
		
	}
	
	public void setCboModel(){
		
             Conexao conn = new Conexao();
                conn.conecta();
                
                String sqlEntradaPro = "select * from Produto";
		ResultSet resultEntradaPro = conn.executaBusca(sqlEntradaPro);
		try {
                    while (resultEntradaPro.next()) 
                    {
                        cboProduto.addItem(resultEntradaPro.getString("proNome"));
                            
                    }
		}catch (SQLException sqlex1) {
			System.out.println("erro");
		}
  
                
                String sqlFornecedor = "select * from Fornecedor";
		ResultSet resultFor = conn.executaBusca(sqlFornecedor);
		try {
                    while (resultFor.next()) 
                    {
                        cboFornecedor.addItem(resultFor.getString("forNome"));
                            
                    }
		}catch (SQLException sqlex1) {
			System.out.println("erro");
		}
	}
            
            
            
	}       


