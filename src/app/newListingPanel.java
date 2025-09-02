package app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
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
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controllers.newListing;
import functions.RoundedPanel;

public class newListingPanel extends javax.swing.JPanel {
    private int client_id;
    private mainFrame parent;
    private int car_id;
    private String primaryRelativePath;
    private String primaryImageName;
    private ArrayList<String> ImageNames;
    private int imageCount=0;
    private int gbcCount=0;

    public newListingPanel(mainFrame parent,int client_id) {
        this.parent=parent;
        this.client_id=client_id;
        initComponents();
    }

    //function to show Primary image after uploading
    private void showPrimaryImage(File imageFile) {
            try {
                //remove old container
                primaryImagePanel.remove(primaryImageContainer);
                ImageIcon primaryPic = new ImageIcon(imageFile.getAbsolutePath());
                Image primaryImg = primaryPic.getImage().getScaledInstance(430, 300, Image.SCALE_SMOOTH);
                //make new one
                primaryImageContainer =new RoundedPanel(10,primaryImg);
                primaryImageContainer.setPreferredSize(new Dimension(430,300));
                primaryImageContainer.setMaximumSize(new Dimension(430,300));
                primaryImageContainer.setMinimumSize(new Dimension(430,300));
                //add to the same place
                primaryImagePanel.add(primaryImageContainer,1);
                primaryImagePanel.revalidate();
                primaryImagePanel.repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ImageIcon logo=new ImageIcon(getClass().getResource("../layout/logo2.png"));
        Image logoImage=logo.getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH);
        logoLabel = new javax.swing.JLabel(new ImageIcon(logoImage));
        HomeBtn=new javax.swing.JButton();
        profileBtn = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        newListingBtn = new javax.swing.JButton();
        supportBtn = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        brandlabel = new javax.swing.JLabel();
        brandTxtField = new javax.swing.JTextField();
        modelLabel = new javax.swing.JLabel();
        modelTxtField = new javax.swing.JTextField();
        yearLabel = new javax.swing.JLabel();
        yearTxtField = new javax.swing.JTextField();
        priceLabel=new javax.swing.JLabel();
        priceTxtField=new javax.swing.JTextField();
        locationLabel = new javax.swing.JLabel();
        locationTxtField = new javax.swing.JTextField();
        descriptionLabel =new javax.swing.JLabel();
        descriptiontxtArea=new javax.swing.JTextArea();
        transactionLabel = new javax.swing.JLabel();
        saleRadioBtn = new javax.swing.JRadioButton();
        rentRadioBtn = new javax.swing.JRadioButton();
        addCarbtn = new javax.swing.JButton();
        photosLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        addPhoto = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        addImagesPanel=new JPanel();

        setBackground(new Color(52,52,52));

        newListing newListing=new newListing();
        jPanel1.setBackground(new java.awt.Color(24,24,24));
        
        logoLabel.setBackground(new java.awt.Color(255, 0, 0));
        logoLabel.setFont(new java.awt.Font("Segoe UI", 1, 24));

        ImageIcon homeIcon=new ImageIcon(getClass().getResource("../layout/Icons/homeIcon.png"));
        Image homeImage=homeIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon homeIconScaled=new ImageIcon(homeImage);
        HomeBtn.setFont(new java.awt.Font("Segoe UI", 0, 18));
        HomeBtn.setText("Home");
        HomeBtn.setIcon(homeIconScaled);
        HomeBtn.setIconTextGap(10);
        HomeBtn.setBorder(null);
        HomeBtn.setBorderPainted(false);
        HomeBtn.setContentAreaFilled(false);
        HomeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        HomeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HomeBtn.setBorderPainted(true);
                HomeBtn.setContentAreaFilled(true);
                HomeBtn.setFont(new Font(getName(),Font.BOLD,24));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                HomeBtn.setBorderPainted(false);
                HomeBtn.setContentAreaFilled(false);
                HomeBtn.setFont(new Font(getName(),Font.PLAIN,18));
            }
        });
        HomeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parent.switchPages("Home");
            }
        });

        ImageIcon profileIcon=new ImageIcon(getClass().getResource("../layout/Icons/profileIcon.png"));
        Image profileImageIcon=profileIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon profileIconScaled=new ImageIcon(profileImageIcon);
        profileBtn.setFont(new java.awt.Font("Segoe UI", 0, 18));
        profileBtn.setText("Profile");
        profileBtn.setIcon(profileIconScaled);
        profileBtn.setIconTextGap(10);
        profileBtn.setBorder(null);
        profileBtn.setBorderPainted(false);
        profileBtn.setContentAreaFilled(false);
        profileBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profileBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                profileBtn.setBorderPainted(true);
                profileBtn.setContentAreaFilled(true);
                profileBtn.setFont(new Font(getName(),Font.BOLD,24));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                profileBtn.setBorderPainted(false);
                profileBtn.setContentAreaFilled(false);
                profileBtn.setFont(new Font(getName(),Font.PLAIN,18));
            }
        });
        profileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parent.switchPages("Profile");
            }
        });

        ImageIcon searchIcon=new ImageIcon(getClass().getResource("../layout/Icons/searchIcon.png"));
        Image searchImage=searchIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon searchIconScaled=new ImageIcon(searchImage);
        searchBtn.setFont(new java.awt.Font("Segoe UI", 0, 18));
        searchBtn.setText("Search");
        searchBtn.setIcon(searchIconScaled);
        searchBtn.setIconTextGap(10);
        searchBtn.setBorder(null);
        searchBtn.setBorderPainted(false);
        searchBtn.setContentAreaFilled(false);
        searchBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchBtn.setBorderPainted(true);
                searchBtn.setContentAreaFilled(true);
                searchBtn.setFont(new Font(getName(),Font.BOLD,24));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchBtn.setBorderPainted(false);
                searchBtn.setContentAreaFilled(false);
                searchBtn.setFont(new Font(getName(),Font.PLAIN,18));
            }
        });
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parent.switchPages("Search");
            }
        });

        ImageIcon newListingIcon=new ImageIcon(getClass().getResource("../layout/Icons/addIcon.png"));
        Image newListingImage=newListingIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon newListingIconScaled=new ImageIcon(newListingImage);
        newListingBtn.setFont(new java.awt.Font("Segoe UI", 0, 18));
        newListingBtn.setText("New Listing");
        newListingBtn.setIcon(newListingIconScaled);
        newListingBtn.setIconTextGap(10);
        newListingBtn.setBorder(null);
        newListingBtn.setFont(new Font(getName(),Font.BOLD,24));
        newListingBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newListingBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            }
        });
        newListingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });

        ImageIcon supportIcon=new ImageIcon(getClass().getResource("../layout/Icons/supportIcon.png"));
        Image supportImageIcon=supportIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon supportIconScaled=new ImageIcon(supportImageIcon);
        supportBtn.setFont(new java.awt.Font("Segoe UI", 0, 18));
        supportBtn.setText("Support");
        supportBtn.setIcon(supportIconScaled);
        supportBtn.setIconTextGap(10);
        supportBtn.setBorder(null);
        supportBtn.setBorderPainted(false);
        supportBtn.setContentAreaFilled(false);
        supportBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        supportBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                supportBtn.setBorderPainted(true);
                supportBtn.setContentAreaFilled(true);
                supportBtn.setFont(new Font(getName(),Font.BOLD,24));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                supportBtn.setBorderPainted(false);
                supportBtn.setContentAreaFilled(false);
                supportBtn.setFont(new Font(getName(),Font.PLAIN,18));
            }
        });
        supportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // parent.switchPages("Support");
                new supportFrame(client_id);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE) // push to center
                        .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(HomeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(profileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newListingBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(supportBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(HomeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(profileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(newListingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(supportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); 
        titleLabel.setText("Add New Listing");

        brandlabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); 
        brandlabel.setText("Brand ");

        modelLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); 
        modelLabel.setText("Model");

        yearLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); 
        yearLabel.setText("Year");
        yearLabel.setMaximumSize(new java.awt.Dimension(69, 32));
        yearLabel.setMinimumSize(new java.awt.Dimension(69, 32));
        yearLabel.setPreferredSize(new java.awt.Dimension(69, 32));

        priceLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); 
        priceLabel.setText("Price");
        priceLabel.setMaximumSize(new java.awt.Dimension(69, 32));
        priceLabel.setMinimumSize(new java.awt.Dimension(69, 32));
        priceLabel.setPreferredSize(new java.awt.Dimension(69, 32));

        locationLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); 
        locationLabel.setText("Location");
        locationLabel.setPreferredSize(new java.awt.Dimension(69, 32));
        
        descriptionLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); 
        descriptionLabel.setText("Description");
        descriptionLabel.setPreferredSize(new java.awt.Dimension(69, 32));

        descriptiontxtArea.setLineWrap(true);
        descriptiontxtArea.setWrapStyleWord(true);
        JScrollPane descriptionPane =new JScrollPane(
            descriptiontxtArea,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        transactionLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        transactionLabel.setText("Transaction");
        transactionLabel.setPreferredSize(new java.awt.Dimension(69, 32));

        saleRadioBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        saleRadioBtn.setText("Sale");
        saleRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });

        rentRadioBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rentRadioBtn.setText("Rent");
        rentRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });

        ButtonGroup group=new ButtonGroup();
        group.add(saleRadioBtn);
        group.add(rentRadioBtn);

        addCarbtn.setFont(new java.awt.Font("Segoe UI", 0, 14));
        addCarbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addCarbtn.setText("Add Car");
        

        photosLabel.setFont(new java.awt.Font("Segoe UI", 0, 24));
        photosLabel.setText("Photos");

        addPhoto.setText("Add photo");
        addPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        
        ImageIcon sideImageIcon=new ImageIcon(getClass().getResource("../layout/newListingImg.png"));
        Image sideImage=sideImageIcon.getImage();
        RoundedPanel right=new RoundedPanel(10,sideImage,false);
        right.setBackground(new Color(52,52,52));

        //create left panel the cardLayout 
        JPanel left=new JPanel(new CardLayout());
        left.setPreferredSize(new Dimension(200, 585));
        left.setMinimumSize(new Dimension(200, 585));
        left.setBackground(new Color(52,52,52));

        JPanel FormPanel=new JPanel();
        FormPanel.setBackground(new Color(52,52,52));
        primaryImagePanel=new JPanel();
        primaryImagePanel.setBackground(new Color(52,52,52));

        //formPanel horizontal layout
        javax.swing.GroupLayout formPanelLayout = new javax.swing.GroupLayout(FormPanel);
        FormPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(descriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(locationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(priceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(yearLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(modelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(brandlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(transactionLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(48, 48, 48)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(brandTxtField, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                            .addComponent(modelTxtField)
                            .addComponent(yearTxtField)
                            .addComponent(priceTxtField)
                            .addComponent(locationTxtField)
                            .addComponent(descriptionPane)
                            .addGroup(formPanelLayout.createSequentialGroup()
                                .addComponent(saleRadioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rentRadioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(addCarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
            ));
        //formPanel vertical layout  
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(brandlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(brandTxtField))
                .addGap(34, 34, 34)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(modelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(modelTxtField, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(yearLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(yearTxtField))
                .addGap(34, 34, 34)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(priceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(priceTxtField))
                .addGap(34, 34, 34)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(locationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(locationTxtField))
                .addGap(34, 34, 34)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(descriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descriptionPane, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(transactionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saleRadioBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rentRadioBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(addCarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 38, Short.MAX_VALUE))
        );

        //primaryImagePanel layout
        primaryImagePanel.setBackground(new Color(52,52,52));

        primaryImageContainer=new RoundedPanel(10);
        primaryImageContainer.add(new JLabel("Here primary image"));
        
        primaryImageContainer.setPreferredSize(new Dimension(430,300));
        primaryImageContainer.setMaximumSize(new Dimension(430,300));
        primaryImageContainer.setMinimumSize(new Dimension(430,300));

        JButton addPrimaryBtn=new JButton("Add image");
        JButton submitPrimaryImgBtn =new JButton("Submit image");
        addPrimaryBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitPrimaryImgBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        primaryImagePanel.setLayout(new BoxLayout(primaryImagePanel, BoxLayout.Y_AXIS));
        primaryImagePanel.add(Box.createVerticalStrut(100));
        primaryImageContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        addPrimaryBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitPrimaryImgBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        primaryImagePanel.add(primaryImageContainer,Box.CENTER_ALIGNMENT);
        primaryImagePanel.add(Box.createVerticalStrut(10));
        primaryImagePanel.add(addPrimaryBtn,Box.CENTER_ALIGNMENT);
        primaryImagePanel.add(Box.createVerticalStrut(5));
        primaryImagePanel.add(submitPrimaryImgBtn,Box.CENTER_ALIGNMENT);

        //addPrimaryImage actions
        addPrimaryBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JFileChooser image=new JFileChooser();
                int choice=image.showOpenDialog(null);
                if(choice==JFileChooser.APPROVE_OPTION){
                    File selected=image.getSelectedFile();
                    File uploadDir=new File("src/layout/uploads/carImages");
                    primaryImageName="user_"+client_id+"_"+selected.getName();
                    Path destination=Paths.get(uploadDir.getAbsolutePath(),primaryImageName);
                    primaryRelativePath="src/layout/uploads/carImages/"+primaryImageName;
                    try {
                        Files.copy(selected.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("Image saved to: " + primaryRelativePath);

                    showPrimaryImage(selected);
                }
            }
        });

        

        //addImages Layout

        addImagesPanel.setBackground(new Color(52,52,52));
        addImagesPanel.setLayout(new BoxLayout(addImagesPanel, BoxLayout.Y_AXIS));
        JPanel imagesContainer=new JPanel();
        imagesContainer.setBackground(new Color(52,52,52));

        JScrollPane imageContainerScrollPane=new JScrollPane();
        imageContainerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        imageContainerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        imageContainerScrollPane.setPreferredSize(new Dimension(left.getWidth(), 450));
        imageContainerScrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 450));
        // imageContainerScrollPane.setBorder(null);
        
        JButton addImgBtn=new JButton("Add Image");
        addImgBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addImagesPanel.add(addImgBtn);
        addImagesPanel.add(Box.createVerticalStrut(10));
        JButton submitImgsBtn=new JButton("Submit Images");
        submitImgsBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // imagesContainer.setLayout(new GridLayout(0, 2,10,10));
        imagesContainer.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.weightx=1.0;
        gbc.weighty=1.0;
        gbc.insets = new Insets(10, 10, 10, 10);

        imageContainerScrollPane.setViewportView(imagesContainer);

        addImagesPanel.add(imageContainerScrollPane);
        addImagesPanel.add(Box.createVerticalStrut(10));
        addImagesPanel.add(submitImgsBtn);

        ImageNames=new ArrayList<String>();
        //addImage button action
        addImgBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
                    JFileChooser image=new JFileChooser();
                    int choice=image.showOpenDialog(null);
                    if(choice==JFileChooser.APPROVE_OPTION){
                        File selected=image.getSelectedFile();
                        File uploadDir=new File("src/layout/uploads/carImages");
                        String ImageName="user_"+client_id+"_"+selected.getName();
                        Path destination=Paths.get(uploadDir.getAbsolutePath(),ImageName);
                        String imageRelativePath="src/layout/uploads/carImages/"+ImageName;
                        ImageNames.add(ImageName);
                        try {
                            Files.copy(selected.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        System.out.println("Image saved to: " + imageRelativePath);
                        
                        ImageIcon newImg=new ImageIcon(selected.getAbsolutePath());
                        Image scaleNewImg=newImg.getImage().getScaledInstance(320, 220, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon=new ImageIcon(scaleNewImg);
                        RoundedPanel card=new RoundedPanel(10,scaledIcon.getImage(),false);
                        
                        card.setPreferredSize(new Dimension(320, 220));
                        card.setMaximumSize(new Dimension(320, 220));

                        imagesContainer.add(card,gbc);
                        imagesContainer.revalidate();
                        imagesContainer.repaint();

                        if(gbcCount == 1){
                            gbcCount=0;
                            gbc.gridx=0;
                            gbc.gridy++;
                        }else{
                            gbc.gridx++;
                            gbcCount++;
                        }
                        imageCount++;
                        System.out.println(imageCount);
                    }
                
            }
        });
        
        //add the panels to cardLayout
        left.add(FormPanel,"Form");
        left.add(primaryImagePanel,"PrimaryImage");
        left.add(addImagesPanel,"addImgsPanel");
        CardLayout cl=(CardLayout) left.getLayout();
        cl.show(left,"Form");
        
        //add Car btn
        addCarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String brand =brandTxtField.getText();
                String model =modelTxtField.getText();
                String year =yearTxtField.getText();
                String price=priceTxtField.getText();
                String location =locationTxtField.getText();
                String description=descriptiontxtArea.getText();
                String transaction= saleRadioBtn.isSelected() ? "sale" : "rent";
                car_id=newListing.addCar(client_id,brand, model, price, year, description, location, transaction);
                System.out.println(car_id);
                if(car_id!=-1){
                    //changing left content after submiting first
                    brandTxtField.setText("");
                    modelTxtField.setText("");
                    yearTxtField.setText("");
                    priceTxtField.setText("");
                    locationTxtField.setText("");
                    descriptiontxtArea.setText("");
                    cl.show(left,"PrimaryImage");
                }
            }
        });
        //submit primaryImage
        submitPrimaryImgBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(newListing.addPrimaryImage(car_id,primaryImageName)){
                    JOptionPane.showMessageDialog(null,"Primary Image Set Succesfully");
                    cl.show(left,"addImgsPanel");

                };
            }
        });

        //submit images
        submitImgsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(newListing.addImages(car_id, ImageNames)){
                    JOptionPane.showMessageDialog(null, "Images added Successfully");
                    cl.show(left,"Form");
                }
            }
        });

        jPanel2.setBackground(new Color(52,52,52));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(left, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,10,30)
                .addComponent(right, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,10,30)
                )
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(right, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(53, 53, 53))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(left, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(53, 53, 53))
            
        );

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(titleLabel)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }                       



    // Variables declaration - do not modify 
    private javax.swing.JButton searchBtn;
    private javax.swing.JButton HomeBtn;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JButton profileBtn;
    private javax.swing.JButton newListingBtn;                    
    private javax.swing.JButton supportBtn;                    
    private javax.swing.JButton addPhoto;
    private javax.swing.JTextField brandTxtField;
    private javax.swing.JLabel brandlabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JTextField locationTxtField;
    private javax.swing.JLabel modelLabel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceTxtField;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextArea descriptiontxtArea;
    private javax.swing.JTextField modelTxtField;
    private javax.swing.JLabel photosLabel;
    private javax.swing.JButton addCarbtn;
    private javax.swing.JRadioButton rentRadioBtn;
    private javax.swing.JRadioButton saleRadioBtn;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel transactionLabel;
    private javax.swing.JLabel yearLabel;
    private javax.swing.JTextField yearTxtField;
    private RoundedPanel primaryImageContainer;
    private JPanel primaryImagePanel;
    private JPanel addImagesPanel;
    // End of variables declaration      
    

    private void showImageUploadPanel(JPanel panel) {
        panel.removeAll();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        RoundedPanel primaryImage = new RoundedPanel(10);
        primaryImage.setLayout(new BorderLayout());

        JButton addImageBtn = new JButton(new ImageIcon("../layout/Icons/addIcon.jpeg"));
        addImageBtn.setPreferredSize(new Dimension(100, 100));
        JButton addPrimaryBtn = new JButton("Add Primary Image");

        primaryImage.add(addImageBtn, BorderLayout.CENTER);

        panel.add(primaryImage, Box.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10));
        panel.add(addPrimaryBtn, Box.CENTER_ALIGNMENT);

        panel.revalidate();
        panel.repaint();
    }
}
