package app;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

public class Main {
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            LoginFrame login = new LoginFrame();
            login.setSize(1280, 750);
            login.setResizable(false);
            login.setVisible(true);
            login.setLocationRelativeTo(null);

            // mainFrame login = new mainFrame(1);
            // // login.setSize(959, 608);//car info frame size
            // login.setExtendedState(JFrame.MAXIMIZED_BOTH);
            // login.setVisible(true);
            // login.setLocationRelativeTo(null);
        });
    }
}
