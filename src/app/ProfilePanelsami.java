package app;

import javax.swing.*;
import java.awt.*;
// import java.awt.event.ActionEvent; // not used directly
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.SwingUtilities;
// DB imports are referenced with fully-qualified names in code; avoid unused imports

/**
 * Combined User Profile Management System
 * @author samih
 */
public class ProfilePanelsami extends JFrame {
    
    private JPanel mainPanel;
    private CardLayout cardLayout;
    
    // Profile View Panel Components
    private JLabel profileImageLabel;
    private JLabel fullNameLabel;
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JLabel locationLabel;
    private JLabel phoneLabel;
    private JLabel roleLabel;
    
    // Edit Panel Components
    private JTextField fullNameField;
    private JTextField usernameField;
    private JTextField locationField;
    private JTextField phoneField;
    private JFileChooser fileChooser;
    private String selectedImagePath;
    
    // Password Panel Components
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    
    public ProfilePanelsami() {
        initializeFrame();
        createPanels();
        setupNavigation();
    }

    public ProfilePanelsami(int clientId) {
        this();
        loadProfile(clientId);
    }
    
    private void initializeFrame() {
        setTitle("User Profile Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 450);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);
    }
    
    private void createPanels() {
        // Create all three panels
        JPanel profileViewPanel = createProfileViewPanel();
        JPanel editProfilePanel = createEditProfilePanel();
        JPanel changePasswordPanel = createChangePasswordPanel();
        
        // Add panels to card layout
        mainPanel.add(profileViewPanel, "PROFILE_VIEW");
        mainPanel.add(editProfilePanel, "EDIT_PROFILE");
        mainPanel.add(changePasswordPanel, "CHANGE_PASSWORD");
        
        // Show profile view by default
        cardLayout.show(mainPanel, "PROFILE_VIEW");
    }
    
    private JPanel createProfileViewPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(24, 24, 24));
        panel.setLayout(new GroupLayout(panel));
        
        // Initialize components
        profileImageLabel = new JLabel();
        profileImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profileImageLabel.setIcon(new ImageIcon("C:\\Users\\samih\\Desktop\\11zon_cropped.png"));
        
        JLabel fullNameLabelText = new JLabel("Full Name:");
        fullNameLabelText.setFont(new Font("Dialog", Font.PLAIN, 18));
        fullNameLabelText.setForeground(Color.WHITE);
        
        JLabel usernameLabelText = new JLabel("Username:");
        usernameLabelText.setFont(new Font("Dialog", Font.PLAIN, 18));
        usernameLabelText.setForeground(Color.WHITE);
        
        JLabel emailLabelText = new JLabel("Email:");
        emailLabelText.setFont(new Font("Dialog", Font.PLAIN, 18));
        emailLabelText.setForeground(Color.WHITE);
        
        JLabel locationLabelText = new JLabel("Location:");
        locationLabelText.setFont(new Font("Dialog", Font.PLAIN, 18));
        locationLabelText.setForeground(Color.WHITE);
        
        JLabel phoneLabelText = new JLabel("Phone:");
        phoneLabelText.setFont(new Font("Dialog", Font.PLAIN, 18));
        phoneLabelText.setForeground(Color.WHITE);
        
        JLabel roleLabelText = new JLabel("Role:");
        roleLabelText.setFont(new Font("Dialog", Font.PLAIN, 18));
        roleLabelText.setForeground(Color.WHITE);
        
    // Data labels (placeholders until DB load)
    fullNameLabel = new JLabel("Loading...");
        fullNameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        fullNameLabel.setForeground(Color.WHITE);
        
    usernameLabel = new JLabel("Loading...");
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        usernameLabel.setForeground(Color.WHITE);
        
    emailLabel = new JLabel("Loading...");
        emailLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        emailLabel.setForeground(Color.WHITE);
        
    locationLabel = new JLabel("Loading...");
        locationLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        locationLabel.setForeground(Color.WHITE);
        
