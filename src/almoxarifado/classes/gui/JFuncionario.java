package almoxarifado.classes.gui;


import almoxarifado.classes.logico.Categoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import almoxarifado.classes.logico.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import javax.swing.border.TitledBorder;
public class JFuncionario extends JPanel implements ActionListener, ListSelectionListener {

    private static final long serialVersionUID = 1L;

    /*Cria-se uma lista para armazenar todas as categorias existentes no banco*/
    private DefaultListModel<String> modelFuncionarios = new DefaultListModel<String>();
    private String sexo;
    private Funcionario f;
    /*Cria-se caixas de texto*/
    private JTextField txtNome;
    private JTextField txtLogin;
    private JTextField txtSenha;
    private JTextField textField;
    /*Defina-se uma lista*/
    private JList listFuncionario;
    /*Cria-se os botões*/
    private JButton btnAdicionar;
    private JButton btnLimpar;
    private JButton btnExcluir;
    private JButton btnAlterar;
    /*Cria-se um painel*/
    private JScrollPane scrollPane;
    /*Cria-se radio box*/
    private JRadioButton rdbtnM;
    private JRadioButton rdbtnF;
    /*Um painel para os radio box*/
    private JPanel borderSexo;
    
    private JComboBox<String> cboFuncionario;

    //Metodo que atuliza no banco os resultados
    public void atualizaFuncionario(String nome)
    {
        //Limpo o objeto
        modelFuncionarios.clear();
        //Instancio objeto
        Funcionario fun = new Funcionario();
        //Executo uma busca sem nenhum valor para retornar tudo que existir no banco e guardo em uma variavel
        ResultSet res = fun.buscaFuncionario(""+nome+"");
        try
        {
            while(res.next())
            {
                //Exponho o elemento no objeto selecionando so a coluna nome que o interessante neste caso
                modelFuncionarios.addElement(res.getString("usuNome"));
            }
        }
        catch(SQLException sqlex)
        {
            //Se ocorrer um erro este apareï¿½era nesta excessao
            JOptionPane.showMessageDialog(null, "Erro "+sqlex.getLocalizedMessage()+".");
        }
    }
    //Construtores
    public JFuncionario() {		
            setPainel();
    }
    /*Procedimento que vai criar o painel os botões e etc*/
    private void setPainel()
    {
        /*Ajusta o valor padrão para o layout*/
        setLayout(null);		
        
        atualizaFuncionario("");

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(10, 372, 89, 23);
        btnAdicionar.addActionListener(this);
        add(btnAdicionar);

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
        btnLimpar.setBounds(123, 372, 89, 23);
        btnLimpar.addActionListener(this);
        add(btnLimpar);

        JLabel lblNomeCompleto = new JLabel("Nome completo:");
        lblNomeCompleto.setBounds(10, 39, 89, 14);
        add(lblNomeCompleto);

        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setBounds(10, 95, 46, 14);
        add(lblLogin);
        
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(10, 145, 46, 14);
        add(lblSenha);
        
        JLabel lblTipo = new JLabel("Tipo de usuario:");
        lblTipo.setBounds(10, 190, 200, 14);
        add(lblTipo);

        txtNome = new JTextField();
        txtNome.setBounds(10, 64, 220, 20);
        txtNome.setColumns(10);
        add(txtNome);

        txtLogin = new JTextField();
        txtLogin.setBounds(10, 116, 220, 20);
        txtLogin.setColumns(10);
        add(txtLogin);
        
        txtSenha = new JTextField();
        txtSenha.setBounds(10, 166, 220, 20);
        txtSenha.setColumns(10);
        add(txtSenha);
        
        textField = new JTextField();
        textField.setBounds(429, 64, 189, 20);
        textField.setColumns(10);
        add(textField);
        textField.addFocusListener
        (new FocusAdapter()
            {
            }
        );
        //Quando digitado algo no txt o evento e disparado e por sua vez chama outro
        textField.addKeyListener
        (
            new KeyAdapter() 
            {	
                @Override
                public void keyReleased(KeyEvent arg0) 
                {
                    atualizaFuncionario(textField.getText());
                }
            }
        );

        scrollPane = new JScrollPane();
        scrollPane.setBounds(429, 94, 189, 253);
        add(scrollPane);
        
        listFuncionario = new JList(modelFuncionarios);
        scrollPane.setViewportView(listFuncionario);
        listFuncionario.addListSelectionListener(this);
        listFuncionario.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listFuncionario.setLayoutOrientation(JList.VERTICAL);
        listFuncionario.setVisibleRowCount(-1);
        

        ButtonGroup sexo = new ButtonGroup();

        borderSexo = new JPanel();
        borderSexo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        borderSexo.setBounds(10, 250, 116, 88);
        add(borderSexo);
        borderSexo.setLayout(null);
        rdbtnM = new JRadioButton("M");
        rdbtnM.setBounds(6, 20, 141, 32);
        borderSexo.add(rdbtnM);
        sexo.add(rdbtnM);
        rdbtnF = new JRadioButton("F");
        rdbtnF.setBounds(6, 55, 141, 23);
        borderSexo.add(rdbtnF);
        sexo.add(rdbtnF);
        
        cboFuncionario = new JComboBox<String>();
        cboFuncionario = new JComboBox<String>();
        cboFuncionario.setBounds(10, 210, 132, 20);
        add(cboFuncionario);
        
        JLabel lblBusca = new JLabel("Busca:");
        lblBusca.setBounds(429, 39, 46, 14);
        add(lblBusca);
        cboFuncionario.addMouseListener
        (
            new MouseAdapter()
            {
                @Override
                public void mouseReleased(MouseEvent arg0)
                {
                    cboFuncionario.removeAllItems();
                    TipoUsuario cat = new TipoUsuario();
                    ResultSet res = cat.buscaUsuario("");
                    try
                    {
                        while(res.next())
                        {
                                //Exponho o elemento no objeto selecionando so a coluna nome que o interessante neste caso
                                cboFuncionario.addItem(res.getString("tipNome"));
                        }
                    }catch(SQLException sqlex)
                    {
                        System.out.println("Erro na atualização da categoria");
                    }
                }
            }
        );
    }

