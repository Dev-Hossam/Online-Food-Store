package test1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MasterGUI extends JFrame {

    private final int currentCustomerId;
    private final String customerName;
    private JLabel titleLabel, welcomeLabel;
    private JButton viewMenuButton, placeOrderButton, reviewButton, viewOrdersButton, signOutButton;

    public MasterGUI(int customerId, String customerName) {
        this.currentCustomerId = customerId;
        this.customerName = customerName;
        initComponents();
    }

    private void initComponents() {
        setTitle("Customer Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        titleLabel = new JLabel("Home", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel = new JLabel("Welcome " + customerName, SwingConstants.RIGHT);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        topPanel.add(welcomeLabel, BorderLayout.EAST);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(topPanel, BorderLayout.NORTH);

        // Center Panel: 4 buttons stacked vertically with spacing
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 0, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        viewMenuButton = new JButton("View Menu");
        placeOrderButton = new JButton("Place Order");
        reviewButton = new JButton("Review a Restaurant");
        viewOrdersButton = new JButton("View Orders");

        buttonPanel.add(viewMenuButton);
        buttonPanel.add(placeOrderButton);
        buttonPanel.add(reviewButton);
        buttonPanel.add(viewOrdersButton);

        getContentPane().add(buttonPanel, BorderLayout.CENTER);

        // Bottom Panel: Sign Out button on the left
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(e -> {
            dispose();
            LoginGui login = new LoginGui();
            login.setLocationRelativeTo(null);
            login.setVisible(true);
        });
        bottomPanel.add(signOutButton);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        // Wire up your existing actions
        viewMenuButton.addActionListener(e -> {
            new VegetarianMenuGUI().setVisible(true);
            dispose();
        });
        reviewButton.addActionListener(e -> {
            new ReviewForm().setVisible(true);
            dispose();
        });
        placeOrderButton.addActionListener(e -> {
            new PlaceOrderGUI(currentCustomerId).setVisible(true);
            dispose();
        });
        viewOrdersButton.addActionListener(e -> {
            new ViewOrdersGUI(currentCustomerId).setVisible(true);
        });
    }

    // Quick test
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()
                -> new MasterGUI(1, "Test User").setVisible(true)
        );
    }
}
