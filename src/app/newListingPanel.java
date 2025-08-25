package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.ScrollPane;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import controllers.newListing;
import functions.RoundedPanel;

public class newListingPanel extends javax.swing.JPanel {
    private int client_id;
    private mainFrame parent;

    public newListingPanel(mainFrame parent,int client_id) {
        this.parent=parent;
        this.client_id=client_id;
        initComponents();
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

        newListing newListing=new newListing();
        jPanel1.setBackground(new java.awt.Color(79, 100, 111));

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
                HomeBtn.setFont(new Font(getName(),Font.PLAIN,18));
            }
        });
        HomeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parent.switchPages("Home");
            }
        });

        profileBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        profileBtn.setText("Profile");
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

        searchBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        searchBtn.setText("Search");
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

        newListingBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        newListingBtn.setText("New Listing");
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
                
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titleLabel.setText("Add New Listing");

        brandlabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        brandlabel.setText("Brand ");

        brandTxtField.setText("jTextField1");

        modelLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        modelLabel.setText("Model");

        modelTxtField.setText("jTextField1");

        yearLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        yearLabel.setText("Year");
        yearLabel.setMaximumSize(new java.awt.Dimension(69, 32));
        yearLabel.setMinimumSize(new java.awt.Dimension(69, 32));
        yearLabel.setPreferredSize(new java.awt.Dimension(69, 32));

        yearTxtField.setText("jTextField1");

        priceLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        priceLabel.setText("Price");
        priceLabel.setMaximumSize(new java.awt.Dimension(69, 32));
        priceLabel.setMinimumSize(new java.awt.Dimension(69, 32));
        priceLabel.setPreferredSize(new java.awt.Dimension(69, 32));

        locationLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        locationLabel.setText("Location");
        locationLabel.setPreferredSize(new java.awt.Dimension(69, 32));

        locationTxtField.setText("jTextField1");
        
        descriptionLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        descriptionLabel.setText("Description");
        descriptionLabel.setPreferredSize(new java.awt.Dimension(69, 32));

        descriptiontxtArea.setText("Description");
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
        addCarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String brand =brandTxtField.getText();
                String model =modelTxtField.getText();
                String year =yearTxtField.getText();
                double price=Double.parseDouble(priceTxtField.getText());
                String location =locationTxtField.getText();
                String description=descriptiontxtArea.getText();
                String transaction= saleRadioBtn.isSelected() ? "sale" : "rent";
                
                if(newListing.addCar(brand, model, price, year, description, location, transaction)){
                    
                }
            }
        });

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
        

        JPanel left=new JPanel();
        left.setPreferredSize(new Dimension(200, 585));
        left.setMinimumSize(new Dimension(200, 585));

        //left horizontal layout
        javax.swing.GroupLayout leftLayout = new javax.swing.GroupLayout(left);
        left.setLayout(leftLayout);
        leftLayout.setHorizontalGroup(
            leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftLayout.createSequentialGroup()
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(descriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(locationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(priceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(yearLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(modelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(brandlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(transactionLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(48, 48, 48)
                        .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(brandTxtField, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                            .addComponent(modelTxtField)
                            .addComponent(yearTxtField)
                            .addComponent(priceTxtField)
                            .addComponent(locationTxtField)
                            .addComponent(descriptionPane)
                            .addGroup(leftLayout.createSequentialGroup()
                                .addComponent(saleRadioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rentRadioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(leftLayout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(addCarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
            ));
        //left vertical layout
        leftLayout.setVerticalGroup(
            leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(brandlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(brandTxtField))
                .addGap(34, 34, 34)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(modelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(modelTxtField, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(yearLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(yearTxtField))
                .addGap(34, 34, 34)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(priceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(priceTxtField))
                .addGap(34, 34, 34)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(locationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(locationTxtField))
                .addGap(34, 34, 34)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(descriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descriptionPane, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(transactionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saleRadioBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rentRadioBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(addCarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(left, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,50,100)
                .addComponent(right, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,50,100)
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
    }// </editor-fold>                        



    // Variables declaration - do not modify 
    private javax.swing.JButton searchBtn;
    private javax.swing.JButton HomeBtn;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JButton profileBtn;
    private javax.swing.JButton newListingBtn;                    
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
    // End of variables declaration                   
}
