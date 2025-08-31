package app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import controllers.carInfo;
import controllers.editCar;

public class editCarFrame extends javax.swing.JFrame {
    private int client_id;
    private int car_id;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(carInfoFrame.class.getName());

    public editCarFrame(int client_id,int car_id) {
        this.client_id=client_id;
        this.car_id=car_id;
        initComponents();
    }

    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        cardetailslabel = new javax.swing.JLabel();
        brandlabel = new javax.swing.JLabel();
        yearLabel = new javax.swing.JLabel();
        modellabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        transactionLabel = new javax.swing.JLabel();
        locationLabel = new javax.swing.JLabel();
        vehicalDescLabel = new javax.swing.JLabel();
        sellerInfoLabel = new javax.swing.JLabel();
        sellerNameLabel = new javax.swing.JLabel();
        phoneNumberLabel = new javax.swing.JLabel();

        brandValueLabel = new javax.swing.JTextField();
        modelValueLabel = new javax.swing.JTextField();
        yearValueLabel = new javax.swing.JTextField();
        priceValueLabel = new javax.swing.JTextField();
        locationValueLabel = new javax.swing.JTextField();
        transactionValueLabel = new javax.swing.JTextField();
        vehicalDescValue = new javax.swing.JTextArea();

        carDetailsPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        vehicalDescriptionPanel = new javax.swing.JPanel();
        sellerInfoPanel = new javax.swing.JPanel();
        ownerPanel=new javax.swing.JPanel();
        imagesPanel=new javax.swing.JPanel();
        
        jScrollPane1 = new javax.swing.JScrollPane();

        contactBtn = new javax.swing.JButton();
        submitEdit=new javax.swing.JButton("Submit Edits");

        editCar editCarInfo=new editCar(this.car_id);

        submitEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(editCarInfo.editCarInfo(brandValueLabel.getText(),
                    modelValueLabel.getText(),priceValueLabel.getText(),
                    yearValueLabel.getText(),vehicalDescValue.getText(),
                    locationValueLabel.getText(),
                    transactionValueLabel.getText())){
                    JOptionPane.showMessageDialog(null,"Car Edited Successfully");
                    dispose();
                }
            }
        });
        
        
        titlePanel.setLayout(new BorderLayout());
        
        titleLabel.setFont(new java.awt.Font("Segoe UI", 0, 24));
        titleLabel.setText("Car Info");

        titlePanel.add(titleLabel,BorderLayout.LINE_START);
        titlePanel.add(submitEdit,BorderLayout.LINE_END);

        cardetailslabel.setFont(new java.awt.Font("Segoe UI", 1, 18));
        cardetailslabel.setText("Car Details");

        brandlabel.setFont(new java.awt.Font("Segoe UI", 0, 18));
        brandlabel.setText("Brand ");

        brandValueLabel.setFont(new java.awt.Font("Segoe UI", 0, 18));

        modellabel.setFont(new java.awt.Font("Segoe UI", 0, 18));
        modellabel.setText("Model");

        modelValueLabel.setFont(new java.awt.Font("Segoe UI", 0, 18));

        yearLabel.setFont(new java.awt.Font("Segoe UI", 0, 18));
        yearLabel.setText("Year");

        yearValueLabel.setFont(new java.awt.Font("Segoe UI", 0, 18));

        priceLabel.setFont(new java.awt.Font("Segoe UI", 0, 18));
        priceLabel.setText("Price");

        priceValueLabel.setFont(new java.awt.Font("Segoe UI", 0, 18));

        locationLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); 
        locationLabel.setText("Location");

        locationValueLabel.setFont(new java.awt.Font("Segoe UI", 0, 18));

        transactionLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); 
        transactionLabel.setText("Transaction");

        transactionValueLabel.setFont(new java.awt.Font("Segoe UI", 0, 18));

        imagesPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));

        carInfo carInfo=new carInfo(this.car_id);

            carInfo.getCarImages().thenAccept(resultSet->{
                try{
                    while(resultSet.next()){
                        String imageName=resultSet.getString("image_path");
                        URL imageUrl=getClass().getResource("../layout/uploads/carImages/"+imageName);
                        ImageIcon image=new ImageIcon(imageUrl);
                        Image scaled=image.getImage().getScaledInstance(320, 220, Image.SCALE_SMOOTH);
                        JLabel imageLabel=new JLabel(new ImageIcon(scaled));
                        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        SwingUtilities.invokeLater(()->{
                            imagesPanel.add(imageLabel);
                            imagesPanel.revalidate();
                            imagesPanel.repaint();
                        });
                    }
                }catch(Exception e){
                    System.out.println("Error getting carImages "+e.getMessage());
                }
            });

            carInfo.getCarInfo().thenAccept(resultSet->{
                try{
                    if(resultSet.next()){
                        SwingUtilities.invokeLater(()->{
                            try{
                                brandValueLabel.setText(resultSet.getString("brand"));
                                modelValueLabel.setText(resultSet.getString("model"));
                                yearValueLabel.setText(resultSet.getString("year"));
                                priceValueLabel.setText(resultSet.getString("price"));
                                locationValueLabel.setText(resultSet.getString("location"));
                                vehicalDescValue.setText(resultSet.getString("description"));
                                transactionValueLabel.setText(resultSet.getString("transaction"));
                            }catch(Exception e){
                            System.out.println(e.getMessage());
                            }
                        });
                    }
                }catch(Exception e){
                    System.out.println("Error getting car details "+e.getMessage());
                }
            });

            carInfo.getOwnerInfo(this.client_id).thenAccept(resultSet->{
                try{
                    if(resultSet.next()){
                        SwingUtilities.invokeLater(()->{
                            try{
                                sellerNameLabel.setText(resultSet.getString("name"));
                                phoneNumberLabel.setText(resultSet.getString("phone"));
                                ImageIcon ownerImage=new ImageIcon(resultSet.getString("profile_image"));
                                Image scaled=ownerImage.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                                JLabel profileLabel=new JLabel(new ImageIcon(scaled));
                                ownerPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
                                ownerPanel.add(profileLabel);
                                ownerPanel.add(sellerNameLabel);
                            }catch(Exception e){
                                System.out.println(e.getMessage());
                            }
                        });
                    }
                }catch(Exception e){
                    System.out.println("Error seller info"+e.getMessage());
                }
            });

        jScrollPane1.setViewportView(imagesPanel);
        jScrollPane1.setBorder(null);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


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
                            .addComponent(transactionLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addComponent(locationValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(transactionValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            )))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        carDetailsPanelLayout.setVerticalGroup(
            carDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cardetailslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(carDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(brandValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(brandlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(carDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modellabel)
                    .addComponent(modelValueLabel))
                .addGap(5, 5, 5)
                .addGroup(carDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(yearLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(yearValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(carDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(priceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(priceValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(carDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(locationValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(locationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(carDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transactionValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(transactionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        vehicalDescLabel.setFont(new java.awt.Font("Segoe UI", 1, 18));
        vehicalDescLabel.setText("Vehical Description");

        vehicalDescValue.setFont(new java.awt.Font("Segoe UI", 0, 14));
        vehicalDescValue.setLineWrap(true);
        vehicalDescValue.setWrapStyleWord(true);
        JScrollPane descriptionValuePane =new JScrollPane(
            vehicalDescValue,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        //vehicleDescPanel layout
        javax.swing.GroupLayout vehicalDescriptionPanelLayout = new javax.swing.GroupLayout(vehicalDescriptionPanel);
        vehicalDescriptionPanel.setLayout(vehicalDescriptionPanelLayout);
        vehicalDescriptionPanelLayout.setHorizontalGroup(
            vehicalDescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicalDescriptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vehicalDescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vehicalDescLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descriptionValuePane, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    
                    .addContainerGap(59, Short.MAX_VALUE)
                )
        );
        vehicalDescriptionPanelLayout.setVerticalGroup(
            vehicalDescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicalDescriptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vehicalDescLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(descriptionValuePane, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                
                )
        );

        sellerInfoLabel.setFont(new java.awt.Font("Segoe UI", 1, 18));
        sellerInfoLabel.setText("Seller Information");

        sellerNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 18));

        phoneNumberLabel.setFont(new java.awt.Font("Segoe UI", 0, 14));

        contactBtn.setText("Contact Seller");
        contactBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
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
                        .addComponent(ownerPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                    .addComponent(contactBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        sellerInfoPanelLayout.setVerticalGroup(
            sellerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sellerInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sellerInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ownerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        setTitle("Car Info");
        ImageIcon image = new ImageIcon(getClass().getResource("../layout/frameIcon.png"));
        Image Icon = image.getImage();
        setIconImage(Icon);
        setSize(959, 608);// car info frame size
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        pack();
    }                      

    // Variables declaration - do not modify                     
    private javax.swing.JLabel brandlabel;
    private javax.swing.JLabel cardetailslabel;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JLabel modellabel;
    private javax.swing.JLabel phoneNumberLabel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JLabel sellerInfoLabel;
    private javax.swing.JLabel sellerNameLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel vehicalDescLabel;
    private javax.swing.JLabel yearLabel;
    private javax.swing.JLabel transactionLabel;
    
    private javax.swing.JTextArea vehicalDescValue;
    private javax.swing.JTextField brandValueLabel;
    private javax.swing.JTextField locationValueLabel;
    private javax.swing.JTextField modelValueLabel;
    private javax.swing.JTextField priceValueLabel;
    private javax.swing.JTextField yearValueLabel;
    private javax.swing.JTextField transactionValueLabel;
    
    
    private javax.swing.JPanel carDetailsPanel;
    private javax.swing.JPanel sellerInfoPanel;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JPanel vehicalDescriptionPanel;
    private javax.swing.JPanel imagesPanel;
    private javax.swing.JPanel ownerPanel;
    
    private javax.swing.JScrollPane jScrollPane1;

    private javax.swing.JButton contactBtn;
    private javax.swing.JButton submitEdit;
    // End of variables declaration                   
}

