package university;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class EnterMarks extends JFrame implements ActionListener {

    JLabel l1, l2, l3, l4;
    JTextField t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11;
    JButton b1;

    EnterMarks() {
        setTitle("Student Marks Entry System");
        setSize(600, 650);
        setLocation(300, 100);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                GradientPaint gp = new GradientPaint(0, 0, new Color(240, 248, 255), 
                                                   0, getHeight(), new Color(230, 240, 250));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 600, 650);
        add(mainPanel);

        // Header with enhanced styling
        l1 = new JLabel("Enter Student Marks", JLabel.CENTER);
        l1.setFont(new Font("Segoe UI", Font.BOLD, 28));
        l1.setForeground(new Color(25, 25, 112));
        l1.setBounds(50, 20, 500, 50);
        l1.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(70, 130, 180)),
            BorderFactory.createEmptyBorder(10, 0, 10, 0)
        ));
        mainPanel.add(l1);

        // Roll Number Section with improved styling
        JPanel rollPanel = createStyledPanel("Student Information", 50, 90, 500, 80);
        mainPanel.add(rollPanel);
        
        l2 = new JLabel("Roll Number:");
        l2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        l2.setForeground(new Color(25, 25, 112));
        l2.setBounds(20, 35, 120, 25);
        rollPanel.add(l2);

        t1 = createStyledTextField();
        t1.setBounds(150, 35, 200, 30);
        t1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rollPanel.add(t1);

        // Subjects and Marks Section
        JPanel marksPanel = createStyledPanel("Subjects & Marks", 50, 190, 500, 350);
        mainPanel.add(marksPanel);

        l3 = new JLabel("Subject", JLabel.CENTER);
        l3.setFont(new Font("Segoe UI", Font.BOLD, 16));
        l3.setForeground(new Color(25, 25, 112));
        l3.setBounds(50, 35, 180, 30);
        l3.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(70, 130, 180)));
        marksPanel.add(l3);

        l4 = new JLabel("Marks", JLabel.CENTER);
        l4.setFont(new Font("Segoe UI", Font.BOLD, 16));
        l4.setForeground(new Color(25, 25, 112));
        l4.setBounds(270, 35, 180, 30);
        l4.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(70, 130, 180)));
        marksPanel.add(l4);

        // Create styled text fields for subjects and marks
        int yPos = 80;
        int spacing = 40;

        t2 = createStyledTextField(); t2.setBounds(50, yPos, 180, 30); marksPanel.add(t2);
        t3 = createStyledTextField(); t3.setBounds(270, yPos, 180, 30); marksPanel.add(t3);

        t4 = createStyledTextField(); t4.setBounds(50, yPos + spacing, 180, 30); marksPanel.add(t4);
        t5 = createStyledTextField(); t5.setBounds(270, yPos + spacing, 180, 30); marksPanel.add(t5);

        t6 = createStyledTextField(); t6.setBounds(50, yPos + 2*spacing, 180, 30); marksPanel.add(t6);
        t7 = createStyledTextField(); t7.setBounds(270, yPos + 2*spacing, 180, 30); marksPanel.add(t7);

        t8 = createStyledTextField(); t8.setBounds(50, yPos + 3*spacing, 180, 30); marksPanel.add(t8);
        t9 = createStyledTextField(); t9.setBounds(270, yPos + 3*spacing, 180, 30); marksPanel.add(t9);

        t10 = createStyledTextField(); t10.setBounds(50, yPos + 4*spacing, 180, 30); marksPanel.add(t10);
        t11 = createStyledTextField(); t11.setBounds(270, yPos + 4*spacing, 180, 30); marksPanel.add(t11);

        // Enhanced Submit Button
        b1 = new JButton("Submit Marks") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2d.setColor(new Color(30, 60, 114));
                } else if (getModel().isRollover()) {
                    g2d.setColor(new Color(65, 105, 225));
                } else {
                    g2d.setColor(new Color(70, 130, 180));
                }
                
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        
        b1.setBounds(220, 560, 160, 45);
        b1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        b1.setForeground(Color.WHITE);
        b1.setContentAreaFilled(false);
        b1.setBorderPainted(false);
        b1.setFocusPainted(false);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add shadow effect
        b1.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 0, 0, 50), 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        
        mainPanel.add(b1);
        b1.addActionListener(this);

        // Add subtle animation on hover
        addHoverEffects();
    }

    private JPanel createStyledPanel(String title, int x, int y, int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(x, y, width, height);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180), 2, true),
                title,
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14),
                new Color(25, 25, 112)
            ),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        // Add subtle shadow
        panel.setBorder(BorderFactory.createCompoundBorder(
            panel.getBorder(),
            BorderFactory.createMatteBorder(2, 2, 4, 4, new Color(0, 0, 0, 20))
        ));
        
        return panel;
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(176, 196, 222), 2),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        field.setBackground(Color.WHITE);
        
        // Add focus effects
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                    BorderFactory.createEmptyBorder(5, 8, 5, 8)
                ));
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(176, 196, 222), 2),
                    BorderFactory.createEmptyBorder(5, 8, 5, 8)
                ));
            }
        });
        
        return field;
    }

    private void addHoverEffects() {
        // Add tooltip
        b1.setToolTipText("Click to save student marks to database");
        
        // Add mouse effects
        b1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                b1.repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                b1.repaint();
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            try {
                Conn c1 = new Conn();

                String roll = t1.getText().trim();
                if (roll.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Roll number cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String s1 = "insert into subject values('" + roll + "','" + t2.getText() + "','" + t4.getText() + "','" +
                        t6.getText() + "','" + t8.getText() + "','" + t10.getText() + "')";

                String s2 = "insert into marks values('" + roll + "','" + t3.getText() + "','" + t5.getText() + "','" +
                        t7.getText() + "','" + t9.getText() + "','" + t11.getText() + "')";

                c1.s.executeUpdate(s1);
                c1.s.executeUpdate(s2);

                JOptionPane.showMessageDialog(this, "Marks Inserted Successfully");
                this.setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error inserting data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new EnterMarks().setVisible(true);
    }
}