    phoneLabel = new JLabel("Loading...");
        phoneLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        phoneLabel.setForeground(Color.WHITE);
        
    roleLabel = new JLabel("Loading...");
        roleLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        roleLabel.setForeground(Color.WHITE);
        
        JButton editButton = new JButton("Edit Account");
        editButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        
        JButton deleteButton = new JButton("Delete Account");
        deleteButton.setBackground(new Color(255, 0, 0));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        
        // Layout
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(profileImageLabel, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(fullNameLabelText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(usernameLabelText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(emailLabelText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(locationLabelText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(phoneLabelText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roleLabelText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(30)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fullNameLabel)
                        .addComponent(usernameLabel)
                        .addComponent(emailLabel)
                        .addComponent(locationLabel)
                        .addComponent(phoneLabel)
                        .addComponent(roleLabel)))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(editButton)
                    .addGap(10)
                    .addComponent(deleteButton))
        );
        
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(profileImageLabel)
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(fullNameLabelText)
                    .addComponent(fullNameLabel))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabelText)
                    .addComponent(usernameLabel))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabelText)
                    .addComponent(emailLabel))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(locationLabelText)
                    .addComponent(locationLabel))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneLabelText)
                    .addComponent(phoneLabel))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(roleLabelText)
                    .addComponent(roleLabel))
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton)
                    .addComponent(deleteButton))
        );
        
        return panel;
    }
    
    private JPanel createEditProfilePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(24, 24, 24));
        
        // Initialize components
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setIcon(new ImageIcon("C:\\Users\\samih\\Desktop\\11zon_cropped.png"));
        
        JLabel fullNameLabelText = new JLabel("Full Name:");
        fullNameLabelText.setFont(new Font("Dialog", Font.PLAIN, 18));
        fullNameLabelText.setForeground(Color.WHITE);
        
        JLabel usernameLabelText = new JLabel("Username:");
        usernameLabelText.setFont(new Font("Dialog", Font.PLAIN, 18));
        usernameLabelText.setForeground(Color.WHITE);
        
        JLabel locationLabelText = new JLabel("Location:");
        locationLabelText.setFont(new Font("Dialog", Font.PLAIN, 18));
        locationLabelText.setForeground(Color.WHITE);
        
        JLabel phoneLabelText = new JLabel("Phone:");
        phoneLabelText.setFont(new Font("Dialog", Font.PLAIN, 18));
        phoneLabelText.setForeground(Color.WHITE);
        
        // Text fields
        fullNameField = new JTextField("John Doe");
        fullNameField.setBackground(new Color(24, 24, 24));
        fullNameField.setForeground(Color.WHITE);
        fullNameField.setFont(new Font("Dialog", Font.PLAIN, 18));
        
        usernameField = new JTextField("johndoe123");
        usernameField.setBackground(new Color(24, 24, 24));
        usernameField.setForeground(Color.WHITE);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 18));
        
        locationField = new JTextField("New York, USA");
        locationField.setBackground(new Color(24, 24, 24));
        locationField.setForeground(Color.WHITE);
        locationField.setFont(new Font("Dialog", Font.PLAIN, 18));
        
        phoneField = new JTextField("+1234567890");
        phoneField.setBackground(new Color(24, 24, 24));
        phoneField.setForeground(Color.WHITE);
        phoneField.setFont(new Font("Dialog", Font.PLAIN, 18));
        
        // Buttons
        JButton addEditPictureButton = new JButton("Add/Edit Profile Picture");
        addEditPictureButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        
        JButton applyButton = new JButton("Apply");
        applyButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        
        // Initialize file chooser
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Image files", "jpg", "jpeg", "png", "gif", "bmp"));
        
        // Layout
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(addEditPictureButton))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(fullNameLabelText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(usernameLabelText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(locationLabelText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(phoneLabelText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fullNameField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(locationField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(phoneField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
                .addComponent(changePasswordButton)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(applyButton)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cancelButton))
        );
        
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(imageLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50)
                        .addComponent(addEditPictureButton)))
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(fullNameLabelText)
                    .addComponent(fullNameField))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabelText)
                    .addComponent(usernameField))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(locationLabelText)
                    .addComponent(locationField))
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneLabelText)
                    .addComponent(phoneField))
                .addGap(20)
                .addComponent(changePasswordButton)
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(applyButton)
                    .addComponent(cancelButton))
        );
        
        return panel;
    }
    
    private JPanel createChangePasswordPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(24, 24, 24));

        // Title
        JLabel titleLabel = new JLabel("Change Password");
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
        titleLabel.setForeground(Color.WHITE);

        // Labels
        JLabel oldPasswordLabel = new JLabel("Old Password:");
        oldPasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        oldPasswordLabel.setForeground(Color.WHITE);

        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        newPasswordLabel.setForeground(Color.WHITE);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        confirmPasswordLabel.setForeground(Color.WHITE);

        // Password fields
        oldPasswordField = new JPasswordField();
        oldPasswordField.setFont(new Font("Dialog", Font.PLAIN, 18));
        oldPasswordField.setBackground(new Color(40, 40, 40));
        oldPasswordField.setForeground(Color.WHITE);

        newPasswordField = new JPasswordField();
        newPasswordField.setFont(new Font("Dialog", Font.PLAIN, 18));
        newPasswordField.setBackground(new Color(40, 40, 40));
        newPasswordField.setForeground(Color.WHITE);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("Dialog", Font.PLAIN, 18));
        confirmPasswordField.setBackground(new Color(40, 40, 40));
        confirmPasswordField.setForeground(Color.WHITE);

        // Buttons
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Dialog", Font.PLAIN, 14));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Dialog", Font.PLAIN, 14));

        // Layout
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(titleLabel)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(oldPasswordLabel)
                        .addComponent(newPasswordLabel)
                        .addComponent(confirmPasswordLabel))
                    .addGap(20)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(oldPasswordField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(newPasswordField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(confirmPasswordField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(confirmButton)
                    .addGap(10)
                    .addComponent(cancelButton))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(oldPasswordLabel)
                    .addComponent(oldPasswordField))
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(newPasswordLabel)
                    .addComponent(newPasswordField))
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmPasswordLabel)
                    .addComponent(confirmPasswordField))
                .addGap(40)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmButton)
                    .addComponent(cancelButton))
        );

        return panel;
    }

    /**
     * Load profile values from DB for given client id and populate UI.
     */
    public void loadProfile(int clientId){
        // Run DB fetch asynchronously
        new Thread(()->{
            try{
                controllers.profile p = new controllers.profile(clientId);
                java.util.concurrent.CompletableFuture<java.sql.ResultSet> fut = p.getClientInfo();
                java.sql.ResultSet rs = fut.get();
                if(rs != null && rs.next()){
                    final String name = rs.getString("name");
                    final String username = rs.getString("username");
                    final String email = rs.getString("email");
                    final String phone = rs.getString("phone");
                    String location = rs.getString("location");
                    final String locFinal = (location==null||location.isEmpty())?"Unknown":location;
                    final String role = rs.getString("role");
                    final String profileImagePath = rs.getString("profile_image");

                    SwingUtilities.invokeLater(()->{
                        fullNameLabel.setText(name);
                        usernameLabel.setText(username);
                        emailLabel.setText(email);
                        phoneLabel.setText(phone);
                        locationLabel.setText(locFinal);
                        roleLabel.setText(role==null?"User":role);
                        if(profileImagePath != null && !profileImagePath.isEmpty()){
                            File imgFile = new File(profileImagePath);
                            if(imgFile.exists()){
                                ImageIcon ico = new ImageIcon(imgFile.getAbsolutePath());
                                Image img = ico.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                                profileImageLabel.setIcon(new ImageIcon(img));
                            }
                        }
                    });
                }
            }catch(Exception e){
                System.out.println("Error loading profile: "+e.getMessage());
            }
        }).start();
    }
    
    private void setupNavigation() {
        // Get components from panels to add listeners
        Component[] profileComponents = ((JPanel) mainPanel.getComponent(0)).getComponents();
        Component[] editComponents = ((JPanel) mainPanel.getComponent(1)).getComponents();
        Component[] passwordComponents = ((JPanel) mainPanel.getComponent(2)).getComponents();
        
        // Find and setup Edit Account button (in profile view)
        findAndSetupButton(profileComponents, "Edit Account", new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            // Copy current data to edit fields
            fullNameField.setText(fullNameLabel.getText());
            usernameField.setText(usernameLabel.getText());
            locationField.setText(locationLabel.getText());
            phoneField.setText(phoneLabel.getText());
            cardLayout.show(mainPanel, "EDIT_PROFILE");
            }
        });
        
        // Find and setup Delete Account button
        findAndSetupButton(profileComponents, "Delete Account", new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(
                    ProfilePanelsami.this,
                    "Are you sure you want to delete your account? This action cannot be undone.",
                    "Confirm Delete Account",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );
                if (result == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(ProfilePanelsami.this, "Account deleted successfully!");
                    System.exit(0);
                }
            }
        });
        
        // Find and setup Change Password button (in edit profile)
        findAndSetupButton(editComponents, "Change Password", new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cardLayout.show(mainPanel, "CHANGE_PASSWORD");
            }
        });
        
        // Find and setup Add/Edit Profile Picture button
        findAndSetupButton(editComponents, "Add/Edit Profile Picture", new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int result = fileChooser.showOpenDialog(ProfilePanelsami.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    selectedImagePath = selectedFile.getAbsolutePath();
                    JOptionPane.showMessageDialog(ProfilePanelsami.this, 
                        "Profile picture selected: " + selectedFile.getName());
                }
            }
        });
        
        // Find and setup Apply button (in edit profile)
        findAndSetupButton(editComponents, "Apply", new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Update profile data
                fullNameLabel.setText(fullNameField.getText());
                usernameLabel.setText(usernameField.getText());
                locationLabel.setText(locationField.getText());
                phoneLabel.setText(phoneField.getText());

                JOptionPane.showMessageDialog(ProfilePanelsami.this, "Profile updated successfully!");
                cardLayout.show(mainPanel, "PROFILE_VIEW");
            }
        });
        
        // Find and setup Cancel button (in edit profile)
        findAndSetupButton(editComponents, "Cancel", new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cardLayout.show(mainPanel, "PROFILE_VIEW");
            }
        });
        
        // Find and setup Confirm button (in change password)
        findAndSetupButton(passwordComponents, "Confirm", new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String oldPassword = new String(oldPasswordField.getPassword());
                String newPassword = new String(newPasswordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(ProfilePanelsami.this, "Please fill all password fields!");
                    return;
                }

                if (!newPassword.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(ProfilePanelsami.this, "New password and confirm password don't match!");
                    return;
                }

                if (newPassword.length() < 6) {
                    JOptionPane.showMessageDialog(ProfilePanelsami.this, "Password must be at least 6 characters long!");
                    return;
                }

                JOptionPane.showMessageDialog(ProfilePanelsami.this, "Password changed successfully!");

                // Clear password fields
                oldPasswordField.setText("");
                newPasswordField.setText("");
                confirmPasswordField.setText("");

                cardLayout.show(mainPanel, "EDIT_PROFILE");
            }
        });
        
        // Find and setup Cancel button (in change password)
        findAndSetupButton(passwordComponents, "Cancel", new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Clear password fields
                oldPasswordField.setText("");
                newPasswordField.setText("");
                confirmPasswordField.setText("");

                cardLayout.show(mainPanel, "EDIT_PROFILE");
            }
        });
    }
    
    private void findAndSetupButton(Component[] components, String buttonText, ActionListener listener) {
        for (Component comp : components) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals(buttonText)) {
                ((JButton) comp).addActionListener(listener);
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            new ProfilePanelsami().setVisible(true);
        });
    }
}