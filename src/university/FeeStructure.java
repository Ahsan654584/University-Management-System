package university;

import javax.swing.*;
// import javax.swing.border.AbstractBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class FeeStructure extends JFrame {

    private JTable feeTable;
    private DefaultTableModel tableModel;
    private final Color PRIMARY_COLOR = new Color(41, 98, 255);
    // private final Color SECONDARY_COLOR = new Color(248, 250, 252);
    private final Color ACCENT_COLOR = new Color(16, 185, 129);
    // private final Color TEXT_DARK = new Color(31, 41, 55);
    private final Color TEXT_LIGHT = new Color(107, 114, 128);

    public FeeStructure() {
        initializeComponents();
        setupUI();
        populateTable();
    }

    private void initializeComponents() {
        setTitle("University Fee Structure - Academic Portal");
        setSize(1500, 950);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Table.gridColor", new Color(229, 231, 235));
            UIManager.put("Table.showHorizontalLines", true);
            UIManager.put("Table.showVerticalLines", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupUI() {
        JPanel mainPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                Color[] colors = { new Color(255, 255, 255), new Color(248, 250, 252), new Color(241, 245, 249) };
                float[] fractions = { 0.0f, 0.5f, 1.0f };

                LinearGradientPaint gradient = new LinearGradientPaint(0, 0, 0, getHeight(), fractions, colors);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                g2d.setColor(new Color(255, 255, 255, 30));
                for (int i = 0; i < getWidth(); i += 60) {
                    for (int j = 0; j < getHeight(); j += 60) {
                        g2d.fillOval(i, j, 2, 2);
                    }
                }
            }
        };
        mainPanel.setLayout(new BorderLayout(30, 30));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        mainPanel.add(createTablePanel(), BorderLayout.CENTER);
        add(mainPanel);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                g2d.setColor(new Color(255, 255, 255, 180));
                g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 25, 25));

                g2d.setStroke(new BasicStroke(2f));
                LinearGradientPaint borderGradient = new LinearGradientPaint(0, 0, getWidth(), 0,
                        new float[] { 0f, 0.5f, 1f },
                        new Color[] { PRIMARY_COLOR.brighter(), PRIMARY_COLOR, PRIMARY_COLOR.brighter() });
                g2d.setPaint(borderGradient);
                g2d.draw(new RoundRectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2, 25, 25));
            }
        };
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        headerPanel.setOpaque(false);

        JLabel iconLabel = new JLabel("🎓", JLabel.CENTER);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 64));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("University Fee Structure", JLabel.CENTER) {
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2d.setColor(new Color(0, 0, 0, 30));
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                g2d.drawString(getText(), x + 2, y + 2);
                g2d.setColor(getForeground());
                g2d.drawString(getText(), x, y);
            }
        };
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 52));
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Academic Year 2025-2026 • Fee Schedule", JLabel.CENTER);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitleLabel.setForeground(TEXT_LIGHT);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        statusPanel.setOpaque(false);
        JLabel statusDot = new JLabel("●");
        statusDot.setFont(new Font("Segoe UI", Font.BOLD, 12));
        statusDot.setForeground(ACCENT_COLOR);
        JLabel statusText = new JLabel("Fee Structure Active");
        statusText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        statusText.setForeground(TEXT_LIGHT);
        statusPanel.add(statusDot);
        statusPanel.add(statusText);

        headerPanel.add(iconLabel);
        headerPanel.add(Box.createVerticalStrut(15));
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(subtitleLabel);
        headerPanel.add(Box.createVerticalStrut(15));
        headerPanel.add(statusPanel);

        return headerPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setOpaque(false);

        String[] columnNames = {"Semester", "BTech", "BCA", "BBA", "BSc", "MBA", "MCA", "MTech"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        feeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(feeTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        return tablePanel;
    }

    private void populateTable() {
        Object[][] data = {
            {"Semester 1", "40,000", "35,000", "30,000", "28,000", "50,000", "45,000", "48,000"},
            {"Semester 2", "42,000", "36,000", "31,000", "29,000", "52,000", "46,000", "49,000"},
            {"Semester 3", "44,000", "37,000", "32,000", "30,000", "54,000", "47,000", "50,000"},
        };
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FeeStructure().setVisible(true);
        });
    }
}
