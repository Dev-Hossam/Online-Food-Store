package test1;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PlaceOrderGUI extends JFrame {

    private final int customerId;
    private final DefaultTableModel model;
    private final JTable table;
    private final JLabel totalLabel;
    private final JButton submitButton;
    private final JButton paymentButton;
    private final JComboBox<String> categoryCombo;
    private final JSpinner qtySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
    private boolean paymentDone = false;

    public PlaceOrderGUI(int customerId) {
        super("Place Order");
        this.customerId = customerId;

        // Frame
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top: category filter
        categoryCombo = new JComboBox<>(new String[]{"All", "Cafe", "Dessert", "Diner", "PancakeHouse"});
        categoryCombo.addActionListener(e -> loadData((String) categoryCombo.getSelectedItem()));
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Category:"));
        top.add(categoryCombo);
        add(top, BorderLayout.NORTH);

        // Center: items table
        model = new DefaultTableModel(
                new Object[]{"Item ID", "Name", "Price", "Quantity", "Subtotal"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int c) {
                switch (c) {
                    case 0:
                        return Integer.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Double.class;
                    case 3:
                        return Integer.class;
                    case 4:
                        return Double.class;
                    default:
                        return Object.class;
                }
            }
        };
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // hide ID & Quantity cols
        TableColumnModel tcm = table.getColumnModel();
        tcm.removeColumn(tcm.getColumn(0)); // ID
        tcm.removeColumn(tcm.getColumn(2)); // Quantity

        // spinner → table
        qtySpinner.addChangeListener(e -> {
            int vr = table.getSelectedRow();
            if (vr >= 0) {
                int mr = table.convertRowIndexToModel(vr);
                model.setValueAt(qtySpinner.getValue(), mr, 3);
            }
            updateActionButtons();
        });

        // table → spinner
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int vr = table.getSelectedRow();
                if (vr >= 0) {
                    int mr = table.convertRowIndexToModel(vr);
                    qtySpinner.setValue(model.getValueAt(mr, 3));
                }
                updateActionButtons();
            }
        });

        // recalc subtotals & total
        model.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 3) {
                int r = e.getFirstRow();
                double price = (Double) model.getValueAt(r, 2);
                int qty = (Integer) model.getValueAt(r, 3);
                model.setValueAt(price * qty, r, 4);
                updateTotal();
                updateActionButtons();
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);

        // Bottom: Back button, spinner, total label, and action buttons
        totalLabel = new JLabel("Total: 0.00");
        submitButton = new JButton("Submit Order");
        submitButton.addActionListener(e -> handleSubmit());

        paymentButton = new JButton("Payment Method");
        paymentButton.addActionListener(e -> openPaymentDialog());

        // ← NEW: Back to Menu button
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> {
            dispose();
            // Pass the proper customerName if you have it; here "" is a placeholder
            new MasterGUI(customerId, "").setVisible(true);
        });

        JPanel bottomLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomLeft.add(backButton);                        // ← add back button at left
        bottomLeft.add(new JLabel("Quantity:"));
        bottomLeft.add(qtySpinner);
        bottomLeft.add(totalLabel);

        JPanel bottomRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomRight.add(paymentButton);
        bottomRight.add(submitButton);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(bottomLeft, BorderLayout.WEST);
        bottom.add(bottomRight, BorderLayout.EAST);
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(bottom, BorderLayout.SOUTH);

        // load & init
        loadData("All");
        updateActionButtons();
    }

    private void updateActionButtons() {
        boolean canPay = false;
        int vr = table.getSelectedRow();
        if (vr >= 0) {
            int mr = table.convertRowIndexToModel(vr);
            int qty = (Integer) model.getValueAt(mr, 3);
            canPay = qty > 0 && !paymentDone;
        }
        paymentButton.setEnabled(canPay);
        submitButton.setEnabled(paymentDone);
    }

    private void openPaymentDialog() {
        setEnabled(false);
        PaymentGUI payGui = new PaymentGUI();
        payGui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        payGui.setLocationRelativeTo(this);
        payGui.setVisible(true);

        payGui.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                Frame[] all = Frame.getFrames();
                CardGUI card = null;
                for (Frame f : all) {
                    if (f instanceof CardGUI && f.isVisible()) {
                        card = (CardGUI) f;
                        break;
                    }
                }
                if (card != null) {
                    card.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e2) {
                            paymentDone = true;
                            setEnabled(true);
                            toFront();
                            updateActionButtons();
                        }
                    });
                } else {
                    paymentDone = true;
                    setEnabled(true);
                    toFront();
                    updateActionButtons();
                }
            }
        });
    }

    private void loadData(String category) {
        model.setRowCount(0);
        String base
                = "SELECT id,name,price,'Cafe'         AS category FROM cafe_menu   UNION ALL "
                + "SELECT id,name,price,'Dessert'      AS category FROM dessert_menu UNION ALL "
                + "SELECT id,name,price,'Diner'        AS category FROM diner_menu   UNION ALL "
                + "SELECT id,name,price,'PancakeHouse' AS category FROM pancake_house_menu";
        boolean filter = !"All".equals(category);
        String sql = filter
                ? "SELECT * FROM (" + base + ") t WHERE category = ?"
                : base;

        try (Connection conn = DataBaseConnections.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            if (filter) {
                ps.setString(1, category);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        0,
                        0.0
                    });
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error loading items:\n" + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }

        paymentDone = false;
        updateTotal();
        updateActionButtons();
    }

    private void updateTotal() {
        double tot = 0;
        for (int r = 0; r < model.getRowCount(); r++) {
            tot += (Double) model.getValueAt(r, 4);
        }
        totalLabel.setText(String.format("Total: %.2f", tot));
    }

    private void handleSubmit() {
        if (!paymentDone) {
            JOptionPane.showMessageDialog(this,
                    "Please complete payment first.",
                    "Payment Required", JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int vr = table.getSelectedRow();
        if (vr < 0) {
            JOptionPane.showMessageDialog(this, "Please select an item first.");
            return;
        }
        int mr = table.convertRowIndexToModel(vr);
        int qty = (Integer) model.getValueAt(mr, 3);
        if (qty <= 0) {
            JOptionPane.showMessageDialog(this, "Quantity must be at least 1.");
            return;
        }

        int id = (Integer) model.getValueAt(mr, 0);
        String name = (String) model.getValueAt(mr, 1);
        double price = (Double) model.getValueAt(mr, 2);
        double sub = (Double) model.getValueAt(mr, 4);

        String insertSQL
                = "INSERT INTO orders "
                + "(customer_id,item_id,item_name,price,quantity,subtotal) "
                + "VALUES (?,?,?,?,?,?)";

        try (Connection conn = DataBaseConnections.getConnection(); PreparedStatement ps = conn.prepareStatement(insertSQL)) {

            ps.setInt(1, customerId);
            ps.setInt(2, id);
            ps.setString(3, name);
            ps.setDouble(4, price);
            ps.setInt(5, qty);
            ps.setDouble(6, sub);
            ps.executeUpdate();

            model.removeRow(mr);
            updateTotal();
            paymentDone = false;
            updateActionButtons();
            JOptionPane.showMessageDialog(this, "Item added to your orders!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error submitting order:\n" + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()
                -> new PlaceOrderGUI(1).setVisible(true)
        );
    }
}
