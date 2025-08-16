package app;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
public class Main{
    public static void main(String args[]){
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            LoginFrame login=new LoginFrame();
            login.setVisible(true);
            login.setLocationRelativeTo(null);
            login.setExtendedState(JFrame.MAXIMIZED_BOTH);
            login.setLocationRelativeTo(null);

            // home login=new home(3);
            // login.setVisible(true);
            // login.setLocationRelativeTo(null);
            // login.setExtendedState(JFrame.MAXIMIZED_BOTH);
            // login.setLocationRelativeTo(null);
        });
    }
}