    //Listeners
    public void valueChanged(ListSelectionEvent e)
    {
        if (e.getValueIsAdjusting() == false) 
        {
            if (listFuncionario.getSelectedIndex() == -1)
            {
            //No selection, disable fire button.
                btnExcluir.setEnabled(false);
                btnAlterar.setEnabled(false);
            } else {
            //Selection, enable the fire button.
                btnAlterar.setEnabled(true);
                btnExcluir.setEnabled(true);
            }		    
        }
        if (e.getSource() == listFuncionario)
        {
            /*Se um valor for selecionado na list este por sua vez é armazenado na variavel esc*/
            String esc = (String)listFuncionario.getSelectedValue();
            /*Instancia-se uma categoria*/
            Usuario cat = new Usuario();
            /*Busca um categoria com o valor selecionado*/
            ResultSet result = cat.buscaUsuario(esc);
            try
            {
                while(result.next()){
                    /*Se houver uma categoria com o nome coloca-se o resultado no campo de texto*/
                    txtNome.setText(result.getString("usuNome"));
                    txtLogin.setText(result.getString("usuLogin"));
                    txtSenha.setText(result.getString("usuSenha"));
                    String sex = result.getString("usuSexo");
                    if(sex.equals("M"))
                    {
                        rdbtnM.setSelected(true);
                    }
                    if(sex.equals("F"))
                    {
                        rdbtnF.setSelected(true);
                    }
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
            if(rdbtnM.isSelected()== true)
            {
                sexo = "M";
            }
            else
            {
                sexo = "F";
            }
            /*Verifica se há algo do objeto text se não houver nada ele retorna uma mensagem de erro.*/
            if(txtNome.getText().contentEquals(""))
            {
                JOptionPane.showMessageDialog(null, "Não pode ser deixar campos vazios.");
            }
            else
            {
                String nom=""; 
                int nom1=0; 
                /*Instancia Funcionario*/
                Funcionario fun = new Funcionario(txtNome.getText(), txtLogin.getText(), txtSenha.getText());
                fun.setSexo(sexo);
                /*Executa uma busca para ver se esse valor ja existe no banco*/
                ResultSet result = fun.buscaUsuario(txtNome.getText());
                try
                {
                    while(result.next())
                    {
                        /*Se houver ele guarda na variavel "nom"*/
                        nom = result.getString("usuNome");
                    }
                }
                catch(SQLException sqlex)
                {
                        JOptionPane.showMessageDialog(null, "Erro "+sqlex.getLocalizedMessage()+".");
                }
                /*Verifica se o valor na variavel "nom" e igual a que esta armazenado no campo text
                    e se houver então já existe uma categoria com o nome com isso retorna um erro.
                    */
                if(nom.contentEquals(txtNome.getText()))
                {

                    JOptionPane.showMessageDialog(null,"Já existe categoria com este nome.");
                }
                else
                {
                    TipoUsuario cat = new TipoUsuario();
                    ResultSet res = cat.buscaUsuario((String)cboFuncionario.getSelectedItem());
                    try
                    {
                        while(res.next())
                        {
                            nom1 = res.getInt("tipCodigo");
                        }
                    }
                    catch(SQLException sqlex)
                    {
                        JOptionPane.showMessageDialog(null, "Erro "+sqlex.getLocalizedMessage()+".");
                    }
                    /*Se passar por todas as validações ele pega o valor que ja existe no objeto e insere no banco*/
                    fun.insereFuncionario(nom1);
                    /*Atualiza o list*/
                    atualizaFuncionario("");
                    /*Exibe uma mensagem de sucesso*/
                    JOptionPane.showMessageDialog(null,"Produto adicionado com sucesso!");
                    /*Limpa o campo text*/
                    txtNome.setText(null);
                    txtLogin.setText(null);
                    txtSenha.setText(null);
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
                Funcionario fun = new Funcionario(txtNome.getText(), txtLogin.getText(), txtSenha.getText());
                fun.setSexo(sexo);
                /*Verifico se o usuario que esta no text existe no banco*/
                ResultSet result = fun.buscaFuncionario(txtNome.getText());
                try
                {
                    while(result.next())
                    {
                        /*Se existir armazeno seu codigo*/
                        catCodigo = result.getInt("usuCodigo");
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
                    fun.excluirFuncionario(catCodigo);
                    /*Atualizo o list*/
                    atualizaFuncionario("");
                    /*limpo o text*/
                    txtNome.setText(null);
                    txtLogin.setText(null);
                    txtSenha.setText(null);
                }else
                {
                    JOptionPane.showMessageDialog(null,"Não existe esta categoria.");
                }
            }
            else
            {
                /*Atualizo o list*/
                atualizaFuncionario("");
            }
        }
        if(e.getSource() == btnAlterar)
        {
            int esc = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja alterar este funcionario?");
            if(esc == 0)
            {
                
                if(rdbtnM.isSelected()== true)
                {
                    sexo = "M";
                }
                else
                {
                    sexo = "F";
                }
                
                int usuCodigo = 0;
                int catCodigo = 0;
                Funcionario fun = new Funcionario(txtNome.getText(), txtLogin.getText(), txtSenha.getText());
                fun.setSexo(sexo);
                TipoUsuario cat = new TipoUsuario();
                /*Guardo a escolha do usuario na variavel esco*/
                String esco = (String)listFuncionario.getSelectedValue();
                /*Verifico se o usuario que esta no text existe no banco com a escolha do usuario*/
                ResultSet result = fun.buscaFuncionario(esco);
                ResultSet res = cat.buscaUsuario((String)cboFuncionario.getSelectedItem());
                try
                {
                    while(res.next())
                    {
                        catCodigo = res.getInt("tipCodigo");
                    }
                }
                catch(SQLException sqlex)
                {
                    JOptionPane.showMessageDialog(null, "Erro "+sqlex.getLocalizedMessage()+".");
                }
                try
                {
                    while(result.next())
                    {
                        /*Se existir armazeno seu codigo*/
                        usuCodigo = result.getInt("usuCodigo");
                    }
                } 
                catch(SQLException ex)
                {
                    Logger.getLogger(JCategoria.class.getName()).log(Level.SEVERE, null, ex);
                }
                /*Verifico se existe algo para se deletar*/
                if(catCodigo != 0)
                {
                    /*Se existir a categoria será alterada apartir do codigo desta*/
                    fun.alterarFuncionario(usuCodigo, catCodigo);
                    /*Atualizo já alterada*/
                    atualizaFuncionario("");
                    /*limpo o campo text*/
                    txtNome.setText(null);
                    txtLogin.setText(null);
                    txtSenha.setText(null);
                }
            }
            else
            {
                /*Atualizo o campo text*/
                atualizaFuncionario("");
            }
        }
        if(e.getSource() == btnLimpar){
            /*Limpo o campo*/
            txtNome.setText(null);
            txtLogin.setText(null);
            txtSenha.setText(null);
        }
    }
}