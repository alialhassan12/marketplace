package app;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 * ViewMessagesPanel - Displays messages/reports between clients and admins
 * Note: This assumes you have a 'messages' table in your database
 */
public class ViewMessagesPanel extends JPanel {

    private JTable messagesTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JLabel statusLabel;
    private Connection dbConnection;
    private int adminId;
    private JComboBox<String> filterComboBox;
    private String currentFilter = "All";
    private JTextArea messageContentArea;
    private JSplitPane splitPane;

    public ViewMessagesPanel(Connection dbConnection, int adminId) {
        this.dbConnection = dbConnection;
        this.adminId = adminId;
        initComponents();
        setupTable();
        loadMessagesData();
    }

    private void initComponents() {
        setBackground(new Color(52, 52, 52));
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        // Create split pane for table and message content
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(300);
        splitPane.setBackground(new Color(52, 52, 52));

        // Table Panel (top)
        JPanel tablePanel = createTablePanel();
        splitPane.setTopComponent(tablePanel);

        // Message Content Panel (bottom)
        JPanel contentPanel = createMessageContentPanel();
        splitPane.setBottomComponent(contentPanel);

        add(splitPane, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = createFooterPanel();
        add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(24, 24, 24));
        headerPanel.setPreferredSize(new Dimension(0, 120));
        headerPanel.setLayout(new BorderLayout());

        // Top row with title and back button
        JPanel topRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        topRow.setBackground(new Color(24, 24, 24));

        JLabel titleLabel = new JLabel("Messages & Reports");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        topRow.add(titleLabel);
        topRow.add(Box.createHorizontalStrut(400));

        // Bottom row with filter and action buttons
        JPanel bottomRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        bottomRow.setBackground(new Color(24, 24, 24));

        // Filter label and combo box
        JLabel filterLabel = new JLabel("Filter by Type:");
        filterLabel.setForeground(Color.WHITE);
        filterLabel.setFont(new Font("Dialog", Font.PLAIN, 14));

        // Filter options based on your table's message_type values
        String[] filterOptions = { "All", "support", "inquiry", "complaint", "feedback" };
        filterComboBox = new JComboBox<>(filterOptions);
        filterComboBox.setBackground(new Color(40, 40, 40));
        filterComboBox.setForeground(Color.WHITE);
        filterComboBox.setFont(new Font("Dialog", Font.PLAIN, 14));
        filterComboBox.setPreferredSize(new Dimension(120, 35));
        filterComboBox.addActionListener(e -> {
            currentFilter = (String) filterComboBox.getSelectedItem();
            loadMessagesData();
        });

        // Action buttons
        JButton replyButton = new JButton("Reply");
        replyButton.setBackground(new Color(0, 150, 0));
        replyButton.setForeground(Color.WHITE);
        replyButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        replyButton.setFocusPainted(false);
        replyButton.setBorderPainted(false);
        replyButton.setPreferredSize(new Dimension(80, 35));
        replyButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replyToMessage();
            }
        });

        JButton markReadButton = new JButton("Mark as Read");
        markReadButton.setBackground(new Color(0, 102, 255));
        markReadButton.setForeground(Color.WHITE);
        markReadButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        markReadButton.setFocusPainted(false);
        markReadButton.setBorderPainted(false);
        markReadButton.setPreferredSize(new Dimension(120, 35));
        markReadButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                markAsRead();
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(220, 20, 60));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorderPainted(false);
        deleteButton.setPreferredSize(new Dimension(80, 35));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSelectedMessage();
            }
        });

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(128, 128, 128));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        refreshButton.setFocusPainted(false);
        refreshButton.setBorderPainted(false);
        refreshButton.setPreferredSize(new Dimension(80, 35));
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMessagesData();
            }
        });

        JButton backButton = new JButton("← Back to Dashboard");
        backButton.setBackground(new Color(0, 102, 255));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(180, 35));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Container parent = getTopLevelAncestor();
                if (parent instanceof AdminPage) {
                    ((AdminPage) parent).showPanel("dashboard");
                }
            }
        });

        bottomRow.add(filterLabel);
        bottomRow.add(filterComboBox);
        bottomRow.add(Box.createHorizontalStrut(30));
        bottomRow.add(replyButton);
        bottomRow.add(markReadButton);
        bottomRow.add(deleteButton);
        bottomRow.add(refreshButton);
        bottomRow.add(backButton);

        headerPanel.add(topRow, BorderLayout.NORTH);
        headerPanel.add(bottomRow, BorderLayout.SOUTH);

        return headerPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(52, 52, 52));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Create table model with columns (adjusted for existing table structure)
        String[] columnNames = {
                "Message ID", "Client ID", "Message Type", "Content Preview", "Date"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };

        messagesTable = new JTable(tableModel);
        setupTableAppearance();

        // Add selection listener to show message content
        messagesTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showSelectedMessageContent();
            }
        });

        scrollPane = new JScrollPane(messagesTable);
        scrollPane.getViewport().setBackground(new Color(52, 52, 52));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));

        tablePanel.add(scrollPane, BorderLayout.CENTER);

        return tablePanel;
    }

    private JPanel createMessageContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(52, 52, 52));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel contentLabel = new JLabel("Message Content:");
        contentLabel.setForeground(Color.WHITE);
        contentLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        contentPanel.add(contentLabel, BorderLayout.NORTH);

        messageContentArea = new JTextArea();
        messageContentArea.setBackground(new Color(40, 40, 40));
        messageContentArea.setForeground(Color.WHITE);
        messageContentArea.setFont(new Font("Dialog", Font.PLAIN, 12));
        messageContentArea.setEditable(false);
        messageContentArea.setLineWrap(true);
        messageContentArea.setWrapStyleWord(true);
        messageContentArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane contentScrollPane = new JScrollPane(messageContentArea);
        contentScrollPane.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        contentPanel.add(contentScrollPane, BorderLayout.CENTER);

        return contentPanel;
    }

    private void setupTableAppearance() {
        messagesTable.setBackground(new Color(40, 40, 40));
        messagesTable.setForeground(Color.WHITE);
        messagesTable.setFont(new Font("Dialog", Font.PLAIN, 12));
        messagesTable.setRowHeight(30);
        messagesTable.setShowGrid(true);
        messagesTable.setGridColor(new Color(80, 80, 80));
        messagesTable.setSelectionBackground(new Color(0, 102, 255));
        messagesTable.setSelectionForeground(Color.WHITE);
        messagesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = messagesTable.getTableHeader();
        header.setBackground(new Color(24, 24, 24));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Dialog", Font.BOLD, 13));
        header.setPreferredSize(new Dimension(0, 35));

        // Column widths (adjusted for existing table structure)
        messagesTable.getColumnModel().getColumn(0).setPreferredWidth(80); // Message ID
        messagesTable.getColumnModel().getColumn(1).setPreferredWidth(80); // Client ID
        messagesTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Message Type
        messagesTable.getColumnModel().getColumn(3).setPreferredWidth(250); // Content Preview
        messagesTable.getColumnModel().getColumn(4).setPreferredWidth(120); // Date
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(24, 24, 24));
        footerPanel.setPreferredSize(new Dimension(0, 40));
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        statusLabel = new JLabel("Total Messages: 0");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        footerPanel.add(statusLabel);
        return footerPanel;
    }

    private void setupTable() {
        messagesTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    public void loadMessagesData() {
        tableModel.setRowCount(0);
        messageContentArea.setText("Select a message to view its content.");

        try {
            if (dbConnection != null && !dbConnection.isClosed()) {
                // Query adjusted for existing table structure
                String query = "SELECT m.message_id, m.client_id, m.message_content, m.message_type, m.created_at " +
                        "FROM messages m ";

                // Apply filter if not "All"
                if (!"All".equals(currentFilter)) {
                    query += "WHERE m.message_type = '" + currentFilter + "' ";
                }

                query += "ORDER BY m.created_at DESC";

                Statement stmt = dbConnection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                int messageCount = 0;

                while (rs.next()) {
                    // Create content preview (first 50 characters)
                    String fullContent = rs.getString("message_content");
                    String contentPreview = (fullContent != null && fullContent.length() > 50)
                            ? fullContent.substring(0, 50) + "..."
                            : fullContent;

                    Object[] row = {
                            rs.getInt("message_id"),
                            rs.getInt("client_id"),
                            rs.getString("message_type") != null ? rs.getString("message_type") : "N/A",
                            contentPreview != null ? contentPreview : "No content",
                            rs.getTimestamp("created_at") != null
                                    ? rs.getTimestamp("created_at").toLocalDateTime()
                                            .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                                    : "N/A"
                    };
                    tableModel.addRow(row);
                    messageCount++;
                }

                String statusText = "Total Messages: " + messageCount;
                if (!"All".equals(currentFilter)) {
                    statusText += " (" + currentFilter + " messages)";
                }
                statusLabel.setText(statusText);

                rs.close();
                stmt.close();
            }
        } catch (Exception ex) {
            // If there's an error loading messages, show sample data
            System.err.println("Error loading messages from database: " + ex.getMessage());
            System.err.println("Showing sample data instead.");

            // Show sample data without error dialog
            loadSampleData();
        }
    }

    private void loadSampleData() {
        // Sample data for demonstration
        Object[][] sampleData = {
                { 1, "user123", "John Doe", "Car not working properly", "Report", "Unread", "High",
                        "2024-01-15 10:30" },
                { 2, "jane_doe", "Jane Smith", "Request for car information", "Inquiry", "Read", "Normal",
                        "2024-01-14 15:45" },
                { 3, "mike_wilson", "Mike Wilson", "Payment issue", "Support", "In Progress", "High",
                        "2024-01-13 09:20" },
                { 4, "sarah_jones", "Sarah Jones", "Car rental complaint", "Complaint", "Resolved", "Medium",
                        "2024-01-12 14:10" }
        };

        for (Object[] row : sampleData) {
            tableModel.addRow(row);
        }
        statusLabel.setText("Total Messages: " + sampleData.length + " (Sample Data - Messages table not found)");
    }

    private void showSelectedMessageContent() {
        int selectedRow = messagesTable.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                // Get message ID from the selected row
                int messageId = (Integer) tableModel.getValueAt(selectedRow, 0);
                int clientId = (Integer) tableModel.getValueAt(selectedRow, 1);
                String messageType = (String) tableModel.getValueAt(selectedRow, 2);

                // Fetch the full message content from database
                String query = "SELECT message_content FROM messages WHERE message_id = " + messageId;
                Statement stmt = dbConnection.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                if (rs.next()) {
                    String fullContent = rs.getString("message_content");
                    messageContentArea.setText("Message ID: " + messageId + "\n" +
                                             "Client ID: " + clientId + "\n" +
                                             "Type: " + messageType + "\n" +
                                             "Date: " + tableModel.getValueAt(selectedRow, 4) + "\n\n" +
                                             "Content:\n" + fullContent);
                } else {
                    messageContentArea.setText("Message content not found.");
                }

                rs.close();
                stmt.close();

            } catch (Exception ex) {
                messageContentArea.setText("Error loading message content: " + ex.getMessage());
            }
        } else {
            messageContentArea.setText("Select a message to view its content.");
        }
    }

    private void replyToMessage() {
        int selectedRow = messagesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a message to reply to.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int messageId = (Integer) tableModel.getValueAt(selectedRow, 0);
        int clientId = (Integer) tableModel.getValueAt(selectedRow, 1);
        String messageType = (String) tableModel.getValueAt(selectedRow, 2);

        // Create reply dialog
        JDialog replyDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this),
                "Reply to Message #" + messageId + " (Client ID: " + clientId + ")", true);
        replyDialog.setSize(500, 400);
        replyDialog.setLocationRelativeTo(this);
        replyDialog.setLayout(new BorderLayout());

        JPanel replyPanel = new JPanel(new BorderLayout(10, 10));
        replyPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        replyPanel.setBackground(new Color(52, 52, 52));

        JLabel replyLabel = new JLabel("Reply to Message #" + messageId + " (" + messageType + ")");
        replyLabel.setForeground(Color.WHITE);
        replyLabel.setFont(new Font("Dialog", Font.BOLD, 14));

        JTextArea replyTextArea = new JTextArea();
        replyTextArea.setBackground(new Color(40, 40, 40));
        replyTextArea.setForeground(Color.WHITE);
        replyTextArea.setFont(new Font("Dialog", Font.PLAIN, 12));
        replyTextArea.setLineWrap(true);
        replyTextArea.setWrapStyleWord(true);

        JScrollPane replyScrollPane = new JScrollPane(replyTextArea);
        replyScrollPane.setPreferredSize(new Dimension(460, 250));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(52, 52, 52));

        JButton sendButton = new JButton("Send Reply");
        sendButton.setBackground(new Color(0, 150, 0));
        sendButton.setForeground(Color.WHITE);
        sendButton.addActionListener(e -> {
            String replyContent = replyTextArea.getText().trim();
            if (replyContent.isEmpty()) {
                JOptionPane.showMessageDialog(replyDialog, "Please enter a reply message.", "Warning",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                // Insert reply into replies table
                String insertQuery = "INSERT INTO replies (message_id, admin_id, reply_content, created_at) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
                PreparedStatement pstmt = dbConnection.prepareStatement(insertQuery);
                pstmt.setInt(1, messageId);
                pstmt.setInt(2, adminId);
                pstmt.setString(3, replyContent);
                int rowsAffected = pstmt.executeUpdate();
                pstmt.close();

                if (rowsAffected > 0) {
                    // Update message status to replied
                    String updateQuery = "UPDATE messages SET status = 'Replied' WHERE message_id = ?";
                    PreparedStatement updateStmt = dbConnection.prepareStatement(updateQuery);
                    updateStmt.setInt(1, messageId);
                    updateStmt.executeUpdate();
                    updateStmt.close();

                    JOptionPane.showMessageDialog(replyDialog, "Reply sent successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    replyDialog.dispose();
                    loadMessagesData(); // Refresh the table
                } else {
                    JOptionPane.showMessageDialog(replyDialog, "Failed to send reply.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(replyDialog, "Error sending reply: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(128, 128, 128));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(e -> replyDialog.dispose());

        buttonPanel.add(sendButton);
        buttonPanel.add(cancelButton);

        replyPanel.add(replyLabel, BorderLayout.NORTH);
        replyPanel.add(replyScrollPane, BorderLayout.CENTER);
        replyPanel.add(buttonPanel, BorderLayout.SOUTH);

        replyDialog.add(replyPanel);
        replyDialog.setVisible(true);
    }

    private void markAsRead() {
        int selectedRow = messagesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a message to mark as read.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int messageId = (Integer) tableModel.getValueAt(selectedRow, 0);

            // Since your table doesn't have a status column, we'll just show a confirmation
            JOptionPane.showMessageDialog(this,
                    "Message #" + messageId + " viewed successfully!\n\n" +
                    "Note: Your messages table doesn't have a status column, so messages cannot be marked as read in the database.",
                    "Message Viewed",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedMessage() {
        int selectedRow = messagesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a message to delete.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int messageId = (Integer) tableModel.getValueAt(selectedRow, 0);
        int clientId = (Integer) tableModel.getValueAt(selectedRow, 1);
        String messageType = (String) tableModel.getValueAt(selectedRow, 2);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this message?\n" +
                        "Message ID: " + messageId + "\n" +
                        "Client ID: " + clientId + "\n" +
                        "Type: " + messageType,
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Delete from database
                String deleteQuery = "DELETE FROM messages WHERE message_id = " + messageId;
                Statement stmt = dbConnection.createStatement();
                int rowsAffected = stmt.executeUpdate(deleteQuery);
                stmt.close();

                if (rowsAffected > 0) {
                    // Remove from table model
                    tableModel.removeRow(selectedRow);
                    messageContentArea.setText("Select a message to view its content.");
                    JOptionPane.showMessageDialog(this, "Message deleted successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Message not found in database.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error deleting message: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Refresh data when panel becomes visible
     */
    public void refreshData() {
        loadMessagesData();
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}