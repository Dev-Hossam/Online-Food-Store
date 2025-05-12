/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Payment {
    private int paymentId;
    private double amount;
    private int orderId;
    private LocalDateTime paymentDate;
    private String paymentType;
    private PaymentStrategy paymentStrategy;
    private int cardNum;

    public Payment(int paymentId, double amount, int orderId, LocalDateTime paymentDate, String paymentType, PaymentStrategy paymentStrategy) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.orderId = orderId;
        this.paymentDate = paymentDate;
        this.paymentType = paymentType;
        this.paymentStrategy = paymentStrategy;
    }

    public Payment() {}

    // === Validation Method ===
    public boolean isValid() {
        return amount > 0 && String.valueOf(cardNum).matches("\\d{16}") && paymentType != null && !paymentType.isEmpty();
    }

    // === CRUD: Create ===
    public boolean saveToDatabase(Connection conn) {
        if (!isValid()) {
            System.out.println("Invalid payment data.");
            return false;
        }

        String sql = "INSERT INTO payments (payment_id, amount, order_id, payment_date, payment_type, card_num) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, paymentId);
            stmt.setDouble(2, amount);
            stmt.setInt(3, orderId);
            stmt.setString(4, paymentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            stmt.setString(5, paymentType);
            stmt.setInt(6, cardNum);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // === CRUD: Read ===
    public static Payment getById(Connection conn, int id) {
        String sql = "SELECT * FROM payments WHERE payment_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Payment p = new Payment();
                p.paymentId = rs.getInt("payment_id");
                p.amount = rs.getDouble("amount");
                p.orderId = rs.getInt("order_id");
                p.paymentDate = LocalDateTime.parse(rs.getString("payment_date"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                p.paymentType = rs.getString("payment_type");
                p.cardNum = rs.getInt("card_num");
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // === CRUD: Update ===
    public boolean updateInDatabase(Connection conn) {
        String sql = "UPDATE payments SET amount = ?, order_id = ?, payment_date = ?, payment_type = ?, card_num = ? WHERE payment_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, amount);
            stmt.setInt(2, orderId);
            stmt.setString(3, paymentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            stmt.setString(4, paymentType);
            stmt.setInt(5, cardNum);
            stmt.setInt(6, paymentId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // === CRUD: Delete ===
    public boolean deleteFromDatabase(Connection conn) {
        String sql = "DELETE FROM payments WHERE payment_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, paymentId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Getters and Setters (same as before)
    public void addCard(int cardNum) {
        this.cardNum = cardNum;
    }

    public boolean processPayment() {
        if (paymentStrategy == null) throw new IllegalStateException("Payment strategy not set");
        return paymentStrategy.processPayment(orderId, amount);
    }

    public void applyDiscount(String code) {
        System.out.println("Discount code applied: " + code);
    }

    public void setPaymentID(int id) { this.paymentId = id; }
    public int getPaymentID() { return paymentId; }
    public void setAmount(double amount) { this.amount = amount; }
    public double getAmount() { return amount; }
    public void setPaymentType(String type) { this.paymentType = type; }
    public String getPaymentType() { return paymentType; }
    public void setPaymentStrategy(PaymentStrategy strategy) { this.paymentStrategy = strategy; }
    public PaymentStrategy getPaymentStrategy() { return paymentStrategy; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getOrderId() { return orderId; }
    public void setPaymentDate(LocalDateTime date) { this.paymentDate = date; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public int getCardNum() { return cardNum; }
}
