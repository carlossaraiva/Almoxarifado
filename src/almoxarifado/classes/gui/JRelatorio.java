package almoxarifado.classes.gui;
import almoxarifado.classes.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import almoxarifado.classes.logico.*;
import java.awt.Color;
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
import javax.swing.table.DefaultTableModel;

public final class JRelatorio extends JPanel implements ActionListener{
	//Metodo que atuliza no banco os resultados
    private JPanel painelRelatorio;
    private JTabbedPane tabela;
    private DefaultTableModel relatorio;
    private JTable tabRelatorio;
    private JScrollPane scrollPane;
	//Construtores
    public JRelatorio() {		
            painelRelatorio = new JPanel();
				
            tabela = new JTabbedPane(JTabbedPane.TOP);
            tabela.setBounds(0, 0, 807, 530);
            tabela.addTab("Estoque", painelRelatorio);
            painelRelatorio.setLayout(null);
            
            setPainel();	
            setTabelasValores();
    }
    //Setters e getters

    private void setPainel(){
            setLayout(null);
            add(tabela);
            
            scrollPane = new JScrollPane();
            scrollPane.setViewportBorder(null);
            scrollPane.setBounds(10, 10, 750, 309);
            painelRelatorio.add(scrollPane);
            
            relatorio = new DefaultTableModel(10,8);
            String nomeColunaUlEnt[] = {"Produto:","Quantidade Atual:","Quantidade a comprar:","Data:","Funcionario:","Quantidade a usuada:","Nome fornecedor:","Telefone:"};
            relatorio.setColumnIdentifiers(nomeColunaUlEnt);
            tabRelatorio= new JTable();
            tabRelatorio.setModel(relatorio);
            scrollPane.setViewportView(tabRelatorio);
            add(tabela);
            tabRelatorio.setGridColor(Color.white);
    }
    public void setTabelasValores(){
        Conexao conn = new Conexao();
        conn.conecta();
        String sql = "select * from produto";
        ResultSet result = conn.executaBusca(sql);  
        int i =0;
        try
        {
            while(result.next())
            {
                if(i < 10)
                {
                    relatorio.setValueAt(result.getString("proNome"),i,0);
                    relatorio.setValueAt(result.getInt("proQtdAtua"),i,1);
                    int subt = result.getInt("proQtdMaxima") - result.getInt("proQtdAtua");
                    relatorio.setValueAt(subt,i,2);
                    String sqlRel = "select * from Fornecedor where forCodigo = "+result.getInt("forCodigo")+"";
                    ResultSet resultRel = conn.executaBusca(sqlRel);
                    while(resultRel.next())
                    {
                        relatorio.setValueAt(resultRel.getString("forNome"), i, 6);
                        relatorio.setValueAt(resultRel.getString("forTelefone"), i, 7);
                    }
                    i = i + 1;     
                } 
            }
        }  

        catch(SQLException sqlex)
        {
            System.out.println("erro");
        }
    }

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}