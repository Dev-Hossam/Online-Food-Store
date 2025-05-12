/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

import java.sql.Connection;

public abstract class PaymentStrategy {

    // Core payment method to be implemented by subclasses
    public abstract boolean processPayment(int orderId, double amount);

    // === Optional: CRUD method signatures ===
    public boolean saveToDatabase(Connection conn) {
        throw new UnsupportedOperationException("Save not implemented");
    }

    public boolean updateInDatabase(Connection conn) {
        throw new UnsupportedOperationException("Update not implemented");
    }

    public boolean deleteFromDatabase(Connection conn) {
        throw new UnsupportedOperationException("Delete not implemented");
    }

    // Optional validation method
    public boolean isValid() {
        return true;
    }
}
