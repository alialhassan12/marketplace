package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controllers.profile;
import functions.RoundedPanel;

public class editProfileFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(editProfileFrame.class.getName());
    private int client_id;
    private String fullName;
    private String email;
    private String phone;
    private String location;
    private String profileImagePath;
    private String profileImageRelativePath;
    private profilePanel profilePanelRef;
    private JPanel profilePicPanel=new JPanel();


    public editProfileFrame(int client_id,String fullname,String email,String phone,String location,String profileImagePath,profilePanel profilePanel) {
        this.client_id=client_id;
        this.fullName=fullname;
        this.email=email;
        this.phone=phone;
        this.location=location;
        this.profileImagePath=profileImagePath;
        this.profilePanelRef=profilePanel;
        initComponents();
    }

    private void showProfileImage(File imageFile) {
        try {
            profilePicPanel.removeAll();
            profilePicPanel.setLayout(new BorderLayout());

            ImageIcon profilePicIcon = new ImageIcon(imageFile.getAbsolutePath());
            Image profileImage = profilePicIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            RoundedPanel profilePic=new RoundedPanel(100,profileImage,false);

            profilePicPanel.add(profilePic, BorderLayout.CENTER);
            profilePicPanel.revalidate();
            profilePicPanel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        fullNameLabel = new javax.swing.JLabel();
        fullNameField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        phonelabel = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        locationLabel = new javax.swing.JLabel();
        locationField = new javax.swing.JTextField();
        confirmBtn = new javax.swing.JButton();

        JButton editProfileBtn=new JButton("Edit profile Picture");
        
        profile profile=new profile(this.client_id);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(670, 343));

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        titleLabel.setText("Edit Profile");

        fullNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fullNameLabel.setText("Full Name: ");

        fullNameField.setText(this.fullName);
        fullNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });

        emailLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        emailLabel.setText("Email: ");

        emailField.setText(this.email);
        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });

        phonelabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        phonelabel.setText("Phone:");
        phonelabel.setPreferredSize(new java.awt.Dimension(51, 25));

        phoneField.setText(this.phone);
        phoneField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });

        locationLabel.setFont(new java.awt.Font("Segoe UI", 0, 18));
        locationLabel.setText("Location:");

        locationField.setText(this.location);

        confirmBtn.setText("confirm");
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String newName=fullNameField.getText();
                String newEmail=emailField.getText();
                String newPhone=phoneField.getText();
                String newLocation=locationField.getText();
                if(profile.editProfile(newName,newEmail, newPhone, newLocation,profileImageRelativePath)){
                    JOptionPane.showMessageDialog(null, "Profile Updated Successfully!");
                    if(profilePanelRef !=null){
                        profilePanelRef.refreshProfileData();
                    }
                    dispose();
                }
            }
        });
        //userr info panel layout
        JPanel userInfo=new JPanel();
        userInfo.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        //Row 0
        gbc.gridx=0;gbc.gridy=0;
        userInfo.add(fullNameLabel,gbc);
        gbc.gridx=1;gbc.gridy=0;
        gbc.weightx=1.0;gbc.fill=GridBagConstraints.HORIZONTAL;
        userInfo.add(fullNameField,gbc);
        //Row 1
        gbc.gridx=0;gbc.gridy=1;
        userInfo.add(emailLabel,gbc);
        gbc.gridx=1;gbc.gridy=1;
        gbc.weightx=1.0;gbc.fill=GridBagConstraints.HORIZONTAL;
        userInfo.add(emailField,gbc);
        //Row 2
        gbc.gridx=0;gbc.gridy=2;
        userInfo.add(phonelabel,gbc);
        gbc.gridx=1;gbc.gridy=2;
        gbc.weightx=1.0;gbc.fill=GridBagConstraints.HORIZONTAL;
        userInfo.add(phoneField,gbc);
        //Row 3
        gbc.gridx=0;gbc.gridy=3;
        userInfo.add(locationLabel,gbc);
        gbc.gridx=1;gbc.gridy=3;
        gbc.weightx=1.0;gbc.fill=GridBagConstraints.HORIZONTAL;
        userInfo.add(locationField,gbc);
        //Row 4
        gbc.gridx=0;gbc.gridy=4;
        gbc.weightx=1.0;gbc.fill=GridBagConstraints.HORIZONTAL;
        userInfo.add(confirmBtn,gbc);

        profilePicPanel.setLayout(new BorderLayout());
        profilePicPanel.setPreferredSize(new Dimension(100,100));
        profilePicPanel.setMaximumSize(new Dimension(100,100));
        profilePicPanel.setMinimumSize(new Dimension(100,100));

        gbc.insets = new Insets(10, 20, 10, 10);

        if(profileImagePath == null){
            ImageIcon profileIcon=new ImageIcon(getClass().getResource("../layout/Icons/noProfileIcon.jpeg"));
            Image profileImage=profileIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            RoundedPanel profilePic=new RoundedPanel(100,profileImage,false);
            profilePic.setPreferredSize(new Dimension(100,100));
            profilePic.setMaximumSize(new Dimension(100,100));
            profilePic.setMinimumSize(new Dimension(100,100));
            profilePicPanel.add(profilePic,BorderLayout.CENTER);
            gbc.gridx=2;gbc.gridy=0;
            gbc.gridheight = 2;
            gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
            userInfo.add(profilePicPanel,gbc);
            gbc.gridx=2;
            gbc.gridy=2;
            gbc.gridheight = 1;
            userInfo.add(editProfileBtn,gbc);
        }else{
            ImageIcon profileIcon=new ImageIcon(profileImagePath);
            Image profileImage=profileIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            RoundedPanel profilePic=new RoundedPanel(100,profileImage,false);
            profilePic.setPreferredSize(new Dimension(100,100));
            profilePic.setMaximumSize(new Dimension(100,100));
            profilePic.setMinimumSize(new Dimension(100,100));
            profilePicPanel.add(profilePic,BorderLayout.CENTER);
            gbc.gridx=2;gbc.gridy=0;
            gbc.gridheight = 2;
            gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
            userInfo.add(profilePicPanel,gbc);
            gbc.gridx=2;
            gbc.gridy=2;
            gbc.gridheight = 1;
            userInfo.add(editProfileBtn,gbc);
        }

        editProfileBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFileChooser image=new JFileChooser();
                int choice=image.showOpenDialog(null);
                if(choice==JFileChooser.APPROVE_OPTION){
                    File selected=image.getSelectedFile();
                    File uploadDir=new File("src/layout/uploads/profilePic");
                    String newFileName="user_"+client_id+"_"+selected.getName();
                    Path destination=Paths.get(uploadDir.getAbsolutePath(),newFileName);
                    profileImageRelativePath="src/layout/uploads/profilePic/"+newFileName;
                    try {
                        Files.copy(selected.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("Image saved to: " + profileImageRelativePath);
                    showProfileImage(selected);
            }}
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(userInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(170, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }                                    

    // Variables declaration - do not modify                     
    private javax.swing.JButton confirmBtn;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField fullNameField;
    private javax.swing.JLabel fullNameLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField locationField;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JTextField phoneField;
    private javax.swing.JLabel phonelabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration                   
}
