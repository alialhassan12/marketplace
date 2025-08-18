package app;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import java.sql.ResultSet;

import javax.swing.*;

import controllers.getCars;
public class home extends javax.swing.JFrame {
    private int clientId;
    private String name;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(home.class.getName());
    public home(int client_id) {
        this.clientId=client_id;
        initComponents();
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ImageIcon logo=new ImageIcon(getClass().getResource("../layout/logo2.png"));
        Image logoImage=logo.getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH);
        logoLabel = new javax.swing.JLabel(new ImageIcon(logoImage));
        HomeBtn=new javax.swing.JButton();
        profileBtn = new javax.swing.JButton();
        BuyBtn = new javax.swing.JButton();
        sellBtn = new javax.swing.JButton();
        rentBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        searchtext = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        brandComboBox = new javax.swing.JComboBox<>();
        brandLaybel = new javax.swing.JLabel();
        priceLaybel = new javax.swing.JLabel();
        priceComboBox1 = new javax.swing.JComboBox<>();
        yearLaybel = new javax.swing.JLabel();
        yearComboBox2 = new javax.swing.JComboBox<>();
        featuredLabel = new javax.swing.JLabel();
        featuredPanel = new javax.swing.JPanel();
        latestPanel =new javax.swing.JPanel();

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
        HomeBtn.setFont(new Font(getName(),Font.BOLD,24));
        HomeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        HomeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                
            }
        });
        HomeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileBtnActionPerformed(evt);
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
                profileBtn.setFont(new Font(getName(),0,24));
            }
        });
        profileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileFrame profile = new profileFrame(clientId);
                    profile.setVisible(true);
                    setVisible(false);
                    profile.setSize(1280, 750);
                    profile.setLocationRelativeTo(null);
                    profile.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    profile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        titleLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        titleLabel.setText("Home");

        searchtext.setText("search");
        searchtext.setBorder(BorderFactory.createLineBorder(new Color(79, 100, 111)));
        searchtext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchtextActionPerformed(evt);
            }
        });

        searchBtn.setText("Search");
        searchBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        brandComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Toyota", "BMW", "Ford", "Tesla", "Honda", "Mercedes-Benz", "Audi", "Nissan" }));

        brandLaybel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        brandLaybel.setText("Brand:");

        priceLaybel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        priceLaybel.setText("Price:");

        priceComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8,500$", "12,300$", "15,000$", "18,750$", "22,900$", "25,500$", "29,999$", "33,200$", "38,450$", "42,000$", "47,600$", "53,900$", "61,000$", "74,500$", "89,999$" }));
        priceComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceComboBox1ActionPerformed(evt);
            }
        });

        yearLaybel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        yearLaybel.setText("Year:");

        yearComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(searchtext, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(brandLaybel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(brandComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(priceLaybel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(priceComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearLaybel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchtext, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn)
                    .addComponent(brandComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brandLaybel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceLaybel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearLaybel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        featuredLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        featuredLabel.setText("Featured Cars");
        featuredLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        featuredLabel.setText("Featured Cars");

        featuredPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));

        getCars getCars=new getCars();
        
        try{
            ResultSet AllCars= getCars.getAllCars();
            ResultSet AllPrimaryCarImages=getCars.getPrimaryCarImages();
            while(AllCars.next() && AllPrimaryCarImages.next()){
                RoundedPanel card=new RoundedPanel(10);
                card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

                String imageName=AllPrimaryCarImages.getString("image_path");
                URL imageUrl=getClass().getResource("../layout/cars/"+imageName);
                if(imageUrl != null){
                    ImageIcon image=new ImageIcon(imageUrl);
                    Image scaled=image.getImage().getScaledInstance(150, 90, Image.SCALE_SMOOTH);
                    JLabel imageLabel=new JLabel(new ImageIcon(scaled));
                    imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    card.add(imageLabel);
                }else{
                    card.add(new JLabel("no image"));
                }
                JLabel cardBrandLabel=new JLabel(AllCars.getString("brand"));
                cardBrandLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                card.add(cardBrandLabel);
                card.add(Box.createVerticalStrut(5));
                JLabel cardModelLbel=new JLabel(AllCars.getString("model"));
                cardModelLbel.setAlignmentX(Component.CENTER_ALIGNMENT);
                card.add(cardModelLbel);
                card.add(Box.createVerticalStrut(5));
                String year=Integer.toString(AllCars.getInt("year"));
                JLabel cardYearJlabel=new JLabel(year);
                cardYearJlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                card.add(cardYearJlabel);
                card.add(Box.createVerticalStrut(10));
                card.setBorder (BorderFactory.createEmptyBorder(10,10,10,10));
                moreBtn=new JButton("Show More");
                moreBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                moreBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                card.add(moreBtn);
                featuredPanel.add(card);
            }
            //refresh panel
            featuredPanel.revalidate();
            featuredPanel.repaint();
        }catch(Exception e){
            System.out.println("Featured Panel Error : "+e.getMessage());
        }

        featuredScrollPane=new JScrollPane(featuredPanel);
        featuredScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        featuredScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        featuredScrollPane.setBorder(null);

        LatestListingsLabel = new JLabel("Latest Listings");
        LatestListingsLabel.setFont(new java.awt.Font("Segoe UI", 1, 24));

        latestPanel = new JPanel();
        latestPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));

        try{
            ResultSet latestCars= getCars.getLatestCars();
            ResultSet AllPrimaryLatestCarImages=getCars.getLatestPrimaryImages();
            while(latestCars.next() && AllPrimaryLatestCarImages.next()){
                RoundedPanel card=new RoundedPanel(10);
                card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

                String imageName=AllPrimaryLatestCarImages.getString("image_path");
                URL imageUrl=getClass().getResource("../layout/cars/"+imageName);
                if(imageUrl != null){
                    ImageIcon image=new ImageIcon(imageUrl);
                    Image scaled=image.getImage().getScaledInstance(150, 90, Image.SCALE_SMOOTH);
                    JLabel imageLabel=new JLabel(new ImageIcon(scaled));
                    imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    card.add(imageLabel);
                }else{
                    card.add(new JLabel("no image"));
                }
                JLabel cardBrandLabel=new JLabel(latestCars.getString("brand"));
                cardBrandLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                card.add(cardBrandLabel);
                card.add(Box.createVerticalStrut(5));
                JLabel cardModelLbel=new JLabel(latestCars.getString("model"));
                cardModelLbel.setAlignmentX(Component.CENTER_ALIGNMENT);
                card.add(cardModelLbel);
                card.add(Box.createVerticalStrut(5));
                String year=Integer.toString(latestCars.getInt("year"));
                JLabel cardYearJlabel=new JLabel(year);
                cardYearJlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                card.add(cardYearJlabel);
                card.add(Box.createVerticalStrut(10));
                card.setBorder (BorderFactory.createEmptyBorder(10,10,10,10));
                moreBtn=new JButton("Show More");
                moreBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                moreBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                card.add(moreBtn);
                latestPanel.add(card);
            }
            //refresh panel
            featuredPanel.revalidate();
            featuredPanel.repaint();
        }catch(Exception e){
            System.out.println("Featured Panel Error : "+e.getMessage());
        }


        latestScrollPane = new JScrollPane(latestPanel);
        latestScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        latestScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        latestScrollPane.setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(featuredScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                            .addComponent(featuredLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LatestListingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(latestScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE))))
                .addContainerGap(136, Short.MAX_VALUE))
        );
