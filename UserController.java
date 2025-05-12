package test1;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private static UserController instance;
    private List<User> users;
    private List<Customer> customers;

    private UserController() {
        users = new ArrayList<>();
        customers = new ArrayList<>();
    }

    public static synchronized UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    /**
     * Sign up a new user and customer. Returns the Customer if successful, or null on failure.
     */
    public Customer signUp(String name, String email, String location,
                           String phone, String password, String city, String street) {
        // Check if email already exists
        for (User existingUser : users) {
            if (existingUser.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Email already registered.");
                return null;
            }
        }

        // Create and store User
        User newUser = new User(name, email, location, phone, password, city, street);
        users.add(newUser);

        // Create and store Customer linked to the new User
        Customer newCustomer = new Customer(newUser.getUserID(), name, password, newUser);
        customers.add(newCustomer);

        System.out.println("Sign-up successful. Customer ID: " + newCustomer.getCustomerID());
        return newCustomer;
    }

    /**
     * Attempt login with email and password. Returns Customer if successful, or null.
     */
    public Customer login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.verifyPassword(password)) {
                for (Customer cust : customers) {
                    if (cust.getUserID() == user.getUserID()) {
                        System.out.println("Login successful. Welcome, " + cust.getUsername());
                        return cust;
                    }
                }
                System.out.println("Login matched user but not customer.");
                return null;
            }
        }
        System.out.println("Login failed. Invalid email or password.");
        return null;
    }
}
