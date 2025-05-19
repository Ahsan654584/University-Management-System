package university;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JLabel l1, l2, title;
    JTextField t1;
    JPasswordField t2;
    JButton b1, b2;

    Login() {
        super("Login");

        setLayout(null);

        // Title Label
        title = new JLabel("University Login");
        title.setFont(new Font("Tahoma", Font.BOLD, 22));
        title.setBounds(180, 10, 250, 40);
        add(title);

        l1 = new JLabel("Username:");
        l1.setBounds(60, 70, 100, 25);
        l1.setFont(new Font("Arial", Font.PLAIN, 16));
        add(l1);

        l2 = new JLabel("Password:");
        l2.setBounds(60, 120, 100, 25);
        l2.setFont(new Font("Arial", Font.PLAIN, 16));
        add(l2);

        t1 = new JTextField();
        t1.setBounds(160, 70, 180, 30);
        t1.setFont(new Font("Arial", Font.PLAIN, 14));
        t1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(t1);

        t2 = new JPasswordField();
        t2.setBounds(160, 120, 180, 30);
        t2.setFont(new Font("Arial", Font.PLAIN, 14));
        t2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(t2);

        b1 = new JButton("Login");
        b1.setBounds(60, 180, 130, 35);
        b1.setFont(new Font("Arial", Font.BOLD, 15));
        b1.setBackground(new Color(34, 139, 34)); // Forest green
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(210, 180, 130, 35);
        b2.setFont(new Font("Arial", Font.BOLD, 15));
        b2.setBackground(new Color(220, 20, 60)); // Crimson
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        add(b2);

        // Image on the right
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(370, 60, 160, 160);
        add(l3);

        getContentPane().setBackground(new Color(245, 245, 245)); // light gray background

        setSize(580, 300);
        setLocation(450, 300);
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
