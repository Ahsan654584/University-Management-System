package university;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.sql.*;

public class TeacherDetails extends JFrame implements ActionListener{
 
    JLabel l1, l2, l3, titleLabel;
    JTable t1;
    JButton b1, b2, b3;
    JTextField t2;
    JPanel headerPanel, tablePanel, actionPanel, deletePanel, addPanel, updatePanel;
    String x[] = {"Name","Father's Name","Age","Date of Birth","Address","Phone","Email","Class X(%)", "Class XII(%)", "CNIC No","Course","Department", "Employee Id"};
    String y[][] = new String[20][13];
    int i=0, j=0;
    
    // Modern color scheme
    private final Color PRIMARY_COLOR = new Color(63, 81, 181);      // Indigo
    private final Color SECONDARY_COLOR = new Color(92, 107, 192);   // Light Indigo
    private final Color ACCENT_COLOR = new Color(255, 87, 34);       // Deep Orange
    private final Color SUCCESS_COLOR = new Color(76, 175, 80);      // Green
    private final Color DANGER_COLOR = new Color(244, 67, 54);       // Red
    private final Color BACKGROUND_COLOR = new Color(245, 245, 245); // Light Gray
    private final Color CARD_COLOR = Color.WHITE;
    private final Color TEXT_COLOR = new Color(33, 33, 33);          // Dark Gray
    
    TeacherDetails(){
        super("University Management System - Teacher Details");
        initializeComponents();
        setupLayout();
        loadData();
        styleComponents();
        setupEventListeners();
    }
    
    private void initializeComponents() {
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Create panels
        headerPanel = new JPanel();
        tablePanel = new JPanel();
        actionPanel = new JPanel();
        deletePanel = new JPanel();
        addPanel = new JPanel();
        updatePanel = new JPanel();
        
        // Initialize components
        titleLabel = new JLabel("Teacher Management Dashboard");
        
        l1 = new JLabel(" Delete Teacher by Employee ID:");
        t2 = new JTextField(15);
        b1 = new JButton("Delete Teacher");
        
        l2 = new JLabel(" Add New Teacher");
        b2 = new JButton("Add Teacher");
        
        l3 = new JLabel(" Update Teacher Details");
        b3 = new JButton("Update Teacher");
    }
    
    private void setupLayout() {
        // Header Panel
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Table Panel
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        
        // Action Panel with better layout
        actionPanel.setLayout(new GridLayout(1, 3, 20, 0));
        actionPanel.setBorder(new EmptyBorder(20, 20, 25, 20));
        actionPanel.setPreferredSize(new Dimension(1400, 120));
        
        // Delete Panel
        deletePanel.setLayout(new BorderLayout(10, 10));
        deletePanel.setBorder(createTitledBorder("Delete Teacher"));
        deletePanel.setPreferredSize(new Dimension(400, 100));
        
        JPanel deleteInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        deleteInputPanel.setBackground(CARD_COLOR);
        deleteInputPanel.add(l1);
        deleteInputPanel.add(t2);
        deleteInputPanel.add(b1);
        
        deletePanel.add(deleteInputPanel, BorderLayout.CENTER);
        
        // Add Panel
        addPanel.setLayout(new BorderLayout(10, 10));
        addPanel.setBorder(createTitledBorder("Add Teacher"));
        addPanel.setPreferredSize(new Dimension(400, 100));
        
        JPanel addInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        addInputPanel.setBackground(CARD_COLOR);
        addInputPanel.add(l2);
        addInputPanel.add(b2);
        
        addPanel.add(addInputPanel, BorderLayout.CENTER);
        
        // Update Panel
        updatePanel.setLayout(new BorderLayout(10, 10));
        updatePanel.setBorder(createTitledBorder("Update Teacher"));
        updatePanel.setPreferredSize(new Dimension(400, 100));
        
        JPanel updateInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        updateInputPanel.setBackground(CARD_COLOR);
        updateInputPanel.add(l3);
        updateInputPanel.add(b3);
        
        updatePanel.add(updateInputPanel, BorderLayout.CENTER);
        
        // Add panels to action panel
        actionPanel.add(deletePanel);
        actionPanel.add(addPanel);
        actionPanel.add(updatePanel);
        
        // Add to main frame
        add(headerPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);
    }
    
