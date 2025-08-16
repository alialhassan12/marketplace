package app;

import javax.swing.*;
import java.awt.*;

public class adminhome extends JFrame {
    private int adminId;

    public adminhome(int adminId) {
        this.adminId = adminId;
        initComponents();
    }

    private void initComponents() {
        // Basic JFrame settings
        setTitle("Admin Home");
        setSize(400, 200);
        setLayout(new BorderLayout());

        // Simple label showing admin ID
        JLabel label = new JLabel("Welcome Admin! Your ID: " + adminId, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        add(label, BorderLayout.CENTER);
    }

    // For standalone testing
    public static void main(String[] args) {
        adminhome admin = new adminhome(1); // example admin ID
        admin.setVisible(true);
        admin.setLocationRelativeTo(null);
        admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
