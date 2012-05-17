package almoxarifado.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import almoxarifado.classes.Funcionario;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JFuncionario extends JPanel implements ActionListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;
	
	private ArrayList <Funcionario> funcionarios = new ArrayList<Funcionario>();
	private DefaultListModel modelFuncionarios = new DefaultListModel();
	
	private JTextField txtFuncionario;
	private JTextField txtRegistro;
	private JTextField textField;	
	private JList listFuncionario;	
	private JButton btnAdicionar;
	private JButton btnLimpar;
	private JButton btnExcluir;
	private JScrollPane scrollPane;
	private JRadioButton rdbtnM;
	private JRadioButton rdbtnF;
	
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
				
		txtFuncionario = new JTextField();
		txtFuncionario.setBounds(10, 64, 220, 20);
		add(txtFuncionario);
		txtFuncionario.setColumns(10);
		
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
		
		
		add(lblNomeCompleto);
		add(lblRegistro);
		add(textField);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(429, 94, 189, 253);
		add(scrollPane);
		listFuncionario = new JList(modelFuncionarios);
		scrollPane.setViewportView(listFuncionario);
		listFuncionario.addListSelectionListener(this);
		listFuncionario.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listFuncionario.setLayoutOrientation(JList.VERTICAL);
		listFuncionario.setVisibleRowCount(-1);
		add(btnLimpar);
		
		ButtonGroup sexo = new ButtonGroup();
		rdbtnM = new JRadioButton("M");
		rdbtnM.setBounds(24, 207, 141, 23);
		add(rdbtnM);
		rdbtnF = new JRadioButton("F");
		rdbtnF.setBounds(24, 242, 141, 23);
		add(rdbtnF);
		sexo.add(rdbtnF);
		sexo.add(rdbtnM);

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
			Funcionario f = new Funcionario();		
			f.setNome(txtFuncionario.getText());			
			modelFuncionarios.addElement(f.getNome());
			f.setRegistro(txtRegistro.getText());
			
			if(rdbtnM.isSelected()== true){
				f.setSexo("M");
				
			}
			else{
				f.setSexo("F");
			}
			
			funcionarios.add(f);						
			txtFuncionario.setText(null);
			txtRegistro.setText(null);			
		}
		
		if(e.getSource() == btnExcluir){
			funcionarios.remove(listFuncionario.getSelectedIndex());
			modelFuncionarios.remove(listFuncionario.getSelectedIndex());			
		}
		
		showArray();
	}
	
	//debugagem
	public void showArray(){
	
		for(Funcionario f: funcionarios){			
			System.out.println(f.getNome() + f.getRegistro() + f.getSexo());
					
		}
	}
}
