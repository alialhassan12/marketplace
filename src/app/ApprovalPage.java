package app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ApprovalPage extends JFrame {
    private JTable pendingTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JLabel statusLabel;

    public ApprovalPage() {
        initComponents();
        setupTable();
        loadPendingClientsData();
        setTitle("Client Approvals - Admin Panel");
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(52, 52, 52));
        setLayout(new BorderLayout());

        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);

        JPanel footerPanel = createFooterPanel();
        add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(24, 24, 24));
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        JLabel titleLabel = new JLabel("Client Approvals");
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
        });

        JButton refreshButton = new JButton("🔄 Refresh");
        refreshButton.setBackground(new Color(0, 150, 0));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        refreshButton.setFocusPainted(false);
        refreshButton.setBorderPainted(false);
        refreshButton.setPreferredSize(new Dimension(120, 35));
        refreshButton.addActionListener(e -> loadPendingClientsData());

        JButton approveButton = new JButton("✅ Approve Selected");
        approveButton.setBackground(new Color(34, 139, 34));
        approveButton.setForeground(Color.WHITE);
        approveButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        approveButton.setFocusPainted(false);
        approveButton.setBorderPainted(false);
        approveButton.setPreferredSize(new Dimension(180, 35));
        approveButton.addActionListener(e -> approveSelectedClient());

        JButton rejectButton = new JButton("❌ Reject Selected");
        rejectButton.setBackground(new Color(220, 20, 60));
        rejectButton.setForeground(Color.WHITE);
        rejectButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        rejectButton.setFocusPainted(false);
        rejectButton.setBorderPainted(false);
        rejectButton.setPreferredSize(new Dimension(170, 35));
        rejectButton.addActionListener(e -> rejectSelectedClient());

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createHorizontalStrut(150));
        headerPanel.add(approveButton);
        headerPanel.add(rejectButton);
        headerPanel.add(refreshButton);
        headerPanel.add(backButton);

        return headerPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(52, 52, 52));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] columnNames = {
            "Client ID", "Name", "Username", "Email", "Phone", "Registration Date", "Status"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        pendingTable = new JTable(tableModel);
        setupTableAppearance();

        scrollPane = new JScrollPane(pendingTable);
        scrollPane.getViewport().setBackground(new Color(52, 52, 52));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));

        tablePanel.add(scrollPane, BorderLayout.CENTER);
        return tablePanel;
    }

    private void setupTableAppearance() {
        pendingTable.setBackground(new Color(40, 40, 40));
        pendingTable.setForeground(Color.WHITE);
        pendingTable.setFont(new Font("Dialog", Font.PLAIN, 13));
        pendingTable.setRowHeight(35);
        pendingTable.setShowGrid(true);
        pendingTable.setGridColor(new Color(80, 80, 80));
        pendingTable.setSelectionBackground(new Color(0, 102, 255));
        pendingTable.setSelectionForeground(Color.WHITE);

        JTableHeader header = pendingTable.getTableHeader();
        header.setBackground(new Color(24, 24, 24));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Dialog", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(0, 40));

        pendingTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        pendingTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        pendingTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        pendingTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        pendingTable.getColumnModel().getColumn(4).setPreferredWidth(120);
        pendingTable.getColumnModel().getColumn(5).setPreferredWidth(150);
        pendingTable.getColumnModel().getColumn(6).setPreferredWidth(100);
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(24, 24, 24));
        footerPanel.setPreferredSize(new Dimension(0, 50));
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        statusLabel = new JLabel("Pending Approvals: 0");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Dialog", Font.PLAIN, 14));

        footerPanel.add(statusLabel);
        return footerPanel;
    }

    private void setupTable() {
        pendingTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    private void loadPendingClientsData() {
        tableModel.setRowCount(0);
        Connection connect = config.getConnection();
        try {
            Statement stmt = connect.createStatement();
            String query = "SELECT client_id, name, username, email, phone, created_at, " +
                          "CASE WHEN is_approved = true THEN 'Approved' " +
                          "WHEN is_approved = false THEN 'Pending' " +
                          "ELSE 'Pending' END as status " +
                          "FROM client WHERE role != 'admin' AND " +
                          "(is_approved = false OR is_approved IS NULL) " +
                          "ORDER BY created_at DESC";
            
            ResultSet rs = stmt.executeQuery(query);
            int pendingCount = 0;
            
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("client_id"),
                    rs.getString("name"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toString() : "N/A",
                    rs.getString("status")
                };
                tableModel.addRow(row);
                pendingCount++;
            }
            
            statusLabel.setText("Pending Approvals: " + pendingCount);
            rs.close();
            stmt.close();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading pending clients: " + ex.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadAllClientsData() {
        tableModel.setRowCount(0);
        Connection connect = config.getConnection();
        try {
            Statement stmt = connect.createStatement();
            String query = "SELECT client_id, name, username, email, phone, created_at, " +
                          "CASE WHEN is_approved = true THEN 'Approved' " +
                          "WHEN is_approved = false THEN 'Pending' " +
                          "ELSE 'Pending' END as status " +
                          "FROM client WHERE role != 'admin' ORDER BY created_at DESC";
            
            ResultSet rs = stmt.executeQuery(query);
            int clientCount = 0;
            
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("client_id"),
                    rs.getString("name"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toString() : "N/A",
                    rs.getString("status")
                };
                tableModel.addRow(row);
                clientCount++;
            }
            
            statusLabel.setText("Total Clients: " + clientCount);
            rs.close();
            stmt.close();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading clients data: " + ex.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void approveSelectedClient() {
        int selectedRow = pendingTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a client to approve.", 
                                        "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int clientId = (int) tableModel.getValueAt(selectedRow, 0);
        String clientName = (String) tableModel.getValueAt(selectedRow, 1);
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to approve client: " + clientName + " (ID: " + clientId + ")?", 
            "Confirm Approval", JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) return;

        Connection connect = config.getConnection();
        try {
            String sql = "UPDATE client SET is_approved = true WHERE client_id = ?";
            PreparedStatement pstmt = connect.prepareStatement(sql);
            pstmt.setInt(1, clientId);
            int affected = pstmt.executeUpdate();
            pstmt.close();

            if (affected > 0) {
                JOptionPane.showMessageDialog(this, "Client approved successfully!", 
                                            "Success", JOptionPane.INFORMATION_MESSAGE);
                loadPendingClientsData();
            } else {
                JOptionPane.showMessageDialog(this, "Client not found or already processed.", 
                                            "Warning", JOptionPane.WARNING_MESSAGE);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error approving client: " + ex.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void rejectSelectedClient() {
        int selectedRow = pendingTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a client to reject.", 
                                        "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int clientId = (int) tableModel.getValueAt(selectedRow, 0);
        String clientName = (String) tableModel.getValueAt(selectedRow, 1);
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to reject client: " + clientName + " (ID: " + clientId + ")?\n" +
            "This will delete the client from the system.", 
            "Confirm Rejection", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if (confirm != JOptionPane.YES_OPTION) return;

        Connection connect = config.getConnection();
        try {
            // Since there's no rejection status in your schema, we'll delete the rejected client
            String sql = "DELETE FROM client WHERE client_id = ?";
            PreparedStatement pstmt = connect.prepareStatement(sql);
            pstmt.setInt(1, clientId);
            int affected = pstmt.executeUpdate();
            pstmt.close();

            if (affected > 0) {
                JOptionPane.showMessageDialog(this, "Client rejected and removed from system!", 
                                            "Success", JOptionPane.INFORMATION_MESSAGE);
                loadPendingClientsData();
            } else {
                JOptionPane.showMessageDialog(this, "Client not found.", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error rejecting client: " + ex.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteClient(int clientId) {
        Connection connect = config.getConnection();
        try {
            String sql = "DELETE FROM Client WHERE client_id = ?";
            PreparedStatement pstmt = connect.prepareStatement(sql);
            pstmt.setInt(1, clientId);
            int affected = pstmt.executeUpdate();
            pstmt.close();

            if (affected > 0) {
                JOptionPane.showMessageDialog(this, "Client removed successfully!", 
                                            "Success", JOptionPane.INFORMATION_MESSAGE);
                loadPendingClientsData();
            } else {
                JOptionPane.showMessageDialog(this, "Client not found.", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error removing client: " + ex.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}