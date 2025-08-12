import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class error extends JFrame {
    JPanel panel = new JPanel();

    error(String error) {
        setLayout(new GridBagLayout());
        setVisible(true);
        setSize(400, 120);
        setResizable(false);
        setLocationRelativeTo(null);

        JLabel errorMsg = new JLabel(error);
        errorMsg.setFont(new Font("Arial",Font.BOLD,19));
        errorMsg.setForeground(Color.red);
        errorMsg.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton ok = new JButton("OK");
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);// centering
        ok.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panel.add(errorMsg);
        panel.add(Box.createVerticalStrut(10));
        panel.add(ok);
        panel.add(Box.createVerticalGlue());

        add(panel);

        getRootPane().setDefaultButton(ok);

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();// close current window
            }
        });
    }
}
