package app;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import app.config;
import app.hashPassword;

/**
 * AddAdminPage - Form to add new administrators
 */
public class AddAdminPage extends javax.swing.JFrame {

    private JTextField nameField, usernameField, emailField, phoneField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton addButton, cancelButton, clearButton;

    public AddAdminPage() {
        initComponents();
        setTitle("Add New Administrator");
        setSize(600, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(52, 52, 52));
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = createFormPanel();
        add(formPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(24, 24, 24));
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel titleLabel = new JLabel("Add New Administrator");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        headerPanel.add(titleLabel);
        return headerPanel;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(52, 52, 52));
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.WEST;

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        JLabel nameLabel = createLabel("Full Name:");
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        nameField = createTextField();
        formPanel.add(nameField, gbc);

        // Username
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.NONE;
        JLabel usernameLabel = createLabel("Username:");
        formPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        usernameField = createTextField();
        formPanel.add(usernameField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.NONE;
        JLabel emailLabel = createLabel("Email:");
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        emailField = createTextField();
        formPanel.add(emailField, gbc);

        // Phone
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.NONE;
        JLabel phoneLabel = createLabel("Phone:");
        formPanel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        phoneField = createTextField();
        formPanel.add(phoneField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.NONE;
        JLabel passwordLabel = createLabel("Password:");
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        passwordField = createPasswordField();
        formPanel.add(passwordField, gbc);

        // Confirm Password
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.NONE;
        JLabel confirmPasswordLabel = createLabel("Confirm Password:");
        formPanel.add(confirmPasswordLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        confirmPasswordField = createPasswordField();
        formPanel.add(confirmPasswordField, gbc);

        return formPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(24, 24, 24));
        buttonPanel.setPreferredSize(new Dimension(0, 80));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        addButton = new JButton("Add Administrator");
        addButton.setBackground(new Color(0, 150, 0));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Dialog", Font.BOLD, 14));
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);
        addButton.setPreferredSize(new Dimension(160, 40));
        addButton.addActionListener(e -> addAdministrator());

        clearButton = new JButton("Clear Form");
        clearButton.setBackground(new Color(128, 128, 128));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        clearButton.setFocusPainted(false);
        clearButton.setBorderPainted(false);
        clearButton.setPreferredSize(new Dimension(120, 40));
        clearButton.addActionListener(e -> clearForm());

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(220, 20, 60));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        cancelButton.setFocusPainted(false);
        cancelButton.setBorderPainted(false);
        cancelButton.setPreferredSize(new Dimension(100, 40));
        cancelButton.addActionListener(e -> this.dispose());

        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(cancelButton);

        return buttonPanel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Dialog", Font.PLAIN, 14));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(30);
        textField.setBackground(new Color(60, 60, 60));
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.WHITE);
        textField.setFont(new Font("Dialog", Font.PLAIN, 16));
        textField.setPreferredSize(new Dimension(300, 35));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(120, 120, 120), 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        // Ensure text is visible
        textField.setSelectionColor(new Color(0, 102, 255));
        textField.setSelectedTextColor(Color.WHITE);
        return textField;
    }

    private JPasswordField createPasswordField() {
        JPasswordField passwordField = new JPasswordField(30);
        passwordField.setBackground(new Color(60, 60, 60));
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 16));
        passwordField.setPreferredSize(new Dimension(300, 35));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(120, 120, 120), 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        // Ensure text is visible
        passwordField.setSelectionColor(new Color(0, 102, 255));
        passwordField.setSelectedTextColor(Color.WHITE);
        passwordField.setEchoChar('●');
        return passwordField;
    }

    private void addAdministrator() {
        // Validation
        if (!validateForm()) {
            return;
        }

        String name = nameField.getText().trim();
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String password = new String(passwordField.getPassword());

        // Check if username already exists
        if (usernameExists(username)) {
            JOptionPane.showMessageDialog(this,
                    "Username already exists. Please choose a different username.",
                    "Username Taken",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

    // Hash password using hashPassword class (SHA-256, Base64)
    hashPassword hasher = new hashPassword();
    String hashedPassword = hasher.hashPasswords(password);

        // Insert into database
        Connection connect = config.getConnection();
        try {
            String query = "INSERT INTO Client (name, username, email, phone, password_hash, role, created_at,is_approved) VALUES (?, ?, ?, ?, ?, 'admin', NOW(),'true')";
            PreparedStatement pstmt = connect.prepareStatement(query);

            pstmt.setString(1, name);
            pstmt.setString(2, username);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setString(5, hashedPassword);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(this,
                        "Administrator added successfully!\n\nUsername: " + username + "\nRole: Admin",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to add administrator. Please try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            pstmt.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Database error: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private boolean validateForm() {
        if (nameField.getText().trim().isEmpty()) {
            showError("Please enter the full name.");
            nameField.requestFocus();
            return false;
        }

        if (usernameField.getText().trim().isEmpty()) {
            showError("Please enter a username.");
            usernameField.requestFocus();
            return false;
        }

        if (emailField.getText().trim().isEmpty()) {
            showError("Please enter an email address.");
            emailField.requestFocus();
            return false;
        }

        if (!isValidEmail(emailField.getText().trim())) {
            showError("Please enter a valid email address.");
            emailField.requestFocus();
            return false;
        }

        if (passwordField.getPassword().length == 0) {
            showError("Please enter a password.");
            passwordField.requestFocus();
            return false;
        }

        if (passwordField.getPassword().length < 6) {
            showError("Password must be at least 6 characters long.");
            passwordField.requestFocus();
            return false;
        }

        if (!java.util.Arrays.equals(passwordField.getPassword(), confirmPasswordField.getPassword())) {
            showError("Passwords do not match.");
            confirmPasswordField.requestFocus();
            return false;
        }

        return true;
    }

    private boolean usernameExists(String username) {
        Connection connect = config.getConnection();
        try {
            String query = "SELECT COUNT(*) FROM Client WHERE username = ?";
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

            rs.close();
            pstmt.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    // Password hashing is now handled by app.hashPassword

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }

    private void clearForm() {
        nameField.setText("");
        usernameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        nameField.requestFocus();
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {
            new AddAdminPage().setVisible(true);
        });
    }
}