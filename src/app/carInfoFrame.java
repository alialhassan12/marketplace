package app;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.net.URL;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import controllers.carInfo;

public class carInfoFrame extends javax.swing.JFrame {
    private int owner_id;
    private int client_id;
    private int car_id;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(home.class.getName());

    public carInfoFrame(int owner_id,int client_id,int car_id) {
        this.owner_id=owner_id;
        this.client_id=client_id;
        this.car_id=car_id;
        initComponents();
    }

    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        carDetailsPanel = new javax.swing.JPanel();
        cardetailslabel = new javax.swing.JLabel();
        brandlabel = new javax.swing.JLabel();
        brandValueLabel = new javax.swing.JLabel();
        modellabel = new javax.swing.JLabel();
        modelValueLabel = new javax.swing.JLabel();
        yearLabel = new javax.swing.JLabel();
        yearValueLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        priceValueLabel = new javax.swing.JLabel();
        locationLabel = new javax.swing.JLabel();
        locationValueLabel = new javax.swing.JLabel();
        vehicalDescriptionPanel = new javax.swing.JPanel();
        vehicalDescLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        sellerInfoPanel = new javax.swing.JPanel();
        sellerInfoLabel = new javax.swing.JLabel();
        sellerNameLabel = new javax.swing.JLabel();
        phoneNumberLabel = new javax.swing.JLabel();
        contactBtn = new javax.swing.JButton();
        imagesPanel=new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        imagesPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        carInfo carInfo=new carInfo(this.car_id);

        try{
            ResultSet carImages=carInfo.getCarImages();
            while(carImages.next()){
                String imageName=carImages.getString("image_path");
                URL imageUrl=getClass().getResource("../layout/cars/"+imageName);
                ImageIcon image=new ImageIcon(imageUrl);
                Image scaled=image.getImage().getScaledInstance(320, 220, Image.SCALE_SMOOTH);
                JLabel imageLabel=new JLabel(new ImageIcon(scaled));
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                imagesPanel.add(imageLabel);
            }
        }catch(Exception e){
            System.out.println("Error imagesPanel "+e.getMessage());
        }

        jScrollPane1.setViewportView(imagesPanel);
        jScrollPane1.setBorder(null);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        titleLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        titleLabel.setText("Car Info");

        cardetailslabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cardetailslabel.setText("Car Details");

        brandlabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        brandlabel.setText("Brand ");

        brandValueLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        brandValueLabel.setText("Brand Value");

        modellabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        modellabel.setText("Model");

        modelValueLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        modelValueLabel.setText("Model Value");

        yearLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        yearLabel.setText("Year");

        yearValueLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        yearValueLabel.setText("Year Value");

        priceLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        priceLabel.setText("Price");

        priceValueLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        priceValueLabel.setText("Price Value");

        locationLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        locationLabel.setText("Location");

        locationValueLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        locationValueLabel.setText("location Value");

        javax.swing.GroupLayout carDetailsPanelLayout = new javax.swing.GroupLayout(carDetailsPanel);
        carDetailsPanel.setLayout(carDetailsPanelLayout);
        carDetailsPanelLayout.setHorizontalGroup(
            carDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(carDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardetailslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(carDetailsPanelLayout.createSequentialGroup()
                        .addGroup(carDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(locationLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, carDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(yearLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(brandlabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                .addComponent(modellabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(priceLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(48, 48, 48)
                        .addGroup(carDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(brandValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(modelValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(yearValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(priceValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(locationValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        carDetailsPanelLayout.setVerticalGroup(
            carDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cardetailslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(carDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(brandValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(brandlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(carDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modellabel)
                    .addComponent(modelValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(carDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(yearLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(yearValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(carDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(priceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(priceValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addGroup(carDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(locationValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(locationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        vehicalDescLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        vehicalDescLabel.setText("Vehical Description");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Luxury SUV, in excellent condition.");

        javax.swing.GroupLayout vehicalDescriptionPanelLayout = new javax.swing.GroupLayout(vehicalDescriptionPanel);
        vehicalDescriptionPanel.setLayout(vehicalDescriptionPanelLayout);
        vehicalDescriptionPanelLayout.setHorizontalGroup(
            vehicalDescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicalDescriptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vehicalDescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vehicalDescLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        vehicalDescriptionPanelLayout.setVerticalGroup(
            vehicalDescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicalDescriptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vehicalDescLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sellerInfoLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sellerInfoLabel.setText("Seller Information");

        sellerNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sellerNameLabel.setText("Seller Name");

        phoneNumberLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        phoneNumberLabel.setText("Phone number");

        contactBtn.setText("Contact Seller");
        contactBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sellerInfoPanelLayout = new javax.swing.GroupLayout(sellerInfoPanel);
        sellerInfoPanel.setLayout(sellerInfoPanelLayout);
        sellerInfoPanelLayout.setHorizontalGroup(
            sellerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sellerInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sellerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sellerInfoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(sellerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(phoneNumberLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sellerNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                    .addComponent(contactBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        sellerInfoPanelLayout.setVerticalGroup(
            sellerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sellerInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sellerInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sellerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(phoneNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(contactBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(carDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vehicalDescriptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sellerInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sellerInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vehicalDescriptionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(carDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void contactBtnActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    // Variables declaration - do not modify                     
    private javax.swing.JLabel brandValueLabel;
    private javax.swing.JLabel brandlabel;
    private javax.swing.JPanel carDetailsPanel;
    private javax.swing.JLabel cardetailslabel;
    private javax.swing.JButton contactBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JLabel locationValueLabel;
    private javax.swing.JLabel modelValueLabel;
    private javax.swing.JLabel modellabel;
    private javax.swing.JLabel phoneNumberLabel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JLabel priceValueLabel;
    private javax.swing.JLabel sellerInfoLabel;
    private javax.swing.JPanel sellerInfoPanel;
    private javax.swing.JLabel sellerNameLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel vehicalDescLabel;
    private javax.swing.JPanel vehicalDescriptionPanel;
    private javax.swing.JLabel yearLabel;
    private javax.swing.JLabel yearValueLabel;
    private javax.swing.JPanel imagesPanel;
    // End of variables declaration                   
}

