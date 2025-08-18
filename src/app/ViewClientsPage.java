package app;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import app.config;

/**
 * ViewClientsPage - Displays all clients in a table
 */
public class ViewClientsPage extends javax.swing.JFrame {
    
    private JTable clientsTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JLabel statusLabel;
    
    public ViewClientsPage() {
        initComponents();
        setupTable();
        loadClientsData();
        setTitle("All Clients - Admin Panel");
        setSize(1100, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(52, 52, 52));
        setLayout(new BorderLayout());
        
        // Header Panel
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);
        
        // Table Panel
        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);
        
        // Footer Panel
        JPanel footerPanel = createFooterPanel();
        add(footerPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(24, 24, 24));
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        
        JLabel titleLabel = new JLabel("All Clients (Non-Admin Users)");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        JButton backButton = new JButton("← Back to Dashboard");
        backButton.setBackground(new Color(0, 102, 255));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(180, 35));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                dispose();
                // TODO: Return to AdminPage (instantiate it if needed)
            }
        });
        
        JButton refreshButton = new JButton("🔄 Refresh");
        refreshButton.setBackground(new Color(0, 150, 0));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        refreshButton.setFocusPainted(false);
        refreshButton.setBorderPainted(false);
        refreshButton.setPreferredSize(new Dimension(120, 35));
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                loadClientsData();
            }
        });
        
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createHorizontalStrut(300));
        headerPanel.add(refreshButton);
        headerPanel.add(backButton);
        
        return headerPanel;
    }
    
    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(52, 52, 52));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create table model with columns
        String[] columnNames = {
            "Client ID", "Name", "Username", "Email", "Phone", "Registration Date"
        };
        
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        
        clientsTable = new JTable(tableModel);
        setupTableAppearance();
        
        scrollPane = new JScrollPane(clientsTable);
        scrollPane.getViewport().setBackground(new Color(52, 52, 52));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        return tablePanel;
    }
    
    private void setupTableAppearance() {
        // Table appearance
        clientsTable.setBackground(new Color(40, 40, 40));
        clientsTable.setForeground(Color.WHITE);
        clientsTable.setFont(new Font("Dialog", Font.PLAIN, 13));
        clientsTable.setRowHeight(35);
        clientsTable.setShowGrid(true);
        clientsTable.setGridColor(new Color(80, 80, 80));
        clientsTable.setSelectionBackground(new Color(0, 102, 255));
        clientsTable.setSelectionForeground(Color.WHITE);
        
        // Header appearance
        JTableHeader header = clientsTable.getTableHeader();
        header.setBackground(new Color(24, 24, 24));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Dialog", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(0, 40));
        
        // Column widths
        clientsTable.getColumnModel().getColumn(0).setPreferredWidth(80);  // Client ID
        clientsTable.getColumnModel().getColumn(1).setPreferredWidth(150); // Name
        clientsTable.getColumnModel().getColumn(2).setPreferredWidth(120); // Username
        clientsTable.getColumnModel().getColumn(3).setPreferredWidth(200); // Email
        clientsTable.getColumnModel().getColumn(4).setPreferredWidth(120); // Phone
        clientsTable.getColumnModel().getColumn(5).setPreferredWidth(150); // Date
    }
    
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(24, 24, 24));
        footerPanel.setPreferredSize(new Dimension(0, 50));
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        statusLabel = new JLabel("Total Clients: 0");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        
        footerPanel.add(statusLabel);
        
        return footerPanel;
    }
    
    private void setupTable() {
        // Additional table setup if needed
        clientsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }
    
    private void loadClientsData() {
        // Clear existing data
        tableModel.setRowCount(0);
        
        Connection connect = config.getConnection();
        
        try {
            Statement stmt = connect.createStatement();
            // Only select clients (exclude admins)
            String query = "SELECT client_id, name, username, email, phone, created_at FROM Client WHERE role != 'admin' ORDER BY client_id";
            ResultSet rs = stmt.executeQuery(query);
            
            int clientCount = 0;
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("client_id"),
                    rs.getString("name"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toString() : "N/A"
                };
                tableModel.addRow(row);
                clientCount++;
            }
            
            // Update status label
            statusLabel.setText("Total Clients: " + clientCount);
            
            rs.close();
            stmt.close();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error loading clients data: " + ex.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewClientsPage().setVisible(true);
            }
        });
    }
}
