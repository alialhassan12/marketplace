package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import app.config;
import app.home;
import app.adminhome;

public class login {
    private String username;
    private String password;

    public login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean getLogin() {
        Connection connect = config.getConnection();

        try {
            Statement stmt = connect.createStatement();
            String query = "SELECT client_id, role ,name FROM Client WHERE username='" + this.username + "' AND password_hash='" + this.password + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                int clientId = rs.getInt("client_id");
                String role = rs.getString("role");
                String name=rs.getString("name");

                if ("admin".equalsIgnoreCase(role)) {
                    // Open admin home
                    adminhome adminPage = new adminhome(clientId);
                    adminPage.setVisible(true);
                    adminPage.setLocationRelativeTo(null);
                    adminPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    // Open normal client home
                    home clientHome = new home(clientId);
                    clientHome.setVisible(true);
                    clientHome.setSize(1280, 750);
                    clientHome.setLocationRelativeTo(null);
                    clientHome.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    clientHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }

                return true;

            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Invalid username or password. Please try again.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Login error: " + ex.getMessage());
            return false;
        }
    }
}
