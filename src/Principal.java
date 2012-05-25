

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import almoxarifado.classes.gui.JLogin;

public class Principal extends JFrame{ 

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException 
    {
        Principal principal = new Principal();
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.updateComponentTreeUI(principal);
        JLogin login = new JLogin();
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setName("teset");
        login.setBounds(150, 150, 350, 200);			
    }
}