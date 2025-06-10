package university;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;

class AddStudent implements ActionListener {

    JFrame f;
    JLabel titleLabel;
    JTextField nameField, fatherNameField, ageField, dobField, addressField, 
               phoneField, emailField, class10Field, class12Field, cnicField, rollNoField;
    JButton submitBtn, cancelBtn;
    JComboBox<String> courseCombo, branchCombo;
    JPanel mainPanel, formPanel, buttonPanel, headerPanel;

    Random ran = new Random();
    long first4 = (ran.nextLong() % 9000L) + 1000L;
    long first = Math.abs(first4);

    public AddStudent() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        setupFrame();
    }

    private void initializeComponents() {
        // Frame setup
        f = new JFrame("Add New Student");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Main panels
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Header panel
        headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(41, 128, 185));
        headerPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        
        titleLabel = new JLabel("Student Registration Form");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Form panel with GridBag layout for better control
        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 220, 220), 1),
            new EmptyBorder(30, 30, 30, 30)
        ));
        
        // Text fields with improved styling
        nameField = createStyledTextField();
        fatherNameField = createStyledTextField();
        ageField = createStyledTextField();
        dobField = createStyledTextField();
        addressField = createStyledTextField();
        phoneField = createStyledTextField();
        emailField = createStyledTextField();
        class10Field = createStyledTextField();
        class12Field = createStyledTextField();
        cnicField = createStyledTextField();
        rollNoField = createStyledTextField();
        rollNoField.setText("1533" + first);
        rollNoField.setEditable(false);
        rollNoField.setBackground(new Color(240, 240, 240));
        
        // ComboBoxes
        String[] courses = {"B.Tech", "BBA", "BE", "Bsc", "Msc", "MBA", "MCA", "BA", "BCom"};
        courseCombo = createStyledComboBox(courses);
        
        String[] branches = {"Computer Engineering", "Electronics", "Electrical", "Mechanical", "Civil"};
        branchCombo = createStyledComboBox(branches);
        
        // Button panel
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        
        // Buttons with modern styling
        submitBtn = createStyledButton("Submit", new Color(46, 204, 113));
        cancelBtn = createStyledButton("Cancel", new Color(231, 76, 60));
    }
    
    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(200, 35));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(5, 10, 5, 10)
        ));
        
        // Add focus effects
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(41, 128, 185), 2),
                    new EmptyBorder(5, 10, 5, 10)
                ));
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(200, 200, 200), 1),
                    new EmptyBorder(5, 10, 5, 10)
                ));
            }
        });
        
        return field;
    }
    
    private JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setPreferredSize(new Dimension(200, 35));
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
        combo.setBorder(new LineBorder(new Color(200, 200, 200), 1));
        return combo;
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(120, 40));
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            Color originalColor = bgColor;
            
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
            }
        });
        
        return button;
    }
    
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(52, 73, 94));
        return label;
    }
    
    private void setupLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Row 1
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createStyledLabel("Name:"), gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);
        gbc.gridx = 2;
        formPanel.add(createStyledLabel("Father's Name:"), gbc);
        gbc.gridx = 3;
        formPanel.add(fatherNameField, gbc);
        
        // Row 2
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(createStyledLabel("Age:"), gbc);
        gbc.gridx = 1;
        formPanel.add(ageField, gbc);
        gbc.gridx = 2;
        formPanel.add(createStyledLabel("Date of Birth:"), gbc);
        gbc.gridx = 3;
        formPanel.add(dobField, gbc);
        
        // Row 3
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(createStyledLabel("Address:"), gbc);
        gbc.gridx = 1;
        formPanel.add(addressField, gbc);
        gbc.gridx = 2;
        formPanel.add(createStyledLabel("Phone:"), gbc);
        gbc.gridx = 3;
        formPanel.add(phoneField, gbc);
        
        // Row 4
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(createStyledLabel("Email:"), gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);
        gbc.gridx = 2;
        formPanel.add(createStyledLabel("Class X (%):"), gbc);
        gbc.gridx = 3;
        formPanel.add(class10Field, gbc);
        
        // Row 5
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(createStyledLabel("Class XII (%):"), gbc);
        gbc.gridx = 1;
        formPanel.add(class12Field, gbc);
        gbc.gridx = 2;
        formPanel.add(createStyledLabel("CNIC No:"), gbc);
        gbc.gridx = 3;
        formPanel.add(cnicField, gbc);
        
        // Row 6
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(createStyledLabel("Roll No:"), gbc);
        gbc.gridx = 1;
        formPanel.add(rollNoField, gbc);
        gbc.gridx = 2;
        formPanel.add(createStyledLabel("Course:"), gbc);
        gbc.gridx = 3;
        formPanel.add(courseCombo, gbc);
        
        // Row 7
        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(createStyledLabel("Branch:"), gbc);
        gbc.gridx = 1;
        formPanel.add(branchCombo, gbc);
        
        // Add components to panels
        headerPanel.add(titleLabel);
        buttonPanel.add(submitBtn);
        buttonPanel.add(cancelBtn);
        
        // Add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void setupEventHandlers() {
        submitBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        
        // Add Enter key support for form submission
        KeyListener enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    submitBtn.doClick();
                }
            }
        };
        
        // Add enter key listener to all text fields
        nameField.addKeyListener(enterKeyListener);
        fatherNameField.addKeyListener(enterKeyListener);
        ageField.addKeyListener(enterKeyListener);
        dobField.addKeyListener(enterKeyListener);
        addressField.addKeyListener(enterKeyListener);
        phoneField.addKeyListener(enterKeyListener);
        emailField.addKeyListener(enterKeyListener);
        class10Field.addKeyListener(enterKeyListener);
        class12Field.addKeyListener(enterKeyListener);
        cnicField.addKeyListener(enterKeyListener);
    }
    
    private void setupFrame() {
        f.add(mainPanel);
        f.setSize(900, 700);
        f.setLocationRelativeTo(null); // Center the window
        f.setResizable(false);
        f.setVisible(true);
        
        // Set focus to first field
        SwingUtilities.invokeLater(() -> nameField.requestFocusInWindow());
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitBtn) {
            // Validate required fields
            if (validateFields()) {
                try {
                    String name = nameField.getText().trim();
                    String fatherName = fatherNameField.getText().trim();
                    String age = ageField.getText().trim();
                    String dob = dobField.getText().trim();
                    String address = addressField.getText().trim();
                    String phone = phoneField.getText().trim();
                    String email = emailField.getText().trim();
                    String class10 = class10Field.getText().trim();
                    String class12 = class12Field.getText().trim();
                    String cnic = cnicField.getText().trim();
                    String rollNo = rollNoField.getText().trim();
                    String course = (String) courseCombo.getSelectedItem();
                    String branch = (String) branchCombo.getSelectedItem();

                    Conn cc = new Conn();
                    String query = "INSERT INTO student VALUES('" + name + "','" + fatherName + "','" + 
                                 age + "','" + dob + "','" + address + "','" + phone + "','" + email + "','" + 
                                 class10 + "','" + class12 + "','" + cnic + "','" + rollNo + "','" + 
                                 course + "','" + branch + "')";
                    
                    cc.s.executeUpdate(query);
                    
                    // Show success message with custom styling
                    JOptionPane.showMessageDialog(f, 
                        "Student details have been successfully registered!", 
                        "Registration Successful", 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    f.dispose();
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(f, 
                        "Error occurred while saving student details: " + e.getMessage(), 
                        "Registration Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (ae.getSource() == cancelBtn) {
            int option = JOptionPane.showConfirmDialog(f, 
                "Are you sure you want to cancel? All entered data will be lost.", 
                "Confirm Cancel", 
                JOptionPane.YES_NO_OPTION);
            
            if (option == JOptionPane.YES_OPTION) {
                f.dispose();
            }
        }
    }
    
    private boolean validateFields() {
        if (nameField.getText().trim().isEmpty()) {
            showValidationError("Please enter student name.");
            nameField.requestFocus();
            return false;
        }
        
        if (fatherNameField.getText().trim().isEmpty()) {
            showValidationError("Please enter father's name.");
            fatherNameField.requestFocus();
            return false;
        }
        
        if (ageField.getText().trim().isEmpty()) {
            showValidationError("Please enter age.");
            ageField.requestFocus();
            return false;
        }
        
        if (dobField.getText().trim().isEmpty()) {
            showValidationError("Please enter date of birth.");
            dobField.requestFocus();
            return false;
        }
        
        if (phoneField.getText().trim().isEmpty()) {
            showValidationError("Please enter phone number.");
            phoneField.requestFocus();
            return false;
        }
        
        if (cnicField.getText().trim().isEmpty()) {
            showValidationError("Please enter CNIC number.");
            cnicField.requestFocus();
            return false;
        }
        
        return true;
    }
    
    private void showValidationError(String message) {
        JOptionPane.showMessageDialog(f, message, "Validation Error", JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new AddStudent());
    }
}