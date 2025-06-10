package university;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame implements ActionListener {

    JLabel l1, l2, title;
    JTextField t1;
    JPasswordField t2;
    JButton b1, b2;

    Login() {
        super("University Management System - Login");

        setLayout(null);
        
        // Main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth(), h = getHeight();
                Color color1 = new Color(74, 144, 226);
                Color color2 = new Color(180, 210, 255);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 650, 400);
        add(mainPanel);

        // Login card panel
        JPanel loginCard = new JPanel();
        loginCard.setLayout(null);
        loginCard.setBackground(Color.WHITE);
        loginCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createRaisedBevelBorder(),
            new EmptyBorder(20, 20, 20, 20)
        ));
        loginCard.setBounds(80, 50, 480, 280);
        mainPanel.add(loginCard);

        // Title Label with modern styling
        title = new JLabel("University Login", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(44, 62, 80));
        title.setBounds(0, 20, 480, 40);
        loginCard.add(title);

        // Subtitle
        JLabel subtitle = new JLabel("For Management use only", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(127, 140, 141));
        subtitle.setBounds(0, 60, 480, 20);
        loginCard.add(subtitle);

        // Username section
        l1 = new JLabel("Username");
        l1.setBounds(60, 110, 100, 25);
        l1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        l1.setForeground(new Color(52, 73, 94));
        loginCard.add(l1);

        t1 = new JTextField();
        t1.setBounds(60, 135, 180, 35);
        t1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        t1.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        loginCard.add(t1);

        // Password section
        l2 = new JLabel("Password");
        l2.setBounds(270, 110, 100, 25);
        l2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        l2.setForeground(new Color(52, 73, 94));
        loginCard.add(l2);

        t2 = new JPasswordField();
        t2.setBounds(270, 135, 180, 35);
        t2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        t2.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        loginCard.add(t2);

        // Login button with modern styling
        b1 = new JButton("LOGIN");
        b1.setBounds(60, 200, 180, 40);
        b1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b1.setBackground(new Color(46, 204, 113));
        b1.setForeground(Color.WHITE);
        b1.setBorder(BorderFactory.createEmptyBorder());
        b1.setFocusPainted(false);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effect for login button
        b1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                b1.setBackground(new Color(39, 174, 96));
            }
            public void mouseExited(MouseEvent e) {
                b1.setBackground(new Color(46, 204, 113));
            }
        });
        
        b1.addActionListener(this);
        loginCard.add(b1);

        // Cancel button with modern styling
        b2 = new JButton("CANCEL");
        b2.setBounds(270, 200, 180, 40);
        b2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b2.setBackground(new Color(231, 76, 60));
        b2.setForeground(Color.WHITE);
        b2.setBorder(BorderFactory.createEmptyBorder());
        b2.setFocusPainted(false);
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effect for cancel button
        b2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                b2.setBackground(new Color(192, 57, 43));
            }
            public void mouseExited(MouseEvent e) {
                b2.setBackground(new Color(231, 76, 60));
            }
        });
        
        b2.addActionListener(this);
        loginCard.add(b2);

        // University logo/image - positioned at top left corner of the card
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
            if (i1.getIconWidth() > 0) {
                Image i2 = i1.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                ImageIcon i3 = new ImageIcon(i2);
                JLabel logoLabel = new JLabel(i3);
                logoLabel.setBounds(20, 15, 60, 60);
                loginCard.add(logoLabel);
            }
        } catch (Exception e) {
            // If image not found, add a placeholder
            JLabel placeholderLogo = new JLabel("🎓", SwingConstants.CENTER);
            placeholderLogo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 30));
            placeholderLogo.setBounds(20, 15, 60, 60);
            placeholderLogo.setForeground(new Color(52, 73, 94));
            loginCard.add(placeholderLogo);
        }

        // Footer text
        JLabel footerLabel = new JLabel("© 2025 University Management System", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setBounds(0, 350, 650, 20);
        mainPanel.add(footerLabel);

        setSize(650, 400);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            Conn c1 = new Conn();
            String u = t1.getText();
            String v = new String(t2.getPassword());

            String q = "SELECT * FROM login WHERE username='" + u + "' AND password='" + v + "'";
            ResultSet rs = c1.s.executeQuery(q);
            if (rs.next()) {
                new Project().setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                this.setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new Login();
    }
}