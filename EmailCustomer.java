/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;
import java.sql.*;
import java.util.regex.*;

public class EmailCustomer implements Observer {
    private String name;
    private String email;

    // Database connection details (update with your actual DB info)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "your_password";

    public EmailCustomer(String name, String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.name = name;
        this.email = email;
    }

    @Override
    public void update(String notification) {
        System.out.println(name + " received an email at " + email + ": " + notification);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // Email validation using regex
    public static boolean isValidEmail(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // -------------------- CRUD Methods --------------------

    public boolean saveToDatabase() {
        String sql = "INSERT INTO customers (name, email) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateEmailInDatabase(String newEmail) {
        if (!isValidEmail(newEmail)) return false;

        String sql = "UPDATE customers SET email = ? WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newEmail);
            pstmt.setString(2, name);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                this.email = newEmail;
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteFromDatabase() {
        String sql = "DELETE FROM customers WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static EmailCustomer findByName(String customerName) {
        String sql = "SELECT name, email FROM customers WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customerName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                return new EmailCustomer(name, email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

