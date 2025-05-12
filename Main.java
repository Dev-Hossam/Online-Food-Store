/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test1;

/**
 *
 * @author ahmed
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
 // Get the singleton controller
        UserController userController = UserController.getInstance();

        // Test sign-up
        Customer newCustomer = userController.signUp(
                "Alice Smith",
                "alice@example.com",
                "USA",
                "1234567890",
                "securePass123",
                "New York",
                "5th Avenue"
        );

        if (newCustomer != null) {
            System.out.println("Customer signed up with ID: " + newCustomer.getCustomerID());
        } else {
            System.out.println("Sign-up failed.");
        }

        // Test login with correct credentials
        Customer loggedInCustomer = userController.login("alice@example.com", "securePass123");

        if (loggedInCustomer != null) {
            System.out.println("Login successful. Welcome, " + loggedInCustomer.getUsername());
        } else {
            System.out.println("Login failed.");
        }

        // Test login with incorrect password
        Customer failedLogin = userController.login("alice@example.com", "wrongPass");

        if (failedLogin == null) {
            System.out.println("Correctly failed to login with wrong password.");
        }
    }
}
