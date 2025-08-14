package app;
import javax.swing.*;

import controllers.register;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterPage extends JPanel {
    Connection connect = null;
    JLabel nameLabel,emailLabel,phoneLabel,usernameLabel,passwordLabel,confirmPasswordLabel;
    JTextField nameTxt, emailTxt, phoneTxt, usernameTxt;
    JPasswordField passwordTxt, confirmPasswordTxt;
    JButton registerBtn, toLogin;

    public RegisterPage(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        JLabel title = new JLabel("Register");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.white);
        add(title);
        add(Box.createVerticalStrut(10));

        nameTxt = new JTextField();
        nameTxt.setPreferredSize(new Dimension(300, 30));
        nameTxt.setOpaque(false);
        nameTxt.setBackground(new Color(34,34,34,179));
        nameTxt.setForeground(Color.white);
        
        nameLabel =new JLabel("Full name:");
        nameLabel.setForeground(Color.white);

        add(nameLabel);
        add(nameTxt);
        add(Box.createVerticalStrut(10));

        usernameTxt = new JTextField();
        usernameTxt.setPreferredSize(new Dimension(300, 30));
        usernameTxt.setOpaque(false);
        usernameTxt.setBackground(new Color(34,34,34,179));
        usernameTxt.setForeground(Color.white);

        usernameLabel=new JLabel("Username:");
        usernameLabel.setForeground(Color.white);

        add(usernameLabel);
        add(usernameTxt);
        add(Box.createVerticalStrut(10));

        emailTxt = new JTextField();
        emailTxt.setPreferredSize(new Dimension(300, 30));
        emailTxt.setOpaque(false);
        emailTxt.setBackground(new Color(34,34,34,179));
        emailTxt.setForeground(Color.white);

        emailLabel=new JLabel("Email:");
        emailLabel.setForeground(Color.white);
        add(emailLabel);
        add(emailTxt);
        add(Box.createVerticalStrut(10));

        phoneTxt = new JTextField();
        phoneTxt.setPreferredSize(new Dimension(300, 30));
        phoneTxt.setOpaque(false);
        phoneTxt.setBackground(new Color(34,34,34,179));
        phoneTxt.setForeground(Color.white);

        phoneLabel=new JLabel("Phone Number:");
        phoneLabel.setForeground(Color.white);

        add(phoneLabel);
        add(phoneTxt);
        add(Box.createVerticalStrut(10));

        passwordTxt = new JPasswordField();
        passwordTxt.setPreferredSize(new Dimension(300, 30));
        passwordTxt.setOpaque(false);
        passwordTxt.setBackground(new Color(34,34,34,179));
        passwordTxt.setForeground(Color.white);

        passwordLabel=new JLabel("Password:");
        passwordLabel.setForeground(Color.white);

        add(passwordLabel);
        add(passwordTxt);
        add(Box.createVerticalStrut(10));

        confirmPasswordTxt = new JPasswordField();
        confirmPasswordTxt.setPreferredSize(new Dimension(300, 30));
        confirmPasswordTxt.setOpaque(false);
        confirmPasswordTxt.setBackground(new Color(34,34,34,179));
        confirmPasswordTxt.setForeground(Color.white);

        confirmPasswordLabel=new JLabel("Confirm Password:");
        confirmPasswordLabel.setForeground(Color.white);
        
        add(confirmPasswordLabel);
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
                register register=new register(name,username,email,phone,password,confirm);
                if(register.getRegister()){
                    JOptionPane.showMessageDialog(null, "Registered successfully!");
                    cardLayout.show(mainPanel, "login");
                }
            }
        });
    }
}
