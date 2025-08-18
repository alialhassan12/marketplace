package app;
import controllers.getCars;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.Border;

public class profileFrame extends javax.swing.JFrame {
    private int client_id;
    private String name;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(home.class.getName());

    public profileFrame(int client_id) {
        this.client_id=client_id;
        initComponents();
    }

    private void initComponents() {
        
        jPanel1 = new javax.swing.JPanel();
        ImageIcon logo=new ImageIcon(getClass().getResource("../layout/logo2.png"));
        Image logoImage=logo.getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH);
        logoLabel = new javax.swing.JLabel(new ImageIcon(logoImage));
        HomeBtn = new javax.swing.JButton();
        profileBtn = new javax.swing.JButton();
        editProfileBtn = new javax.swing.JButton();
        BuyBtn = new javax.swing.JButton();
        sellBtn = new javax.swing.JButton();
        rentBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        userInfoPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        profilePicLabel= new javax.swing.JLabel();
        usernameLabel= new javax.swing.JLabel();
        emailLabel= new javax.swing.JLabel();
        phone= new javax.swing.JLabel();
        MyListingsLabel=new javax.swing.JLabel();
        locationLabel= new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userInfoPanel = new javax.swing.JPanel();
        myListingsPanel = new javax.swing.JPanel();
        scrollPanel = new javax.swing.JPanel();
        emailLabel = new javax.swing.JLabel();
        fullNameLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(79, 100, 111));
        jPanel1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        logoLabel.setBackground(new java.awt.Color(255, 0, 0));
        logoLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        HomeBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        HomeBtn.setText("Home");
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
                HomeBtn.setFont(new Font(getName(),0,24));
            }
        });
        HomeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                home home =new home(client_id);
                home.setVisible(true);
                setVisible(false);
                home.setSize(1280, 750);
                home.setLocationRelativeTo(null);
                home.setExtendedState(JFrame.MAXIMIZED_BOTH);
                home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

        profileBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        profileBtn.setText("Profile");
        profileBtn.setBorder(null);
        profileBtn.setFont(new Font(getName(),Font.BOLD,24));
        profileBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profileBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                
            }
        });
        profileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileBtnActionPerformed(evt);
            }
        });

        BuyBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BuyBtn.setText("Buy");
        BuyBtn.setBorder(null);
        BuyBtn.setBorderPainted(false);
        BuyBtn.setContentAreaFilled(false);
        BuyBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BuyBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BuyBtn.setBorderPainted(true);
                BuyBtn.setContentAreaFilled(true);
                BuyBtn.setFont(new Font(getName(),Font.BOLD,24));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BuyBtn.setBorderPainted(false);
                BuyBtn.setContentAreaFilled(false);
                BuyBtn.setFont(new Font(getName(),0,24));
            }
        });
        BuyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuyBtnActionPerformed(evt);
            }
        });

        sellBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sellBtn.setText("Sell");
        sellBtn.setBorder(null);
        sellBtn.setBorderPainted(false);
        sellBtn.setContentAreaFilled(false);
        sellBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sellBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sellBtn.setBorderPainted(true);
                sellBtn.setContentAreaFilled(true);
                sellBtn.setFont(new Font(getName(),Font.BOLD,24));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sellBtn.setBorderPainted(false);
                sellBtn.setContentAreaFilled(false);
                sellBtn.setFont(new Font(getName(),0,24));
            }
        });
        sellBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellBtnActionPerformed(evt);
            }
        });

        rentBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rentBtn.setText("Rent");
        rentBtn.setBorder(null);
        rentBtn.setBorderPainted(false);
        rentBtn.setContentAreaFilled(false);
        rentBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rentBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rentBtn.setBorderPainted(true);
                rentBtn.setContentAreaFilled(true);
                rentBtn.setFont(new Font(getName(),Font.BOLD,24));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                rentBtn.setBorderPainted(false);
                rentBtn.setContentAreaFilled(false);
                rentBtn.setFont(new Font(getName(),0,24));
            }
        });
        rentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rentBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(HomeBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(profileBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BuyBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sellBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rentBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
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
                .addComponent(BuyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sellBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        titleLabel.setText("Profile");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        profilePicLabel.setText("Profile Picture");

        usernameLabel.setText(this.name);

        emailLabel.setText("email");

        phone.setText("phone");

        locationLabel.setText("Location");

        editProfileBtn.setText("Edit Profile");
        editProfileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });

        javax.swing.GroupLayout userInfoPanelLayout = new javax.swing.GroupLayout(userInfoPanel);
        userInfoPanel.setLayout(userInfoPanelLayout);
        userInfoPanelLayout.setHorizontalGroup(
            userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                        .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(phone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(locationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(editProfileBtn))
                .addContainerGap(195, Short.MAX_VALUE))
        );
        userInfoPanelLayout.setVerticalGroup(
            userInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userInfoPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(usernameLabel)
                .addGap(18, 18, 18)
                .addComponent(emailLabel)
                .addGap(18, 18, 18)
                .addComponent(phone)
                .addGap(18, 18, 18)
                .addComponent(locationLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(editProfileBtn)
                .addContainerGap())
        );

        MyListingsLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        MyListingsLabel.setText("My Listings");

        myListingsPanel.setLayout(new GridLayout(0,3,10,10));

        getCars getCars=new getCars();
        try{
            ResultSet MyListingCars=getCars.getMyListingsCars(client_id);
            ResultSet MyListingPrimaryImages=getCars.getMyListingsCarsPrimaryImages(3);
            
            while(MyListingCars.next() && MyListingPrimaryImages.next()){
                RoundedPanel card=new RoundedPanel(10);
                card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
                String imageName=MyListingPrimaryImages.getString("image_path");
                URL imageUrl=getClass().getResource("../layout/cars/"+imageName);
                if(imageUrl != null){
                    ImageIcon image=new ImageIcon(imageUrl);
                    Image scaled=image.getImage().getScaledInstance(220, 150, Image.SCALE_SMOOTH);
                    JLabel imageLabel=new JLabel(new ImageIcon(scaled));
                    imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    card.add(imageLabel);
                }else{
                    card.add(new JLabel("no image"));
                }
                JLabel cardBrandLabel=new JLabel(MyListingCars.getString("brand"));
                cardBrandLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                card.add(cardBrandLabel);
                card.add(Box.createVerticalStrut(5));
                JLabel cardModelLbel=new JLabel(MyListingCars.getString("model"));
                cardModelLbel.setAlignmentX(Component.CENTER_ALIGNMENT);
                card.add(cardModelLbel);
                card.add(Box.createVerticalStrut(5));
                String year=Integer.toString(MyListingCars.getInt("year"));
                JLabel cardYearJlabel=new JLabel(year);
                cardYearJlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                card.add(cardYearJlabel);
                card.add(Box.createVerticalStrut(10));
                card.setBorder (BorderFactory.createEmptyBorder(10,10,10,10));
                JButton moreBtn = new JButton("Show More");
                moreBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                moreBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                card.add(moreBtn);
                myListingsPanel.add(card);
            }
        }catch(Exception e){
            System.out.println("Error getting My Listings: "+e.getMessage());
        }

        // javax.swing.GroupLayout myListingsPanelLayout = new javax.swing.GroupLayout(myListingsPanel);
        // myListingsPanel.setLayout(myListingsPanelLayout);
        // myListingsPanelLayout.setHorizontalGroup(
        //     myListingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        //     .addGap(0, 1020, Short.MAX_VALUE)
        // );
        // myListingsPanelLayout.setVerticalGroup(
        //     myListingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        //     .addGap(0, 0, Short.MAX_VALUE)
        // );

        javax.swing.GroupLayout scrollPanelLayout = new javax.swing.GroupLayout(scrollPanel);
        scrollPanel.setLayout(scrollPanelLayout);
        scrollPanelLayout.setHorizontalGroup(
            scrollPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scrollPanelLayout.createSequentialGroup()
                .addComponent(MyListingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(scrollPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profilePicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(userInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, scrollPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(myListingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        scrollPanelLayout.setVerticalGroup(
            scrollPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scrollPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(scrollPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(profilePicLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MyListingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myListingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(scrollPanel);
        jScrollPane1.setBorder(null);
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(555, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pack();
    }// </editor-fold>                        

    private void profileBtnActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void BuyBtnActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void sellBtnActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void rentBtnActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void jPanel1AncestorAdded(javax.swing.event.AncestorEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    // Variables declaration - do not modify                     
    private javax.swing.JButton BuyBtn;
    private javax.swing.JButton HomeBtn;
    private javax.swing.JLabel MyListingsLabel;
    private javax.swing.JButton editProfileBtn;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPanel scrollPanel;
    private javax.swing.JPanel myListingsPanel;
    private javax.swing.JLabel phone;
    private javax.swing.JButton profileBtn;
    private javax.swing.JLabel profilePicLabel;
    private javax.swing.JButton rentBtn;
    private javax.swing.JButton sellBtn;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel userInfoPanel;
    private javax.swing.JLabel usernameLabel; 
    private javax.swing.JLabel fullNameLabel;
    private javax.swing.JLabel phoneLabel;
    // End of variables declaration
}
