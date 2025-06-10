package university;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TeacherAttendance extends JFrame implements ActionListener {
    
    private JLabel titleLabel, teacherIdLabel, firstHalfLabel, secondHalfLabel, dateLabel;
    private JComboBox<String> teacherIdComboBox, firstHalfCombo, secondHalfCombo;
    private JButton submitButton, cancelButton;
    private JPanel mainPanel, headerPanel, formPanel, buttonPanel;
    private Font titleFont, labelFont;
    
    public TeacherAttendance() {
        initializeComponents();
        setupLayout();
        loadTeacherData();
        setupEventHandlers();
        configureWindow();
    }
    
    private void initializeComponents() {
        // Initialize fonts
        titleFont = new Font("Segoe UI", Font.BOLD, 24);
        labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        
        // Title label
        titleLabel = new JLabel("Teacher Attendance Management", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(new Color(155, 89, 182));
        
        // Date label
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        dateLabel = new JLabel("Today: " + dateFormat.format(new Date()), SwingConstants.CENTER);
        dateLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        dateLabel.setForeground(new Color(127, 140, 141));
        
        // Form labels
        teacherIdLabel = new JLabel("Select Teacher ID:");
        teacherIdLabel.setFont(labelFont);
        
        firstHalfLabel = new JLabel("First Half:");
        firstHalfLabel.setFont(labelFont);
        
        secondHalfLabel = new JLabel("Second Half:");
        secondHalfLabel.setFont(labelFont);
        
        // ComboBoxes with modern styling
        teacherIdComboBox = new JComboBox<>();
        teacherIdComboBox.setFont(labelFont);
        teacherIdComboBox.setPreferredSize(new Dimension(200, 35));
        teacherIdComboBox.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        String[] attendanceOptions = {"Present", "Absent", "Leave"};
        
        firstHalfCombo = new JComboBox<>(attendanceOptions);
        firstHalfCombo.setFont(labelFont);
        firstHalfCombo.setPreferredSize(new Dimension(200, 35));
        firstHalfCombo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        secondHalfCombo = new JComboBox<>(attendanceOptions);
        secondHalfCombo.setFont(labelFont);
        secondHalfCombo.setPreferredSize(new Dimension(200, 35));
        secondHalfCombo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        // Modern buttons with teacher-specific colors
        submitButton = createModernButton("Submit Attendance", new Color(142, 68, 173), new Color(125, 60, 152));
        cancelButton = createModernButton("Cancel", new Color(231, 76, 60), new Color(192, 57, 43));
        
        // Panels
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
        
        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));
        
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 221, 225), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
    }
    
    private JButton createModernButton(String text, Color bgColor, Color hoverColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2.setColor(hoverColor.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(hoverColor);
                } else {
                    g2.setColor(bgColor);
                }
                
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.dispose();
                
                super.paintComponent(g);
            }
        };
        
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150, 40));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    private void setupLayout() {
        // Header panel with icon-like styling
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        
        // Add a small teacher icon using Unicode
        JLabel iconLabel = new JLabel("👨‍🏫", SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        
        JPanel titleWithIcon = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        titleWithIcon.setBackground(Color.WHITE);
        titleWithIcon.add(iconLabel);
        titleWithIcon.add(titleLabel);
        
        titlePanel.add(titleWithIcon, BorderLayout.CENTER);
        titlePanel.add(dateLabel, BorderLayout.SOUTH);
        
        headerPanel.add(titlePanel, BorderLayout.CENTER);
        
        // Form panel with GridBagLayout for better control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Teacher ID row
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(teacherIdLabel, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(teacherIdComboBox, gbc);
        
        // First half row
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        formPanel.add(firstHalfLabel, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(firstHalfCombo, gbc);
        
        // Second half row
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        formPanel.add(secondHalfLabel, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(secondHalfCombo, gbc);
        
        // Button panel
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        
        // Main panel assembly
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void loadTeacherData() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from teacher");
            while (rs.next()) {
                teacherIdComboBox.addItem(rs.getString("emp_id"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading teacher data: " + e.getMessage(),
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void setupEventHandlers() {
        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);
        
        // Add hover effects for ComboBoxes
        addHoverEffect(teacherIdComboBox);
        addHoverEffect(firstHalfCombo);
        addHoverEffect(secondHalfCombo);
    }
    
    private void addHoverEffect(JComboBox<String> comboBox) {
        comboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                comboBox.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(155, 89, 182), 2),
                    BorderFactory.createEmptyBorder(4, 9, 4, 9)
                ));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                comboBox.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }
        });
    }
    
    private void configureWindow() {
        setTitle("Teacher Attendance System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        
        // Set window icon (if you have an icon file)
        try {
            setIconImage(new ImageIcon(getClass().getResource("/icons/teacher.png")).getImage());
        } catch (Exception e) {
            // Icon not found, continue without it
        }
        
        getContentPane().setBackground(new Color(244, 236, 247));
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitButton) {
            if (teacherIdComboBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this,
                    "Please select a teacher ID.",
                    "Input Required",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String firstHalf = (String) firstHalfCombo.getSelectedItem();
            String secondHalf = (String) secondHalfCombo.getSelectedItem();
            String dateTime = new java.util.Date().toString();
            String teacherId = (String) teacherIdComboBox.getSelectedItem();
            
            String query = "insert into attendance_teacher values(" + teacherId + 
                          ",'" + dateTime + "','" + firstHalf + "','" + secondHalf + "')";
            
            try {
                Conn c1 = new Conn();
                c1.s.executeUpdate(query);
                
                // Show success message with better formatting
                JOptionPane.showMessageDialog(this,
                    "Teacher attendance recorded successfully!\n\n" +
                    "Teacher ID: " + teacherId + "\n" +
                    "First Half: " + firstHalf + "\n" +
                    "Second Half: " + secondHalf + "\n" +
                    "Date: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()),
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                
                this.setVisible(false);
                
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(this,
                    "Error recording attendance: " + ee.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
                ee.printStackTrace();
            }
        } else {
            this.setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Run on EDT
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TeacherAttendance();
            }
        });
    }
}