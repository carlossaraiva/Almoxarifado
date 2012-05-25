package almoxarifado.classes.gui;

import almoxarifado.classes.logico.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JFornecedor extends JPanel implements ActionListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;
	

	private DefaultListModel<String> modelFornecedores = new DefaultListModel<String>();
	private Fornecedor f;
	
	private JTextField txtRzSocial;
	private JTextField txtCNPJ;
        private JTextField txtTelefone;
	private JTextField textField;	
	private JList<String> listFornecedor;	
	private JButton btnAdicionar;
        private JButton btnAlterar;
	private JButton btnLimpar;
	private JButton btnExcluir;
	private JScrollPane scrollPane;
        
        public void atualizaCategoria(String nome)
        {
            //Limpo o objeto
            modelFornecedores.clear();
            //Instancio objeto
            Fornecedor cat = new Fornecedor();
            //Executo uma busca sem nenhum valor para retornar tudo que existir no banco e guardo em uma variavel
            ResultSet result = cat.buscaFornecedor(""+nome+"");
            try
            {
                while(result.next())
                {
                    //Exponho o elemento no objeto selecionando so a coluna nome que o interessante neste caso
                    modelFornecedores.addElement(result.getString("forNome"));
                }
            }
            catch(SQLException sqlex)
            {
                //Se ocorrer um erro este apareï¿½era nesta excessao
                JOptionPane.showMessageDialog(null, "Erro "+sqlex.getLocalizedMessage()+".");
            }  
        }
	
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
                
                btnAlterar = new JButton("Alterar");
                btnAlterar.setBounds(529, 360, 89, 23);
                btnAlterar.addActionListener(this);
                btnAlterar.setEnabled(false);
                add(btnAlterar);
				
		txtRzSocial = new JTextField();
		txtRzSocial.setBounds(10, 64, 220, 20);
		add(txtRzSocial);
		txtRzSocial.setColumns(10);
		
		txtCNPJ = new JTextField();
		txtCNPJ.setBounds(10, 116, 220, 20);
		add(txtCNPJ);
		txtCNPJ.setColumns(10);
                
                txtTelefone = new JTextField();
		txtTelefone.setBounds(10, 166, 220, 20);
		add(txtTelefone);
		txtTelefone.setColumns(10);
				
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
				
		JLabel lblRegistro = new JLabel("Endereço:");
		lblRegistro.setBounds(10, 95, 76, 14);
                
                JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 145, 76, 14);
				
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
                add(lblTelefone);
		add(textField);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(429, 94, 189, 253);
		add(scrollPane);
		
		listFornecedor = new JList<String>(modelFornecedores);
		scrollPane.setViewportView(listFornecedor);
		listFornecedor.addListSelectionListener(this);
		add(btnLimpar);
                
                atualizaCategoria("");
	
	}
	
	
	//Listeners
	public void valueChanged(ListSelectionEvent e){
		
		System.out.println(listFornecedor.getSelectedIndex());
		
	    if (e.getValueIsAdjusting() == false) {

	        if (listFornecedor.getSelectedIndex() == -1) {
	        //No selection, disable fire button.
	            btnExcluir.setEnabled(false);
                    btnAlterar.setEnabled(false);

	        } else {
	        //Selection, enable the fire button.
	            btnExcluir.setEnabled(true);
                    btnAlterar.setEnabled(true);
	        }		    
	    }	
            if (e.getSource() == listFornecedor)
            {
                /*Se um valor for selecionado na list este por sua vez é armazenado na variavel esc*/
                String esc = (String)listFornecedor.getSelectedValue();
                /*Instancia-se uma categoria*/
                Fornecedor cat = new Fornecedor();
                /*Busca um categoria com o valor selecionado*/
                ResultSet result = cat.buscaFornecedor(esc);
                try
                {
                    while(result.next()){
                        /*Se houver uma categoria com o nome coloca-se o resultado no campo de texto*/
                        txtRzSocial.setText(result.getString("forNome"));
                        txtCNPJ.setText(result.getString("forEndereco"));
                        txtTelefone.setText(result.getString("forTelefone"));
                    }		
                }
                catch(SQLException sqlex)
                {
                    JOptionPane.showMessageDialog(null, "Erro "+sqlex.getLocalizedMessage()+".");
                }
            }
	}
	
	public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == btnAdicionar)
            {
                /*Verifica se há algo do objeto text se não houver nada ele retorna uma mensagem de erro.*/
                if(txtRzSocial.getText().contentEquals(""))
                {
                    JOptionPane.showMessageDialog(null, "Não pode ser deixar campos vazios.");
                }
                else
                {
                    String nom=""; 
                    /*Instancia Funcionario*/
                    Fornecedor fun = new Fornecedor(txtRzSocial.getText(), txtCNPJ.getText(), txtTelefone.getText());
                    /*Executa uma busca para ver se esse valor ja existe no banco*/
                    ResultSet result = fun.buscaFornecedor(txtRzSocial.getText());
                    try
                    {
                        while(result.next())
                        {
                            /*Se houver ele guarda na variavel "nom"*/
                            nom = result.getString("forNome");
                        }
                    }
                    catch(SQLException sqlex)
                    {
                            JOptionPane.showMessageDialog(null, "Erro "+sqlex.getLocalizedMessage()+".");
                    }
                    /*Verifica se o valor na variavel "nom" e igual a que esta armazenado no campo text
                        e se houver então já existe uma categoria com o nome com isso retorna um erro.
                        */
                    if(nom.contentEquals(txtRzSocial.getText()))
                    {
                        JOptionPane.showMessageDialog(null,"Já existe categoria com este nome.");
                    }
                    else
                    {
                        /*Se passar por todas as validações ele pega o valor que ja existe no objeto e insere no banco*/
                        fun.insereFornecedor();
                        /*Atualiza o list*/
                        atualizaCategoria("");
                        /*Exibe uma mensagem de sucesso*/
                        JOptionPane.showMessageDialog(null,"Produto adicionado com sucesso!");
                        /*Limpa o campo text*/
                        txtRzSocial.setText(null);
                        txtCNPJ.setText(null);
                        txtTelefone.setText(null);
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
                    Fornecedor fun = new Fornecedor();
                    /*Verifico se o usuario que esta no text existe no banco*/
                    ResultSet result = fun.buscaFornecedor(txtRzSocial.getText());
                    try
                    {
                        while(result.next())
                        {
                            /*Se existir armazeno seu codigo*/
                            catCodigo = result.getInt("forCodigo");
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
                        fun.excluirFornecedor(catCodigo);
                        /*Atualizo o list*/
                        atualizaCategoria("");
                        /*limpo o text*/
                        txtRzSocial.setText(null);
                        txtCNPJ.setText(null);
                        txtTelefone.setText(null);
                    }else
                    {
                        JOptionPane.showMessageDialog(null,"Não existe esta categoria.");
                    }
                }
                else
                {
                    /*Atualizo o list*/
                    atualizaCategoria("");
                }
            }
            if(e.getSource() == btnAlterar)
            {
                int esc = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja alterar este fornecedor?");
                if(esc == 0)
                {
                    int forCodigo = 0;
                    Fornecedor fun = new Fornecedor(txtRzSocial.getText(), txtCNPJ.getText(), txtTelefone.getText());
                    String esco = (String)listFornecedor.getSelectedValue();
                    /*Executa uma busca para ver se esse valor ja existe no banco*/
                    ResultSet result = fun.buscaFornecedor(esco);
                    try
                    {
                        while(result.next())
                        {
                            /*Se existir armazeno seu codigo*/
                            forCodigo = result.getInt("forCodigo");
                        }
                    } 
                    catch(SQLException ex)
                    {
                        Logger.getLogger(JCategoria.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    /*Verifico se existe algo para se deletar*/
                    System.out.print("Este codigo "+forCodigo);
                    if(forCodigo != 0)
                    {
                        /*Se existir a categoria será alterada apartir do codigo desta*/
                        fun.alterarFornecedor(forCodigo);
                        /*Atualizo já alterada*/
                        atualizaCategoria("");
                        /*limpo o campo text*/
                        txtRzSocial.setText(null);
                        txtCNPJ.setText(null);
                        txtTelefone.setText(null);
                    }
                }
                else
                {
                    /*Atualizo o campo text*/
                    atualizaCategoria("");
                }
            }
            if(e.getSource() == btnLimpar){
                /*Limpo o campo*/
                txtRzSocial.setText(null);
                txtCNPJ.setText(null);
                txtTelefone.setText(null);
            }
	}
}
