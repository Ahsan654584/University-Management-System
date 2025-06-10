package university;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

class UpdateTeacher implements ActionListener{

    JFrame f;
    JLabel id,id1,id2,id3,id4,id5,id6,id7,id8,id9,id10,id11,id12,id15,lab,lab1,lab2;
    JTextField t,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14;
    JButton b,b1,b2; 
    String id_emp;

    UpdateTeacher(){
        f = new JFrame("Update Teacher Details");
        f.setSize(900, 700);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.getContentPane().setBackground(new Color(255, 248, 240));

        // Title
        id8 = new JLabel("Update Teacher Details", JLabel.CENTER);
        id8.setBounds(0, 20, 900, 40);
        id8.setFont(new Font("Arial", Font.BOLD, 28));
        id8.setForeground(new Color(139, 69, 19));
        f.add(id8);

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setBounds(50, 80, 800, 50);
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(new Color(255, 228, 225));
        searchPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(205, 92, 92), 2), 
            "Search Teacher", 
            TitledBorder.LEFT, 
            TitledBorder.TOP, 
            new Font("Arial", Font.BOLD, 14),
            new Color(205, 92, 92)));

        JLabel l1 = new JLabel("Enter Employee ID: ");
        l1.setFont(new Font("Arial", Font.BOLD, 14));
        searchPanel.add(l1);
        
        t12 = new JTextField(15);
        t12.setFont(new Font("Arial", Font.PLAIN, 14));
        searchPanel.add(t12);
        
        b2 = new JButton("Search");
        b2.setFont(new Font("Arial", Font.BOLD, 12));
        b2.setBackground(new Color(205, 92, 92));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        searchPanel.add(b2);

        f.add(searchPanel);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setBounds(50, 150, 800, 450);
        formPanel.setLayout(null);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(205, 92, 92), 2), 
            "Teacher Information", 
            TitledBorder.LEFT, 
            TitledBorder.TOP, 
            new Font("Arial", Font.BOLD, 14),
            new Color(205, 92, 92)));

        // Row 1
        createField(formPanel, "Name:", 20, 40, t1 = new JTextField());
        createField(formPanel, "Father's Name:", 420, 40, t2 = new JTextField());

        // Row 2
        createField(formPanel, "Age:", 20, 80, t3 = new JTextField());
        createField(formPanel, "DOB (dd/mm/yyyy):", 420, 80, t4 = new JTextField());

        // Row 3
        createField(formPanel, "Address:", 20, 120, t5 = new JTextField());
        createField(formPanel, "Phone:", 420, 120, t6 = new JTextField());

        // Row 4
        createField(formPanel, "Email ID:", 20, 160, t7 = new JTextField());
        createField(formPanel, "Class X (%):", 420, 160, t8 = new JTextField());

        // Row 5
        createField(formPanel, "Class XII (%):", 20, 200, t9 = new JTextField());
        createField(formPanel, "CNIC:", 420, 200, t10 = new JTextField());

        // Row 6
        createField(formPanel, "Employee ID:", 20, 240, t11 = new JTextField());
        createField(formPanel, "Education:", 420, 240, t13 = new JTextField());

        // Row 7
        createField(formPanel, "Department:", 20, 280, t14 = new JTextField());

        f.add(formPanel);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(50, 620, 800, 60);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 15));
        buttonPanel.setBackground(new Color(255, 248, 240));

        b = new JButton("Update Teacher");
        b.setFont(new Font("Arial", Font.BOLD, 14));
        b.setBackground(new Color(255, 140, 0));
        b.setForeground(Color.WHITE);
        b.setPreferredSize(new Dimension(150, 35));
        b.addActionListener(this);
        buttonPanel.add(b);

        b1 = new JButton("Cancel");   
        b1.setFont(new Font("Arial", Font.BOLD, 14));
        b1.setBackground(new Color(220, 20, 60));
        b1.setForeground(Color.WHITE);
        b1.setPreferredSize(new Dimension(150, 35));
        b1.addActionListener(this);
        buttonPanel.add(b1);

        f.add(buttonPanel);
        f.setVisible(true);
    }

    private void createField(JPanel parent, String labelText, int x, int y, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setBounds(x, y, 130, 25);
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        label.setForeground(new Color(139, 69, 19));
        parent.add(label);

        textField.setBounds(x + 140, y, 180, 25);
        textField.setFont(new Font("Arial", Font.PLAIN, 12));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(2, 5, 2, 5)));
        parent.add(textField);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b){
            try{
                Conn con = new Conn();
                String str = "update teacher set name='"+t1.getText()+"',fathers_name='"+t2.getText()+"',age='"+t3.getText()+"', dob='"+t4.getText()+"',address='"+t5.getText()+"',phone='"+t6.getText()+"',email='"+t7.getText()+"',class_x='"+t8.getText()+"',class_xii='"+t9.getText()+"',cnic='"+t10.getText()+"', emp_id = '"+t11.getText()+"',course='"+t13.getText()+"',dept='"+t14.getText()+"' where emp_id='"+t12.getText()+"'";
                con.s.executeUpdate(str);
                JOptionPane.showMessageDialog(null,"Successfully Updated!");
                f.setVisible(false);
                new TeacherDetails().setVisible(true);
            }catch(Exception e){
                System.out.println("The error is:"+e);
            }
        }
        if(ae.getSource()==b1){
            f.setVisible(false);
        }
        if(ae.getSource() == b2){
            try{
                Conn con = new Conn();
                String str = "select * from teacher where emp_id = '"+t12.getText()+"'";
                ResultSet rs = con.s.executeQuery(str);

                if(rs.next()){
                    t1.setText(rs.getString(1));
                    t2.setText(rs.getString(2));
                    t3.setText(rs.getString(3));
                    t4.setText(rs.getString(4));
                    t5.setText(rs.getString(5));
                    t6.setText(rs.getString(6));
                    t7.setText(rs.getString(7));
                    t8.setText(rs.getString(8));
                    t9.setText(rs.getString(9));
                    t10.setText(rs.getString(10));
                    t11.setText(rs.getString(11));
                    t13.setText(rs.getString(12));
                    t14.setText(rs.getString(13));
                } else {
                    JOptionPane.showMessageDialog(null,"Teacher not found!");
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error occurred while searching!");
            }
        }
    }

    public static void main(String[] arg){
        new UpdateTeacher().f.setVisible(true);
    }
}