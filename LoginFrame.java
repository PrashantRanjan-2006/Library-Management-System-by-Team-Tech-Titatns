









import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.geom.RoundRectangle2D;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Library Management System - Login");
        setSize(500, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(135, 206, 250),
                    0, getHeight(), new Color(70, 130, 180)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());

        
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

        
        JLabel iconLabel = createBookIcon();
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel titleLabel = new JLabel("Library Management System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("Digital Book Repository");
        subtitleLabel.setFont(new Font("Segoe UI Light", Font.ITALIC, 16));
        subtitleLabel.setForeground(new Color(240, 248, 255));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(iconLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        headerPanel.add(subtitleLabel);

        
        JPanel cardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                
                g2d.setColor(new Color(0, 0, 0, 30));
                g2d.fill(new RoundRectangle2D.Float(5, 5, getWidth()-5, getHeight()-5, 20, 20));
                
                g2d.setColor(Color.WHITE);
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth()-5, getHeight()-5, 20, 20));
            }
        };
        cardPanel.setOpaque(false);
        cardPanel.setLayout(new GridBagLayout());
        cardPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel welcomeLabel = new JLabel("Welcome Back!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        welcomeLabel.setForeground(new Color(70, 130, 180));
        cardPanel.add(welcomeLabel, gbc);

        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel userIconLabel = createUserIcon();
        cardPanel.add(userIconLabel, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        usernameField = createStyledTextField("Enter username");
        cardPanel.add(usernameField, gbc);


        gbc.gridx = 0; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        JLabel lockIconLabel = createLockIcon();
        cardPanel.add(lockIconLabel, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        passwordField = createStyledPasswordField("Enter password");
        cardPanel.add(passwordField, gbc);

        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton loginButton = createStyledButton("LOGIN");
        cardPanel.add(loginButton, gbc);

        
        JPanel cardWrapper = new JPanel();
        cardWrapper.setOpaque(false);
        cardWrapper.setLayout(new GridBagLayout());
        cardWrapper.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        cardWrapper.add(cardPanel);

        
        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        JLabel demoLabel = new JLabel("Demo Credentials");
        demoLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        demoLabel.setForeground(Color.WHITE);
        demoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel adminLabel = new JLabel("Admin: admin / admin");
        adminLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        adminLabel.setForeground(new Color(240, 248, 255));
        adminLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel studentLabel = new JLabel("Student: student / 1234");
        studentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        studentLabel.setForeground(new Color(240, 248, 255));
        studentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoPanel.add(demoLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(adminLabel);
        infoPanel.add(studentLabel);

        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(cardWrapper, BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.SOUTH);

        add(mainPanel);

        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    showStyledMessage("Please enter both username and password.", "Input Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                User user = LibraryData.getInstance().authenticate(username, password);
                if (user != null) {
                    dispose();
                    SwingUtilities.invokeLater(() -> new LibraryFrame(user).setVisible(true));
                } else {
                    showStyledMessage("Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                }
            }
        });

        
        getRootPane().setDefaultButton(loginButton);
    }

    private JLabel createBookIcon() {
        JLabel iconLabel = new JLabel();
        iconLabel.setPreferredSize(new Dimension(80, 80));
        iconLabel.setIcon(new ImageIcon(createBookImage()));
        return iconLabel;
    }

    private JLabel createUserIcon() {
        JLabel iconLabel = new JLabel();
        iconLabel.setPreferredSize(new Dimension(30, 30));
        iconLabel.setIcon(new ImageIcon(createUserImage()));
        return iconLabel;
    }

    private JLabel createLockIcon() {
        JLabel iconLabel = new JLabel();
        iconLabel.setPreferredSize(new Dimension(30, 30));
        iconLabel.setIcon(new ImageIcon(createLockImage()));
        return iconLabel;
    }

    private BufferedImage createBookImage() {
        BufferedImage img = new BufferedImage(80, 80, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillRoundRect(20, 15, 35, 50, 5, 5);
        
        
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(22, 17, 31, 46, 3, 3);
        
        
        g2d.setColor(new Color(200, 200, 200));
        for (int i = 0; i < 6; i++) {
            g2d.drawLine(25, 22 + i * 6, 50, 22 + i * 6);
        }
        
        
        g2d.setColor(new Color(70, 130, 180));
        g2d.fillRoundRect(25, 45, 25, 8, 2, 2);
        
        g2d.dispose();
        return img;
    }

    private BufferedImage createUserImage() {
        BufferedImage img = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        
        g2d.setColor(new Color(70, 130, 180));
        g2d.fillOval(9, 5, 12, 12);
        
        
        g2d.fillOval(6, 15, 18, 12);
        
        g2d.dispose();
        return img;
    }

    private BufferedImage createLockImage() {
        BufferedImage img = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        
        g2d.setColor(new Color(70, 130, 180));
        g2d.fillRoundRect(8, 15, 14, 10, 3, 3);
        
        
        g2d.setStroke(new BasicStroke(2));
        g2d.drawArc(11, 8, 8, 10, 0, 180);
        
        
        g2d.setColor(Color.WHITE);
        g2d.fillOval(14, 18, 2, 2);
        g2d.fillRect(14, 20, 2, 3);
        
        g2d.dispose();
        return img;
    }

    private JTextField createStyledTextField(String placeholder) {
        JTextField field = new JTextField(15) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getText().isEmpty()) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setColor(Color.GRAY);
                    g2d.setFont(getFont().deriveFont(Font.ITALIC));
                    g2d.drawString(placeholder, 10, getHeight() / 2 + 5);
                }
            }
        };
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(200, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    private JPasswordField createStyledPasswordField(String placeholder) {
        JPasswordField field = new JPasswordField(15) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getPassword().length == 0) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setColor(Color.GRAY);
                    g2d.setFont(getFont().deriveFont(Font.ITALIC));
                    g2d.drawString(placeholder, 10, getHeight() / 2 + 5);
                }
            }
        };
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(200, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2d.setColor(new Color(50, 100, 150));
                } else if (getModel().isRollover()) {
                    g2d.setColor(new Color(90, 150, 200));
                } else {
                    g2d.setColor(new Color(70, 130, 180));
                }
                
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 25, 25));
                
                g2d.setColor(Color.WHITE);
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                g2d.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 2);
            }
        };
        
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(250, 45));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return button;
    }

    private void showStyledMessage(String message, String title, int messageType) {
        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 12));
        UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.BOLD, 12));
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}