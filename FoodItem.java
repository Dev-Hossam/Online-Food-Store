/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

import java.sql.*;

public class FoodItem {
    int foodID;
    private String name;
    private double price;
    private String description;
    private String availability;

    private String size;
    private double additionalSizePrice;
    private String customInstructions;

    private Connection connection;

    // Constructor for new items (no ID yet)
    public FoodItem(Connection connection, String name, double price, String description, String availability) {
        this(-1, name, price, description, availability);
        this.connection = connection;
    }

    // Constructor for existing items
    public FoodItem(int foodID, String name, double price, String description, String availability) {
        this.foodID = foodID;
        this.name = name;
        this.price = price;
        this.description = description;
        this.availability = availability;
        this.size = "Regular";
        this.additionalSizePrice = 0.0;
        this.customInstructions = "";
    }

    // --- Getters and business logic ---
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description + (customInstructions.isEmpty() ? "" : " (Instructions: " + customInstructions + ")");
    }

    public double getPrice() {
        return price + additionalSizePrice;
    }

    public boolean checkAvailability() {
        return availability.equalsIgnoreCase("Available");
    }

    public void setSize(String size, double extraPrice) {
        this.size = size;
        this.additionalSizePrice = extraPrice;
    }

    public void setInstructions(String instructions) {
        this.customInstructions = instructions;
    }

    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Size: " + size);
        System.out.println("Price: $" + getPrice());
        System.out.println("Description: " + getDescription());
        System.out.println("Available: " + (checkAvailability() ? "Yes" : "No"));
    }

    // --- Database operations ---

    public void save() throws SQLException {
        if (!CustomValidator.validateMenuName(name) ||
            !CustomValidator.validateDescription(description) ||
            !CustomValidator.validatePrice(price)) {
            throw new IllegalArgumentException("Invalid food item data.");
        }

        String sql = (foodID == -1) ?
            "INSERT INTO food_items (name, price, description, availability) VALUES (?, ?, ?, ?)" :
            "UPDATE food_items SET name = ?, price = ?, description = ?, availability = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setString(3, description);
            stmt.setString(4, availability);

            if (foodID != -1) {
                stmt.setInt(5, foodID);
            }

            stmt.executeUpdate();

            if (foodID == -1) {
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        foodID = keys.getInt(1);
                    }
                }
            }
        }
    }

    public void delete() throws SQLException {
        if (foodID == -1) throw new IllegalStateException("Cannot delete unsaved item.");
        String sql = "DELETE FROM food_items WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, foodID);
            stmt.executeUpdate();
        }
    }

    public static FoodItem loadById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM food_items WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new FoodItem(
                        id,
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("availability")
                    );
                } else {
                    return null;
                }
            }
        }
    }
}
