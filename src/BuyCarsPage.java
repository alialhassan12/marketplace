import javax.swing.*;
import java.awt.*;

public class BuyCarsPage extends JFrame {

    public BuyCarsPage(int clientId) {
        setTitle("Buy Cars");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("Cars available for purchase (Client ID: " + clientId + ")");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 18));

        add(label, BorderLayout.CENTER);
    }
}
