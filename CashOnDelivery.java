/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CashOnDelivery extends PaymentStrategy {
    private int orderId;
    private double amount;
    private boolean confirmed; // Indicates if the cash payment is confirmed

    public CashOnDelivery() {
    }

    public CashOnDelivery(int orderId, double amount, boolean confirmed) {
        this.orderId = orderId;
        this.amount = amount;
        this.confirmed = confirmed;
    }

    // === VALIDATION ===
    public boolean isValid() {
        return orderId > 0 && amount > 0;
    }

    // === PROCESS PAYMENT ===
    @Override
    public boolean processPayment(int orderId, double amount) {
        System.out.println("Cash on Delivery payment for Order " + orderId + " with amount " + amount);
        return true;
    }

    // === CREATE ===
    public boolean saveToDatabase(Connection conn) {
        if (!isValid()) {
            System.out.println("Invalid cash payment data.");
            return false;
        }

        String sql = "INSERT INTO cash_payments (order_id, amount, confirmed) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.setDouble(2, amount);
            stmt.setBoolean(3, confirmed);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // === READ ===
    public static CashOnDelivery getByOrderId(Connection conn, int orderId) {
        String sql = "SELECT * FROM cash_payments WHERE order_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CashOnDelivery(
                        rs.getInt("order_id"),
                        rs.getDouble("amount"),
                        rs.getBoolean("confirmed")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // === UPDATE ===
    public boolean updateInDatabase(Connection conn) {
        String sql = "UPDATE cash_payments SET amount = ?, confirmed = ? WHERE order_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, amount);
            stmt.setBoolean(2, confirmed);
            stmt.setInt(3, orderId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // === DELETE ===
    public boolean deleteFromDatabase(Connection conn) {
        String sql = "DELETE FROM cash_payments WHERE order_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // === Getters and Setters ===
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}

