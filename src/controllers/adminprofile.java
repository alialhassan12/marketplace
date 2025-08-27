package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.concurrent.CompletableFuture;
import javax.swing.JOptionPane;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import app.config;

public class adminprofile {
    private int client_id;
    Connection connect = config.getConnection();

    public adminprofile(int client_id) {
        this.client_id = client_id;
    }

    public CompletableFuture<ResultSet> getClientInfo() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String query = "SELECT * FROM client WHERE client_id = ?";
                PreparedStatement stmt = connect.prepareStatement(query);
                stmt.setInt(1, this.client_id);
                ResultSet rs = stmt.executeQuery();
                return rs;
            } catch (SQLException e) {
                System.out.println("Error getting client Info " + e.getMessage());
                return null;
            }
        });
    }

    public boolean updateProfile(String name, String username, String email, String location, String phone) {
        if (name.trim().isEmpty() || username.trim().isEmpty() || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name, Username, and Email are required");
            return false;
        }

        try {
            String query = "UPDATE client SET name = ?, username = ?, email = ?, location = ?, phone = ? WHERE client_id = ?";
            PreparedStatement stmt = connect.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, username);
            stmt.setString(3, email);
            stmt.setString(4, location);
            stmt.setString(5, phone);
            stmt.setInt(6, this.client_id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updating Profile: " + e.getMessage());
            return false;
        }
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        try {
            String verifyQuery = "SELECT password_hash FROM client WHERE client_id = ?";
            PreparedStatement verifyStmt = connect.prepareStatement(verifyQuery);
            verifyStmt.setInt(1, this.client_id);
            ResultSet rs = verifyStmt.executeQuery();

            if (rs.next()) {
                String currentHash = rs.getString("password_hash");
                if (!oldPassword.equals(currentHash)) {
                    return false;
                }

                String updateQuery = "UPDATE client SET password_hash = ? WHERE client_id = ?";
                PreparedStatement updateStmt = connect.prepareStatement(updateQuery);
                updateStmt.setString(1, newPassword);
                updateStmt.setInt(2, this.client_id);

                int rowsAffected = updateStmt.executeUpdate();
                return rowsAffected > 0;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error changing password: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteAccount() {
        String query = "DELETE FROM client WHERE client_id = ?";
        try {
            PreparedStatement stmt = connect.prepareStatement(query);
            stmt.setInt(1, this.client_id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }

    private static final String BASE_UPLOAD_DIR = "src/layout/uploads/profilePic/";

    private String resizeImage(String inputPath, String outputPath, String ext) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(inputPath));

            // Ensure parent dir exists
            File outputFile = new File(outputPath);
            outputFile.getParentFile().mkdirs();

            // Create 124x124 image
            BufferedImage resizedImage = new BufferedImage(124, 124, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage.getScaledInstance(124, 124, Image.SCALE_SMOOTH), 0, 0, null);
            g.dispose();

            // Save with the same extension
            ImageIO.write(resizedImage, ext, outputFile);

            return outputFile.getAbsolutePath();
        } catch (Exception e) {
            System.out.println("Error resizing image: " + e.getMessage());
            return null;
        }
    }

    private String getFileExtension(String filePath) {
        String ext = null;
        int i = filePath.lastIndexOf('.');
        if (i > 0 && i < filePath.length() - 1) {
            ext = filePath.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    public boolean profilePic(String inputPath) {
        try {
            // Get original extension (.jpg, .png, etc.)
            String ext = getFileExtension(inputPath);
            if (ext == null) {
                ext = "jpg"; // fallback
            }

            // Build output file path inside src/layout/uploads/profilePic/
            String fileName = "profile_" + this.client_id + "." + ext;
            String outputPath = BASE_UPLOAD_DIR + fileName;

            // Ensure directory exists
            File dir = new File(BASE_UPLOAD_DIR);
            dir.mkdirs();

            // Resize and save
            String resizedPath = resizeImage(inputPath, outputPath, ext);

            if (resizedPath == null) {
                return false;
            }

            // Save the src/... path in DB
            String dbPath = "src/layout/uploads/profilePic/" + fileName;

            String query = "UPDATE client SET profile_image = ? WHERE client_id = ?";
            PreparedStatement stmt = connect.prepareStatement(query);
            stmt.setString(1, dbPath);
            stmt.setInt(2, this.client_id);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updating profile pic: " + e.getMessage());
            return false;
        }
    }

    public CompletableFuture<ResultSet> getMyListingsCars(int client_id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String query = "SELECT * FROM car c " +
                        "JOIN carimage ci ON c.car_id = ci.car_id " +
                        "WHERE owner_id = ? AND is_primary = true";
                PreparedStatement stmt = connect.prepareStatement(query);
                stmt.setInt(1, client_id);
                ResultSet rs = stmt.executeQuery();
                return rs;
            } catch (SQLException e) {
                System.out.println("Error getting primary images for latest Cars " + e.getMessage());
                return null;
            }
        });
    }
}
