package almoxarifado.classes.base;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import almoxarifado.gui.JDesktop;

public class Principal extends JFrame{ 

	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
				Principal principal = new Principal();
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				SwingUtilities.updateComponentTreeUI(principal);
				JDesktop desktop = new JDesktop();
				desktop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
}