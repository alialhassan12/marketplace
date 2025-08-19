
package app;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import controllers.changePassword;

public class changePasswordFrame extends javax.swing.JFrame {
    private int client_id;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(changePasswordFrame.class.getName());

    public changePasswordFrame(int client_id) {
        this.client_id=client_id;
        initComponents();
    }

    private void initComponents() {

        titlelabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        newPasswordLabel = new javax.swing.JLabel();
        newPasswordLabel1 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        confirmBtn=new JButton("Confirm");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titlelabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        titlelabel.setText("Change Password");

        newPasswordLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        newPasswordLabel.setText("New Password: ");

        newPasswordLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        newPasswordLabel1.setText("Confirm New Password:");

        confirmBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String newPassword=new String(jPasswordField1.getPassword());
                String confirmPass=new String(jPasswordField2.getPassword());
                changePassword change=new changePassword(client_id, newPassword, confirmPass);
                if(change.change()){
                    JOptionPane.showMessageDialog(null, "Password changed Successfully!");
                    setVisible(false);
                }else{
                    jPasswordField1.setText("");
                    jPasswordField2.setText("");
                }
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newPasswordLabel)
                    .addComponent(jPasswordField2)
                    .addComponent(newPasswordLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordField1)
                    .addComponent(confirmBtn))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(newPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(newPasswordLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18,18,18)
                .addComponent(confirmBtn)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titlelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    // Variables declaration - do not modify                     
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JLabel newPasswordLabel;
    private javax.swing.JLabel newPasswordLabel1;
    private javax.swing.JLabel titlelabel;
    private javax.swing.JButton confirmBtn;
    // End of variables declaration                   
}
