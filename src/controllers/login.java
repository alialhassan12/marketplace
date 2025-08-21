package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import app.config;
import app.home;
import app.AdminPage;

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
            String query = "SELECT client_id, role, name, is_approved FROM Client WHERE username='" + this.username + "' AND password_hash='" + this.password + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                int clientId = rs.getInt("client_id");
                String role = rs.getString("role");
                String name = rs.getString("name");
                boolean isApproved = rs.getBoolean("is_approved");

                // Check if client is approved (only for clients, not admins)
                if (!"admin".equalsIgnoreCase(role) && !isApproved) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Your account is pending approval. Please wait for admin approval before signing in.",
                            "Account Pending Approval",
                            JOptionPane.WARNING_MESSAGE);
                    return false;
                }

                if ("admin".equalsIgnoreCase(role)) {
                    // Open admin home - now passing both clientId and name
                    AdminPage adminPage = new AdminPage(clientId, name);
                    adminPage.setVisible(true);
                    adminPage.setResizable(false);
                    adminPage.setLocationRelativeTo(null);
                    adminPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    // Open normal client home (only if approved)
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