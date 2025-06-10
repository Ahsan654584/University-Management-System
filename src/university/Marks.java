package university;

import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class Marks extends JFrame {

    JTextArea t1;
    JPanel p1;

    Marks() {}

    Marks(String str) {
        setTitle("Student Marks");
        setSize(500, 600);
        setLayout(new BorderLayout());

        p1 = new JPanel();
        t1 = new JTextArea(50, 15);
        JScrollPane jsp = new JScrollPane(t1);
        t1.setFont(new Font("Serif", Font.ITALIC, 18));
        t1.setEditable(false);

        add(p1, BorderLayout.NORTH);
        add(jsp, BorderLayout.CENTER);

        setLocation(450, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mark(str);
    }

    public void mark(String rollNo) {
        try {
            Conn c = new Conn();

            t1.setText("\tResult of Examination\n\nSubjects:\n");

            // Ensure rollNo is quoted properly for SQL string
            ResultSet rs1 = c.s.executeQuery("SELECT * FROM subject WHERE rollno='" + rollNo + "'");
            if (rs1.next()) {
                t1.append("\n\t" + rs1.getString("subject1"));
                t1.append("\n\t" + rs1.getString("subject2"));
                t1.append("\n\t" + rs1.getString("subject3"));
                t1.append("\n\t" + rs1.getString("subject4"));
                t1.append("\n\t" + rs1.getString("subject5"));
                t1.append("\n-----------------------------------------\n");
            } else {
                t1.append("\nNo subject record found for Roll No: " + rollNo);
            }

            ResultSet rs2 = c.s.executeQuery("SELECT * FROM marks WHERE rollno='" + rollNo + "'");
            if (rs2.next()) {
                t1.append("\nMarks:\n");
                t1.append("\n\t" + rs2.getString("marks1"));
                t1.append("\n\t" + rs2.getString("marks2"));
                t1.append("\n\t" + rs2.getString("marks3"));
                t1.append("\n\t" + rs2.getString("marks4"));
                t1.append("\n\t" + rs2.getString("marks5"));
                t1.append("\n-----------------------------------------\n");
            } else {
                t1.append("\nNo marks record found for Roll No: " + rollNo);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Example roll number "101" passed to constructor
        new Marks("101").setVisible(true);
    }
}
