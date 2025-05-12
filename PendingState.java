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
// PendingState.java
public class PendingState implements OrderState {
    @Override
    public void processOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": Processing initiated.");
        order.setState(new ProcessingState());
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": Cannot deliver, order is still pending.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": Order cancelled.");
        order.setState(new CancelledState());
    }

    @Override
    public void completeOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": Cannot complete, order is not yet delivered.");
    }

    @Override
    public String getStateName() {
        return "Pending";
    }
}
