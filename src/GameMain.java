import java.io.IOException;

import javax.swing.UIManager;

public class GameMain {
	public static void main(String[] args) throws IOException {
		try {
	           //here you can put the selected theme class name in JTattoo
	           UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
	
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		new GameGUI("Numbers Game");
	}
}
