import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JPanel {
    Connection connect = null;
    JLabel usernameLabel, passwordLabel;
    JTextField loginUsernameTxt;
    JPasswordField loginPasswordTxt;
    JButton loginBtn, toRegister;

    public Login(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        JLabel title = new JLabel("Login");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title);
        add(Box.createVerticalStrut(20));

        usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.BLACK);
        add(usernameLabel);
        loginUsernameTxt = new JTextField();
        loginUsernameTxt.setMaximumSize(new Dimension(300, 30));
        add(loginUsernameTxt);
        add(Box.createVerticalStrut(10));

        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.BLACK);
        add(passwordLabel);
        loginPasswordTxt = new JPasswordField();
        loginPasswordTxt.setMaximumSize(new Dimension(300, 30));
        add(loginPasswordTxt);
        add(Box.createVerticalStrut(10));

        loginBtn = new JButton("Login");
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(loginBtn);

        toRegister = new JButton("Don't have an account?");
        toRegister.setForeground(Color.BLUE);
        toRegister.setContentAreaFilled(false);
        toRegister.setBorderPainted(false);
        toRegister.setFocusPainted(false);
        toRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(toRegister);

        // --- Switch to register panel
        toRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "register");
            }
        });

        // --- Login button action
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect = config.getConnection();
                String username = loginUsernameTxt.getText();
                String password = new String(loginPasswordTxt.getPassword());
                String hashedPass = new hashPassword().hashPasswords(password);

                try {
                    Statement stmt = connect.createStatement();
                    String query = "SELECT client_id FROM Client WHERE username='" + username + "' AND password_hash='"
                            + hashedPass + "'";
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs.next()) {
                        home home = new home(rs.getInt("client_id"));
                        home.setVisible(true);
                        SwingUtilities.getWindowAncestor(Login.this).setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid credentials!");
                    }
                } catch (Exception ex) {
                    System.out.println("Login error: " + ex.getMessage());
                }
            }
        });
    }
}
