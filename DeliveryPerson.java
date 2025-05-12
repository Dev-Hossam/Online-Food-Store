/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;


import java.util.ArrayList;
import java.util.List;

/**
 * DeliveryPerson class represents a delivery agent handling orders.
 * Includes support for order management, location tracking, and delivery status.
 */
public class DeliveryPerson {

    private List<Order> assignedOrderList;
    private String currentLocation;
    private String status;

    // Default constructor
    public DeliveryPerson() {
        this.assignedOrderList = new ArrayList<>();
        this.currentLocation = "Unknown";
        this.status = "Unavailable";
    }

    // Parameterized constructor
    public DeliveryPerson(List<Order> orderList, String location, String status) {
        this.assignedOrderList = orderList != null ? new ArrayList<>(orderList) : new ArrayList<>();
        this.currentLocation = (location != null && !location.trim().isEmpty()) ? location : "Unknown";
        this.status = (status != null && !status.trim().isEmpty()) ? status : "Unavailable";
    }

    /**
     * Assigns a new order to the delivery person.
     * @param order The order to assign.
     */
    public void receiveOrder(Order order) {
        if (order != null) {
            assignedOrderList.add(order);
        }
    }

    /**
     * Updates the delivery status of a specific order.
     * @param order The order to update.
     * @param status The new delivery status.
     */
public void updateDeliveryStatus(Order order, String status) {
    if (order != null && status != null && !status.trim().isEmpty()) {
        // Update the order's state based on the status
        if (status.equals("In Progress")) {
            order.setState(new OutForDeliveryState());
        } else if (status.equals("Delivered")) {
            order.setState(new DeliveredState());
        } else if (status.equals("Cancelled")) {
            order.setState(new CancelledState());
        }
        System.out.println("Order " + order.getOrderId() + " updated to status: " + status);
    }
}
    public List<Order> getAssignedOrders() {
        return new ArrayList<>(assignedOrderList);
    }

    public void setAssignedOrders(List<Order> orderList) {
        if (orderList != null) {
            this.assignedOrderList = new ArrayList<>(orderList);
        }
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void updateLocation(String location) {
        if (location != null && !location.trim().isEmpty()) {
            this.currentLocation = location;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status != null && !status.trim().isEmpty()) {
            this.status = status;
        }
    }

    @Override
    public String toString() {
        return "DeliveryPerson{" +
                "orders=" + assignedOrderList.size() +
                ", location='" + currentLocation + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    
}
