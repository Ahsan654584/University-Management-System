package university;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;

public class StudentDetails extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public StudentDetails() {
        setTitle("Student Details");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Column names
        String[] columnNames = {
            "Name", "Father's Name", "Age", "DOB", "Address", "Phone", "Email",
            "Class X (%)", "Class XII (%)", "CNIC No", "Roll No", "Course", "Branch"
        };

        // Non-editable model by overriding isCellEditable
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // make all cells non-editable
            }
        };

        table = new JTable(model);

        // Wrap cell renderer for columns with long text (e.g., Address, Email)
        TextAreaRenderer textAreaRenderer = new TextAreaRenderer();

        // Apply custom renderer to "Address" and "Email" columns
        table.getColumnModel().getColumn(4).setCellRenderer(textAreaRenderer); // Address
        table.getColumnModel().getColumn(6).setCellRenderer(textAreaRenderer); // Email

        // Set row height to allow multi-line display
        table.setRowHeight(60);

        // Appearance tweaks
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setGridColor(Color.LIGHT_GRAY);
        table.setSelectionBackground(new Color(184, 207, 229));
        table.setSelectionForeground(Color.BLACK);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 15));
        header.setBackground(new Color(100, 149, 237));
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);

        // Center align numeric columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);  // Age
        table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);  // Class X (%)
        table.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);  // Class XII (%)
        table.getColumnModel().getColumn(10).setCellRenderer(centerRenderer); // Roll No

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadData();
    }

    private void loadData() {
        String url = "jdbc:mysql://localhost:3306/ums"; // Your DB URL
        String user = "root";                            // Your DB username
        String password = "root";                        // Your DB password

        String query = "SELECT * FROM student";

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Object[] row = {
                    rs.getString("name"),
                    rs.getString("fathers_name"),
                    rs.getInt("age"),
                    rs.getString("dob"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getDouble("class_x"),
                    rs.getDouble("class_xii"),
                    rs.getString("cnic"),
                    rs.getString("rollno"),
                    rs.getString("course"),
                    rs.getString("branch")
                };
                model.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }

    // Custom cell renderer to enable text wrapping in JTable cells
    static class TextAreaRenderer extends JTextArea implements TableCellRenderer {
        public TextAreaRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            setText(value != null ? value.toString() : "");
            setFont(table.getFont());

            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }

            // Adjust row height to fit the text content
            int preferredHeight = getPreferredSize().height;
            if (table.getRowHeight(row) != preferredHeight) {
                table.setRowHeight(row, preferredHeight);
            }
            return this;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentDetails().setVisible(true);
        });
    }
}
