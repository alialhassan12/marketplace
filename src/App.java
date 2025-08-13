
import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
         login log = new login();
         log.setVisible(true);
         log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         log.setExtendedState(JFrame.MAXIMIZED_BOTH);// full screen
         log.setLocationRelativeTo(null);
        
    }
}
