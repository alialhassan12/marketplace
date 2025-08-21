package app;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * ViewAdminsPage - Displays all admins in a table
 */
public class ViewAdminsPage extends javax.swing.JFrame {
    
    private JTable adminsTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    
    public ViewAdminsPage() {
        initComponents();
        setupTable();
        loadAdminsData();
        setTitle("All Administrators - Admin Panel");
        setSize(1000, 600);
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
        
        JLabel titleLabel = new JLabel("All Administrators");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        JButton backButton = new JButton("← Back to Dashboard");
        backButton.setBackground(new Color(0, 102, 255));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(180, 35));
        backButton.addActionListener(e -> {
            this.dispose();
            // Navigate back to AdminPage
        });
        
        JButton refreshButton = new JButton("🔄 Refresh");
        refreshButton.setBackground(new Color(0, 150, 0));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        refreshButton.setFocusPainted(false);
        refreshButton.setBorderPainted(false);
        refreshButton.setPreferredSize(new Dimension(120, 35));
        refreshButton.addActionListener(e -> loadAdminsData());
        
        JButton addAdminButton = new JButton("+ Add New Admin");
        addAdminButton.setBackground(new Color(220, 20, 60));
        addAdminButton.setForeground(Color.WHITE);
        addAdminButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        addAdminButton.setFocusPainted(false);
        addAdminButton.setBorderPainted(false);
        addAdminButton.setPreferredSize(new Dimension(150, 35));
        addAdminButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Add New Admin functionality can be implemented here.", 
                "Feature", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createHorizontalStrut(200));
        headerPanel.add(addAdminButton);
        headerPanel.add(refreshButton);
        headerPanel.add(backButton);
        
        return headerPanel;
    }
    
    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(52, 52, 52));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create table model with columns (removed Last Login)
        String[] columnNames = {
            "Admin ID", "Name", "Username", "Email", "Phone", "Status", "Registration Date"
        };
        
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        
        adminsTable = new JTable(tableModel);
        setupTableAppearance();
        
        scrollPane = new JScrollPane(adminsTable);
        scrollPane.getViewport().setBackground(new Color(52, 52, 52));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        return tablePanel;
    }
    
    private void setupTableAppearance() {
        adminsTable.setBackground(new Color(40, 40, 40));
        adminsTable.setForeground(Color.WHITE);
        adminsTable.setFont(new Font("Dialog", Font.PLAIN, 13));
        adminsTable.setRowHeight(35);
        adminsTable.setShowGrid(true);
        adminsTable.setGridColor(new Color(80, 80, 80));
        adminsTable.setSelectionBackground(new Color(220, 20, 60));
        adminsTable.setSelectionForeground(Color.WHITE);
        
        JTableHeader header = adminsTable.getTableHeader();
        header.setBackground(new Color(24, 24, 24));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Dialog", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(0, 40));
        
        // Column widths (removed Last Login)
        adminsTable.getColumnModel().getColumn(0).setPreferredWidth(80);  // Admin ID
        adminsTable.getColumnModel().getColumn(1).setPreferredWidth(150); // Name
        adminsTable.getColumnModel().getColumn(2).setPreferredWidth(120); // Username
        adminsTable.getColumnModel().getColumn(3).setPreferredWidth(200); // Email
        adminsTable.getColumnModel().getColumn(4).setPreferredWidth(120); // Phone
        adminsTable.getColumnModel().getColumn(5).setPreferredWidth(80);  // Status
        adminsTable.getColumnModel().getColumn(6).setPreferredWidth(150); // Registration Date
    }
    
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(24, 24, 24));
        footerPanel.setPreferredSize(new Dimension(0, 50));
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JLabel statusLabel = new JLabel("Total Administrators: 0");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        
        footerPanel.add(statusLabel);
        
        return footerPanel;
    }
    
    private void setupTable() {
        adminsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }
    
    private void loadAdminsData() {
        tableModel.setRowCount(0);
        
        Connection connect = config.getConnection();
        
        try {
            Statement stmt = connect.createStatement();
            String query = "SELECT client_id, name, username, email, phone, role, created_at FROM Client WHERE role = 'admin' ORDER BY client_id";
            ResultSet rs = stmt.executeQuery(query);
            
            int adminCount = 0;
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("client_id"),
                    rs.getString("name"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("phone") != null ? rs.getString("phone") : "N/A",
                    "Active",
                    rs.getTimestamp("created_at") != null
                        ? rs.getTimestamp("created_at").toLocalDateTime().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                        : "N/A"
                };
                tableModel.addRow(row);
                adminCount++;
            }
            
            Component[] components = ((JPanel) getContentPane().getComponent(2)).getComponents();
            if (components.length > 0 && components[0] instanceof JLabel) {
                ((JLabel) components[0]).setText("Total Administrators: " + adminCount);
            }
            
            rs.close();
            stmt.close();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error loading administrators data: " + ex.getMessage(), 
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
        
        java.awt.EventQueue.invokeLater(() -> {
            new ViewAdminsPage().setVisible(true);
        });
    }
}
