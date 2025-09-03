
package app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import app.config;

public class ViewClientsPanel extends JPanel {
    private JTable clientsTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JLabel statusLabel;
    private JTextField searchField;
    private JButton searchButton;
    private JButton clearSearchButton;

    private Connection dbConnection;
    private String currentSearchQuery = "";

    public ViewClientsPanel() {
        this.dbConnection = null;
        initComponents();
        setupTable();
        loadClientsData();
    }

    public ViewClientsPanel(Connection dbConnection) {
        this.dbConnection = dbConnection;
        initComponents();
        setupTable();
        loadClientsData();
    }

    private void initComponents() {
        setBackground(new Color(52, 52, 52));
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
        headerPanel.setPreferredSize(new Dimension(0, 140));
        headerPanel.setLayout(new BorderLayout());

        // Top row with title
        JPanel topRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        topRow.setBackground(new Color(24, 24, 24));

        JLabel titleLabel = new JLabel("Clients Management");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        topRow.add(titleLabel);

        // Bottom row with search and buttons - horizontal layout with proper sizing
        JPanel bottomRow = new JPanel(new BorderLayout());
        bottomRow.setBackground(new Color(24, 24, 24));

        // Left side - Search components
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        searchPanel.setBackground(new Color(24, 24, 24));

        JLabel searchLabel = new JLabel("Client ID:");
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setFont(new Font("Dialog", Font.BOLD, 14));

        searchField = new JTextField(10);
        searchField.setFont(new Font("Dialog", Font.PLAIN, 14));
        searchField.setBackground(new Color(40, 40, 40));
        searchField.setForeground(Color.WHITE);
        searchField.setCaretColor(Color.WHITE);
        searchField.setToolTipText("Enter client ID number to search");
        searchField.addActionListener(e -> performSearch());

        searchButton = new JButton("Search");
        searchButton.setBackground(new Color(0, 102, 255));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Dialog", Font.BOLD, 11));
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);
        searchButton.setPreferredSize(new Dimension(80, 35));
        searchButton.setToolTipText("Search for specific client by ID");
        searchButton.addActionListener(e -> performSearch());

