package app;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.sql.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class home extends JFrame {
    Connection connect = null;
    JPanel panel1;
    JLabel nameLabel;
    JLabel title;
    JButton buyBtn;
    JButton rentBtn;
    JButton sellBtn;

    public home(int client_id) {
        String query = "SELECT name FROM Client WHERE client_id = " + client_id;
        connect = config.getConnection();

        try {
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                nameLabel = new JLabel("Welcome, " + rs.getString("name") + "!");
            } else {
                nameLabel = new JLabel("Welcome, User!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            nameLabel = new JLabel("Welcome, User!");
        }

        setLayout(new GridBagLayout());

        title = new JLabel("Main Menu");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        buyBtn = new JButton("Buy a Car");
        rentBtn = new JButton("Rent a Car");
        sellBtn = new JButton("Sell Your Car");

        buyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new BuyCarsPage(client_id).setVisible(true);
            }
        });

        rentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new RentCarsPage(client_id).setVisible(true);
            }
        });

        sellBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new SellCarForm(client_id).setVisible(true);
            }
        });

        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.add(title);
        panel1.add(Box.createVerticalStrut(10));
        panel1.add(nameLabel);
        panel1.add(Box.createVerticalStrut(20));
        panel1.add(buyBtn);
        panel1.add(Box.createVerticalStrut(10));
        panel1.add(rentBtn);
        panel1.add(Box.createVerticalStrut(10));
        panel1.add(sellBtn);

        add(panel1);
        pack();
        setLocationRelativeTo(null);
    }
}
