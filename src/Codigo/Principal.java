package Codigo;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;

import java.util.ArrayList;


import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class Principal extends JFrame implements ActionListener,InternalFrameListener{ 

	private static final long serialVersionUID = 1L;
			
	private JFuncionario jfuncionario;
	private JProduto jproduto;
	private JFornecedor jfornecedor;
	private JEstoque jestoque;
	private JPanel principal;
		
	private JInternalFrame frmFornecedor;
	private JInternalFrame frmProduto;
	private JInternalFrame frmFuncionario;
	private JInternalFrame frmEstoque;
	
	private JMenuItem mntmProduto;
	private JMenuItem mntmFuncionario; 
	private JMenuItem mntmFornecedor;
	private JDesktopPane desktopPane;
	
	public ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
	public ArrayList<Produto> produtos = new ArrayList<Produto>();
	public ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
	
	public Principal() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		//Atualiza a UI compatível com o sistema operacional em uso.
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		SwingUtilities.updateComponentTreeUI(this);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1131, 739);
		
		//Constroe a barra de menu.
		setMenuBar();
		
		//Constroe o desktop virtual e as janelas internas do aplicativo.
		setItnFrames();
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override

	
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

		
	}
		
	public void setMenuBar(){
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		mntmFuncionario = new JMenuItem("Funcionario");					
		mnCadastro.add(mntmFuncionario);
		mntmFuncionario.addActionListener(this);
		
		mntmProduto = new JMenuItem("Produto");
		mnCadastro.add(mntmProduto);
		mntmProduto.addActionListener(this);
		
		mntmFornecedor = new JMenuItem("Fornecedor");
		mntmFornecedor.addActionListener(this);
		mnCadastro.add(mntmFornecedor);
		
		JMenu mnFichaDeControle = new JMenu("Ficha de Controle");
		menuBar.add(mnFichaDeControle);
		
		JMenuItem mntmEntrada = new JMenuItem("Entrada");
		mnFichaDeControle.add(mntmEntrada);
		
		JMenuItem mntmSada = new JMenuItem("Sa\u00EDda");
		mnFichaDeControle.add(mntmSada);
		
	}

	public void setItnFrames(){
		
		principal = new JPanel();
		principal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(principal);
		principal.setLayout(null);
		
		//Cria a desktop interna, que possibilita criação de janelas dentro da aplicação.
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.menu);
		desktopPane.setBounds(0, 0, 1115, 680);
		principal.add(desktopPane);
		
		//Frame Produto, instancia a janela interna e inicia seus componentes.
		frmProduto = new JInternalFrame("Produto");
		frmProduto.setFocusable(false);
		frmProduto.setBounds(139, 70, 646, 500);
		frmProduto.getContentPane().setBounds(new Rectangle(0, 0, 0, 0));		
		frmProduto.setClosable(true);
		jproduto = new JProduto();
		frmProduto.getContentPane().add(jproduto);
		
		//Frame Estoque, instancia a janela interna e inicia seus componentes;
		frmEstoque = new JInternalFrame("Estoque");
		frmEstoque.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		frmEstoque.setBounds(186, 21, 646, 500);
		frmEstoque.setFocusable(false);
		frmEstoque.getContentPane().setBounds(new Rectangle(0, 0, 0, 0));
		frmEstoque.setClosable(false);
		//frmEstoque.getContentPane().add(jestoque);
		frmEstoque.setVisible(true);
		frmEstoque.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		//Frame Funcionário, instancia a janela interna e inicia seus componentes.
		frmFuncionario = new JInternalFrame("Funcionario");
		frmFuncionario.setBounds(50, 50, 646, 500);		
		frmFuncionario.setFocusable(false);
		frmFuncionario.getContentPane().setBounds(new Rectangle(0, 0, 0, 0));
		frmFuncionario.setClosable(true);
		jfuncionario = new JFuncionario();
		frmFuncionario.getContentPane().add(jfuncionario);
		
		
		//Frame Fornecedor, instancia a janela interna e inicia seus componentes.
		frmFornecedor = new JInternalFrame("Fornecedor");
		frmFornecedor.setBounds(100, 100, 646, 500);
		frmFornecedor.setClosable(true);
		frmFornecedor.setFocusable(false);
		frmFornecedor.getContentPane().setBounds(new Rectangle(0, 0, 0, 0));
		frmFornecedor.setClosable(true);
		jfornecedor = new JFornecedor();
		frmFornecedor.getContentPane().add(jfornecedor);
		
		//Adiciona os frames internos na desktop do aplicativo.
		desktopPane.add(frmFornecedor);
		desktopPane.add(frmProduto);
		desktopPane.add(frmFuncionario);
		//desktopPane.add(frmEstoque);
		//define os frames internos invisiveis por padrão na inicialização.
		frmFornecedor.setVisible(false);
		frmProduto.setVisible(false);
		
		//Define padrão para o fechaemento dos frames interno, esconder ao fechar ao invés de descartar a janela.
        frmFornecedor.setDefaultCloseOperation(HIDE_ON_CLOSE);
        frmProduto.setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 555, 680);
        jestoque = new JEstoque();
        panel.add(jestoque);
        desktopPane.add(panel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(550, 0, 555, 680);
        desktopPane.add(panel_1);
        
        //Adiciona os listeners internos para eventos nos internal frames.        
        frmFornecedor.addInternalFrameListener(this);
        frmProduto.addInternalFrameListener(this);
        frmFuncionario.addInternalFrameListener(this);
		
	}
	
	public void showArray(){
		
		for(Funcionario f : funcionarios){
			System.out.println(f.getNome());
		}
		
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		produtos = jproduto.getProdutos();
		funcionarios = jfuncionario.getFuncionarios();
		fornecedores = jfornecedor.getFornecedores();
		
		showArray();
		
	}
}