layout.setVerticalGroup(
    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(layout.createSequentialGroup()
        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(featuredLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(featuredScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(LatestListingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(latestScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
);

        pack();
    }// </editor-fold>                        

    private void searchtextActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

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

    private void priceComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void jPanel1AncestorAdded(javax.swing.event.AncestorEvent evt) {                                      
        // TODO add your handling code here:
    }                                                                     

    // Variables declaration - do not modify                     
    private javax.swing.JButton BuyBtn;
    private javax.swing.JButton HomeBtn;
    private javax.swing.JComboBox<String> brandComboBox;
    private javax.swing.JLabel brandLaybel;
    private javax.swing.JLabel featuredLabel;
    private javax.swing.JPanel featuredPanel;
    private javax.swing.JScrollPane featuredScrollPane;
    private javax.swing.JLabel LatestListingsLabel;
    private javax.swing.JScrollPane latestScrollPane;
    private javax.swing.JPanel latestPanel;
    private javax.swing.JButton moreBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JComboBox<String> priceComboBox1;
    private javax.swing.JLabel priceLaybel;
    private javax.swing.JButton profileBtn;
    private javax.swing.JButton rentBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchtext;
    private javax.swing.JButton sellBtn;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JComboBox<String> yearComboBox2;
    private javax.swing.JLabel yearLaybel;
    // End of variables declaration                   
}
