














import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class LibraryFrame extends JFrame {
    private User currentUser;
    private JTextArea infoArea;

    public LibraryFrame(User user) {
        this.currentUser = user;
        setTitle("Library System - " + currentUser.getUsername());
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(248, 250, 252),
                    0, getHeight(), new Color(238, 242, 255)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());

        
        JPanel headerPanel = createHeaderPanel();
        
        
        JPanel contentPanel = createContentPanel();
        
        
        JPanel buttonPanel = createButtonPanel();
        
        
        JPanel footerPanel = createFooterPanel();

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);
        
        
        displayWelcomeMessage();
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false);
        
        JLabel libraryIcon = new JLabel();
        libraryIcon.setIcon(new ImageIcon(createLibraryIcon()));
        
        JLabel titleLabel = new JLabel("Digital Library System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(70, 130, 180));
        
        leftPanel.add(libraryIcon);
        leftPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        leftPanel.add(titleLabel);

        
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setOpaque(false);
        
        JLabel userIcon = new JLabel();
        userIcon.setIcon(new ImageIcon(createUserIcon()));
        
        JLabel userLabel = new JLabel("Welcome, " + currentUser.getUsername());
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        userLabel.setForeground(new Color(70, 130, 180));
        
        JLabel roleLabel = new JLabel(currentUser.isAdmin() ? "(Administrator)" : "(Student)");
        roleLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        roleLabel.setForeground(new Color(100, 100, 100));

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setOpaque(false);
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.add(userLabel);
        userInfoPanel.add(roleLabel);

        rightPanel.add(userIcon);
        rightPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        rightPanel.add(userInfoPanel);

        headerPanel.add(leftPanel, BorderLayout.WEST);
        headerPanel.add(rightPanel, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        
        infoArea = new JTextArea() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw card background with shadow
                g2d.setColor(new Color(0, 0, 0, 20));
                g2d.fill(new RoundRectangle2D.Float(3, 3, getWidth()-3, getHeight()-3, 15, 15));
                
                g2d.setColor(Color.WHITE);
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth()-3, getHeight()-3, 15, 15));
                
                super.paintComponent(g);
            }
        };
        
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        infoArea.setOpaque(false);
        infoArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        TitledBorder border = BorderFactory.createTitledBorder("Information Panel");
        border.setTitleFont(new Font("Segoe UI", Font.BOLD, 14));
        border.setTitleColor(new Color(70, 130, 180));
        
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setOpaque(false);
        cardPanel.setBorder(border);
        cardPanel.add(scrollPane);

        contentPanel.add(cardPanel, BorderLayout.CENTER);
        return contentPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        
        JPanel studentPanel = new JPanel();
        studentPanel.setOpaque(false);
        studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));
        
        TitledBorder studentBorder = BorderFactory.createTitledBorder("Library Functions");
        studentBorder.setTitleFont(new Font("Segoe UI", Font.BOLD, 12));
        studentBorder.setTitleColor(new Color(70, 130, 180));
        studentPanel.setBorder(studentBorder);

        JButton viewBooks = createStyledButton(" View Books", new Color(76, 175, 80));
        viewBooks.addActionListener(e -> {
            infoArea.setText(LibraryData.getInstance().listBooks());
        });

        JButton borrowBook = createStyledButton("Borrow Book", new Color(33, 150, 243));
        borrowBook.addActionListener(e -> {
            String title = showStyledInputDialog("Enter book title to borrow:");
            if (title != null && !title.trim().isEmpty()) {
                String msg = LibraryData.getInstance().borrowBook(title.trim(), currentUser.getUsername());
                infoArea.setText(msg);
            }
        });

        JButton returnBook = createStyledButton("Return Book", new Color(255, 152, 0));
        returnBook.addActionListener(e -> {
            String title = showStyledInputDialog("Enter book title to return:");
            if (title != null && !title.trim().isEmpty()) {
                String msg = LibraryData.getInstance().returnBook(title.trim(), currentUser.getUsername());
                infoArea.setText(msg);
            }
        });

        JButton checkFine = createStyledButton("Check Fine", new Color(244, 67, 54));
        checkFine.addActionListener(e -> {
            int fine = LibraryData.getInstance().getFine(currentUser.getUsername());
            infoArea.setText("Your current fine: ₹" + fine);
        });

        studentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        studentPanel.add(viewBooks);
        studentPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        studentPanel.add(borrowBook);
        studentPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        studentPanel.add(returnBook);
        studentPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        studentPanel.add(checkFine);
        studentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        buttonPanel.add(studentPanel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        
        if (currentUser.isAdmin()) {
            JPanel adminPanel = new JPanel();
            adminPanel.setOpaque(false);
            adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.Y_AXIS));
            
            TitledBorder adminBorder = BorderFactory.createTitledBorder("Admin Functions");
            adminBorder.setTitleFont(new Font("Segoe UI", Font.BOLD, 12));
            adminBorder.setTitleColor(new Color(156, 39, 176));
            adminPanel.setBorder(adminBorder);

            JButton viewBorrowedBooks = createStyledButton("Borrowed Books", new Color(156, 39, 176));
            viewBorrowedBooks.addActionListener(e -> 
                infoArea.setText(LibraryData.getInstance().getBorrowedBooksDetails()));

            JButton viewAllBooks = createStyledButton(" All Books Status", new Color(103, 58, 183));
            viewAllBooks.addActionListener(e -> 
                infoArea.setText(LibraryData.getInstance().getAllBooksDetails()));

            JButton viewUserBooks = createStyledButton(" User's Books", new Color(63, 81, 181));
            viewUserBooks.addActionListener(e -> {
                java.util.List<String> usernames = LibraryData.getInstance().getAllUsernames();
                if (usernames.isEmpty()) {
                    showStyledMessage("No users found.", "Information", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                String[] usernameArray = usernames.toArray(new String[0]);
                String selectedUser = (String) JOptionPane.showInputDialog(
                    this,
                    "Select a user to view their borrowed books:",
                    "Select User",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    usernameArray,
                    usernameArray[0]
                );
                
                if (selectedUser != null) {
                    infoArea.setText(LibraryData.getInstance().getUserBorrowingHistory(selectedUser));
                }
            });

            adminPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            adminPanel.add(viewBorrowedBooks);
            adminPanel.add(Box.createRigidArea(new Dimension(0, 8)));
            adminPanel.add(viewAllBooks);
            adminPanel.add(Box.createRigidArea(new Dimension(0, 8)));
            adminPanel.add(viewUserBooks);
            adminPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            buttonPanel.add(adminPanel);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        
        JPanel logoutPanel = new JPanel();
        logoutPanel.setOpaque(false);
        
        JButton logoutButton = createStyledButton(" Logout", new Color(96, 125, 139));
        logoutButton.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
        });

        logoutPanel.add(logoutButton);
        buttonPanel.add(logoutPanel);
        buttonPanel.add(Box.createVerticalGlue());

        return buttonPanel;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setOpaque(false);
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 15, 20));

        
        JLabel credits = new JLabel("<html><center><div style='font-family: Segoe UI; color: #607D8B;'>"
                + "<b>Developed by Team Tech Titans</b><br><br>"
                + " Prashant Ranjan (24SCSE1011537)<br>"
                + " Avesh Singh (24SCSE1011267)<br>"
                + " Nishant Pandit (24SCSE1010365)<br>"
                + " Kushagra Bhardwaj (24SCSE1010579)<br><br>"
                + "<i>Digital Library Management System v2.0</i>"
                + "</div></center></html>");
        credits.setHorizontalAlignment(SwingConstants.CENTER);
        credits.setFont(new Font("Segoe UI", Font.PLAIN, 11));

        footerPanel.add(credits, BorderLayout.CENTER);
        return footerPanel;
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                Color btnColor = color;
                if (getModel().isPressed()) {
                    btnColor = btnColor.darker();
                } else if (getModel().isRollover()) {
                    btnColor = btnColor.brighter();
                }
                
                
                g2d.setColor(new Color(0, 0, 0, 30));
                g2d.fill(new RoundRectangle2D.Float(2, 2, getWidth()-2, getHeight()-2, 20, 20));
                
                // Draw button
                g2d.setColor(btnColor);
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth()-2, getHeight()-2, 20, 20));
                
                // Draw text
                g2d.setColor(Color.WHITE);
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                g2d.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 2);
            }
        };
        
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(180, 40));
        button.setMaximumSize(new Dimension(180, 40));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return button;
    }

    private BufferedImage createLibraryIcon() {
        BufferedImage img = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        
        g2d.setColor(new Color(70, 130, 180));
        g2d.fillRect(5, 25, 30, 10);
        
        
        g2d.setColor(new Color(100, 150, 200));
        for (int i = 0; i < 4; i++) {
            g2d.fillRect(8 + i * 6, 15, 3, 10);
        }
        
        
        g2d.setColor(new Color(50, 100, 150));
        int[] xPoints = {2, 20, 38};
        int[] yPoints = {15, 5, 15};
        g2d.fillPolygon(xPoints, yPoints, 3);
        
        
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillRect(17, 20, 6, 5);
        
        g2d.dispose();
        return img;
    }

    private BufferedImage createUserIcon() {
        BufferedImage img = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        
        g2d.setColor(new Color(70, 130, 180));
        g2d.fillOval(9, 5, 12, 12);
        
        
        g2d.fillOval(6, 15, 18, 12);
        
        g2d.dispose();
        return img;
    }

    private void displayWelcomeMessage() {
        String welcomeMsg = "Welcome to Digital Library System\n\n";
        welcomeMsg += "Hello " + currentUser.getUsername() + "!\n\n";
        
        if (currentUser.isAdmin()) {
            welcomeMsg += "Administrator Access Granted\n\n";
            welcomeMsg += "Available Functions:\n";
            welcomeMsg += "View and manage all books\n";
            welcomeMsg += "Monitor borrowed books\n";
            welcomeMsg += "Check user borrowing history\n";
            welcomeMsg += "View detailed reports\n\n";
        } else {
            welcomeMsg += "Student Access\n\n";
            welcomeMsg += "Available Functions:\n";
            welcomeMsg += "Browse available books\n";
            welcomeMsg += "Borrow books (14-day limit)\n";
            welcomeMsg += "Return borrowed books\n";
            welcomeMsg += "Check your fines\n\n";
        }
        
        welcomeMsg += "Tips:\n";
        welcomeMsg += "Books can be borrowed for 14 days\n";
        welcomeMsg += "Late returns incur ₹5 per day fine\n";
        welcomeMsg += "Use the buttons on the left to navigate\n\n";
        welcomeMsg += "Happy Reading!";
        
        infoArea.setText(welcomeMsg);
    }

    private String showStyledInputDialog(String message) {
        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 12));
        UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.BOLD, 12));
        return JOptionPane.showInputDialog(this, message);
    }

    private void showStyledMessage(String message, String title, int messageType) {
        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 12));
        UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.BOLD, 12));
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}