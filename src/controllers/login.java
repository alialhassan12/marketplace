package controllers;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import app.config; 
import app.home;

public class login {
    private String username;
    private String password;
    public login(String username,String password){
        this.username=username;
        this.password=password;
    }
    public boolean getLogin(){
        Connection connect = config.getConnection();
        
        try {
                Statement stmt = connect.createStatement();
                String query = "SELECT client_id FROM Client WHERE username='" + this.username + "' AND password_hash='"
                        + this.password+ "'";
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    home home = new home(rs.getInt("client_id"));
                    home.setVisible(true);
                    home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    return true;
                    // SwingUtilities.getWindowAncestor(loginPage.this).setVisible(false);
                } else {

                    JOptionPane.showMessageDialog(null, "Invalid credentials!");
                    return false;
                }
                } catch (Exception ex) {
                    System.out.println("Login error: " + ex.getMessage());
                    return false;
                }

    }
}
