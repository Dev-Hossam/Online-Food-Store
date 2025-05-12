/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

import test1.OrderState;
import test1.Order;

/**
 *
 * @author Dell
 */
public class DeliveredState implements OrderState {
    @Override
    public void processOrder(Order order) {
        System.out.println("Order already delivered.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Cannot cancel. Order delivered.");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Order already delivered.");
    }

    @Override
    public void completeOrder(Order order) {
        System.out.println("Order is now completed.");
        // You could optionally set a new state here or log as complete.
    }

    @Override
    public String getStateName() {
        return "Delivered";
    }
}

