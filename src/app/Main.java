package app;
import javax.swing.*;

import java.awt.*;
import java.io.*;
import javax.imageio.*;

public class Main extends JFrame {
    public Main() {
        try {
            Image bgImage = ImageIO.read(new File("modern-empty-room.jpg"));
            JPanel background = new JPanel() {
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                }
            };
            background.setLayout(new GridBagLayout());

            CardLayout cardLayout = new CardLayout();
            JPanel mainPanel = new JPanel(cardLayout);
            mainPanel.setOpaque(false);

            mainPanel.add(new loginPage(cardLayout, mainPanel), "login");
            mainPanel.add(new RegisterPage(cardLayout, mainPanel), "register");

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.NORTH; // push down
            gbc.insets = new Insets(100, 0, 0, 0); // adjust vertical position
            background.add(mainPanel, gbc);

            setContentPane(background);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setLocationRelativeTo(null);
            setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
