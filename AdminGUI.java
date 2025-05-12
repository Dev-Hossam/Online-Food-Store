package test1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminGUI extends JFrame {

    private final String adminName;
    private final JButton editMenuButton;

    public AdminGUI(String adminName) {
        super("Admin Dashboard");
        this.adminName = adminName;

        // Frame settings
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ─── Top panel ─────────────────────────────────────────────────────────
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Admin name at top-left
        JLabel nameLabel = new JLabel(adminName);
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD, 14f));
        topPanel.add(nameLabel, BorderLayout.WEST);

        // "Home" at top-center
        JLabel homeLabel = new JLabel("Home", SwingConstants.CENTER);
        homeLabel.setFont(homeLabel.getFont().deriveFont(Font.PLAIN, 14f));
        topPanel.add(homeLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // ─── Center: Edit Menu button ──────────────────────────────────────────
        editMenuButton = new JButton("Edit Menu");
        editMenuButton.addActionListener(e -> openEditMenu());

        JPanel centerPanel = new JPanel();
        centerPanel.add(editMenuButton);
        add(centerPanel, BorderLayout.CENTER);

        // ─── Bottom: Sign Out button ───────────────────────────────────────────
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(e -> {
            dispose();
            LoginGui login = new LoginGui();
            login.setLocationRelativeTo(null);
            login.setVisible(true);
        });
        bottomPanel.add(signOutButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Opens the EditMenu window so the admin can add/delete items.
     */
    private void openEditMenu() {
        EditMenu edit = new EditMenu();
        this.dispose();
        edit.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminGUI("SuperAdmin").setVisible(true);
        });
    }
}
