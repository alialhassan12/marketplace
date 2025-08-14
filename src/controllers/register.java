package controllers;
import app.config;
import app.hashPassword;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class register {
    private String name;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String confirmPassword;

    public register(String name,String username,String email,String phone,String password,String confirmPassword){
        this.name=name;
        this.username=username;
        this.email=email;
        this.phone=phone;
        this.password=password;
        this.confirmPassword=confirmPassword;
    }
    public boolean getRegister(){
        Connection connect = config.getConnection();
        if (!this.password.equals(this.confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords don't match!");
                    return false;
                }
                if (this.name.isEmpty() || this.username.isEmpty() || this.email.isEmpty() || this.phone.isEmpty() || this.password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!");
                    return false;
                }
                try {
                    String query = "INSERT INTO Client(name,email,phone,password_hash,username) VALUES('" + this.name + "','"
                            + this.email + "','" + this.phone + "','" + new hashPassword().hashPasswords(this.password) + "','"
                            + this.username + "')";
                    Statement stmt = connect.createStatement();
                    stmt.execute(query);
                    return true;
                } catch (Exception ex) {
                    System.out.println("Register error: " + ex.getMessage());
                    return false;
                }
    }
}
