package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import controllers.adminprofile; // Fixed import - should be lowercase 'profile'
import java.sql.SQLException;

/**
 * Combined User Profile Management System
 * @author samih
 */
public class ProfilePanelsami extends JFrame {
    
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private int clientId;
    private adminprofile profileController;
    
    // Profile View Panel Components
    private JLabel profileImageLabel;
    private JLabel fullNameLabel;
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JLabel locationLabel;
    private JLabel phoneLabel;
    private JLabel createdAtLabel;
    
    // Edit Panel Components
    private JTextField fullNameField;
    private JTextField usernameField;
    private JTextField emailField;
    private JTextField locationField;
    private JTextField phoneField;
    private JFileChooser fileChooser;
    private String selectedImagePath;
    private JLabel editImageLabel;
    
    // UI Component references for easier access
    private JButton editButton;
    private JButton deleteButton;
    private JButton addEditPictureButton;
    private JButton changePasswordButton;
    private JButton applyButton;
    private JButton cancelEditButton;
    
    public ProfilePanelsami() {
        initializeFrame();
        createPanels();
        setupNavigation();
    }

    public ProfilePanelsami(int clientId) {
        this.clientId = clientId;
        this.profileController = new adminprofile(clientId);
        initializeFrame();
        createPanels();
        setupNavigation();
        loadProfile(clientId);
    }
    
