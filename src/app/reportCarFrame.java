package app;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controllers.reports;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class reportCarFrame extends JFrame{
    private int client_id;
    private int owner_id;
    private int reportedCar_id;
    public reportCarFrame(int client_id,int owner_id,int reportedCar_id){
        this.client_id=client_id;
        this.owner_id=owner_id;
        this.reportedCar_id=reportedCar_id;

        setResizable(false);
        setPreferredSize(new Dimension(500,300));
        setLocationRelativeTo(null);

        reports reports =new reports(this.client_id, this.owner_id,this.reportedCar_id);

        setTitle("Report Car");
        JLabel titlelabel=new JLabel("Report Car");
        titlelabel.setFont(new java.awt.Font("Segoe UI", 1, 24));

        JLabel reasonLabel=new JLabel("Reason ");
        JTextArea reasonText=new JTextArea();

        reasonLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); 
        reasonLabel.setPreferredSize(new java.awt.Dimension(69, 32));

        reasonText.setLineWrap(true);
        reasonText.setWrapStyleWord(true);
        JScrollPane reasonPane =new JScrollPane(
            reasonText,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        reasonPane.setPreferredSize(new Dimension(400,100));
        reasonPane.setMaximumSize(new Dimension(400,120));
        reasonPane.setMinimumSize(new Dimension(400,120));

        JButton submitBtn=new JButton("Submit Report");
        submitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        titlelabel.setAlignmentX(CENTER_ALIGNMENT);
        contentPanel.add(titlelabel);
        contentPanel.add(Box.createVerticalStrut(20));
        reasonLabel.setAlignmentX(CENTER_ALIGNMENT);
        contentPanel.add(reasonLabel);
        contentPanel.add(Box.createVerticalStrut(5));
        reasonPane.setAlignmentX(CENTER_ALIGNMENT);
        contentPanel.add(reasonPane);
        contentPanel.add(Box.createVerticalStrut(10));
        submitBtn.setAlignmentX(CENTER_ALIGNMENT);
        contentPanel.add(submitBtn);

        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String messageContent=reasonText.getText();
                if(reports.sendReport(messageContent)){
                    JOptionPane.showMessageDialog(null, "Car Reported");
                    dispose();
                }
            }
        });

        setContentPane(contentPanel);
        pack();
        setVisible(true);
    }
}
