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
 * ReportedCarsPanel - Displays reported cars with details
 */
public class ReportedCarsPanel extends JPanel {

    private JTable reportedCarsTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JLabel statusLabel;
    private Connection dbConnection;
    private JButton refreshButton;
    private JButton resolveButton;

    public ReportedCarsPanel(Connection dbConnection) {
        this.dbConnection = dbConnection;
        initComponents();
        loadReportedCarsData();
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
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new BorderLayout());

        // Title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        titlePanel.setBackground(new Color(24, 24, 24));

        JLabel titleLabel = new JLabel("Reported Cars");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        // Action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 15));
        buttonPanel.setBackground(new Color(24, 24, 24));

        JButton backButton = new JButton("← Back to Dashboard");
        backButton.setBackground(new Color(128, 128, 128));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.addActionListener(e -> {
            // Close the dialog containing this panel
            SwingUtilities.getWindowAncestor(this).setVisible(false);
        });

        refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(0, 102, 255));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        refreshButton.setFocusPainted(false);
        refreshButton.setBorderPainted(false);
        refreshButton.addActionListener(e -> loadReportedCarsData());

        resolveButton = new JButton("Mark as Resolved");
        resolveButton.setBackground(new Color(0, 150, 0));
        resolveButton.setForeground(Color.WHITE);
        resolveButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        resolveButton.setFocusPainted(false);
        resolveButton.setBorderPainted(false);
        resolveButton.addActionListener(e -> markAsResolved());

        buttonPanel.add(backButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(resolveButton);

        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(buttonPanel, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(52, 52, 52));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Create table model with columns
        String[] columnNames = {
                "Report ID", "Car ID", "Car Details", "Reporter ID", "Report Content", "Report Date"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };

        reportedCarsTable = new JTable(tableModel);
        setupTableAppearance();

        scrollPane = new JScrollPane(reportedCarsTable);
        scrollPane.getViewport().setBackground(new Color(52, 52, 52));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));

        tablePanel.add(scrollPane, BorderLayout.CENTER);

        return tablePanel;
    }

    private void setupTableAppearance() {
        reportedCarsTable.setBackground(new Color(40, 40, 40));
        reportedCarsTable.setForeground(Color.WHITE);
        reportedCarsTable.setFont(new Font("Dialog", Font.PLAIN, 12));
        reportedCarsTable.setRowHeight(30);
        reportedCarsTable.setShowGrid(true);
        reportedCarsTable.setGridColor(new Color(80, 80, 80));
        reportedCarsTable.setSelectionBackground(new Color(0, 102, 255));
        reportedCarsTable.setSelectionForeground(Color.WHITE);
        reportedCarsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = reportedCarsTable.getTableHeader();
        header.setBackground(new Color(24, 24, 24));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Dialog", Font.BOLD, 13));
        header.setPreferredSize(new Dimension(0, 35));

        // Column widths
        reportedCarsTable.getColumnModel().getColumn(0).setPreferredWidth(80); // Report ID
        reportedCarsTable.getColumnModel().getColumn(1).setPreferredWidth(80); // Car ID
        reportedCarsTable.getColumnModel().getColumn(2).setPreferredWidth(200); // Car Details
        reportedCarsTable.getColumnModel().getColumn(3).setPreferredWidth(120); // Reporter
        reportedCarsTable.getColumnModel().getColumn(4).setPreferredWidth(250); // Report Content
        reportedCarsTable.getColumnModel().getColumn(5).setPreferredWidth(120); // Report Date
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(24, 24, 24));
        footerPanel.setPreferredSize(new Dimension(0, 40));
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        statusLabel = new JLabel("Total Reports: 0");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        footerPanel.add(statusLabel);
        return footerPanel;
    }

    public void loadReportedCarsData() {
        tableModel.setRowCount(0);

        try {
            if (dbConnection != null && !dbConnection.isClosed()) {
                // Query to get reported cars with details
                String query = "SELECT r.client_id, r.car_id, r.report_content, r.created_at, " +
                              "c.brand, c.model, c.year " +
                              "FROM reports r " +
                              "LEFT JOIN car c ON r.car_id = c.car_id " +
                              "ORDER BY r.created_at DESC";

                Statement stmt = dbConnection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                int reportCount = 0;

                while (rs.next()) {
                    // Create car details string
                    String carDetails = rs.getString("brand") + " " + rs.getString("model") +
                                       " (" + rs.getInt("year") + ")";

                    // Create content preview (first 50 characters)
                    String fullContent = rs.getString("report_content");
                    String contentPreview = (fullContent != null && fullContent.length() > 50)
                            ? fullContent.substring(0, 50) + "..."
                            : fullContent;

                    Object[] row = {
                            reportCount + 1, // Report ID (sequential)
                            rs.getInt("car_id"),
                            carDetails != null ? carDetails : "Car details not available",
                            rs.getInt("client_id"),
                            contentPreview != null ? contentPreview : "No content",
                            rs.getTimestamp("created_at") != null
                                    ? rs.getTimestamp("created_at").toLocalDateTime()
                                            .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                                    : "N/A"
                    };
                    tableModel.addRow(row);
                    reportCount++;
                }

                statusLabel.setText("Total Reports: " + reportCount);
                rs.close();
                stmt.close();
            }
        } catch (Exception ex) {
            System.err.println("Error loading reported cars: " + ex.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Error loading reported cars: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void markAsResolved() {
        int selectedRow = reportedCarsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a report to mark as resolved.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int carId = (Integer) tableModel.getValueAt(selectedRow, 1);
        String carDetails = (String) tableModel.getValueAt(selectedRow, 2);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to mark this report as resolved?\n\n" +
                "Car: " + carDetails + "\n" +
                "This will remove the report from the list.",
                "Confirm Resolution",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Delete the report from database
                String deleteQuery = "DELETE FROM reports WHERE car_id = " + carId;
                Statement stmt = dbConnection.createStatement();
                int rowsAffected = stmt.executeUpdate(deleteQuery);
                stmt.close();

                if (rowsAffected > 0) {
                    // Remove from table model
                    tableModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(this,
                            "Report marked as resolved successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Update status label
                    int currentCount = tableModel.getRowCount();
                    statusLabel.setText("Total Reports: " + currentCount);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Report not found in database.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error resolving report: " + ex.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Refresh data when panel becomes visible
     */
    public void refreshData() {
        loadReportedCarsData();
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}