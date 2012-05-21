package almoxarifado.classes.gui;


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

import almoxarifado.classes.logico.Funcionario;
import almoxarifado.classes.logico.Produto;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
public class JFuncionario extends JPanel implements ActionListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;
	
	private ArrayList <Funcionario> funcionarios = new ArrayList<Funcionario>();
	private DefaultListModel<String> modelFuncionarios = new DefaultListModel<String>();
	private String sexo;
	private Funcionario f;
	
	private JTextField txtNome;
	private JTextField txtRegistro;
	private JTextField textField;	
	private JList<String> listFuncionario;	
	private JButton btnAdicionar;
	private JButton btnLimpar;
	private JButton btnExcluir;
	private JScrollPane scrollPane;
	private JRadioButton rdbtnM;
	private JRadioButton rdbtnF;
	private JPanel borderSexo;
	
	//Construtores
	public JFuncionario() {		
		setPainel();		
	}		
	
	public JFuncionario(ArrayList<Funcionario> funcionarios){		
		setPainel();
		this.funcionarios = funcionarios;		
		setModelFuncionarios();
	}
	
	//Getters e setters
	public void setModelFuncionarios(){
		for(Funcionario f : funcionarios){
			modelFuncionarios.addElement(f.getNome());
		}	
	}
	
	private void setPainel(){
		setLayout(null);		
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(10, 372, 89, 23);
		add(btnAdicionar);
		btnAdicionar.addActionListener(this);
				
		txtNome = new JTextField();
		txtNome.setBounds(10, 64, 220, 20);
		add(txtNome);
		txtNome.setColumns(10);
		
		txtRegistro = new JTextField();
		txtRegistro.setBounds(10, 116, 220, 20);
		add(txtRegistro);
		txtRegistro.setColumns(10);
				
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
				
		JLabel lblRegistro = new JLabel("Registro:");
		lblRegistro.setBounds(10, 95, 46, 14);
				
		textField = new JTextField();
		textField.setBounds(429, 64, 189, 20);
		
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				modelFuncionarios.clear();
				Funcionario f = new Funcionario();
				ResultSet result = f.buscaFuncionario(textField.getText());
				try
				{
					while(result.next())
					{
						modelFuncionarios.addElement(result.getString("forNome"));
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
		add(textField);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(429, 94, 189, 253);
		add(scrollPane);
		listFuncionario = new JList<String>(modelFuncionarios);
		scrollPane.setViewportView(listFuncionario);
		listFuncionario.addListSelectionListener(this);
		listFuncionario.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listFuncionario.setLayoutOrientation(JList.VERTICAL);
		listFuncionario.setVisibleRowCount(-1);
		add(btnLimpar);
		
		ButtonGroup sexo = new ButtonGroup();
		
		borderSexo = new JPanel();
		borderSexo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		borderSexo.setBounds(10, 160, 116, 88);
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

	}
	
	public ArrayList<Funcionario> getFuncionarios(){
		return this.funcionarios;
	}
	
	//Listeners
	public void valueChanged(ListSelectionEvent e){
		
		System.out.println(listFuncionario.getSelectedIndex());
		
	    if (e.getValueIsAdjusting() == false) {

	        if (listFuncionario.getSelectedIndex() == -1) {
	        //No selection, disable fire button.
	            btnExcluir.setEnabled(false);

	        } else {
	        //Selection, enable the fire button.
	            btnExcluir.setEnabled(true);
	        }		    
	    }		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnAdicionar){
			
			if(rdbtnM.isSelected()== true){
				 sexo = "M";
			}
			else{
				 sexo = "F";
			}
			
			f = new Funcionario(txtNome.getText(),txtRegistro.getText(), sexo);
			f.insereFuncionario();
			txtNome.setText(null);
			txtRegistro.setText(null);			
		}
		
		if(e.getSource() == btnExcluir){
			f.excluirFuncionario(listFuncionario.getSelectedValue());
			
		
		}
	}
	

}
