package test1;
import java.util.ArrayList;
import java.util.List;

public class Admin {
    private int adminID;
    private boolean loggedIn = false;
    private User U;
    // Store users managed by this admin
    private List<User> managedUsers = new ArrayList<>();
    
    // Added missing fields that were previously expected to be inherited
    private String password;
    private String phone;
    private String email;
    private String Street;

    public Admin(int adminID, User U, String password, String phone, String email, String Street) {
        this.adminID = adminID;
        this.U = U;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.Street = Street;
    }
    

    public void setU(User U) {
        this.U = U;
    }

    public User getU() {
        return U;
    }
    
    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }
    
    public int getAdminID() {
        return adminID;
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    public List<User> getManagedUsers() {
        return managedUsers;
    }
    
    public void setManagedUsers(List<User> managedUsers) {
        this.managedUsers = managedUsers;
    }
    
    // Deactivate user (aka suspend)
   public void deactivateUser(User user) {
        if (user != null) {
            // Direct access to protected field since there's no setter in User class
            user.isActive = false;
            System.out.println("User " + user.getUserID() + " has been deactivated.");
        }
    }
    
    public void activateUser(User user) {
        if (user != null) {
            // Direct access to protected field since there's no setter in User class
            user.isActive = true;
            System.out.println("User " + user.getUserID() + " has been activated.");
        }
    }
    
    // Set admin's password (removed @Override)
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    
    // Set admin's phone (removed @Override)
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getPhone() {
        return this.phone;
    }
    
    // Email getter and setter
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
    
    // Login using email + password
    public boolean login(String email, String password) {
        if (this.email != null && this.email.equals(email) && 
            this.password != null && this.password.equals(password)) {
            this.loggedIn = true;
            System.out.println("Admin logged in successfully.");
            return true;
        } else {
            System.out.println("Login failed for admin.");
            return false;
        }
    }
    
    public void logout() {
        this.loggedIn = false;
        System.out.println("Admin logged out.");
    }
    
    // Admin sets the street of their profile (removed @Override)
    public void setStreet(String street) {
        this.Street = street;
    }
    
    public String getStreet() {
        return this.Street;
    }
    
    // View all users under management
    public List<User> viewAllUsers() {
        return new ArrayList<>(managedUsers); // Return copy to prevent modification
    }
    
    // Add a user to admin's managed list (simulate management)
    public void manageUsers(User user) {
        if (user != null && !managedUsers.contains(user)) {
            managedUsers.add(user);
            System.out.println("User " + user.getUserID() + " is now managed by Admin " + this.adminID);
        } else if (user != null) {
            System.out.println("User " + user.getUserID() + " is already managed.");
        }
    }
}