package test1;
import java.util.ArrayList;
import java.util.List;
public class User implements ReadOnlyUser {
    private static int nextID = 1; // Static counter for auto-generated IDs

    protected int userID;
    protected String name;
    protected String email;
    protected String location;
    protected String phone;
    protected String password;
    protected String city;
    protected String Street;
    protected boolean isActive = true; // default to active



    // Constructor that auto-generates userID
    public User(String name, String email, String location, String phone, String password, String city, String Street) {
        this.userID = nextID++;
        this.name = name;
        this.email = email;
        this.location = location;
        this.phone = phone;
        this.password = password;
        this.city = city;
        this.Street=Street;
    }   

    @Override
    public int getUserID() {
        return userID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static int getNextID() {
        return nextID;
    }

    public static void setNextID(int nextID) {
        User.nextID = nextID;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public boolean verifyPassword(String inputPassword) {
    return this.password != null && this.password.equals(inputPassword);
}
    public boolean isActive() {
    return isActive;
}
public static User signUp(List<User> users, String name, String email, String location, String phone, String password, String city, String street) {
    // Check if the email is already used
    for (User user : users) {
        if (user.getEmail().equalsIgnoreCase(email)) {
            return null;
        }
    }
    // Create and return a new user
    User newUser = new User(name, email, location, phone, password, city, street);
    newUser.setStreet(street);
    users.add(newUser);
    return newUser;
}


public static User login(List<User> users, String email, String password) {
    for (User user : users) {
        if (user.getEmail().equalsIgnoreCase(email) && user.verifyPassword(password)) {
            return user;
        }
    }
    return null;
}


}
