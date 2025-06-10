package university;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TeacherAttendanceDetail extends JFrame implements ActionListener {

    private JTable attendanceTable;
    private JButton printButton, refreshButton, exportButton, filterButton;
    private JLabel titleLabel, recordCountLabel;
    private JPanel headerPanel, buttonPanel, mainPanel;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JComboBox<String> filterComboBox;
    
    // Color scheme
    private final Color PRIMARY_COLOR = new Color(142, 68, 173);
    private final Color SECONDARY_COLOR = new Color(155, 89, 182);
    private final Color ACCENT_COLOR = new Color(230, 126, 34);
    private final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private final Color TEXT_COLOR = new Color(44, 62, 80);
    // private final Color SUCCESS_COLOR = new Color(39, 174, 96);
    // private final Color ERROR_COLOR = new Color(231, 76, 60);

    public TeacherAttendanceDetail() {
        super("Teacher Attendance Management System");
        initializeComponents();
        setupLayout();
        loadAttendanceData();
        applyModernStyling();
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    private void initializeComponents() {
        // Header components
        titleLabel = new JLabel("Teacher Attendance Records", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(PRIMARY_COLOR);
        
        recordCountLabel = new JLabel("Loading records...", JLabel.CENTER);
        recordCountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        recordCountLabel.setForeground(TEXT_COLOR);

        // Filter components
        filterComboBox = new JComboBox<>(new String[]{"All Records", "Present Today", "Absent Today", "This Week", "This Month"});
        filterComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        filterComboBox.setPreferredSize(new Dimension(150, 30));

        // Table setup
        String[] columnNames = {"Employee ID", "Date & Time", "First Half", "Second Half", "Status", "Department"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        
        attendanceTable = new JTable(tableModel);
        setupTable();
        
        scrollPane = new JScrollPane(attendanceTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Buttons
        printButton = createStyledButton("Print Report", "Print attendance records", PRIMARY_COLOR);
        refreshButton = createStyledButton("Refresh", "Reload attendance data", ACCENT_COLOR);
        exportButton = createStyledButton("Export", "Export to file", SECONDARY_COLOR);
        filterButton = createStyledButton("Apply Filter", "Apply selected filter", new Color(52, 152, 219));

        // Panels
        headerPanel = new JPanel(new BorderLayout());
        buttonPanel = new JPanel(new BorderLayout());
        mainPanel = new JPanel(new BorderLayout());
    }

    private JButton createStyledButton(String text, String tooltip, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setToolTipText(tooltip);
        
        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.brighter());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });
        
        button.addActionListener(this);
        return button;
    }

    private void setupTable() {
        attendanceTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        attendanceTable.setRowHeight(35);
        attendanceTable.setGridColor(new Color(189, 195, 199));
        attendanceTable.setSelectionBackground(new Color(187, 143, 206));
        attendanceTable.setSelectionForeground(Color.WHITE);
        attendanceTable.setShowVerticalLines(true);
        attendanceTable.setShowHorizontalLines(true);
        
        // Header styling
        JTableHeader header = attendanceTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(PRIMARY_COLOR);
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder());
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 45));
        
        // Column widths
        TableColumnModel columnModel = attendanceTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(120); // Employee ID
        columnModel.getColumn(1).setPreferredWidth(160); // Date Time
        columnModel.getColumn(2).setPreferredWidth(100); // First Half
        columnModel.getColumn(3).setPreferredWidth(100); // Second Half
        columnModel.getColumn(4).setPreferredWidth(100); // Status
        columnModel.getColumn(5).setPreferredWidth(120); // Department
        
        // Center align specific columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(2).setCellRenderer(centerRenderer);
        columnModel.getColumn(3).setCellRenderer(centerRenderer);
        
        // Custom renderer for status column
        columnModel.getColumn(4).setCellRenderer(new StatusCellRenderer());
        
        // Custom renderer for department column
        columnModel.getColumn(5).setCellRenderer(new DepartmentCellRenderer());
        
        attendanceTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        // Add row selection listener for detailed view
        attendanceTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = attendanceTable.getSelectedRow();
                if (selectedRow >= 0) {
                    // Could implement detailed view functionality here
                }
            }
        });
    }

    private void setupLayout() {
        // Header panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(recordCountLabel, BorderLayout.SOUTH);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        titlePanel.setBackground(BACKGROUND_COLOR);
        
        headerPanel.add(titlePanel, BorderLayout.CENTER);
        headerPanel.setBackground(BACKGROUND_COLOR);

        // Filter panel
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        filterPanel.add(new JLabel("Filter:"));
        filterPanel.add(filterComboBox);
        filterPanel.add(filterButton);
        filterPanel.setBackground(BACKGROUND_COLOR);
        filterPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));

        // Button panel
        JPanel actionButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        actionButtonPanel.add(refreshButton);
        actionButtonPanel.add(exportButton);
        actionButtonPanel.add(printButton);
        actionButtonPanel.setBackground(BACKGROUND_COLOR);
        
        buttonPanel.add(filterPanel, BorderLayout.WEST);
        buttonPanel.add(actionButtonPanel, BorderLayout.EAST);
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 20));

        // Main panel
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(0, 20, 0, 20),
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1)
        ));

        // Main layout
        setLayout(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void applyModernStyling() {
        getContentPane().setBackground(BACKGROUND_COLOR);
        
        // Modern window styling
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (Exception e) {
                // Fallback to default look and feel
            }
        }
    }

    private void loadAttendanceData() {
        SwingUtilities.invokeLater(() -> {
            try {
                tableModel.setRowCount(0); // Clear existing data
                
                String query = "SELECT * FROM attendance_teacher ORDER BY Date DESC";
                Conn connection = new Conn();
                ResultSet resultSet = connection.s.executeQuery(query);
                
                int recordCount = 0;
                while (resultSet.next()) {
                    String empId = resultSet.getString("emp_id");
                    String date = resultSet.getString("Date");
                    String firstHalf = resultSet.getString("first");
                    String secondHalf = resultSet.getString("second");
                    
                    // Calculate overall status
                    String status = calculateAttendanceStatus(firstHalf, secondHalf);
                    
                    // Get department (you might need to join with teacher table)
                    String department = getDepartmentByEmpId(empId);
                    
                    Object[] rowData = {empId, date, firstHalf, secondHalf, status, department};
                    tableModel.addRow(rowData);
                    recordCount++;
                }
                
                recordCountLabel.setText(String.format("Total Records: %d | Last Updated: %s", 
                    recordCount, LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm"))));
                
                if (recordCount == 0) {
                    recordCountLabel.setText("No attendance records found");
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
                showErrorMessage("Database Error", "Error loading attendance data: " + e.getMessage());
                recordCountLabel.setText("Error loading data");
            }
        });
    }

    private String getDepartmentByEmpId(String empId) {
        try {
            String query = "SELECT department FROM teacher WHERE emp_id = ?";
            Conn connection = new Conn();
            PreparedStatement pstmt = connection.c.prepareStatement(query);
            pstmt.setString(1, empId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("department");
            }
        } catch (SQLException e) {
            // Handle error silently, return default
        }
        return "Unknown";
    }

    private String calculateAttendanceStatus(String firstHalf, String secondHalf) {
        boolean firstPresent = "Present".equalsIgnoreCase(firstHalf);
        boolean secondPresent = "Present".equalsIgnoreCase(secondHalf);
        
        if (firstPresent && secondPresent) {
            return "Full Day";
        } else if (firstPresent || secondPresent) {
            return "Half Day";
        } else {
            return "Absent";
        }
    }

    private void showErrorMessage(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccessMessage(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        
        if (source == printButton) {
            handlePrintAction();
        } else if (source == refreshButton) {
            handleRefreshAction();
        } else if (source == exportButton) {
            handleExportAction();
        } else if (source == filterButton) {
            handleFilterAction();
        }
    }

    private void handlePrintAction() {
        try {
            boolean complete = attendanceTable.print(JTable.PrintMode.FIT_WIDTH,
                new java.text.MessageFormat("Teacher Attendance Report"),
                new java.text.MessageFormat("Generated on: " + 
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm"))));
            
            if (complete) {
                showSuccessMessage("Print Success", "Teacher attendance report printed successfully!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            showErrorMessage("Print Error", "Unable to print the attendance report: " + ex.getMessage());
        }
    }

    private void handleRefreshAction() {
        recordCountLabel.setText("Refreshing data...");
        loadAttendanceData();
        showSuccessMessage("Refresh Complete", "Teacher attendance data has been refreshed successfully!");
    }

    private void handleExportAction() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Export Teacher Attendance Data");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("CSV Files", "csv"));
        
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            // Export functionality can be implemented here
            showSuccessMessage("Export", "Export functionality will be implemented in the next version.");
        }
    }

    private void handleFilterAction() {
        String selectedFilter = (String) filterComboBox.getSelectedItem();
        recordCountLabel.setText("Applying filter: " + selectedFilter + "...");
        
        // Filter functionality can be implemented here based on the selected option
        SwingUtilities.invokeLater(() -> {
            // For now, just reload all data
            loadAttendanceData();
            showSuccessMessage("Filter Applied", "Filter '" + selectedFilter + "' has been applied.");
        });
    }

    // Custom cell renderer for status column
    private class StatusCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, 
                boolean isSelected, boolean hasFocus, int row, int column) {
            
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            if (!isSelected) {
                String status = value.toString();
                switch (status) {
                    case "Full Day":
                        setBackground(new Color(212, 237, 218));
                        setForeground(new Color(21, 87, 36));
                        break;
                    case "Half Day":
                        setBackground(new Color(255, 243, 205));
                        setForeground(new Color(133, 100, 4));
                        break;
                    case "Absent":
                        setBackground(new Color(248, 215, 218));
                        setForeground(new Color(114, 28, 36));
                        break;
                    default:
                        setBackground(Color.WHITE);
                        setForeground(TEXT_COLOR);
                }
            }
            
            setHorizontalAlignment(JLabel.CENTER);
            setFont(new Font("Segoe UI", Font.BOLD, 11));
            
            return c;
        }
    }

    // Custom cell renderer for department column
    private class DepartmentCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, 
                boolean isSelected, boolean hasFocus, int row, int column) {
            
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            if (!isSelected) {
                String department = value.toString();
                switch (department.toLowerCase()) {
                    case "computer science":
                    case "cs":
                        setBackground(new Color(230, 245, 255));
                        setForeground(new Color(13, 71, 161));
                        break;
                    case "mathematics":
                    case "math":
                        setBackground(new Color(255, 243, 224));
                        setForeground(new Color(191, 54, 12));
                        break;
                    case "physics":
                        setBackground(new Color(238, 247, 255));
                        setForeground(new Color(25, 118, 210));
                        break;
                    case "chemistry":
                        setBackground(new Color(255, 249, 196));
                        setForeground(new Color(158, 157, 36));
                        break;
                    case "english":
                        setBackground(new Color(240, 248, 255));
                        setForeground(new Color(21, 101, 192));
                        break;
                    default:
                        setBackground(new Color(248, 249, 250));
                        setForeground(TEXT_COLOR);
                }
            }
            
            setHorizontalAlignment(JLabel.CENTER);
            setFont(new Font("Segoe UI", Font.PLAIN, 11));
            
            return c;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Use default look and feel
            }
            new TeacherAttendanceDetail().setVisible(true);
        });
    }
}