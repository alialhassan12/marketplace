import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
public class login extends JFrame{
    Connection connect=null;
    JLabel title;
    JLabel nameLabel;
    JLabel emailLabel;
    JLabel phoneLabel;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JLabel confirmPasswordLabel;

    JTextField nameTxt;
    JTextField emailTxt;
    JTextField phoneTxt;
    JTextField registerUsernameTxt;
    JTextField loginUsernameTxt;
    
    JPasswordField registerPasswordTxt;
    JPasswordField loginPasswordTxt;
    JPasswordField confirmPasswordTxt;

    JButton toRegister;
    JButton toLogin;
    JButton loginBtn;
    JButton registerBtn;
    JPanel loginPanel;
    JPanel registerPanel;
    login(){
        usernameLabel =new JLabel("Username: ");
        passwordLabel =new JLabel("Password: ");
        loginUsernameTxt=new JTextField();
        loginPasswordTxt=new JPasswordField();
        loginBtn=new JButton("Login");
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        toRegister=new JButton("dont have an account?");
        toRegister.setForeground(Color.BLUE);
        toRegister.setContentAreaFilled(false); //remove button background
        toRegister.setBorderPainted(false);//remove border
        toRegister.setFocusPainted(false);//remove focus highlight
        toRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //-------login panel setup
        loginPanel=new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));//simple layout to list items vertically
        title=new JLabel("Login");
        title.setFont(new Font("Arial",Font.BOLD,24));
        // loginPanel.setPreferredSize(new Dimension(300,300));
        loginUsernameTxt.setPreferredSize(new Dimension(300,30));
        loginPasswordTxt.setPreferredSize(new Dimension(300,30));
        // loginPanel.setBorder(BorderFactory.createLineBorder(Color.black,));
        loginPanel.add(title);
        loginPanel.add(Box.createVerticalStrut(20));//add a gap of 20px in BoxLayout
        loginPanel.add(usernameLabel);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(loginUsernameTxt);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(passwordLabel);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(loginPasswordTxt);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(loginBtn);
        loginPanel.add(toRegister);

        //------register panel setup
        registerPanel=new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        
        nameLabel=new JLabel("Full Name: ");
        nameTxt=new JTextField();
        nameTxt.setPreferredSize(new Dimension(300,30));

        usernameLabel=new JLabel("UserName: ");
        registerUsernameTxt=new JTextField();
        registerUsernameTxt.setPreferredSize(new Dimension(300,30));

        emailLabel=new JLabel("Email");
        emailTxt=new JTextField();
        emailTxt.setPreferredSize(new Dimension(300,30));

        phoneLabel=new JLabel("Phone Number:");
        phoneTxt=new JTextField();
        phoneTxt.setPreferredSize(new Dimension(300,30));

        passwordLabel=new JLabel("Password");
        registerPasswordTxt=new JPasswordField();
        registerPasswordTxt.setPreferredSize(new Dimension(300,30));

        confirmPasswordLabel=new JLabel("Confirm Password");
        confirmPasswordTxt=new JPasswordField();
        confirmPasswordTxt.setPreferredSize(new Dimension(300,30));

        title=new JLabel("Register");
        title.setFont(new Font("Arial",Font.BOLD,24));
        registerPanel.add(title);
        registerPanel.add(Box.createVerticalStrut(10));
        registerPanel.setVisible(false);

        registerPanel.add(nameLabel);
        registerPanel.add(Box.createVerticalStrut(10));
        registerPanel.add(nameTxt);
        registerPanel.add(Box.createVerticalStrut(20));

        registerPanel.add(usernameLabel);
        registerPanel.add(Box.createVerticalStrut(10));
        registerPanel.add(registerUsernameTxt);
        registerPanel.add(Box.createVerticalStrut(20));

        registerPanel.add(emailLabel);
        registerPanel.add(Box.createVerticalStrut(10));
        registerPanel.add(emailTxt);
        registerPanel.add(Box.createVerticalStrut(20));

        registerPanel.add(phoneLabel);
        registerPanel.add(Box.createVerticalStrut(10));
        registerPanel.add(phoneTxt);
        registerPanel.add(Box.createVerticalStrut(20));

        registerPanel.add(passwordLabel);
        registerPanel.add(Box.createVerticalStrut(10));
        registerPanel.add(registerPasswordTxt);
        registerPanel.add(Box.createVerticalStrut(20));
        
        registerPanel.add(confirmPasswordLabel);
        registerPanel.add(Box.createVerticalStrut(10));
        registerPanel.add(confirmPasswordTxt);
        registerPanel.add(Box.createVerticalStrut(20));

        registerBtn=new JButton("Regitser");
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerPanel.add(registerBtn);
        registerPanel.add(Box.createVerticalStrut(20));

        toLogin=new JButton("Already have an account?");
        toLogin.setForeground(Color.BLUE);
        toLogin.setContentAreaFilled(false); //remove button background
        toLogin.setBorderPainted(false);//remove border
        toLogin.setFocusPainted(false);//remove focus highlight
        toLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerPanel.add(toLogin);

        setLayout(new GridBagLayout());
        add(loginPanel);
        add(registerPanel);
        pack();

        getRootPane().setDefaultButton(loginBtn);

        //-------actions
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                connect=config.getConnection();
                String username=loginUsernameTxt.getText();
                String password=new String(loginPasswordTxt.getPassword());
                String hashedPass=new hashPassword().hashPasswords(password);
                String query="Select  client_id, username,password_hash From Client where username ='"+username+"' and password_hash='"+hashedPass+"'";
                try{
                    Statement stmt=connect.createStatement();
                    ResultSet rs=stmt.executeQuery(query);
                    if(rs.next()){
                        home home=new home(rs.getInt("client_id"));
                        home.setVisible(true);
                        setVisible(false);
                        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        home.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        home.setLocationRelativeTo(null);
                    }else{
                        if(username.equals("") || password.equals("")){
                            new error("Please Provide Credentials");
                        }else{
                            new error("Check username or password");
                            loginUsernameTxt.setText("");
                            loginPasswordTxt.setText("");
                        }
                    }
                }catch(Exception ex){
                    System.out.println("Error from login"+ex.getMessage());
                }
            }
        });

        registerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Connection connect=config.getConnection();
                String name=nameTxt.getText();
                String username=registerUsernameTxt.getText();
                String email=emailTxt.getText();
                String phone =phoneTxt.getText();
                String password=new String(registerPasswordTxt.getPassword());
                String confirmPassword=new String(confirmPasswordTxt.getPassword());

                if(!password.equals(confirmPassword)){
                    new error("Passwords dont match");
                    return;
                }
                if(name.equals("") || username.equals("") || email.equals("") ||phone.equals("") ||password.equals("") ||confirmPassword.equals("")){
                    new error("Please fill all fields");
                    return;
                }

                try{
                    hashPassword hashPassword=new hashPassword();
                    String query="Insert Into Client(name,email,phone,password_hash,username) Values('"+name+"','"+email+"','"+phone+"','"+hashPassword.hashPasswords(password)+"','"+username+"');";
                    Statement stmt=connect.createStatement();
                    stmt.execute(query);
                    registerPanel.setVisible(false);
                    loginPanel.setVisible(true);
                }catch(Exception ex){
                    System.out.println("Error From Register: "+ex.getMessage());
                }
            }
        });

        toRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                loginPanel.setVisible(false);
                registerPanel.setVisible(true);
            }
        });

        toLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                loginPanel.setVisible(true);
                registerPanel.setVisible(false);
            }
        });

    }
}
