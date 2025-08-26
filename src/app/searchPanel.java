package app;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;

import javax.swing.*;

import controllers.search;
public class searchPanel extends javax.swing.JPanel {
    private mainFrame parent;
    private int clientId;
    private String name;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(searchPanel.class.getName());
    public searchPanel(mainFrame parent,int client_id) {
        this.parent=parent;
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
        searchBtn2 = new javax.swing.JButton();
        newListingBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        searchtext = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        brandComboBox = new javax.swing.JComboBox<>();
        brandLabel = new javax.swing.JLabel();
        priceLaybel = new javax.swing.JLabel();
        priceComboBox1 = new javax.swing.JComboBox<>();
        yearLaybel = new javax.swing.JLabel();
        yearComboBox2 = new javax.swing.JComboBox<>();
        resultLabel=new javax.swing.JLabel();
        resultScrollPane=new javax.swing.JScrollPane();
        resultPanel =new javax.swing.JPanel();

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
        logoLabel.setFont(new java.awt.Font("Segoe UI", 1, 24));

        HomeBtn.setFont(new java.awt.Font("Segoe UI", 0, 18));
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
        searchBtn.setFont(new Font(getName(),Font.BOLD,24));
        searchBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                
            }
        });
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuyBtnActionPerformed(evt);
            }
        });

        newListingBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        newListingBtn.setText("New Listing");
        newListingBtn.setBorder(null);
        newListingBtn.setBorderPainted(false);
        newListingBtn.setContentAreaFilled(false);
        newListingBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newListingBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                newListingBtn.setBorderPainted(true);
                newListingBtn.setContentAreaFilled(true);
                newListingBtn.setFont(new Font(getName(),Font.BOLD,24));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                newListingBtn.setBorderPainted(false);
                newListingBtn.setContentAreaFilled(false);
                newListingBtn.setFont(new Font(getName(),Font.PLAIN,18));
            }
        });
        newListingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parent.switchPages("newListing");
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

        titleLabel.setFont(new java.awt.Font("Segoe UI", 0, 24));
        titleLabel.setText("Search Cars ");

        searchtext.setText("search");
        searchtext.setBorder(BorderFactory.createLineBorder(new Color(79, 100, 111)));
        searchtext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchtextActionPerformed(evt);
            }
        });

        resultLabel.setText("Results: ");
        resultLabel.setFont(new Font(getName(),Font.BOLD,24));
        
        resultPanel.setLayout(new GridLayout(0,3,10,10));

        searchBtn2.setText("Search");
        searchBtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        searchBtn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                search search=new search();
            try{
                int carBrandIndex=brandComboBox.getSelectedIndex();
                String carBrand=brandComboBox.getItemAt(carBrandIndex);
                
                int carYearIndex=yearComboBox2.getSelectedIndex();
                int carYear=yearComboBox2.getItemAt(carYearIndex);

                int carPriceIndex=priceComboBox1.getSelectedIndex();
                double carPrice=priceComboBox1.getItemAt(carPriceIndex);
                System.out.println("carBrand: "+carBrand+" carYear: "+carYear+" carPrice: "+carPrice);

                ResultSet searchCar=search.searchCar(carBrand,carYear,carPrice);
                
                resultPanel.removeAll();

                if(!searchCar.isBeforeFirst()){
                    JLabel emptyLabel=new JLabel("NO Results Found...");
                    emptyLabel.setFont(new Font(getName(),0,18));
                    resultPanel.add(emptyLabel);
                }else{
                    while (searchCar.next()){
                        RoundedPanel card=new RoundedPanel(10);
                        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
                        String imageName=searchCar.getString("image_path");
                        URL imageUrl=getClass().getResource("../layout/uploads/carImages/"+imageName);
                        if(imageUrl != null){
                            ImageIcon image=new ImageIcon(imageUrl);
                            Image scaled=image.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
                            JLabel imageLabel=new JLabel(new ImageIcon(scaled));
                            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                            card.add(imageLabel);
                        }else{
                            card.add(new JLabel("no image"));
                        }
                        JLabel cardBrandLabel=new JLabel(searchCar.getString("brand"));
                        cardBrandLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        card.add(cardBrandLabel);
                        card.add(Box.createVerticalStrut(5));
                        JLabel cardModelLbel=new JLabel(searchCar.getString("model"));
                        cardModelLbel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        card.add(cardModelLbel);
                        card.add(Box.createVerticalStrut(5));
                        String year=Integer.toString(searchCar.getInt("year"));
                        JLabel cardYearJlabel=new JLabel(year);
                        cardYearJlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        card.add(cardYearJlabel);
                        card.add(Box.createVerticalStrut(10));
                        card.setBorder (BorderFactory.createEmptyBorder(10,10,10,10));
                        JButton moreBtn = new JButton("Show More");
                        moreBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        moreBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        int ownerId=searchCar.getInt("owner_id");
                        int carId=searchCar.getInt("car_id");
                        moreBtn.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e){
                                try{
                                    System.out.println("owner: "+ownerId+" carId: "+carId);
                                    carInfoFrame carInfo = new carInfoFrame(ownerId,clientId,carId);
                                    carInfo.setSize(959, 608);//car info frame size
                                    carInfo.setResizable(false);
                                    carInfo.setVisible(true);
                                    carInfo.setLocationRelativeTo(null);
                                }catch(Exception ex){
                                    System.out.println("error loading car Info "+ex.getMessage());
                                }
                            }
                        });

                        card.add(moreBtn);
                        resultPanel.add(card);
                    }
                    
                }
                resultPanel.revalidate();// re-run layout manager
                resultPanel.repaint();  // redraw
            }catch(Exception ex){
                System.out.println("Error getting search Result: "+ex.getMessage());
            }}});

            

        brandComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Toyota", "BMW", "Ford", "Tesla", "Honda", "Mercedes-Benz", "Audi", "Nissan" }));

        brandLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        brandLabel.setText("Brand:");

        priceLaybel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        priceLaybel.setText("Price:");

        priceComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new Double[] { 12000.50, 15000.00, 9500.75, 40000.00, 32000.99, 35000.00, 29.999, 33.200, 38.450, 42.000, 47.600, 53.900, 61.000, 74.500, 89.999 }));
        priceComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceComboBox1ActionPerformed(evt);
            }
        });

        yearLaybel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        yearLaybel.setText("Year:");

        yearComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new Integer[] { 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024, 2025 }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(searchtext, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(brandLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(searchBtn2)
                    .addComponent(brandComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brandLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceLaybel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearLaybel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        

        
        resultScrollPane.setViewportView(resultPanel);
        resultScrollPane.setBorder(null);
        resultScrollPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        resultScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        resultScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resultScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(resultScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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
    private javax.swing.JButton searchBtn;
    private javax.swing.JButton HomeBtn;
    private javax.swing.JComboBox<String> brandComboBox;
    private javax.swing.JLabel brandLabel;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JPanel resultPanel;
    private javax.swing.JScrollPane resultScrollPane;
    private javax.swing.JButton moreBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JComboBox<Double> priceComboBox1;
    private javax.swing.JLabel priceLaybel;
    private javax.swing.JButton profileBtn;
    private javax.swing.JButton searchBtn2;
    private javax.swing.JTextField searchtext;
    private javax.swing.JButton newListingBtn;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JComboBox<Integer> yearComboBox2;
    private javax.swing.JLabel yearLaybel;
    // End of variables declaration                   
}
