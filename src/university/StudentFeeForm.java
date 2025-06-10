package university;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentFeeForm extends JFrame implements ActionListener {

    Choice c1;
    JComboBox<String> comboBox, comboBox_2, comboBox_3;
    JTextField t1, t2, t3;
    JButton b1, b2;

    public StudentFeeForm() {
        setTitle("Student Fee Form");
        setSize(900, 600);
        setLocation(300, 100);
        setLayout(null);

        JLabel heading = new JLabel("Student Fee Form");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(300, 30, 300, 40);
        add(heading);

        JLabel lblRoll = new JLabel("Select Roll Number");
        lblRoll.setBounds(50, 100, 150, 20);
        add(lblRoll);

        c1 = new Choice();
        c1.setBounds(200, 100, 200, 20);
        add(c1);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(50, 140, 150, 20);
        add(lblName);

        t2 = new JTextField();
        t2.setBounds(200, 140, 200, 20);
        add(t2);

        JLabel lblFName = new JLabel("Father's Name");
        lblFName.setBounds(50, 180, 150, 20);
        add(lblFName);

        t3 = new JTextField();
        t3.setBounds(200, 180, 200, 20);
        add(t3);

        JLabel lblCourse = new JLabel("Course");
        lblCourse.setBounds(50, 220, 150, 20);
        add(lblCourse);

        comboBox_3 = new JComboBox<>();
        comboBox_3.setBounds(200, 220, 200, 20);
        add(comboBox_3);

        JLabel lblBranch = new JLabel("Branch");
        lblBranch.setBounds(50, 260, 150, 20);
        add(lblBranch);

        comboBox = new JComboBox<>();
        comboBox.setBounds(200, 260, 200, 20);
        add(comboBox);

        JLabel lblSemester = new JLabel("Semester");
        lblSemester.setBounds(50, 300, 150, 20);
        add(lblSemester);

        comboBox_2 = new JComboBox<>();
        comboBox_2.setBounds(200, 300, 200, 20);
        add(comboBox_2);

        JLabel lblFee = new JLabel("Total Fee");
        lblFee.setBounds(50, 340, 150, 20);
        add(lblFee);

        t1 = new JTextField();
        t1.setBounds(200, 340, 200, 20);
        add(t1);

        b1 = new JButton("Pay");
        b1.setBounds(100, 400, 100, 30);
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(250, 400, 100, 30);
        add(b2);

        loadStudentData();
        setupEventListeners();

        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadStudentData() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT rollno FROM student");

            while (rs.next()) {
                c1.add(rs.getString("rollno"));
            }

            comboBox.addItem("Computer Science");
            comboBox.addItem("Electrical");
            comboBox.addItem("Mechanical");

            comboBox_2.addItem("1st Semester");
            comboBox_2.addItem("2nd Semester");
            comboBox_2.addItem("3rd Semester");
            comboBox_2.addItem("4th Semester");

            comboBox_3.addItem("B.Tech");
            comboBox_3.addItem("M.Tech");
            comboBox_3.addItem("MBA");

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load data from database");
        }
    }

    private void setupEventListeners() {
        b1.addActionListener(this);
        b2.addActionListener(this);

        c1.addItemListener(_ -> {
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "password");
                String rollno = c1.getSelectedItem();
                String query = "SELECT * FROM student WHERE rollno=?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, rollno);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    t2.setText(rs.getString("name"));
                    t3.setText(rs.getString("fname"));
                    comboBox_3.setSelectedItem(rs.getString("course"));
                    comboBox.setSelectedItem(rs.getString("branch"));
                }

                rs.close();
                ps.close();
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error fetching student data");
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            // Pay Fee
            String rollno = c1.getSelectedItem();
            String name = t2.getText();
            String fname = t3.getText();
            String course = (String) comboBox_3.getSelectedItem();
            String branch = (String) comboBox.getSelectedItem();
            String semester = (String) comboBox_2.getSelectedItem();
            String fee = t1.getText();

            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "password");
                String query = "INSERT INTO fee (rollno, name, fname, course, branch, semester, fee) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, rollno);
                ps.setString(2, name);
                ps.setString(3, fname);
                ps.setString(4, course);
                ps.setString(5, branch);
                ps.setString(6, semester);
                ps.setString(7, fee);

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(null, "Fee Paid Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Payment Failed");
                }

                ps.close();
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }

        } else if (e.getSource() == b2) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentFeeForm();
    }
}
