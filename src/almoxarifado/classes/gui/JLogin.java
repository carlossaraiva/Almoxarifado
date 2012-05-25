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
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class JLogin extends JFrame implements ActionListener{

	private JPanel desktop;
        
        private JTextField txtLogin;
        private JTextField txtSenha;
        
        private JButton logar;
        
	public JLogin()
        {
            setIntFrames();
            setVisible(true);
	}
	public void setIntFrames(){
				
            desktop = new JPanel();
            desktop.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(desktop);
            desktop.setLayout(null);

            JLabel lblLogin = new JLabel("Login: ");
            lblLogin.setBounds(15, 50, 100, 20);
            add(lblLogin);
            
            txtLogin = new JTextField();
            txtLogin.setBounds(50,50,150,20);
            add(txtLogin);
            
            JLabel lblSenha = new JLabel("Senha: ");
            lblSenha.setBounds(15, 100, 100, 20);
            add(lblSenha);
            
            txtSenha = new JTextField();
            txtSenha.setBounds(50, 100, 150, 20);
            add(txtSenha);
            
            logar = new JButton("Logar");
            logar.setBounds(220, 75, 70, 20);
            add(logar);
            logar.addActionListener(this);
        }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logar)
        {
            Funcionario fun = new Funcionario();
            String result = fun.logarFuncionario(txtLogin.getText(), txtSenha.getText());
            txtLogin.setText(null);
            txtSenha.setText(null);
            if(result != "")
            {
                JDesktop desktop = new JDesktop(result);
                desktop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                desktop.setBounds(50, 50, 1090, 690);
                this.desktop.setVisible(false);
            }
            System.out.println(result);
        }
    }
}
