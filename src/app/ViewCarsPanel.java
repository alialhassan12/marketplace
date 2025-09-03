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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ViewCarsPanel - Displays all cars in a table with management functionality
 */
public class ViewCarsPanel extends JPanel {

    private JTable carsTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JLabel statusLabel;
    private Connection dbConnection;
    private JComboBox<String> filterComboBox;
    private String currentFilter = "All";
    private JTextField searchField;
    private JButton searchButton;
    private JButton clearSearchButton;
    private String currentSearchQuery = "";

    public ViewCarsPanel(Connection dbConnection) {
        this.dbConnection = dbConnection;
        initComponents();
        setupTable();
        loadCarsData();
    }

    private void initComponents() {
        setBackground(new Color(52, 52, 52));
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
        headerPanel.setLayout(new BorderLayout());

        // Top row with title and back button
        JPanel topRow = new JPanel(new BorderLayout());
        topRow.setBackground(new Color(24, 24, 24));

        JLabel titleLabel = new JLabel("Car Management");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        topRow.add(titleLabel, BorderLayout.WEST);

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

        topRow.add(backButton, BorderLayout.EAST);

        // Bottom row with search, filter and action buttons
        JPanel bottomRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        bottomRow.setBackground(new Color(24, 24, 24));

        // Search components
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        searchPanel.setBackground(new Color(24, 24, 24));

        JLabel searchLabel = new JLabel("Car ID:");
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setFont(new Font("Dialog", Font.BOLD, 14));

        searchField = new JTextField(10);
        searchField.setFont(new Font("Dialog", Font.PLAIN, 14));
        searchField.setBackground(new Color(40, 40, 40));
        searchField.setForeground(Color.WHITE);
        searchField.setCaretColor(Color.WHITE);
        searchField.setToolTipText("Enter car ID number to search");
        searchField.addActionListener(e -> performSearch());

        searchButton = new JButton("Search");
        searchButton.setBackground(new Color(0, 102, 255));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Dialog", Font.BOLD, 11));
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);
        searchButton.setPreferredSize(new Dimension(80, 35));
        searchButton.setToolTipText("Search for specific car by ID");
        searchButton.addActionListener(e -> performSearch());

