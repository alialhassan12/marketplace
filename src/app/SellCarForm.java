package app;
import javax.swing.*;
import java.awt.*;

public class SellCarForm extends JFrame {

    public SellCarForm(int clientId) {
        setTitle("Sell Your Car");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("Car selling form for Client ID: " + clientId);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 18));

        add(label, BorderLayout.CENTER);
    }
}
