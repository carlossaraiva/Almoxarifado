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
import almoxarifado.classes.logico.*;
import almoxarifado.classes.util.TextClock;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
public final class JEstoque extends JPanel implements ActionListener {

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
	
	public void setTabelas(){
		add(tab);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(21, 76, 320, 109);
		painelEstoque.add(scrollPane);
		
		ultimasEntradasModel = new DefaultTableModel(5,3);
		String nomeColunaUlEnt[] = {"Data:","Produto:","Fornecedor:"};
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
		
		ultimasSaidasModel = new DefaultTableModel(5,3);
		String nomeColunaUlSaidas[] = {"Data:","Produto:","Funcionário:"};
		ultimasSaidasModel.setColumnIdentifiers(nomeColunaUlSaidas);
		tblUltimasSaidas= new JTable();
		tblUltimasSaidas.setModel(ultimasSaidasModel);
		scrollPane2.setViewportView(tblUltimasSaidas);
		
		tblUltimasSaidas.setGridColor(Color.white);
                
                
		JButton btnNovaEntrada = new JButton("Nova Entrada");
		btnNovaEntrada.addActionListener(this);

		btnNovaEntrada.setBounds(21, 458, 113, 23);
		painelEstoque.add(btnNovaEntrada);
		
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
	
        
	
        
        public JEstoque(String tipCodigo) {
                
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
                btnNovaEntrada.setVisible(false);
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
			
		if(tipCodigo.equals("Diretoria de serviço"))
                {
                    btnNovaEntrada.setVisible(true);
                }
                setTabelas();
                setTabelasValores();

	}
        
        public void setTabelasValores() {

		Conexao conn = new Conexao();
		conn.conecta();

		String sqlEntrada = "select * from Usuario";
		ResultSet resultEntrada = conn.executaBusca(sqlEntrada);

		int i = 0;
		try {
                    while (resultEntrada.next() && i < 5) 
                    {
                        ultimasEntradasModel.setValueAt(resultEntrada.getInt("usuCodigo"), i, 0);
                            i = i + 1;
                    }
		}catch (SQLException sqlex) {
			System.out.println("erro");
		}
                
                Conexao conn1 = new Conexao();
		conn1.conecta();
                
                String sqlEntrada1 = "select * from Retirada";
		ResultSet resultEntrada1 = conn1.executaBusca(sqlEntrada1);

		int j = 0;
		try {
                    while (resultEntrada1.next() && j < 5) 
                    {
                        
                        int res = resultEntrada1.getInt("pedCodigo");
                        String sqlPed = "select * from Pedido where pedCodigo = "+res+"";
                        ResultSet resPed = conn1.executaBusca(sqlPed);
                        while(resPed.next())
                        {
                            int res1 = resPed.getInt("usuCodigo");
                            String sqlUsu = "select * from Usuario where usuCodigo = "+res1+"";
                            ResultSet resUsu = conn1.executaBusca(sqlUsu);
                            while(resUsu.next())
                            {
                                ultimasSaidasModel.setValueAt(resUsu.getString("usuNome"), j, 0);
                            }
                        }
                            j = j + 1;
                    }
		}catch (SQLException sqlex1) {
			System.out.println("erro");
		}
                
                Conexao conn2 = new Conexao();
		conn2.conecta();
                
                String sqlEntrada2 = "select * from Fornecedor";
		ResultSet resultEntrada2 = conn2.executaBusca(sqlEntrada2);

		int k = 0;
		try {
                    while (resultEntrada2.next() && k < 5) 
                    {
                        estoqueMinimoModel.setValueAt(resultEntrada2.getInt("forCodigo"), k, 0);
                            k = k + 1;
                    }
		}catch (SQLException sqlex1) {
			System.out.println("erro");
		}
	}

	public void actionPerformed(ActionEvent e) {
		entrada.setVisible(true);
		
		
		
	}
}