    private void initializeFrame() {
        setTitle("User Profile Manager");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Better than EXIT_ON_CLOSE
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void createPanels() {
        // Create panels
        JPanel profileViewPanel = createProfileViewPanel();
        JPanel editProfilePanel = createEditProfilePanel();

        // Add panels to card layout
        mainPanel.add(profileViewPanel, "PROFILE_VIEW");
        mainPanel.add(editProfilePanel, "EDIT_PROFILE");
        
        // Show profile view by default
        cardLayout.show(mainPanel, "PROFILE_VIEW");
    }
    
    private JPanel createProfileHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(24, 24, 24));
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        JLabel titleLabel = new JLabel("Profile Information");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        JButton backButton = new JButton("← Back to Dashboard");
        backButton.setBackground(new Color(0, 102, 255));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(180, 35));
        backButton.addActionListener(e -> {
            this.dispose(); // Close the profile frame
        });

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createHorizontalStrut(300));
        headerPanel.add(backButton);

        return headerPanel;
    }

    private JPanel createProfileViewPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(52, 52, 52));

        // Header Panel
        JPanel headerPanel = createProfileHeaderPanel();
        panel.add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(52, 52, 52));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Initialize components
        profileImageLabel = new JLabel("No Profile Picture");
        profileImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profileImageLabel.setVerticalAlignment(SwingConstants.CENTER);
        profileImageLabel.setForeground(Color.WHITE);
        profileImageLabel.setPreferredSize(new Dimension(200, 200));
        
        // Create and style labels
        JLabel[] labelTexts = {
            new JLabel("Full Name:"),
            new JLabel("Username:"),
            new JLabel("Email:"),
            new JLabel("Location:"),
            new JLabel("Phone:"),
            new JLabel("Created At:")
        };
        
        for (JLabel label : labelTexts) {
            label.setFont(new Font("Dialog", Font.PLAIN, 18));
            label.setForeground(Color.WHITE);
        }
        
        // Data labels (placeholders until DB load)
        fullNameLabel = createDataLabel("Loading...");
        usernameLabel = createDataLabel("Loading...");
        emailLabel = createDataLabel("Loading...");
        locationLabel = createDataLabel("Loading...");
        phoneLabel = createDataLabel("Loading...");
        createdAtLabel = createDataLabel("Loading...");
        
        editButton = new JButton("Edit Profile");
        editButton.setBackground(new Color(0, 102, 255));
        editButton.setForeground(Color.WHITE);
        editButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        editButton.setFocusPainted(false);
        editButton.setBorderPainted(false);
        editButton.setPreferredSize(new Dimension(120, 35));
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        deleteButton = new JButton("Delete Account");
        deleteButton.setBackground(new Color(220, 20, 60));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorderPainted(false);
        deleteButton.setPreferredSize(new Dimension(140, 35));
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Layout using GroupLayout (keeping your existing layout structure)
        GroupLayout layout = new GroupLayout(contentPanel);
        contentPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(profileImageLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelTexts[0], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelTexts[1], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelTexts[2], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelTexts[3], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelTexts[4], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelTexts[5], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(30)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fullNameLabel)
                        .addComponent(usernameLabel)
                        .addComponent(emailLabel)
                        .addComponent(locationLabel)
                        .addComponent(phoneLabel)
                        .addComponent(createdAtLabel)))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(editButton)
                    .addGap(10)
                    .addComponent(deleteButton))
        );
        
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(profileImageLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTexts[0])
                    .addComponent(fullNameLabel))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTexts[1])
                    .addComponent(usernameLabel))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTexts[2])
                    .addComponent(emailLabel))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTexts[3])
                    .addComponent(locationLabel))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTexts[4])
                    .addComponent(phoneLabel))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTexts[5])
                    .addComponent(createdAtLabel))
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton)
                    .addComponent(deleteButton))
        );
        
        panel.add(contentPanel, BorderLayout.CENTER);
        return panel;
    }
    
    private JLabel createDataLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Dialog", Font.PLAIN, 18));
        label.setForeground(Color.WHITE);
        return label;
    }
    
    private JPanel createEditHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(24, 24, 24));
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        JLabel titleLabel = new JLabel("Edit Profile");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        headerPanel.add(titleLabel);

        return headerPanel;
    }

    private JPanel createEditProfilePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(52, 52, 52));

        // Header Panel
        JPanel headerPanel = createEditHeaderPanel();
        panel.add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(52, 52, 52));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Initialize components
        editImageLabel = new JLabel("No Profile Picture");
        editImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        editImageLabel.setVerticalAlignment(SwingConstants.CENTER);
        editImageLabel.setForeground(Color.WHITE);
        editImageLabel.setPreferredSize(new Dimension(150, 150));
        
        // Create labels
        JLabel[] labelTexts = {
            new JLabel("Full Name:"),
            new JLabel("Username:"),
            new JLabel("Email:"),
            new JLabel("Location:"),
            new JLabel("Phone:")
        };
        
        for (JLabel label : labelTexts) {
            label.setFont(new Font("Dialog", Font.PLAIN, 18));
            label.setForeground(Color.WHITE);
        }
        
        // Text fields
        fullNameField = createTextField();
        usernameField = createTextField();
        emailField = createTextField();
        locationField = createTextField();
        phoneField = createTextField();
        
        // Buttons
        addEditPictureButton = new JButton("Add/Edit Profile Picture");
        addEditPictureButton.setBackground(new Color(0, 150, 0));
        addEditPictureButton.setForeground(Color.WHITE);
        addEditPictureButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        addEditPictureButton.setFocusPainted(false);
        addEditPictureButton.setBorderPainted(false);
        addEditPictureButton.setPreferredSize(new Dimension(200, 35));
        addEditPictureButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBackground(new Color(255, 204, 0));
        changePasswordButton.setForeground(Color.WHITE);
        changePasswordButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        changePasswordButton.setFocusPainted(false);
        changePasswordButton.setBorderPainted(false);
        changePasswordButton.setPreferredSize(new Dimension(150, 35));
        changePasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        applyButton = new JButton("Apply");
        applyButton.setBackground(new Color(0, 150, 0));
        applyButton.setForeground(Color.WHITE);
        applyButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        applyButton.setFocusPainted(false);
        applyButton.setBorderPainted(false);
        applyButton.setPreferredSize(new Dimension(80, 35));
        applyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cancelEditButton = new JButton("Cancel");
        cancelEditButton.setBackground(new Color(128, 128, 128));
        cancelEditButton.setForeground(Color.WHITE);
        cancelEditButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        cancelEditButton.setFocusPainted(false);
        cancelEditButton.setBorderPainted(false);
        cancelEditButton.setPreferredSize(new Dimension(80, 35));
        cancelEditButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Initialize file chooser
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Image files", "jpg", "jpeg", "png", "gif", "bmp"));
        
        // Layout (keeping your existing structure)
        GroupLayout layout = new GroupLayout(contentPanel);
        contentPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(editImageLabel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addComponent(addEditPictureButton)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelTexts[0], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelTexts[1], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelTexts[2], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelTexts[3], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelTexts[4], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fullNameField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(emailField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(locationField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(phoneField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
                .addComponent(changePasswordButton)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(applyButton)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cancelEditButton))
        );
        
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(editImageLabel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(addEditPictureButton)
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTexts[0])
                    .addComponent(fullNameField))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTexts[1])
                    .addComponent(usernameField))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTexts[2])
                    .addComponent(emailField))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTexts[3])
                    .addComponent(locationField))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTexts[4])
                    .addComponent(phoneField))
                .addGap(20)
                .addComponent(changePasswordButton)
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(applyButton)
                    .addComponent(cancelEditButton))
        );
        
        panel.add(contentPanel, BorderLayout.CENTER);
        return panel;
    }


    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setBackground(new Color(40, 40, 40));
        field.setForeground(Color.WHITE);
        field.setFont(new Font("Dialog", Font.PLAIN, 16));
        return field;
    }

    
    

    public void loadProfile(int clientId) {
        if (profileController == null) {
            profileController = new adminprofile(clientId);
        }
        
        profileController.getClientInfo().thenAccept(result -> {
            SwingUtilities.invokeLater(() -> {
                try {
                    if (result != null && result.next()) {
                        // Map database fields to UI labels
                        String name = result.getString("name");
                        fullNameLabel.setText(name != null ? name : "Unknown");
                        
                        String username = result.getString("username");
                        if (username == null) {
                            username = result.getString("name");
                        }
                        usernameLabel.setText(username != null ? username : "Unknown");
                        
                        emailLabel.setText(result.getString("email"));
                        phoneLabel.setText(result.getString("phone"));
                        
                        String location = result.getString("location");
                        locationLabel.setText(location == null ? "Unknown" : location);
                        
                        String createdAt = result.getString("created_at");
                        createdAtLabel.setText("Account Created At: " + (createdAt != null ? createdAt : "Unknown"));
                        
                        // Handle profile image
                        String profileImagePath = result.getString("profile_image");
                        loadProfileImage(profileImagePath);
                        
                        // Populate edit fields
                        fullNameField.setText(name != null ? name : "");
                        usernameField.setText(username != null ? username : "");
                        emailField.setText(result.getString("email"));
                        phoneField.setText(result.getString("phone"));
                        locationField.setText(location != null ? location : "");
                        
                    } else {
                        setErrorLabels("No data found");
                    }
                } catch (SQLException e) {
                    System.out.println("Error reading result set: " + e.getMessage());
                    setErrorLabels("Error loading");
                }
            });
        }).exceptionally(throwable -> {
            SwingUtilities.invokeLater(() -> setErrorLabels("Database error"));
            System.out.println("Error loading profile: " + throwable.getMessage());
            return null;
        });
    }
    
    private void loadProfileImage(String profileImagePath) {
        if (profileImagePath == null) {
            profileImageLabel.setText("No Profile Picture");
            profileImageLabel.setIcon(null);
            editImageLabel.setText("No Profile Picture");
            editImageLabel.setIcon(null);
        } else {
            File imageFile = new File(profileImagePath);
            if (imageFile.exists()) {
                try {
                    ImageIcon profilePic = new ImageIcon(imageFile.getAbsolutePath());
                    Image profileImage = profilePic.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                    profileImageLabel.setIcon(new ImageIcon(profileImage));
                    profileImageLabel.setText("");
                    
                    Image editImage = profilePic.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    editImageLabel.setIcon(new ImageIcon(editImage));
                    editImageLabel.setText("");
                } catch (Exception e) {
                    profileImageLabel.setText("Image error");
                    editImageLabel.setText("Image error");
                    System.out.println("Error loading image: " + e.getMessage());
                }
            } else {
                profileImageLabel.setText("Image not found");
                editImageLabel.setText("Image not found");
            }
        }
    }
    
    private void setErrorLabels(String errorMessage) {
        fullNameLabel.setText(errorMessage);
        usernameLabel.setText(errorMessage);
        emailLabel.setText(errorMessage);
        phoneLabel.setText(errorMessage);
        locationLabel.setText(errorMessage);
        createdAtLabel.setText(errorMessage);
    }
    
    private void setupNavigation() {
        // Edit Profile button
        editButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "EDIT_PROFILE");
        });
        
        // Delete Account button
        deleteButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                ProfilePanelsami.this,
                "Are you sure you want to delete your account? This action cannot be undone.",
                "Confirm Delete Account",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            if (result == JOptionPane.YES_OPTION) {
                if (profileController.deleteAccount()) {
                    setVisible(false);
                    // Return to login frame
                    try {
                        LoginFrame login = new LoginFrame();
                        login.setSize(1280, 750);
                        login.setResizable(false);
                        login.setVisible(true);
                        login.setLocationRelativeTo(null);
                    } catch (Exception ex) {
                        System.out.println("Error opening login frame: " + ex.getMessage());
                        System.exit(0);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete account!");
                }
            }
        });
        
        // Change Password button
        changePasswordButton.addActionListener(e -> {
            changePasswordFrame frame = new changePasswordFrame(clientId);
            frame.setVisible(true);
        });
        
        // Add/Edit Profile Picture button
        addEditPictureButton.addActionListener(e -> {
            JFileChooser image = new JFileChooser();
            int choice = image.showOpenDialog(null);
            if (choice == JFileChooser.APPROVE_OPTION) {
                File selected = image.getSelectedFile();
                File uploadDir = new File("src/layout/uploads/profilePic");
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                String newFileName = "user_" + clientId + "_" + selected.getName();
                Path destination = Paths.get(uploadDir.getAbsolutePath(), newFileName);
                String relativePath = "src/layout/uploads/profilePic/" + newFileName;
                
                try {
                    Files.copy(selected.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                
                System.out.println("Image saved to: " + relativePath);
                if (profileController.profilePic(relativePath)) {
                    JOptionPane.showMessageDialog(null, "Profile picture uploaded!");
                    showProfileImage(selected);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update profile picture in database!");
                }
            }
        });
        
        // Apply button (in edit profile)
        applyButton.addActionListener(e -> {
            String newName = fullNameField.getText().trim();
            String newUsername = usernameField.getText().trim();
            String newEmail = emailField.getText().trim();
            String newLocation = locationField.getText().trim();
            String newPhone = phoneField.getText().trim();
            
            // Validate inputs
            if (newName.isEmpty() || newUsername.isEmpty() || newEmail.isEmpty()) {
                JOptionPane.showMessageDialog(ProfilePanelsami.this, "Name, Username, and Email are required!");
                return;
            }
            
            // Email validation
            if (!newEmail.contains("@") || !newEmail.contains(".")) {
                JOptionPane.showMessageDialog(ProfilePanelsami.this, "Please enter a valid email address!");
                return;
            }
            
            // Update database using the profile controller
            try {
                if (profileController.updateProfile(newName, newUsername, newEmail, newLocation, newPhone)) {
                    // Update UI labels on success
                    fullNameLabel.setText(newName);
                    usernameLabel.setText(newUsername);
                    emailLabel.setText(newEmail);
                    locationLabel.setText(newLocation.isEmpty() ? "Unknown" : newLocation);
                    phoneLabel.setText(newPhone.isEmpty() ? "Not provided" : newPhone);

                    JOptionPane.showMessageDialog(ProfilePanelsami.this, "Profile updated successfully!");
                    cardLayout.show(mainPanel, "PROFILE_VIEW");
                } else {
                    JOptionPane.showMessageDialog(ProfilePanelsami.this, "Failed to update profile. Please try again.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ProfilePanelsami.this, "Database error: " + ex.getMessage());
            }
        });
        
        // Cancel button (in edit profile)
        cancelEditButton.addActionListener(e -> {
            // Reload original data
            loadProfile(clientId);
            cardLayout.show(mainPanel, "PROFILE_VIEW");
        });

        
    }
    
    private void showProfileImage(File imageFile) {
    System.out.println("Showing profile image from file: " + imageFile.getAbsolutePath());
    System.out.println("File exists: " + imageFile.exists());
    System.out.println("File size: " + imageFile.length() + " bytes");
    
    try {
        ImageIcon profilePic = new ImageIcon(imageFile.getAbsolutePath());
        System.out.println("Original image dimensions: " + profilePic.getIconWidth() + "x" + profilePic.getIconHeight());
        
        if (profilePic.getIconWidth() == -1) {
            System.out.println("Failed to load image - invalid format or corrupted file");
            return;
        }
        
        Image profileImage = profilePic.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        Image editImage = profilePic.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        
        ImageIcon profileIcon = new ImageIcon(profileImage);
        ImageIcon editIcon = new ImageIcon(editImage);
        
        profileImageLabel.setIcon(profileIcon);
        profileImageLabel.setText("");
        profileImageLabel.revalidate();
        profileImageLabel.repaint();
        
        editImageLabel.setIcon(editIcon);
        editImageLabel.setText("");
        editImageLabel.revalidate();
        editImageLabel.repaint();
        
        System.out.println("Image display updated successfully");
        
    } catch (Exception e) {
        System.out.println("Exception in showProfileImage: " + e.getMessage());
        e.printStackTrace();
    }
}
    
    
}