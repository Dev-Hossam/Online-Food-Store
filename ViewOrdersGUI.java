package test1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewOrdersGUI extends JFrame {

    private final int customerId;
    private final DefaultTableModel model;
    private final JTable table;

    public ViewOrdersGUI(int customerId) {
        super("My Orders");
        this.customerId = customerId;

        // ─── Frame setup ───────────────────────────────────────────────
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ─── Table model & load data ─────────────────────────────────
        model = new DefaultTableModel(
                new Object[]{"Item", "Price", "Quantity", "Subtotal", "When"}, 0
        );
        table = new JTable(model);
        loadData();

        add(new JScrollPane(table), BorderLayout.CENTER);

        // ─── Bottom: Back button ──────────────────────────────────────
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose();
            // reopen the main dashboard; replace "" with real customerName if you have it
            MasterGUI master = new MasterGUI(customerId, "");
            master.setLocationRelativeTo(null);
            master.setVisible(true);
        });
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottom.add(backButton);
        add(bottom, BorderLayout.SOUTH);
    }

    private void loadData() {
        String sql = "SELECT item_name, price, quantity, subtotal, order_time "
                + "FROM orders WHERE customer_id = ? ORDER BY order_time DESC";
        try (Connection conn = DataBaseConnections.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("item_name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getDouble("subtotal"),
                        rs.getTimestamp("order_time")
                    });
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error loading orders:\n" + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