        clearSearchButton = new JButton("Clear");
        clearSearchButton.setBackground(new Color(128, 128, 128));
        clearSearchButton.setForeground(Color.WHITE);
        clearSearchButton.setFont(new Font("Dialog", Font.BOLD, 11));
        clearSearchButton.setFocusPainted(false);
        clearSearchButton.setBorderPainted(false);
        clearSearchButton.setPreferredSize(new Dimension(70, 35));
        clearSearchButton.setToolTipText("Clear search and show all clients");
        clearSearchButton.addActionListener(e -> clearSearch());

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearSearchButton);

        // Right side - Action buttons with compact sizing
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 5));
        actionPanel.setBackground(new Color(24, 24, 24));

        JButton deleteButton = new JButton("Delete Client");
        deleteButton.setBackground(new Color(220, 20, 60));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Dialog", Font.BOLD, 11));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorderPainted(false);
        deleteButton.setPreferredSize(new Dimension(120, 35));
        deleteButton.setToolTipText("Permanently delete the selected client");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSelectedClient();
            }
        });

        JButton pendingButton = new JButton("Pending Approvals");
        pendingButton.setBackground(new Color(255, 204, 0)); // Yellow
        pendingButton.setForeground(Color.BLACK); // better contrast on yellow
        pendingButton.setFont(new Font("Dialog", Font.BOLD, 11));
        pendingButton.setFocusPainted(false);
        pendingButton.setBorderPainted(false);
        pendingButton.setPreferredSize(new Dimension(120, 35));
        pendingButton.setToolTipText("View clients waiting for approval");
        pendingButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                java.awt.Container parent = getTopLevelAncestor();
                if (parent instanceof AdminPage) {
                    ((AdminPage) parent).showPanel("approvals");
                }
            }
        });

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(0, 150, 0));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFont(new Font("Dialog", Font.BOLD, 11));
        refreshButton.setFocusPainted(false);
        refreshButton.setBorderPainted(false);
        refreshButton.setPreferredSize(new Dimension(120, 35));
        refreshButton.setToolTipText("Reload client data from database");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadClientsData();
            }
        });

        JButton backButton = new JButton("Back to Dashboard");
        backButton.setBackground(new Color(0, 102, 255));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Dialog", Font.BOLD, 11));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(120, 35));
        backButton.setToolTipText("Return to main dashboard");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                java.awt.Container parent = getTopLevelAncestor();
                if (parent instanceof AdminPage) {
                    ((AdminPage) parent).showPanel("dashboard");
                }
            }
        });

        actionPanel.add(deleteButton);
        actionPanel.add(pendingButton);
        actionPanel.add(refreshButton);
        actionPanel.add(backButton);

        bottomRow.add(searchPanel, BorderLayout.WEST);
        bottomRow.add(actionPanel, BorderLayout.EAST);

        headerPanel.add(topRow, BorderLayout.NORTH);
        headerPanel.add(bottomRow, BorderLayout.SOUTH);

        return headerPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(52, 52, 52));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] columnNames = {
                "Client ID", "Name", "Username", "Email", "Phone", "Registration Date"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
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
        clientsTable.setBackground(new Color(40, 40, 40));
        clientsTable.setForeground(Color.WHITE);
        clientsTable.setFont(new Font("Dialog", Font.PLAIN, 13));
        clientsTable.setRowHeight(35);
        clientsTable.setShowGrid(true);
        clientsTable.setGridColor(new Color(80, 80, 80));
        clientsTable.setSelectionBackground(new Color(0, 102, 255));
        clientsTable.setSelectionForeground(Color.WHITE);

        JTableHeader header = clientsTable.getTableHeader();
        header.setBackground(new Color(24, 24, 24));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Dialog", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(0, 40));

        clientsTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        clientsTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        clientsTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        clientsTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        clientsTable.getColumnModel().getColumn(4).setPreferredWidth(120);
        clientsTable.getColumnModel().getColumn(5).setPreferredWidth(150);
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
        clientsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    private void loadClientsData() {
        tableModel.setRowCount(0);
        Connection connect = (dbConnection != null) ? dbConnection : config.getConnection();
        try {
            Statement stmt = connect.createStatement();
            String baseQuery = "SELECT client_id, name, username, email, phone, created_at FROM Client WHERE role != 'admin'";
            String query;

            if (!currentSearchQuery.isEmpty()) {
                query = baseQuery + " AND client_id = " + currentSearchQuery + " ORDER BY client_id";
            } else {
                query = baseQuery + " ORDER BY client_id";
            }

            ResultSet rs = stmt.executeQuery(query);
            int clientCount = 0;
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("client_id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getTimestamp("created_at") != null
                                ? rs.getTimestamp("created_at").toLocalDateTime()
                                        .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                                : "N/A"
                };
                tableModel.addRow(row);
                clientCount++;
            }

            if (!currentSearchQuery.isEmpty()) {
                statusLabel.setText("Search Results: " + clientCount + " client(s) found for ID: " + currentSearchQuery);
            } else {
                statusLabel.setText("Total Clients: " + clientCount);
            }

            rs.close();
            stmt.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading clients data: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performSearch() {
        String searchText = searchField.getText().trim();
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Client ID to search.", "Empty Search",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int clientId = Integer.parseInt(searchText);
            currentSearchQuery = String.valueOf(clientId);
            loadClientsData();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric Client ID.", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearSearch() {
        searchField.setText("");
        currentSearchQuery = "";
        loadClientsData();
    }

    private void deleteSelectedClient() {
        int selectedRow = clientsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a client to delete.", "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int clientId = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete client ID " + clientId + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION)
            return;
        Connection connect = config.getConnection();
        try {
            String sql = "DELETE FROM Client WHERE client_id = ?";
            PreparedStatement pstmt = connect.prepareStatement(sql);
            pstmt.setInt(1, clientId);
            int affected = pstmt.executeUpdate();
            pstmt.close();
            if (affected > 0) {
                JOptionPane.showMessageDialog(this, "Client deleted successfully.", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                loadClientsData();
            } else {
                JOptionPane.showMessageDialog(this, "Client not found or could not be deleted.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error deleting client: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Public method to refresh data from parent components
    public void refreshData() {
        loadClientsData();
    }
}