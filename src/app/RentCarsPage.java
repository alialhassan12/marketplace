package app;
import javax.swing.*;
import java.awt.*;

public class RentCarsPage extends JFrame {

    public RentCarsPage(int clientId) {
        setTitle("Rent Cars");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("Cars available for rent (Client ID: " + clientId + ")");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 18));

        add(label, BorderLayout.CENTER);
    }
}
