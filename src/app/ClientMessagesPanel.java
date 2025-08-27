package app;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class ClientMessagesPanel extends JPanel {

    private Connection dbConnection;
    private int clientId;

    private JTextField subjectField;
    private JTextArea contentArea;
    private JComboBox<String> typeComboBox;
    private JComboBox<String> carComboBox;
    private JComboBox<String> userComboBox;

    public ClientMessagesPanel(Connection dbConnection, int clientId) {
        this.dbConnection = dbConnection;
        this.clientId = clientId;
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(52, 52, 52));
        setLayout(new BorderLayout());

        // HEADER
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        header.setBackground(new Color(24, 24, 24));
        JLabel title = new JLabel("Contact Admin");
        title.setFont(new Font("Dialog", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);

        // CENTER FORM
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(52, 52, 52));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Type
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel typeLabel = new JLabel("Message Type:");
        typeLabel.setForeground(Color.WHITE);
        formPanel.add(typeLabel, gbc);

        gbc.gridx = 1;
        String[] types = { "General Inquiry", "Support", "Report Car", "Report User" };
        typeComboBox = new JComboBox<>(types);
        formPanel.add(typeComboBox, gbc);

        // Car Combo (hidden initially)
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel carLabel = new JLabel("Select Car:");
        carLabel.setForeground(Color.WHITE);
        formPanel.add(carLabel, gbc);

        gbc.gridx = 1;
        carComboBox = new JComboBox<>();
        carComboBox.setVisible(false);
        carLabel.setVisible(false);
        formPanel.add(carComboBox, gbc);

        // User Combo (hidden initially)
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel userLabel = new JLabel("Select User:");
        userLabel.setForeground(Color.WHITE);
        formPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        userComboBox = new JComboBox<>();
        userComboBox.setVisible(false);
        userLabel.setVisible(false);
        formPanel.add(userComboBox, gbc);

        // Subject
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel subjectLabel = new JLabel("Subject:");
        subjectLabel.setForeground(Color.WHITE);
        formPanel.add(subjectLabel, gbc);

        gbc.gridx = 1;
        subjectField = new JTextField(25);
        formPanel.add(subjectField, gbc);

        // Content
        gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.NORTH;
        JLabel contentLabel = new JLabel("Message:");
        contentLabel.setForeground(Color.WHITE);
        formPanel.add(contentLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        contentArea = new JTextArea(8, 25);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(contentArea);
        formPanel.add(scroll, gbc);

        add(formPanel, BorderLayout.CENTER);

        // FOOTER
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(new Color(24, 24, 24));

        JButton sendBtn = new JButton("Send");
        sendBtn.setBackground(new Color(0, 150, 0));
        sendBtn.setForeground(Color.WHITE);
        sendBtn.addActionListener(e -> sendMessage());

        JButton clearBtn = new JButton("Clear");
        clearBtn.setBackground(new Color(128, 128, 128));
        clearBtn.setForeground(Color.WHITE);
        clearBtn.addActionListener(e -> clearForm());

        footer.add(sendBtn);
        footer.add(clearBtn);

        add(footer, BorderLayout.SOUTH);

        // Show/hide combos depending on type
        typeComboBox.addActionListener(e -> {
            String selected = (String) typeComboBox.getSelectedItem();
            boolean carVisible = "Report Car".equals(selected);
            boolean userVisible = "Report User".equals(selected);
            carComboBox.setVisible(carVisible);
            userComboBox.setVisible(userVisible);
            carLabel.setVisible(carVisible);
            userLabel.setVisible(userVisible);

            if (carVisible) loadCars();
            if (userVisible) loadUsers();
        });
    }

    private void loadCars() {
        try {
            carComboBox.removeAllItems();
            String sql = "SELECT car_id, brand, model FROM car";
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                carComboBox.addItem(rs.getInt("car_id") + " - " + rs.getString("brand") + " " + rs.getString("model"));
            }
        } catch (Exception e) {
            carComboBox.addItem("Error loading cars");
        }
    }

    private void loadUsers() {
        try {
            userComboBox.removeAllItems();
            String sql = "SELECT client_id, username FROM client";
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                userComboBox.addItem(rs.getInt("client_id") + " - " + rs.getString("username"));
            }
        } catch (Exception e) {
            userComboBox.addItem("Error loading users");
        }
    }

    private void sendMessage() {
        String subject = subjectField.getText().trim();
        String content = contentArea.getText().trim();
        String type = (String) typeComboBox.getSelectedItem();

        if (subject.isEmpty() || content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in subject and content.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Adjust subject if report
        if ("Report Car".equals(type) && carComboBox.getSelectedItem() != null) {
            subject = "Car Report: " + carComboBox.getSelectedItem();
        } else if ("Report User".equals(type) && userComboBox.getSelectedItem() != null) {
            subject = "User Report: " + userComboBox.getSelectedItem();
        }

        try {
            String query = "INSERT INTO messages (client_id, subject, message_content, message_type, status, priority, created_at) " +
                           "VALUES (?, ?, ?, ?, 'Unread', 'Normal', CURRENT_TIMESTAMP)";
            PreparedStatement pstmt = dbConnection.prepareStatement(query);
            pstmt.setInt(1, clientId);
            pstmt.setString(2, subject);
            pstmt.setString(3, content);
            pstmt.setString(4, type);
            pstmt.executeUpdate();
            pstmt.close();

            JOptionPane.showMessageDialog(this, "Message sent!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        subjectField.setText("");
        contentArea.setText("");
        typeComboBox.setSelectedIndex(0);
        carComboBox.removeAllItems();
        userComboBox.removeAllItems();
        carComboBox.setVisible(false);
        userComboBox.setVisible(false);
    }
}
