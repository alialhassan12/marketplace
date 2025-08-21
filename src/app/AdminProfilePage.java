package app;

import javax.swing.*;
import java.awt.*;

public class AdminProfilePage extends JFrame {
    public AdminProfilePage(int adminId) {
        setTitle("Admin Profile");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel label = new JLabel("Admin Profile Page (reset)", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }
}