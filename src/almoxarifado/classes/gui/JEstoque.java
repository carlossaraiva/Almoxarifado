package almoxarifado.classes.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import almoxarifado.classes.util.*;
import almoxarifado.classes.util.TextClock;
public class JEstoque extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tab;
	private JPanel painelEstoque;
	private JEntrada entrada;
	private DefaultTableModel ultimasEntradasModel;
	private DefaultTableModel ultimasSaidasModel;
	private DefaultTableModel estoqueMinimoModel;
	private JTable tblUltimasEntradas;
	private JTable tblUltimasSaidas;
	private JTable tblEstoqueMinimo;
	private JScrollPane scrollPane;
	
	public JEstoque() {
		setLayout(null);
		painelEstoque = new JPanel();
				
		tab = new JTabbedPane(JTabbedPane.TOP);
		tab.setBounds(0, 0, 707, 530);
		tab.addTab("Estoque", painelEstoque);
		painelEstoque.setLayout(null);
		
		JLabel lblDatahora = new JLabel("Data/Hora:");
		lblDatahora.setBounds(21, 11, 59, 14);
		painelEstoque.add(lblDatahora);
		
		JButton btnNovaEntrada = new JButton("Nova Entrada");
		btnNovaEntrada.addActionListener(this);
		
		btnNovaEntrada.setBounds(21, 458, 113, 23);
		painelEstoque.add(btnNovaEntrada);
		
		JButton btnNovaSada = new JButton("Nova Sa\u00EDda");
		btnNovaSada.setBounds(144, 458, 105, 23);
		painelEstoque.add(btnNovaSada);
		
		JLabel lblltimasEntradas = new JLabel("\u00DAltimas entradas:");
		lblltimasEntradas.setBounds(21, 51, 96, 14);
		painelEstoque.add(lblltimasEntradas);
		
		JLabel lblltimasSadas = new JLabel("\u00DAltimas sa\u00EDdas:");
		lblltimasSadas.setBounds(356, 51, 96, 14);
		painelEstoque.add(lblltimasSadas);
		
		JLabel lblProdutoEmEstoque = new JLabel("Produto em Estoque M\u00EDnimo");
		lblProdutoEmEstoque.setBounds(21, 216, 148, 14);
		painelEstoque.add(lblProdutoEmEstoque);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(21, 199, 631, 14);
		painelEstoque.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(21, 36, 631, 14);
		painelEstoque.add(separator_1);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(21, 361, 631, 14);
		painelEstoque.add(separator_3);
		
		entrada = new JEntrada();
			
		setTabelas();
		setTabelasValores();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		entrada.setVisible(true);
		
		
		
	}
	
	public void setTabelas(){
		add(tab);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(21, 76, 320, 109);
		painelEstoque.add(scrollPane);
		
		ultimasEntradasModel = new DefaultTableModel(5,4);
		String nomeColunaUlEnt[] = {"Data:","Produto:","Fornecedor:","Data:"};
		ultimasEntradasModel.setColumnIdentifiers(nomeColunaUlEnt);
		tblUltimasEntradas= new JTable();
		tblUltimasEntradas.setModel(ultimasEntradasModel);
		scrollPane.setViewportView(tblUltimasEntradas);
		add(tab);
		tblUltimasEntradas.setGridColor(Color.white);
		
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setViewportBorder(null);
		scrollPane2.setBounds(356, 76, 320, 109);
		painelEstoque.add(scrollPane2);
		
		ultimasSaidasModel = new DefaultTableModel(5,4);
		String nomeColunaUlSaidas[] = {"Data:","Produto:","Funcionário:","Data:"};
		ultimasSaidasModel.setColumnIdentifiers(nomeColunaUlSaidas);
		tblUltimasSaidas= new JTable();
		tblUltimasSaidas.setModel(ultimasSaidasModel);
		scrollPane2.setViewportView(tblUltimasSaidas);
		
		tblUltimasSaidas.setGridColor(Color.white);

		
		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setViewportBorder(null);
		scrollPane3.setBounds(21, 241, 320, 109);
		painelEstoque.add(scrollPane3);
		
		estoqueMinimoModel = new DefaultTableModel(5,2);
		tblEstoqueMinimo= new JTable();
		tblEstoqueMinimo.setModel(estoqueMinimoModel);
		scrollPane3.setViewportView(tblEstoqueMinimo);
		add(tab);
		tblEstoqueMinimo.setGridColor(Color.white);	
		
		TextClock clock = new TextClock();
		clock.setBounds(79, -1, 131, 37);
		painelEstoque.add(clock);
		
	}
	
	public void setTabelasValores(){
		
		for(int linhas = 0; linhas < 5; linhas++){
			for(int colunas = 0; colunas < 4; colunas++){
				
				ultimasEntradasModel.setValueAt("23/23/1099", linhas, colunas);
			}
		}
		
		for(int linhas = 0; linhas < 5; linhas++){
			for(int colunas = 0; colunas < 4; colunas++){
				
				ultimasSaidasModel.setValueAt("2", linhas, colunas);
			}
		}
		
		for(int linhas = 0; linhas < 5; linhas++){
			for(int colunas = 0; colunas < 2; colunas++){
				
				estoqueMinimoModel.setValueAt("2", linhas, colunas);
			}
		}
		
		
	}
}