    private void loadData() {
        try{
            Conn c1 = new Conn();
            String s1 = "select * from teacher";
            ResultSet rs = c1.s.executeQuery(s1);
            while(rs.next()){
                y[i][j++]=rs.getString("name");
                y[i][j++]=rs.getString("fathers_name");
                y[i][j++]=rs.getString("age");
                y[i][j++]=rs.getString("dob");
                y[i][j++]=rs.getString("address");
                y[i][j++]=rs.getString("phone");
                y[i][j++]=rs.getString("email");
                y[i][j++]=rs.getString("class_x");
                y[i][j++]=rs.getString("class_xii");
                y[i][j++]=rs.getString("aadhar");
                y[i][j++]=rs.getString("course");
                y[i][j++]=rs.getString("dept");
                y[i][j++]=rs.getString("emp_id");
                i++;
                j=0;
            }
            t1 = new JTable(y, x);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        // Create scrollpane and add to table panel
        JScrollPane sp = new JScrollPane(t1);
        sp.setBorder(createTitledBorder("Teacher Information"));
        tablePanel.add(sp, BorderLayout.CENTER);
    }
    
    private void styleComponents() {
        // Set background colors
        getContentPane().setBackground(BACKGROUND_COLOR);
        headerPanel.setBackground(PRIMARY_COLOR);
        tablePanel.setBackground(BACKGROUND_COLOR);
        actionPanel.setBackground(BACKGROUND_COLOR);
        deletePanel.setBackground(CARD_COLOR);
        addPanel.setBackground(CARD_COLOR);
        updatePanel.setBackground(CARD_COLOR);
        
        // Style title
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Style labels - make them more compact
        Font labelFont = new Font("Segoe UI", Font.BOLD, 12);
        l1.setFont(labelFont);
        l1.setForeground(TEXT_COLOR);
        l1.setText("Employee ID:");
        
        l2.setFont(labelFont);
        l2.setForeground(TEXT_COLOR);
        
        l3.setFont(labelFont);
        l3.setForeground(TEXT_COLOR);
        
        // Style text field - make it more compact
        t2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        t2.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        t2.setPreferredSize(new Dimension(150, 30));
        
        // Style buttons
        styleButton(b1, DANGER_COLOR, " Delete");
        styleButton(b2, SUCCESS_COLOR, " Add Teacher");
        styleButton(b3, ACCENT_COLOR, " Update");
        
        // Style table
        if (t1 != null) {
            styleTable();
        }
    }
    
    private void styleButton(JButton button, Color bgColor, String text) {
        button.setText(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 11));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(120, 32));
        
        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            Color originalColor = button.getBackground();
            
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(originalColor.darker());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
            }
        });
    }
    
    private void styleTable() {
        // Table styling
        t1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        t1.setRowHeight(30);
        t1.setGridColor(new Color(230, 230, 230));
        t1.setSelectionBackground(new Color(232, 245, 233));
        t1.setSelectionForeground(TEXT_COLOR);
        t1.setShowVerticalLines(true);
        t1.setShowHorizontalLines(true);
        
        // Header styling
        JTableHeader header = t1.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(SECONDARY_COLOR);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        
        // Center align specific columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        t1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // Age
        t1.getColumnModel().getColumn(7).setCellRenderer(centerRenderer); // Class X
        t1.getColumnModel().getColumn(8).setCellRenderer(centerRenderer); // Class XII
        t1.getColumnModel().getColumn(12).setCellRenderer(centerRenderer); // Employee ID
        
        // Set preferred column widths
        setColumnWidths();
    }
    
    private void setColumnWidths() {
        int[] columnWidths = {120, 150, 50, 100, 200, 120, 200, 80, 80, 120, 100, 120, 100};
        for (int i = 0; i < columnWidths.length && i < t1.getColumnCount(); i++) {
            t1.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }
    }
    
    private TitledBorder createTitledBorder(String title) {
        TitledBorder border = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            title,
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 12),
            SECONDARY_COLOR
        );
        return border;
    }
    
    private void setupEventListeners() {
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        // Add Enter key listener to text field
        t2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    b1.doClick();
                }
            }
        });
    }
    
    public void actionPerformed(ActionEvent ae){
        Conn c1 = new Conn();
    
        if(ae.getSource() == b1){
            try{
                String empId = t2.getText().trim();
                if(empId.isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                        "Please enter an Employee ID to delete.", 
                        "Input Required", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete teacher with Employee ID: " + empId + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
                
                if(confirm == JOptionPane.YES_OPTION) {
                    String q = "delete from teacher where emp_id = '"+empId+"'";
                    int result = c1.s.executeUpdate(q);
                    
                    if(result > 0) {
                        JOptionPane.showMessageDialog(this,
                            "Teacher deleted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                        this.setVisible(false);
                        new TeacherDetails().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this,
                            "No teacher found with Employee ID: " + empId,
                            "Not Found",
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(this,
                    "Error deleting teacher: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            }
    
        }else if(ae.getSource() == b2){
            new AddTeacher().f.setVisible(true);
        }else if(ae.getSource() == b3){
            new UpdateTeacher().f.setVisible(true);
        }
    }
    
    public static void main(String[] args){
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TeacherDetails().setVisible(true);
            }
        });
    }
}