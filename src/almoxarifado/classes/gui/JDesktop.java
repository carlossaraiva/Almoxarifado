package almoxarifado.classes.gui;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Component;

import almoxarifado.classes.logico.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class JDesktop extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JFuncionario jfuncionario;
	private JProduto jproduto;
        private JRelatorio jrelatorio;
	private JFornecedor jfornecedor;
	private JCategoria jcategoria;
	private JEstoque jestoque;
	
	private JInternalFrame frmFornecedor;
	private JInternalFrame frmProduto;
        private JInternalFrame frmRelatorio;
	private JInternalFrame frmFuncionario;
	private JInternalFrame frmCategoria;
	private JInternalFrame frmEstoque;
	
	private JMenuItem mntmProduto;
        private JMenuItem mntmRelatorio;
	private JMenuItem mntmFuncionario; 
	private JMenuItem mntmCategoria;
	private JMenuItem mntmFornecedor;
	private JDesktopPane desktop;
        
    private JMenu mnFichaDeControle;
    private JMenuItem mntmEntrada;
    private JMenuItem mntmRelat;
    private String tipNome;
    
        

	public JDesktop(String usuNome)
        {
            int tipCodigo = 0;
            Funcionario fun = new Funcionario();
            TipoUsuario tip = new TipoUsuario();
            ResultSet usu = fun.buscaFuncionario(usuNome);
            try
            {
               while(usu.next())
               {
                   tipCodigo = usu.getInt("tipCodigo");
               } 
            }catch(SQLException sqlex)
            {
                JOptionPane.showMessageDialog(null, "Erro ao verificar o tipo de usuario. Descrição: "+sqlex.getLocalizedMessage()+".");
            }
            this.tipNome = tip.buscaCategoria(tipCodigo);
            
            if(this.tipNome.equals("Diretoria de serviço"))
            {
                setMenuBar();
                setIntFrames();
                setVisible(true);
                setBounds(0,0,1290,720);
                mntmFuncionario.setVisible(true);
                mntmProduto.setVisible(true);
                mntmCategoria.setVisible(true);
            }else
            {
                setMenuBar();
                setIntFrames();
                setVisible(true);
                setBounds(0,0,1290,720);
            }
            
        }
	

	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == mntmFornecedor){
			
			frmFornecedor.setVisible(true);
		}
		
		if(e.getSource() == mntmFuncionario){
			frmFuncionario.setVisible(true);
		
		}
		
		if(e.getSource() == mntmProduto){
			frmProduto.setVisible(true);
		}	
                
                if(e.getSource() == mntmRelatorio){
			frmRelatorio.setVisible(true);
		}
		
		if(e.getSource() == mntmCategoria){
			frmCategoria.setVisible(true);
		
		}
	}
		
	public void setMenuBar(){
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		mntmFuncionario = new JMenuItem("Funcionario");					
		mntmFuncionario.addActionListener(this);
                mntmFuncionario.setVisible(true);
		mnCadastro.add(mntmFuncionario);
		
		mntmProduto = new JMenuItem("Produto");
		mntmProduto.addActionListener(this);
                mntmProduto.setVisible(true);
		mnCadastro.add(mntmProduto);
		
		mntmFornecedor = new JMenuItem("Fornecedor");
		mntmFornecedor.addActionListener(this);
		mnCadastro.add(mntmFornecedor);
		
		mntmCategoria = new JMenuItem("Categorias");
		mntmCategoria.addActionListener(this);
                mntmCategoria.setVisible(true);                
		mnCadastro.add(mntmCategoria);
		
		mnFichaDeControle = new JMenu("Ficha de Controle");
		menuBar.add(mnFichaDeControle);
                
                mntmRelatorio = new JMenuItem("Relatorio");
                mntmRelatorio.addActionListener(this);
		mnFichaDeControle.add(mntmRelatorio);
		
	}

	public void setIntFrames(){
				
		desktop = new JDesktopPane();
		getContentPane().add(desktop);
		desktop.setBounds(0, 0, 1115, 300);
		desktop.setBackground(Color.GRAY);
						
		//Frame Produto, instancia a janela interna e inicia seus componentes.
		frmProduto = new JInternalFrame("Produto");
		frmProduto.setFocusable(false);
		frmProduto.setBounds(139, 70, 646, 500);
		frmProduto.setClosable(true);
		jproduto = new JProduto();
		frmProduto.getContentPane().add(jproduto);
                
                frmRelatorio = new JInternalFrame("Relatorio");
		frmRelatorio.setFocusable(false);
		frmRelatorio.setBounds(139, 70, 846, 500);
		frmRelatorio.setClosable(true);
		jrelatorio = new JRelatorio();
		frmRelatorio.getContentPane().add(jrelatorio);
		
		//Frame Estoque, instancia a janela interna e inicia seus componentes;
		frmEstoque = new JInternalFrame("Controle de Estoque");
		frmEstoque.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		//frmEstoque.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		frmEstoque.setBounds(331, 50, 709, 554);
		frmEstoque.setFocusable(false);
		frmEstoque.setClosable(false);
                jestoque = new JEstoque(this.tipNome);
                frmEstoque.getContentPane().add(jestoque);
                //Frame Funcion�rio, instancia a janela interna e inicia seus componentes.
		frmCategoria = new JInternalFrame("Categoria");
		frmCategoria.setVisible(true);
		frmCategoria.setBounds(70, 50, 646, 500);		
		frmCategoria.setFocusable(false);
		frmCategoria.getContentPane().setBounds(new Rectangle(0, 0, 0, 0));
		frmCategoria.setClosable(true);
		jcategoria = new JCategoria();
		frmCategoria.getContentPane().add(jcategoria);
		//Frame Funcionário, instancia a janela interna e inicia seus componentes.
		frmFuncionario = new JInternalFrame("Funcionario");
		frmFuncionario.setBounds(50, 50, 646, 500);		
		frmFuncionario.setFocusable(false);
		frmFuncionario.getContentPane().setBounds(new Rectangle(0, 0, 0, 0));
		frmFuncionario.setClosable(true);
		jfuncionario = new JFuncionario();
		frmFuncionario.getContentPane().add(jfuncionario);
		desktop.add(frmProduto);
                desktop.add(frmRelatorio);
		desktop.add(frmCategoria);
		desktop.add(frmFuncionario);
		desktop.add(frmEstoque);
		//desktopPane.add(frmEstoque);
		//define os frames internos invisiveis por padrão na inicialização.
		frmEstoque.setVisible(true);
		frmProduto.setVisible(false);
		frmCategoria.setVisible(false);
                frmProduto.setDefaultCloseOperation(HIDE_ON_CLOSE);
		frmEstoque.setDefaultCloseOperation(HIDE_ON_CLOSE);
		frmFuncionario.setDefaultCloseOperation(HIDE_ON_CLOSE);
                frmCategoria.setDefaultCloseOperation(HIDE_ON_CLOSE);
                frmRelatorio.setDefaultCloseOperation(HIDE_ON_CLOSE);
                
		
		//Frame Fornecedor, instancia a janela interna e inicia seus componentes.
		frmFornecedor = new JInternalFrame("Fornecedor");
		frmFornecedor.setBounds(0, 0, 646, 500);
		desktop.add(frmFornecedor);
		frmFornecedor.setClosable(true);
		frmFornecedor.setFocusable(false);
		frmFornecedor.getContentPane().setBounds(new Rectangle(0, 0, 0, 0));
		frmFornecedor.setClosable(true);
		jfornecedor = new JFornecedor();
		frmFornecedor.getContentPane().add(jfornecedor);
		frmFornecedor.setVisible(false);
		
		//Define padrão para o fechaemento dos frames interno, esconder ao fechar ao invés de descartar a janela.
                frmFornecedor.setDefaultCloseOperation(HIDE_ON_CLOSE);
        

        }
}
