package app;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

import controllers.getCars;
public class homePanel extends javax.swing.JPanel {
    private mainFrame parent;
    private int clientId;
    private String name;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(homePanel.class.getName());
    public homePanel(mainFrame parent,int client_id) {
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
        searchBtn = new javax.swing.JButton();
        newListingBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        featuredLabel = new javax.swing.JLabel();
        featuredPanel = new javax.swing.JPanel();
        latestPanel =new javax.swing.JPanel();


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
                profileBtn.setFont(new Font(getName(),Font.PLAIN,18));
            }
        });
        profileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parent.switchPages("Profile");
                // System.out.println("clientId :"+clientId);
                // profileFrame profile = new profileFrame(clientId);
                //     profile.setVisible(true);
                //     setVisible(false);
                //     profile.setSize(1280, 750);
                //     profile.setLocationRelativeTo(null);
                //     profile.setExtendedState(JFrame.MAXIMIZED_BOTH);
                //     profile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                // searchFrame searchFrame = new searchFrame(clientId);
                // setVisible(false);
                // searchFrame.setLocationRelativeTo(null);
                // searchFrame.setSize(1280, 750);
                // searchFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                // searchFrame.setVisible(true);
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
        titleLabel.setText("Home");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    )
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        featuredLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        featuredLabel.setText("Featured Cars");
        featuredLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        featuredLabel.setText("Featured Cars");

        featuredPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));

        getCars getCars=new getCars();
        
        //getting featured cars
        getCars.getFeaturedCars().thenAccept(result->{
            try{
                while(result.next() ){
                    RoundedPanel card=new RoundedPanel(10);
                    card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
                    
                    String imageName=result.getString("image_path");
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
                    JLabel cardBrandLabel=new JLabel(result.getString("brand"));
                    cardBrandLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    card.add(cardBrandLabel);
                    card.add(Box.createVerticalStrut(5));
                    JLabel cardModelLbel=new JLabel(result.getString("model"));
                    cardModelLbel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    card.add(cardModelLbel);
                    card.add(Box.createVerticalStrut(5));
                    String year=Integer.toString(result.getInt("year"));
                    JLabel cardYearJlabel=new JLabel(year);
                    cardYearJlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    card.add(cardYearJlabel);
                    card.add(Box.createVerticalStrut(10));
                    card.setBorder (BorderFactory.createEmptyBorder(10,10,10,10));
                    moreBtn=new JButton("Show More");
                    moreBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    moreBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                    int ownerId=result.getInt("owner_id");
                    int carId=result.getInt("car_id");
                    moreBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            try{
                                // System.out.println("owner: "+ownerId+" carId: "+carId);
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
                    featuredPanel.add(card);
                    //refresh panel
                    featuredPanel.revalidate();
                    featuredPanel.repaint();
                    
                }
            }catch(Exception e){
                    System.out.println("error getting Featured cars "+e.getMessage());
                }
        });

        featuredScrollPane=new JScrollPane(featuredPanel);
        featuredScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        featuredScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        featuredScrollPane.setBorder(null);

        LatestListingsLabel = new JLabel("Latest Listings");
        LatestListingsLabel.setFont(new java.awt.Font("Segoe UI", 1, 24));

        latestPanel = new JPanel();
        latestPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));
        
        //adding the Latset Listing
            getCars.getLatestCars().thenAccept(result->{
                try{
                    while(result.next()){
                        RoundedPanel card=new RoundedPanel(10);
                        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
                        
                        String imageName=result.getString("image_path");
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
                        JLabel cardBrandLabel=new JLabel(result.getString("brand"));
                        cardBrandLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        card.add(cardBrandLabel);
                        card.add(Box.createVerticalStrut(5));
                        JLabel cardModelLbel=new JLabel(result.getString("model"));
                        cardModelLbel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        card.add(cardModelLbel);
                        card.add(Box.createVerticalStrut(5));
                        String year=Integer.toString(result.getInt("year"));
                        JLabel cardYearJlabel=new JLabel(year);
                        cardYearJlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        card.add(cardYearJlabel);
                        card.add(Box.createVerticalStrut(10));
                        card.setBorder (BorderFactory.createEmptyBorder(10,10,10,10));
                        moreBtn=new JButton("Show More");
                        moreBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        moreBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        int ownerId=result.getInt("owner_id");
                        int carId=result.getInt("car_id");
                        moreBtn.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e){
                                try{
                                    // System.out.println("owner: "+ownerId+" carId: "+carId);
                                    SwingUtilities.invokeLater(()->{
                                        carInfoFrame carInfo = new carInfoFrame(ownerId,clientId,carId);
                                        carInfo.setSize(959, 608);//car info frame size
                                        carInfo.setResizable(false);
                                        carInfo.setVisible(true);
                                        carInfo.setLocationRelativeTo(null);
                                    });
                                }catch(Exception ex){
                                    System.out.println("error loading car Info "+ex.getMessage());
                                }
                            }
                        });
                        SwingUtilities.invokeLater(()->{
                            card.add(moreBtn);
                            latestPanel.add(card);
                            //refresh panel
                            featuredPanel.revalidate();
                            featuredPanel.repaint();
                        });
                    }
                }catch(Exception e){
                    System.out.println("Error getting latest listing "+e.getMessage());
                }
            });
            


        latestScrollPane = new JScrollPane(latestPanel);
        latestScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        latestScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        latestScrollPane.setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JButton profileBtn;
    private javax.swing.JButton newListingBtn;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration                   
}