        clearSearchButton = new JButton("Clear");
        clearSearchButton.setBackground(new Color(128, 128, 128));
        clearSearchButton.setForeground(Color.WHITE);
        clearSearchButton.setFont(new Font("Dialog", Font.BOLD, 11));
        clearSearchButton.setFocusPainted(false);
        clearSearchButton.setBorderPainted(false);
        clearSearchButton.setPreferredSize(new Dimension(70, 35));
        clearSearchButton.setToolTipText("Clear search and show all cars");
        clearSearchButton.addActionListener(e -> clearSearch());

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearSearchButton);

        // Filter label and combo box
        JLabel filterLabel = new JLabel("Filter by Status:");
        filterLabel.setForeground(Color.WHITE);
        filterLabel.setFont(new Font("Dialog", Font.PLAIN, 14));

        String[] filterOptions = { "All", "available", "sold", "rent", "for sale" };
        filterComboBox = new JComboBox<>(filterOptions);
        filterComboBox.setBackground(new Color(40, 40, 40));
        filterComboBox.setForeground(Color.WHITE);
        filterComboBox.setFont(new Font("Dialog", Font.PLAIN, 14));
        filterComboBox.setPreferredSize(new Dimension(120, 35));
        filterComboBox.addActionListener(e -> {
            currentFilter = (String) filterComboBox.getSelectedItem();
            loadCarsData();
        });

        // Action buttons
        JButton addCarButton = new JButton("+ Add Car");
        addCarButton.setBackground(new Color(0, 150, 0));
        addCarButton.setForeground(Color.WHITE);
        addCarButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        addCarButton.setFocusPainted(false);
        addCarButton.setBorderPainted(false);
        addCarButton.setPreferredSize(new Dimension(100, 35));
        addCarButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // TODO: Implement add car functionality
                JOptionPane.showMessageDialog(ViewCarsPanel.this,
                        "Add Car functionality will be implemented here.",
                        "Add Car",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton showInfoButton = new JButton("Show Info");
        showInfoButton.setBackground(new Color(0, 102, 255));
        showInfoButton.setForeground(Color.WHITE);
        showInfoButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        showInfoButton.setFocusPainted(false);
        showInfoButton.setBorderPainted(false);
        showInfoButton.setPreferredSize(new Dimension(100, 35));

        showInfoButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int selectedRow = carsTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(ViewCarsPanel.this,
                            "Please select a car to view information.",
                            "No Selection",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int carId = (int) tableModel.getValueAt(selectedRow, 0); // Car ID column
                    int ownerId = (int) tableModel.getValueAt(selectedRow, 7); // Owner ID column

                    // If you also need clientId, fetch it from DB or include it in the table model
                    int clientId = 0; // Replace with your logic

                    carInfoFrame carInfo = new carInfoFrame(ownerId, clientId, carId);
                    carInfo.setSize(959, 608);
                    carInfo.setResizable(false);
                    carInfo.setVisible(true);
                    carInfo.setLocationRelativeTo(null);
                } catch (Exception ex) {
                    System.out.println("Error loading car info: " + ex.getMessage());
                }
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(220, 20, 60));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorderPainted(false);
        deleteButton.setPreferredSize(new Dimension(130, 35));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSelectedCar();
            }
        });

        JButton refreshButton = new JButton("🔄 Refresh");
        refreshButton.setBackground(new Color(128, 128, 128));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        refreshButton.setFocusPainted(false);
        refreshButton.setBorderPainted(false);
        refreshButton.setPreferredSize(new Dimension(100, 35));
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadCarsData();
            }
        });

        bottomRow.add(searchPanel);
        bottomRow.add(filterLabel);
        bottomRow.add(filterComboBox);
        bottomRow.add(Box.createHorizontalStrut(30));
        bottomRow.add(addCarButton);
        bottomRow.add(showInfoButton);
        bottomRow.add(deleteButton);
        bottomRow.add(refreshButton);

        headerPanel.add(topRow, BorderLayout.NORTH);
        headerPanel.add(bottomRow, BorderLayout.SOUTH);

        return headerPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(52, 52, 52));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create table model with columns
        String[] columnNames = {
                "Car ID", "Brand", "Model", "Year", "Price", "Status", "Location", "Owner ID", "Posted Date"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };

        carsTable = new JTable(tableModel);
        setupTableAppearance();

        scrollPane = new JScrollPane(carsTable);
        scrollPane.getViewport().setBackground(new Color(52, 52, 52));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));

        tablePanel.add(scrollPane, BorderLayout.CENTER);

        return tablePanel;
    }

    private void setupTableAppearance() {
        carsTable.setBackground(new Color(40, 40, 40));
        carsTable.setForeground(Color.WHITE);
        carsTable.setFont(new Font("Dialog", Font.PLAIN, 13));
        carsTable.setRowHeight(35);
        carsTable.setShowGrid(true);
        carsTable.setGridColor(new Color(80, 80, 80));
        carsTable.setSelectionBackground(new Color(0, 102, 255));
        carsTable.setSelectionForeground(Color.WHITE);
        carsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = carsTable.getTableHeader();
        header.setBackground(new Color(24, 24, 24));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Dialog", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(0, 40));

        // Column widths
        carsTable.getColumnModel().getColumn(0).setPreferredWidth(80); // Car ID
        carsTable.getColumnModel().getColumn(1).setPreferredWidth(100); // Brand
        carsTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Model
        carsTable.getColumnModel().getColumn(3).setPreferredWidth(80); // Year
        carsTable.getColumnModel().getColumn(4).setPreferredWidth(100); // Price
        carsTable.getColumnModel().getColumn(5).setPreferredWidth(80); // Status
        carsTable.getColumnModel().getColumn(6).setPreferredWidth(120); // Location
        carsTable.getColumnModel().getColumn(7).setPreferredWidth(80); // Owner ID
        carsTable.getColumnModel().getColumn(8).setPreferredWidth(140); // Posted Date
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(24, 24, 24));
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        statusLabel = new JLabel("Total Cars: 0");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        footerPanel.add(statusLabel);
        return footerPanel;
    }

    private void setupTable() {
        carsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    public void loadCarsData() {
        tableModel.setRowCount(0);
        try {
            if (dbConnection != null && !dbConnection.isClosed()) {
                Statement stmt = dbConnection.createStatement();

                String query = "SELECT car_id, brand, model, year, price, status, location, owner_id, posted_at FROM car";

                // Apply filter if not "All"
                if (!"All".equals(currentFilter)) {
                    query += " WHERE status = '" + currentFilter + "'";
                }

                // Apply search if not empty
                if (!currentSearchQuery.isEmpty()) {
                    if (!"All".equals(currentFilter)) {
                        query += " AND car_id = " + currentSearchQuery;
                    } else {
                        query += " WHERE car_id = " + currentSearchQuery;
                    }
                }

                query += " ORDER BY car_id DESC";

                ResultSet rs = stmt.executeQuery(query);
                int carCount = 0;

                while (rs.next()) {
                    Object[] row = {
                            rs.getInt("car_id"),
                            rs.getString("brand") != null ? rs.getString("brand") : "N/A",
                            rs.getString("model") != null ? rs.getString("model") : "N/A",
                            rs.getInt("year"),
                            String.format("$%.2f", rs.getDouble("price")),
                            rs.getString("status") != null ? rs.getString("status") : "N/A",
                            rs.getString("location") != null ? rs.getString("location") : "N/A",
                            rs.getInt("owner_id"),
                            rs.getTimestamp("posted_at") != null
                                    ? rs.getTimestamp("posted_at").toLocalDateTime()
                                            .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                                    : "N/A"
                    };
                    tableModel.addRow(row);
                    carCount++;
                }

                String statusText;
                if (!currentSearchQuery.isEmpty()) {
                    statusText = "Search Results: " + carCount + " car(s) found for ID: " + currentSearchQuery;
                } else {
                    statusText = "Total Cars: " + carCount;
                    if (!"All".equals(currentFilter)) {
                        statusText += " (" + currentFilter + " cars)";
                    }
                }
                statusLabel.setText(statusText);

                rs.close();
                stmt.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error loading cars data: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void deleteSelectedCar() {
        int selectedRow = carsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a car to delete.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int carId = (Integer) tableModel.getValueAt(selectedRow, 0);
        String brand = (String) tableModel.getValueAt(selectedRow, 1);
        String model = (String) tableModel.getValueAt(selectedRow, 2);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this car?\n" +
                        "Car ID: " + carId + "\n" +
                        "Brand: " + brand + "\n" +
                        "Model: " + model,
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (dbConnection != null && !dbConnection.isClosed()) {
                    // Delete car from database
                    String deleteQuery = "DELETE FROM car WHERE car_id = ?";
                    PreparedStatement pstmt = dbConnection.prepareStatement(deleteQuery);
                    pstmt.setInt(1, carId);

                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this,
                                "Car deleted successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        loadCarsData(); // Refresh the table
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Failed to delete car. Car may not exist.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    pstmt.close();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error deleting car: " + ex.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    private void performSearch() {
        String searchText = searchField.getText().trim();
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Car ID to search.", "Empty Search",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int carId = Integer.parseInt(searchText);
            currentSearchQuery = String.valueOf(carId);
            loadCarsData();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric Car ID.", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearSearch() {
        searchField.setText("");
        currentSearchQuery = "";
        loadCarsData();
    }

    /**
     * Refresh data when panel becomes visible
     */
    public void refreshData() {
        loadCarsData();
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}