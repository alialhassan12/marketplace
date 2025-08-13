import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Register extends JPanel {
    Connection connect = null;
    JTextField nameTxt, emailTxt, phoneTxt, usernameTxt;
    JPasswordField passwordTxt, confirmPasswordTxt;
    JButton registerBtn, toLogin;

    public Register(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        JLabel title = new JLabel("Register");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title);
        add(Box.createVerticalStrut(10));

        nameTxt = new JTextField();
        nameTxt.setMaximumSize(new Dimension(300, 30));
        add(new JLabel("Full Name:"));
        add(nameTxt);
        add(Box.createVerticalStrut(10));

        usernameTxt = new JTextField();
        usernameTxt.setMaximumSize(new Dimension(300, 30));
        add(new JLabel("Username:"));
        add(usernameTxt);
        add(Box.createVerticalStrut(10));

        emailTxt = new JTextField();
        emailTxt.setMaximumSize(new Dimension(300, 30));
        add(new JLabel("Email:"));
        add(emailTxt);
        add(Box.createVerticalStrut(10));

        phoneTxt = new JTextField();
        phoneTxt.setMaximumSize(new Dimension(300, 30));
        add(new JLabel("Phone Number:"));
        add(phoneTxt);
        add(Box.createVerticalStrut(10));

        passwordTxt = new JPasswordField();
        passwordTxt.setMaximumSize(new Dimension(300, 30));
        add(new JLabel("Password:"));
        add(passwordTxt);
        add(Box.createVerticalStrut(10));

        confirmPasswordTxt = new JPasswordField();
        confirmPasswordTxt.setMaximumSize(new Dimension(300, 30));
        add(new JLabel("Confirm Password:"));
        add(confirmPasswordTxt);
        add(Box.createVerticalStrut(10));

        registerBtn = new JButton("Register");
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(registerBtn);

        toLogin = new JButton("Already have an account?");
        toLogin.setForeground(Color.BLUE);
        toLogin.setContentAreaFilled(false);
        toLogin.setBorderPainted(false);
        toLogin.setFocusPainted(false);
        toLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(toLogin);

        // --- Switch to login panel
        toLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "login");
            }
        });

        // --- Register button logic
        registerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect = config.getConnection();
                String name = nameTxt.getText();
                String username = usernameTxt.getText();
                String email = emailTxt.getText();
                String phone = phoneTxt.getText();
                String password = new String(passwordTxt.getPassword());
                String confirm = new String(confirmPasswordTxt.getPassword());

                if (!password.equals(confirm)) {
                    JOptionPane.showMessageDialog(null, "Passwords don't match!");
                    return;
                }
                if (name.isEmpty() || username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!");
                    return;
                }

                try {
                    String query = "INSERT INTO Client(name,email,phone,password_hash,username) VALUES('" + name + "','"
                            + email + "','" + phone + "','" + new hashPassword().hashPasswords(password) + "','"
                            + username + "')";
                    Statement stmt = connect.createStatement();
                    stmt.execute(query);
                    JOptionPane.showMessageDialog(null, "Registered successfully!");
                    cardLayout.show(mainPanel, "login");
                } catch (Exception ex) {
                    System.out.println("Register error: " + ex.getMessage());
                }
            }
        });
    }
}
