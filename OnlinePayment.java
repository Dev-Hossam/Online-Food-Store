/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OnlinePayment extends PaymentStrategy {
    private int cardNum;
    private String cardHolderName;
    private String expiryDate; // Format: MM/YY
    private int cvv;

    public OnlinePayment(int cardNum, String cardHolderName, String expiryDate, int cvv) {
        this.cardNum = cardNum;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public OnlinePayment() {
    }

    // === VALIDATION ===
    public boolean isValid() {
        return String.valueOf(cardNum).matches("\\d{16}") &&
               cardHolderName != null && !cardHolderName.trim().isEmpty() &&
               expiryDate.matches("\\d{2}/\\d{2}") &&
               String.valueOf(cvv).matches("\\d{3}");
    }

    // === PROCESS PAYMENT ===
    @Override
    public boolean processPayment(int orderId, double amount) {
        System.out.println("Online payment processed for Order " + orderId + " with amount " + amount);
        return true;
    }

    // === CREATE ===
    public boolean saveToDatabase(Connection conn) {
        if (!isValid()) {
            System.out.println("Invalid card data.");
            return false;
        }

        String sql = "INSERT INTO online_payments (card_num, card_holder_name, expiry_date, cvv) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cardNum);
            stmt.setString(2, cardHolderName);
            stmt.setString(3, expiryDate);
            stmt.setInt(4, cvv);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // === READ ===
    public static OnlinePayment getByCardNum(Connection conn, int cardNum) {
        String sql = "SELECT * FROM online_payments WHERE card_num = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cardNum);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new OnlinePayment(
                        rs.getInt("card_num"),
                        rs.getString("card_holder_name"),
                        rs.getString("expiry_date"),
                        rs.getInt("cvv")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // === UPDATE ===
    public boolean updateInDatabase(Connection conn) {
        String sql = "UPDATE online_payments SET card_holder_name = ?, expiry_date = ?, cvv = ? WHERE card_num = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cardHolderName);
            stmt.setString(2, expiryDate);
            stmt.setInt(3, cvv);
            stmt.setInt(4, cardNum);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // === DELETE ===
    public boolean deleteFromDatabase(Connection conn) {
        String sql = "DELETE FROM online_payments WHERE card_num = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cardNum);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // === Getters and Setters ===
    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}
