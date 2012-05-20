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


public class JDesktop extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JFuncionario jfuncionario;
	private JProduto jproduto;
	private JFornecedor jfornecedor;
	private JEstoque jestoque;
	
	private JInternalFrame frmFornecedor;
	private JInternalFrame frmProduto;
	private JInternalFrame frmFuncionario;
	private JInternalFrame frmEstoque;
	
	private JMenuItem mntmProduto;
	private JMenuItem mntmFuncionario; 
	private JMenuItem mntmFornecedor;
	private JDesktopPane desktop;

	public JDesktop(){
		setMenuBar();
		setIntFrames();
		setVisible(true);
		setBounds(0,0,1290,720);
		
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

	public void setIntFrames(){
				
		desktop = new JDesktopPane();
		getContentPane().add(desktop);
		desktop.setBounds(0, 0, 1115, 680);
		desktop.setBackground(Color.GRAY);
						
		//Frame Produto, instancia a janela interna e inicia seus componentes.
		frmProduto = new JInternalFrame("Produto");
		frmProduto.setFocusable(false);
		frmProduto.setBounds(139, 70, 646, 500);
		frmProduto.setClosable(true);
		jproduto = new JProduto();
		frmProduto.getContentPane().add(jproduto);
		
		//Frame Estoque, instancia a janela interna e inicia seus componentes;
		frmEstoque = new JInternalFrame("Controle de Estoque");
		frmEstoque.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		//frmEstoque.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		frmEstoque.setBounds(331, 11, 709, 554);
		frmEstoque.setFocusable(false);
		frmEstoque.setClosable(false);
        jestoque = new JEstoque();
        frmEstoque.getContentPane().add(jestoque);
	
		//Frame Funcionário, instancia a janela interna e inicia seus componentes.
		frmFuncionario = new JInternalFrame("Funcionario");
		frmFuncionario.setBounds(50, 50, 646, 500);		
		frmFuncionario.setFocusable(false);
		frmFuncionario.getContentPane().setBounds(new Rectangle(0, 0, 0, 0));
		frmFuncionario.setClosable(true);
		jfuncionario = new JFuncionario();
		frmFuncionario.getContentPane().add(jfuncionario);
		desktop.add(frmProduto);
		desktop.add(frmFuncionario);
		desktop.add(frmEstoque);
		//desktopPane.add(frmEstoque);
		//define os frames internos invisiveis por padrão na inicialização.
		frmEstoque.setVisible(true);
		frmProduto.setVisible(false);
        frmProduto.setDefaultCloseOperation(HIDE_ON_CLOSE);
		frmEstoque.setDefaultCloseOperation(HIDE_ON_CLOSE);
		frmFuncionario.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
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

		
        /*
        //Adiciona os listeners internos para eventos nos internal frames.        
        frmFornecedor.addInternalFrameListener(this);
        frmProduto.addInternalFrameListener(this);
        frmFuncionario.addInternalFrameListener(this);
		*/
	}

}
