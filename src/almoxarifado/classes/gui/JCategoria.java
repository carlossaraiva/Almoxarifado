package almoxarifado.classes.gui;
import almoxarifado.classes.logico.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JCategoria extends JPanel implements ActionListener, ListSelectionListener {

    private static final long serialVersionUID = 1L;

    /*Cria-se uma lista para armazenar todas as categorias existentes no banco*/
    private DefaultListModel modelCategorias = new DefaultListModel();
    /*Cria-se caixas de texto*/
    private JTextField txtNomeCategoria;
    private JTextField txtNomeTipo;
    private JTextField textField;
    /*Defina-se uma lista*/
    private JList listProduto;
    /*Cria-se os botões*/
    private JButton btnAdicionar;
    private JButton btnLimpar;
    private JButton btnExcluir;
    private JButton btnAlterar;
    /*Nome que ficara acima das caixas de texto*/
    private JLabel lblNomeCategoria;
    private JLabel lblNomeTipo;
    private JLabel lblNomeEx;
    
    private JPanel borderTipo;
    private JPanel borderCategoria;
    /**/
    private JScrollPane scrollPane;
    
    /*Cria-se radio box*/
    private JRadioButton rdbtnA;
    private JRadioButton rdbtnB;
    
    //Metodo que atuliza no banco os resultados
    public void atualizaCategoria(String nome)
    {
        if(rdbtnA.isSelected()==true)
        {
            //Limpo o objeto
            modelCategorias.clear();
            //Instancio objeto
            Categoria cat = new Categoria();
            //Executo uma busca sem nenhum valor para retornar tudo que existir no banco e guardo em uma variavel
            ResultSet result = cat.buscaCategoria(""+nome+"");
            try
            {
                while(result.next())
                {
                    //Exponho o elemento no objeto selecionando so a coluna nome que o interessante neste caso
                    modelCategorias.addElement(result.getString("catNome"));
                }
            }
            catch(SQLException sqlex)
            {
                //Se ocorrer um erro este apareï¿½era nesta excessao
                JOptionPane.showMessageDialog(null, "Erro "+sqlex.getLocalizedMessage()+".");
            }
        }
        if(rdbtnB.isSelected()==true)
        {
            //Limpo o objeto
            modelCategorias.clear();
            //Instancio objeto
            TipoUsuario cat = new TipoUsuario();
            //Executo uma busca sem nenhum valor para retornar tudo que existir no banco e guardo em uma variavel
            ResultSet result = cat.buscaUsuario(""+nome+"");
            try
            {
                while(result.next())
                {
                    //Exponho o elemento no objeto selecionando so a coluna nome que o interessante neste caso
                    modelCategorias.addElement(result.getString("tipNome"));
                }
            }
            catch(SQLException sqlex)
            {
                //Se ocorrer um erro este apareï¿½era nesta excessao
                JOptionPane.showMessageDialog(null, "Erro "+sqlex.getLocalizedMessage()+".");
            }
        }
    }	
    //Construtores
    public JCategoria()
    {		
            setPainel();
            
    }
    //Setters e getters
    private void setPainel()
    {
        /*Ajusta o valor padrão para o layout*/
        setLayout(null);		
        /*Cria-se um campo de texto*/				
        txtNomeCategoria = new JTextField();
        /*Define-se o tamanho dele*/
        txtNomeCategoria.setBounds(10, 45, 220, 20);
        /*Adiciona no formulario*/
        add(txtNomeCategoria);
        txtNomeCategoria.setColumns(10);
        
        /*Cria-se um campo de texto*/				
        txtNomeTipo = new JTextField();
        /*Define-se o tamanho dele*/
        txtNomeTipo.setBounds(10, 45, 220, 20);
        /*Adiciona no formulario*/
        add(txtNomeTipo);
        txtNomeTipo.setColumns(10);

        /*Instancia-se um botão e coloca-se um nome*/
        btnAdicionar = new JButton("Adicionar");
        /*Defini onde apareçera no formulario*/
        btnAdicionar.setBounds(10, 372, 89, 23);
        /*Defini-se uma evento ao se clicar nele*/
        btnAdicionar.addActionListener(this);
        /*Adiciona-se no formulario*/
        add(btnAdicionar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(429, 360, 89, 23);
        btnExcluir.addActionListener(this);
        /*Aparece false se nenhum valor for adicionado através do list*/
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
        
        /*Cria-se um campo que conterá um texto fixo*/
        lblNomeEx = new JLabel("Escolha uma das opções para habilitar as opções.");
        /*Tamanho do campo*/
        lblNomeEx.setBounds(10, 25, 250, 14);
        /*Adiciona-se no form*/
        add(lblNomeEx);
        
        /*Cria-se um campo que conterá um texto fixo*/
        lblNomeCategoria = new JLabel("Nome da categoria:");
        /*Tamanho do campo*/
        lblNomeCategoria.setBounds(10, 25, 98, 14);
        /*Adiciona-se no form*/
        add(lblNomeCategoria);
        
        /*Cria-se um campo que conterá um texto fixo*/
        lblNomeTipo = new JLabel("Nome:");
        /*Tamanho do campo*/
        lblNomeTipo.setBounds(10, 25, 230, 14);
        /*Adiciona-se no form*/
        
        final ButtonGroup sel = new ButtonGroup();
        
        rdbtnA = new JRadioButton("Usar");
        rdbtnA.setBounds(172, 10, 141, 32);
        rdbtnA.addItemListener
        (
            new ItemListener() {

                public void itemStateChanged(ItemEvent e) {
                    btnAdicionar.setEnabled(true);
                    btnLimpar.setEnabled(true);
                    lblNomeCategoria.setEnabled(true);
                    lblNomeTipo.setEnabled(false);
                    txtNomeCategoria.setEnabled(true);
                    txtNomeTipo.setEnabled(false);
                    txtNomeTipo.setText(null);
                    atualizaCategoria("");
                }
            }
        );
        sel.add(rdbtnA);
        
        
        rdbtnB = new JRadioButton("Usar");
        rdbtnB.setBounds(172, 10, 141, 23);
        rdbtnB.addItemListener
        (
            new ItemListener() {

                public void itemStateChanged(ItemEvent e) {
                    btnAdicionar.setEnabled(true);
                    btnLimpar.setEnabled(true);
                    lblNomeCategoria.setEnabled(false);
                    lblNomeTipo.setEnabled(true);
                    txtNomeCategoria.setEnabled(false);
                    txtNomeTipo.setEnabled(true);
                    txtNomeCategoria.setText(null);
                    atualizaCategoria("");
                }
            }
        );
        sel.add(rdbtnB);
        
        borderTipo = new JPanel();
        borderTipo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de funcionario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        borderTipo.setBounds(05, 150, 250, 88);
        borderTipo.setLayout(null);
        borderTipo.add(lblNomeTipo);
        borderTipo.add(txtNomeTipo);
        borderTipo.add(rdbtnB);
        add(borderTipo);       
        
        borderCategoria = new JPanel();
        borderCategoria.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de categoria de produto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        borderCategoria.setBounds(05, 50, 250, 88);
        borderCategoria.setLayout(null);
        borderCategoria.add(lblNomeCategoria);
        borderCategoria.add(txtNomeCategoria);
        borderCategoria.add(rdbtnA);
        add(borderCategoria);

        /*Chama o procedimento que atualiza a lista de categorias mandando com o valor vazio para buscar no banco*/
        atualizaCategoria("");

        textField = new JTextField();
        //Quando digitado algo no txt o evento e disparado e por sua vez chama outro
        textField.addKeyListener
        (
            new KeyAdapter() 
            {	
                @Override
                public void keyReleased(KeyEvent arg0) 
                {
                    atualizaCategoria(textField.getText());
                }
            }
        );
        textField.setBounds(429, 64, 189, 20);
        textField.setColumns(10);
        add(textField);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(429, 94, 189, 253);
        add(scrollPane);

        listProduto = new JList(modelCategorias);
        scrollPane.setViewportView(listProduto);
        listProduto.addListSelectionListener(this);        
        
        if(rdbtnA.isSelected() == false && rdbtnB.isSelected() == false)
        {
            btnAdicionar.setEnabled(false);
            btnLimpar.setEnabled(false);
            lblNomeCategoria.setEnabled(false);
            lblNomeTipo.setEnabled(false);
            txtNomeCategoria.setEnabled(false);
            txtNomeTipo.setEnabled(false);
        }
    }
    
    //Listeners
    public void valueChanged(ListSelectionEvent e){
        if (e.getValueIsAdjusting() == false)
        {

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
        if (e.getSource() == listProduto)
        {
            if(rdbtnA.isSelected()==true)
            {
                /*Se um valor for selecionado na list este por sua vez é armazenado na variavel esc*/
                String esc = (String)listProduto.getSelectedValue();
                /*Instancia-se uma categoria*/
                Categoria cat = new Categoria();
                /*Busca um categoria com o valor selecionado*/
                ResultSet result = cat.buscaCategoria(esc);
                try
                {
                    while(result.next()){
                        /*Se houver uma categoria com o nome coloca-se o resultado no campo de texto*/
                        txtNomeCategoria.setText(result.getString("catNome"));
                    }		
                }
                catch(SQLException sqlex)
                {
                    JOptionPane.showMessageDialog(null, "Erro "+sqlex.getLocalizedMessage()+".");
                }
            }
            if(rdbtnB.isSelected()==true)
            {
                /*Se um valor for selecionado na list este por sua vez é armazenado na variavel esc*/
                String esc = (String)listProduto.getSelectedValue();
                /*Instancia-se uma categoria*/
                TipoUsuario cat = new TipoUsuario();
                /*Busca um categoria com o valor selecionado*/
                ResultSet result = cat.buscaUsuario(esc);
                try
                {
                    while(result.next()){
                        /*Se houver uma categoria com o nome coloca-se o resultado no campo de texto*/
                        txtNomeTipo.setText(result.getString("tipNome"));
                    }		
                }
                catch(SQLException sqlex)
                {
                    JOptionPane.showMessageDialog(null, "Erro "+sqlex.getLocalizedMessage()+".");
                }
            }
        }
    }
	
    public void actionPerformed(ActionEvent e)
    {
		
        if (e.getSource() == btnAdicionar)
        {
            if(rdbtnA.isSelected() == true)
            {
                /*Verifica se há algo do objeto text se não houver nada ele retorna uma mensagem de erro.*/
                if(txtNomeCategoria.getText().contentEquals(""))
                {
                    JOptionPane.showMessageDialog(null, "Não pode ser deixar campos vazios.");
                }
                else
                {
                    String nom=""; 
                    /*Instancia Categoria*/
                    Categoria cat = new Categoria(txtNomeCategoria.getText());
                    /*Executa uma busca para ver se esse valor ja existe no banco*/
                    ResultSet result = cat.buscaCategoria(txtNomeCategoria.getText());
                    try
                    {
                        while(result.next())
                        {
                            /*Se houver ele guarda na variavel "nom"*/
                            nom = result.getString("catNome");
                        }
                    }
                    catch(SQLException sqlex)
                    {
                            JOptionPane.showMessageDialog(null, "Erro "+sqlex.getLocalizedMessage()+".");
                    }
                    /*Verifica se o valor na variavel "nom" e igual a que esta armazenado no campo text
                        e se houver então já existe uma categoria com o nome com isso retorna um erro.
                        */
                    if(nom.contentEquals(txtNomeCategoria.getText()))
                    {

                        JOptionPane.showMessageDialog(null,"Já existe categoria com este nome.");
                    }
                    else
                    {
                        /*Se passar por todas as validações ele pega o valor que ja existe no objeto e insere no banco*/
                        cat.insereCategoria();
                        /*Atualiza o list*/
                        atualizaCategoria("");
                        /*Exibe uma mensagem de sucesso*/
                        JOptionPane.showMessageDialog(null,"Produto adicionado com sucesso!");
                        /*Limpa o campo text*/
                        txtNomeCategoria.setText(null);
                    }
                }
            }
            if(rdbtnB.isSelected() == true)
            {
                /*Verifica se há algo do objeto text se não houver nada ele retorna uma mensagem de erro.*/
                if(txtNomeTipo.getText().contentEquals(""))
                {
                    JOptionPane.showMessageDialog(null, "Não pode ser deixar campos vazios.");
                }
                else
                {
                    String nom=""; 
                    /*Instancia Categoria*/
                    TipoUsuario tip = new TipoUsuario(txtNomeTipo.getText());
                    /*Executa uma busca para ver se esse valor ja existe no banco*/
                    ResultSet result = tip.buscaUsuario(txtNomeTipo.getText());
                    try
                    {
                        while(result.next())
                        {
                            /*Se houver ele guarda na variavel "nom"*/
                            nom = result.getString("tipNome");
                        }
                    }
                    catch(SQLException sqlex)
                    {
                            JOptionPane.showMessageDialog(null, "Erro "+sqlex.getLocalizedMessage()+".");
                    }
                    /*Verifica se o valor na variavel "nom" e igual a que esta armazenado no campo text
                        e se houver então já existe uma categoria com o nome com isso retorna um erro.
                        */
                    if(nom.contentEquals(txtNomeTipo.getText()))
                    {

                        JOptionPane.showMessageDialog(null,"Já existe categoria com este nome.");
                    }
                    else
                    {
                        /*Se passar por todas as validações ele pega o valor que ja existe no objeto e insere no banco*/
                        tip.insereUsuario();
                        /*Atualiza o list*/
                        atualizaCategoria("");
                        /*Exibe uma mensagem de sucesso*/
                        JOptionPane.showMessageDialog(null,"Produto adicionado com sucesso!");
                        /*Limpa o campo text*/
                        txtNomeTipo.setText(null);
                    }
                }
            }
        }		
        if(e.getSource() == btnExcluir){
            if(rdbtnA.isSelected()==true)
            {
                /*Mensagem de segurança para ver se o usuario tem certeza que deseja deletar*/
                int esc = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja deletar esta categoria?");
                /*Verifico a escolha se afirmativa*/
                if(esc == 0)
                {
                    int catCodigo = 0;
                    Categoria cat = new Categoria();
                    /*Verifico se o usuario que esta no text existe no banco*/
                    ResultSet result = cat.buscaCategoria(txtNomeCategoria.getText());
                    try
                    {
                        while(result.next())
                        {
                            /*Se existir armazeno seu codigo*/
                            catCodigo = result.getInt("catCodigo");
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
                        cat.deletaCategoria(catCodigo);
                        /*Atualizo o list*/
                        atualizaCategoria("");
                        /*limpo o text*/
                        txtNomeCategoria.setText(null);
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
            if(rdbtnB.isSelected()==true)
            {
                /*Mensagem de segurança para ver se o usuario tem certeza que deseja deletar*/
                int esc = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja deletar este tipo de usuario?");
                /*Verifico a escolha se afirmativa*/
                if(esc == 0)
                {
                    int catCodigo = 0;
                    TipoUsuario cat = new TipoUsuario();
                    /*Verifico se o usuario que esta no text existe no banco*/
                    ResultSet result = cat.buscaUsuario(txtNomeTipo.getText());
                    try
                    {
                        while(result.next())
                        {
                            /*Se existir armazeno seu codigo*/
                            catCodigo = result.getInt("tipCodigo");
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
                        cat.deletaUsuario(catCodigo);
                        /*Atualizo o list*/
                        atualizaCategoria("");
                        /*limpo o text*/
                        txtNomeCategoria.setText(null);
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
        }
        if(e.getSource() == btnAlterar)
        {
            if(rdbtnA.isSelected()==true)
            {
                int esc = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja alterar esta categoria?");
                if(esc == 0)
                {
                    int catCodigo = 0;
                    Categoria cat = new Categoria(txtNomeCategoria.getText());
                    /*Guardo a escolha do usuario na variavel esco*/
                    String esco = (String)listProduto.getSelectedValue();
                    /*Verifico se o usuario que esta no text existe no banco com a escolha do usuario*/
                    ResultSet result = cat.buscaCategoria(esco);
                    try
                    {
                        while(result.next())
                        {
                            /*Se existir armazeno seu codigo*/
                            catCodigo = result.getInt("catCodigo");
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
                        cat.alteraCategoria(catCodigo);
                        /*Atualizo já alterada*/
                        atualizaCategoria("");
                        /*limpo o campo text*/
                        txtNomeCategoria.setText(null);
                    }
                }
                else
                {
                    /*Atualizo o campo text*/
                    atualizaCategoria("");
                }
            }
            if(rdbtnB.isSelected()==true)
            {
                int esc = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja alterar este tipo de usuario?");
                if(esc == 0)
                {
                    int catCodigo = 0;
                    TipoUsuario cat = new TipoUsuario(txtNomeTipo.getText());
                    /*Guardo a escolha do usuario na variavel esco*/
                    String esco = (String)listProduto.getSelectedValue();
                    /*Verifico se o usuario que esta no text existe no banco com a escolha do usuario*/
                    ResultSet result = cat.buscaUsuario(esco);
                    try
                    {
                        while(result.next())
                        {
                            /*Se existir armazeno seu codigo*/
                            catCodigo = result.getInt("tipCodigo");
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
                        cat.alterarUsuario(catCodigo);
                        /*Atualizo já alterada*/
                        atualizaCategoria("");
                        /*limpo o campo text*/
                        txtNomeTipo.setText(null);
                    }
                }
                else
                {
                    /*Atualizo o campo text*/
                    atualizaCategoria("");
                }
            }
        }
        if(e.getSource() == btnLimpar)
        {
            /*Limpo o campo*/
            txtNomeCategoria.setText(null);
            txtNomeTipo.setText(null);
        }
    }
}