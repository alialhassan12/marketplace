package app;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class supportFrame extends JFrame{
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private int clientId;
    private Connection connect=config.getConnection();

    public supportFrame(int clientId) {
        this.clientId = clientId;

        setLayout(new BorderLayout());
        setBackground(new Color(52, 52, 52));

        // Chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setBackground(new Color(24, 24, 24));
        chatArea.setForeground(Color.WHITE);
        chatArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chatArea.setCursor(null);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Input field
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputField = new JTextField();
        sendButton = new JButton("Send");
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        // Load previous messages
        loadMessages();

        // // Send button action
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                sendMessage();
            }
        });
        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                sendMessage();
            }
        });

        setSize(800,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Support chat");
        ImageIcon image = new ImageIcon(getClass().getResource("../layout/frameIcon.png"));
        Image Icon = image.getImage();
        setIconImage(Icon);
        setVisible(true);
    }
    private void loadMessages() {
        try {
            chatArea.setText("");
            String query = "SELECT m.message_content, r.reply_content " +
                        "FROM messages m LEFT JOIN replies r ON m.message_id = r.message_id " +
                        "WHERE m.client_id = "+this.clientId+" ORDER BY m.message_id ASC";
            Statement stmt=connect.createStatement();
            ResultSet rs=stmt.executeQuery(query);

            while (rs.next()) {
                String userMsg = rs.getString("message_content");
                String adminReply = rs.getString("reply_content");

                chatArea.append("You: " + userMsg + "\n");
                if (adminReply != null) {
                    chatArea.append("\nAdmin: " + adminReply + "\n");
                }
                chatArea.append("\n");
            }
        } catch (SQLException ex) {
            System.out.println("error Loading Messages "+ex.getMessage());
        }
    }

    private void sendMessage() {
        String message = inputField.getText().trim();
        if (message.isEmpty()) return;

        try {
            String insert = "INSERT INTO messages (client_id, message_content, message_type) "+
                            "VALUES ("+this.clientId+", '"+message+"', 'support')";
            Statement stmt=connect.createStatement();
            stmt.execute(insert);

            inputField.setText("");
            loadMessages(); // Refresh chat
        } catch (SQLException ex) {
            System.out.println("error sending Message "+ex.getMessage());
        }
    }

}
