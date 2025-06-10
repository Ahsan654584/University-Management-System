package university;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Project extends JFrame implements ActionListener{
    Project(){
        super("University Management System - Dashboard");
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth(), h = getHeight();
                Color color1 = new Color(33, 47, 61);
                Color color2 = new Color(44, 62, 80);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainPanel.setLayout(new BorderLayout());
        
        // Try to add background image as overlay
        try {
            ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
            if (ic.getIconWidth() > 0) {
                Image i3 = ic.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
                ImageIcon icc3 = new ImageIcon(i3);
                JLabel backgroundLabel = new JLabel(icc3);
                backgroundLabel.setOpaque(false);
                mainPanel.add(backgroundLabel, BorderLayout.CENTER);
            }
        } catch (Exception e) {
            // If background image not found, continue with gradient
        }
        
        setContentPane(mainPanel);
        
        // Create modern menu bar
        JMenuBar mb = new JMenuBar();
        mb.setBackground(new Color(52, 73, 94));
        mb.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        mb.setPreferredSize(new Dimension(0, 50));
        
        // Master Menu
        JMenu master = createModernMenu("Master", new Color(52, 152, 219));
        JMenuItem m1 = createModernMenuItem("New Faculty", "icons/icon1.png", KeyEvent.VK_D);
        JMenuItem m2 = createModernMenuItem("New Student Admission", "icons/icon2.png", KeyEvent.VK_M);
        m1.addActionListener(this);
        m2.addActionListener(this);
        master.add(m1);
        master.add(m2);
        
        // Details Menu
        JMenu user = createModernMenu("Details", new Color(231, 76, 60));
        JMenuItem u1 = createModernMenuItem("Student Details", "icons/icon3.png", KeyEvent.VK_P);
        JMenuItem u2 = createModernMenuItem("Teacher Details", "icons/icon4.jpg", KeyEvent.VK_B);
        u1.addActionListener(this);
        u2.addActionListener(this);
        user.add(u1);
        user.add(u2);
        
        // Attendance Menu
        JMenu attendance = createModernMenu("Attendance", new Color(46, 204, 113));
        JMenuItem a1 = createModernMenuItem("Student Attendance", "icons/icon14.jpg", KeyEvent.VK_P);
        JMenuItem a2 = createModernMenuItem("Teacher Attendance", "icons/icon15.png", KeyEvent.VK_B);
        a1.addActionListener(this);
        a2.addActionListener(this);
        attendance.add(a1);
        attendance.add(a2);
        
        // Attendance Detail Menu
        JMenu attendance_detail = createModernMenu("Attendance Detail", new Color(155, 89, 182));
        JMenuItem b1 = createModernMenuItem("Student Attendance Detail", "icons/icon15.png", KeyEvent.VK_P);
        JMenuItem b2 = createModernMenuItem("Teacher Attendance Detail", "icons/icon14.jpg", KeyEvent.VK_B);
        b1.addActionListener(this);
        b2.addActionListener(this);
        attendance_detail.add(b1);
        attendance_detail.add(b2);
        
        // Examination Menu
        JMenu exam = createModernMenu("Examination", new Color(230, 126, 34));
        JMenuItem c1 = createModernMenuItem("Examination Details", "icons/icon16.png", KeyEvent.VK_P);
        JMenuItem c2 = createModernMenuItem("Enter Marks", "icons/icon17.png", KeyEvent.VK_B);
        c1.addActionListener(this);
        c2.addActionListener(this);
        exam.add(c1);
        exam.add(c2);
        
        // Update Details Menu
        JMenu report = createModernMenu("Update Details", new Color(52, 152, 219));
        JMenuItem r1 = createModernMenuItem("Update Students", "icons/icon5.png", KeyEvent.VK_R);
        JMenuItem r2 = createModernMenuItem("Update Teachers", "icons/icon6.png", KeyEvent.VK_R);
        r1.addActionListener(this);
        r2.addActionListener(this);
        report.add(r1);
        report.add(r2);
        
        // Fee Details Menu
        JMenu fee = createModernMenu("Fee Details", new Color(26, 188, 156));
        JMenuItem s1 = createModernMenuItem("Fee Structure", "icons/icon7.png", KeyEvent.VK_R);
        JMenuItem s2 = createModernMenuItem("Student Fee Form", "icons/icon8.png", KeyEvent.VK_R);
        s1.addActionListener(this);
        s2.addActionListener(this);
        fee.add(s1);
        fee.add(s2);
        
        // Utility Menu
        JMenu utility = createModernMenu("Utility", new Color(241, 196, 15));
        JMenuItem ut1 = createModernMenuItem("Notepad", "icons/icon9.png", KeyEvent.VK_C);
        JMenuItem ut2 = createModernMenuItem("Calculator", "icons/icon10.png", KeyEvent.VK_X);
        JMenuItem ut3 = createModernMenuItem("Web Browser", "icons/icon11.png", KeyEvent.VK_W);
        ut1.addActionListener(this);
        ut2.addActionListener(this);
        ut3.addActionListener(this);
        utility.add(ut1);
        utility.add(ut2);
        utility.add(ut3);
        
        // Exit Menu
        JMenu exit = createModernMenu("Exit", new Color(231, 76, 60));
        JMenuItem ex = createModernMenuItem("Exit", "icons/icon12.png", KeyEvent.VK_Z);
        ex.addActionListener(this);
        exit.add(ex);
        
        // Add all menus to menu bar
        mb.add(master);
        mb.add(user);
        mb.add(attendance);
        mb.add(attendance_detail);
        mb.add(exam);
        mb.add(report);
        mb.add(fee);
        mb.add(utility);
        mb.add(Box.createHorizontalGlue()); // Push exit to the right
        mb.add(exit);
        
        setJMenuBar(mb);
        
        // Add welcome panel overlay
        createWelcomePanel();
        
        setVisible(false);
    }
    
    private JMenu createModernMenu(String text, Color color) {
        JMenu menu = new JMenu(text);
        menu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        menu.setForeground(Color.WHITE);
        menu.setBorder(new EmptyBorder(8, 15, 8, 15));
        menu.setOpaque(true);
        menu.setBackground(color);
        
        // Add hover effect
        menu.addMouseListener(new MouseAdapter() {
            Color originalColor = color;
            public void mouseEntered(MouseEvent e) {
                menu.setBackground(originalColor.brighter());
            }
            public void mouseExited(MouseEvent e) {
                menu.setBackground(originalColor);
            }
        });
        
        return menu;
    }
    
    private JMenuItem createModernMenuItem(String text, String iconPath, int acceleratorKey) {
        JMenuItem item = new JMenuItem(text);
        item.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        item.setBackground(Color.WHITE);
        item.setBorder(new EmptyBorder(8, 10, 8, 10));
        item.setAccelerator(KeyStroke.getKeyStroke(acceleratorKey, ActionEvent.CTRL_MASK));
        
        // Try to load icon
        try {
            ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(iconPath));
            if (icon.getIconWidth() > 0) {
                Image image = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                item.setIcon(new ImageIcon(image));
            }
        } catch (Exception e) {
            // If icon not found, continue without icon
        }
        
        // Add hover effect
        item.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                item.setBackground(new Color(240, 240, 240));
            }
            public void mouseExited(MouseEvent e) {
                item.setBackground(Color.WHITE);
            }
        });
        
        return item;
    }
    
    private void createWelcomePanel() {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setOpaque(false);
        welcomePanel.setLayout(new BorderLayout());
        
        // Welcome message
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        
        JLabel welcomeLabel = new JLabel("Welcome to University Management System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 48));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setOpaque(true);
        welcomeLabel.setBackground(new Color(0, 0, 0, 100)); // Semi-transparent background
        welcomeLabel.setBorder(new EmptyBorder(30, 50, 30, 50));
        
        JLabel subtitleLabel = new JLabel("Select an option from the menu to get started", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setOpaque(true);
        subtitleLabel.setBackground(new Color(0, 0, 0, 80));
        subtitleLabel.setBorder(new EmptyBorder(20, 40, 20, 40));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        centerPanel.add(welcomeLabel, gbc);
        
        gbc.gridy = 1;
        centerPanel.add(subtitleLabel, gbc);
        
        welcomePanel.add(centerPanel, BorderLayout.CENTER);
        
        // Add time/date panel
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        statusPanel.setOpaque(false);
        statusPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel timeLabel = new JLabel("System Ready");
        timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setOpaque(true);
        timeLabel.setBackground(new Color(0, 0, 0, 120));
        timeLabel.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        statusPanel.add(timeLabel);
        welcomePanel.add(statusPanel, BorderLayout.SOUTH);
        
        // Add welcome panel as glass pane overlay
        setGlassPane(welcomePanel);
        getGlassPane().setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        String msg = ae.getActionCommand();
        if(msg.equals("New Student Admission")){
            new AddStudent().f.setVisible(true);
            
        }else if(msg.equals("New Faculty")){
            new AddTeacher().f.setVisible(true);
            
        }else if(msg.equals("Student Details")){
            new StudentDetails().setVisible(true);
            
        }else if(msg.equals("Teacher Details")){
            new TeacherDetails().setVisible(true);
           
        }
        else if(msg.equals("Update Students")){
            new UpdateStudent().f.setVisible(true);
           
        }
        else if(msg.equals("Update Teachers")){
            new UpdateTeacher().f.setVisible(true);
           
        }
        else if(msg.equals("Fee Structure")){
            new FeeStructure().setVisible(true);
           
        }
        else if(msg.equals("Student Fee Form")){
            new StudentFeeForm().setVisible(true);
           
        }
        else if(msg.equals("Notepad")){
            try{
                // Runtime.getRuntime().exec("notepad.exe");
            }catch(Exception e){ }
        }else if(msg.equals("Calculator")){
            try{
                // Runtime.getRuntime().exec("calc.exe");
            }catch(Exception e){ }
        }else if(msg.equals("Web Browser")){
            
            try{
                /*Runtime.getRuntime().exec("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");*/
            }catch(Exception e){ }
        }else if(msg.equals("Exit")){
            System.exit(0);
        }else if(msg.equals("Student Attendance")){
            new StudentAttendance().setVisible(true);
        }else if(msg.equals("Teacher Attendance")){
            new TeacherAttendance().setVisible(true);
        }else if(msg.equals("Student Attendance Detail")){
            new StudentAttendanceDetail().setVisible(true);
        }else if(msg.equals("Teacher Attendance Detail")){
            new TeacherAttendanceDetail().setVisible(true);
        }else if(msg.equals("Examination Details")){
            new ExaminationDetails().setVisible(true);
        }else if(msg.equals("Enter Marks")){
            new EnterMarks().setVisible(true);
        }
        
    }
    
    
    public static void main(String[] args){
        new Project().setVisible(true);
    }
    
}