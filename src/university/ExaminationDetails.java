package university;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.DbUtils;

public class ExaminationDetails extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTable table;
    private JTextField search;
    private JButton b1;

    public void stu() {
        try {
            Conn con = new Conn();
            String sql = "select * from student";
            PreparedStatement st = con.c.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            table.setModel(DbUtils.resultSetToTableModel(rs));

            rs.close();
            st.close();
            con.c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ExaminationDetails() {
        setTitle("Student Examination Details System");
        setBounds(200, 100, 1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create main content pane with gradient background
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                GradientPaint gp = new GradientPaint(0, 0, new Color(240, 248, 255), 
                                                   0, getHeight(), new Color(220, 235, 250));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Modern header with enhanced styling
        JLabel l1 = new JLabel("Examination Details Portal", JLabel.CENTER);
        l1.setForeground(new Color(25, 25, 112));
        l1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        l1.setBounds(200, 20, 800, 60);
        l1.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(70, 130, 180)),
            BorderFactory.createEmptyBorder(10, 0, 15, 0)
        ));
        contentPane.add(l1);

        // Search Panel with modern design
        JPanel searchPanel = createStyledPanel("Student Search", 50, 100, 1100, 120);
        contentPane.add(searchPanel);

        // Back button with modern styling
        JLabel l3 = new JLabel("← Back") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (mouseOver) {
                    g2d.setColor(new Color(70, 130, 180, 100));
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                }
                super.paintComponent(g);
            }
            
            private boolean mouseOver = false;
            
            {
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        mouseOver = true;
                        repaint();
                    }
                    
                    @Override
                    public void mouseExited(MouseEvent e) {
                        mouseOver = false;
                        repaint();
                    }
                });
            }
        };
        
        l3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
            }
        });
        l3.setForeground(new Color(70, 130, 180));
        l3.setFont(new Font("Segoe UI", Font.BOLD, 16));
        l3.setBounds(30, 40, 80, 35);
        l3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        l3.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        searchPanel.add(l3);

        // Search field with modern styling
        JLabel searchLabel = new JLabel("Search Student Roll Number:");
        searchLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchLabel.setForeground(new Color(25, 25, 112));
        searchLabel.setBounds(140, 20, 200, 25);
        searchPanel.add(searchLabel);

        search = createStyledTextField();
        search.setBounds(140, 45, 400, 40);
        search.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        search.setToolTipText("Enter student roll number to search");
        searchPanel.add(search);

        // Enhanced Result button
        b1 = new JButton("View Result") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2d.setColor(new Color(220, 20, 60));
                } else if (getModel().isRollover()) {
                    g2d.setColor(new Color(255, 69, 0));
                } else {
                    g2d.setColor(new Color(255, 20, 147));
                }
                
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        
        b1.addActionListener(this);
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        b1.setBounds(580, 45, 150, 40);
        b1.setContentAreaFilled(false);
        b1.setBorderPainted(false);
        b1.setFocusPainted(false);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.setToolTipText("Click to view examination results");
        searchPanel.add(b1);

        // Table Panel with enhanced design
        JPanel tablePanel = createStyledPanel("Student Records", 50, 240, 1100, 400);
        contentPane.add(tablePanel);

        // Enhanced scroll pane and table
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 40, 1060, 340);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        scrollPane.getViewport().setBackground(Color.WHITE);
        tablePanel.add(scrollPane);

        // Modern table styling
        table = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                
                if (isRowSelected(row)) {
                    component.setBackground(new Color(70, 130, 180, 100));
                    component.setForeground(new Color(25, 25, 112));
                } else if (row % 2 == 0) {
                    component.setBackground(new Color(248, 248, 255));
                    component.setForeground(new Color(47, 79, 79));
                } else {
                    component.setBackground(Color.WHITE);
                    component.setForeground(new Color(47, 79, 79));
                }
                
                return component;
            }
        };
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    Object value = table.getModel().getValueAt(row, 10);
                    if (value != null) {
                        search.setText(value.toString());
                    }
                }
            }
        });

        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setSelectionBackground(new Color(70, 130, 180, 150));
        table.setSelectionForeground(new Color(25, 25, 112));
        table.setGridColor(new Color(200, 200, 200));
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(1, 1));
        
        // Enhanced table header
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(0, 40));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(25, 25, 112)));
        
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        scrollPane.setViewportView(table);

        // Add subtle animation effects
        addHoverEffects();
        
        stu();
    }

    private JPanel createStyledPanel(String title, int x, int y, int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(x, y, width, height);
        panel.setBackground(Color.WHITE);
        
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 2, true),
            title,
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 16),
            new Color(25, 25, 112)
        );
        
        panel.setBorder(BorderFactory.createCompoundBorder(
            titledBorder,
            BorderFactory.createEmptyBorder(10, 15, 15, 15)
        ));
        
        // Add subtle shadow effect
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(3, 3, 6, 6, new Color(0, 0, 0, 30)),
            panel.getBorder()
        ));
        
        return panel;
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(176, 196, 222), 2, true),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        field.setBackground(new Color(255, 250, 250));
        
        // Add focus effects
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(70, 130, 180), 2, true),
                    BorderFactory.createEmptyBorder(8, 12, 8, 12)
                ));
                field.setBackground(Color.WHITE);
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(176, 196, 222), 2, true),
                    BorderFactory.createEmptyBorder(8, 12, 8, 12)
                ));
                field.setBackground(new Color(255, 250, 250));
            }
        });
        
        return field;
    }

    private void addHoverEffects() {
        // Add hover effects to buttons
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
        
        // Add table selection effects
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b1) {
                String rollNumber = search.getText().trim();
                if (rollNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                        "Please enter a roll number to search for results.", 
                        "No Roll Number", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                new Marks(rollNumber).setVisible(true);
                this.setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "An error occurred while processing your request.\nPlease try again.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new ExaminationDetails().setVisible(true);
        });
    }
}