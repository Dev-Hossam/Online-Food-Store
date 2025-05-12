/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

import java.sql.Connection;
import java.sql.SQLException;

public class FoodItemManager {
    private final Connection connection;

    public FoodItemManager(Connection connection) {
        this.connection = connection;
    }

    public void displayFoodDetails(FoodItem item) {
        item.displayDetails();
    }

    public void applySize(FoodItem item, String size, double price) {
        item.setSize(size, price);
    }

    public void applyInstructions(FoodItem item, String instructions) {
        item.setInstructions(instructions);
    }

    // --- CRUD operations ---

    public FoodItem createFoodItem(String name, double price, String description, String availability) throws SQLException {
        if (!CustomValidator.validateMenuName(name) ||
            !CustomValidator.validateDescription(description) ||
            !CustomValidator.validatePrice(price)) {
            throw new IllegalArgumentException("Invalid food item data.");
        }

        FoodItem item = new FoodItem(connection, name, price, description, availability);
        item.save(); // insert into DB
        return item;
    }

    public void updateFoodItem(FoodItem item, String name, double price, String description, String availability) throws SQLException {
        if (!CustomValidator.validateMenuName(name) ||
            !CustomValidator.validateDescription(description) ||
            !CustomValidator.validatePrice(price)) {
            throw new IllegalArgumentException("Invalid food item data.");
        }

        item = new FoodItem(item.foodID, name, price, description, availability);
        item.save(); // update in DB
    }

    public void deleteFoodItem(FoodItem item) throws SQLException {
        item.delete(); // delete from DB
    }

    public FoodItem getFoodItemById(int id) throws SQLException {
        return FoodItem.loadById(connection, id);
    }
}
