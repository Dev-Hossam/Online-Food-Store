/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;



import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Restaurant implements Subject {
    private int restaurantID;
    private String name;
    private String address;
    private boolean isOpen;
    private List<String> menuItems = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();
    private List<String> specialOffers = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    public Restaurant() {
        this.restaurantID = 0;
        this.name = "";
        this.address = "";
        this.isOpen = false;
    }

    public Restaurant(int restaurantID, String name, String address, boolean isOpen) {
        setRestaurantID(restaurantID);
        setName(name);
        setAddress(address);
        this.isOpen = isOpen;
    }

    public Restaurant(int restaurantID){
    setRestaurantID(restaurantID);
    }
    public Restaurant(String name) {
        this.restaurantID = 0;
        setName(name);
        this.address = "";
        this.isOpen = false;
    }

    // Observer methods
    @Override
    public void addObserver(Observer o) {
        if (o != null && !observers.contains(o)) {
            observers.add(o);
            System.out.println("New customer subscribed to " + name + " notifications!");
        }
    }

    @Override
    public void removeObserver(Observer o) {
        if (o != null && observers.remove(o)) {
            System.out.println("A customer unsubscribed from " + name + " notifications.");
        }
    }

    @Override
    public void notifyAllObservers(String notification) {
        for (Observer observer : observers) {
            observer.update(notification);
        }
    }

    // Getters and Setters with validation
    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        if (restaurantID < 0) {
            throw new IllegalArgumentException("Restaurant ID cannot be negative.");
        }
        this.restaurantID = restaurantID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Restaurant name cannot be empty.");
        }
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty.");
        }
        this.address = address;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
        notifyAllObservers(name + (isOpen ? " is now open!" : " is now closed."));
    }

    // Order Management
    public String processOrder(String orderDetails) {
        if (!isOpen) {
            return "Sorry, restaurant is closed.";
        }

        if (orderDetails == null || orderDetails.trim().isEmpty()) {
            return "Order details cannot be empty.";
        }

        String orderIDStr = "ORD-" + UUID.randomUUID().toString().substring(0, 8);
        int orderID= Integer.parseInt(orderIDStr);
        Order newOrder = new Order(orderID);
        
        orderList.add(newOrder);
        notifyAllObservers("New order processed: " + orderID);
        return "Order processed: " + orderID;
    }

   public String updateOrderStatus(String orderID, String newStatus) {
    if (newStatus == null || newStatus.trim().isEmpty()) {
        return "Invalid status.";
    }

    for (Order order : orderList) {
        // Using == for primitive comparison and checking if the order ID matches
        if (order.getOrderId() == Integer.parseInt(orderID)) {
            // Check status and transition accordingly
            switch (newStatus.trim().toLowerCase()) {
                case "in progress":
                    order.setState(new OutForDeliveryState());
                    break;
                case "delivered":
                    order.setState(new DeliveredState());
                    break;
                case "cancelled":
                    order.setState(new CancelledState());
                    break;
                default:
                    return "Invalid status provided.";
            }
            
            // Notify observers about the status update
            notifyAllObservers("Order " + orderID + " updated to status: " + newStatus);
            return "Order " + orderID + " updated.";
        }
    }
    return "Order not found.";
}


public String getOrderStatus(String orderID) {
    for (Order order : orderList) {
        // Fix: Using == for primitive comparison instead of equals()
        if (order.getOrderId() == Integer.parseInt(orderID)) {
            return "Order " + orderID + " status: " + order.getStateName();
        }
    }
    return "Order not found.";
}


    // Menu Management
    public String addMenuItem(String menuItem) {
        if (menuItem == null || menuItem.trim().isEmpty()) {
            return "Cannot add empty menu item.";
        }

        if (menuItems.contains(menuItem)) {
            return "Item already exists.";
        }

        menuItems.add(menuItem);
        notifyAllObservers("New menu item added: " + menuItem);
        return "Item added.";
    }

    public String removeMenuItem(String menuItem) {
        if (menuItem == null || menuItem.trim().isEmpty()) {
            return "Cannot remove empty menu item.";
        }

        if (menuItems.remove(menuItem)) {
            notifyAllObservers("Menu item removed: " + menuItem);
            return "Item removed.";
        }
        return "Item not found.";
    }

    public void setMenu(ArrayList<String> menu) {
        if (menu == null) {
            this.menuItems = new ArrayList<>();
        } else {
            this.menuItems = new ArrayList<>(menu);
        }
        notifyAllObservers("Menu updated with " + this.menuItems.size() + " items.");
    }

    public ArrayList<String> getMenu() {
        return new ArrayList<>(menuItems);
    }

    public void setOrderList(ArrayList<Order> orders) {
        if (orders == null) {
            this.orderList = new ArrayList<>();
        } else {
            this.orderList = new ArrayList<>(orders);
        }
    }

    public ArrayList<Order> getOrderList() {
        return new ArrayList<>(orderList);
    }

    // Offers
    public void addSpecialOffer(String offer) {
        if (offer != null && !offer.trim().isEmpty()) {
            specialOffers.add(offer);
            notifyAllObservers("New special offer: " + offer);
        }
    }

    public void announceEvent(String eventDetails) {
        if (eventDetails != null && !eventDetails.trim().isEmpty()) {
            notifyAllObservers("Event at " + name + ": " + eventDetails);
        }
    }

    public List<String> getSpecialOffers() {
        return new ArrayList<>(specialOffers);
    }

    public List<Observer> getObservers() {
        return new ArrayList<>(observers);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + restaurantID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", isOpen=" + isOpen +
                ", menuItems=" + menuItems.size() +
                ", orders=" + orderList.size() +
                ", observers=" + observers.size() +
                ", specialOffers=" + specialOffers.size() +
                '}';
    }
}
