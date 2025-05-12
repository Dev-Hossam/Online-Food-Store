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
public class CancelledState implements OrderState {
    @Override
    public void processOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": Cannot process, order has been cancelled.");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": Cannot deliver, order has been cancelled.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": Order already cancelled.");
    }

    @Override
    public void completeOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": Cannot complete, order has been cancelled.");
    }

    @Override
    public String getStateName() {
        return "Cancelled";
    }
}
