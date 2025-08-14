package app;
import javax.swing.*;

import controllers.login;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class loginPage extends JPanel {
    Connection connect = null;
    JLabel usernameLabel, passwordLabel;
    JTextField loginUsernameTxt;
    JPasswordField loginPasswordTxt;
    JButton loginBtn, toRegister;

    public loginPage(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        JLabel title = new JLabel("Login");
        title.setForeground(Color.white);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title);
        add(Box.createVerticalStrut(20));

        usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.white);
        add(usernameLabel);
        add(Box.createVerticalStrut(10));

        loginUsernameTxt = new JTextField();
        loginUsernameTxt.setMaximumSize(new Dimension(300, 30));
        loginUsernameTxt.setOpaque(false);
        loginUsernameTxt.setBackground(new Color(34,34,34,179));
        loginUsernameTxt.setForeground(Color.white);

        add(loginUsernameTxt);
        add(Box.createVerticalStrut(10));

        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.white);
        add(passwordLabel);
        add(Box.createVerticalStrut(10));

        loginPasswordTxt = new JPasswordField();
        loginPasswordTxt.setMaximumSize(new Dimension(300, 30));
        loginPasswordTxt.setOpaque(false);
        loginPasswordTxt.setBackground(new Color(34,34,34,179));
        loginPasswordTxt.setForeground(Color.white);
        
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
                login login=new login(username, hashedPass);
                if(login.getLogin()){
                    SwingUtilities.getWindowAncestor(loginPage.this).setVisible(false);
                }
            }
        });
    }
}
