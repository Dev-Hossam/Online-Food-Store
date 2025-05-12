/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

import test1.OutForDeliveryState;
import test1.OrderState;
import test1.Order;

/**
 *
 * @author Dell
 */
public class ProcessingState implements OrderState {
    @Override
    public void processOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": Already in processing state.");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": Order is out for delivery.");
        order.setState(new OutForDeliveryState());
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": Cannot cancel, order is being processed.");
    }

    @Override
    public void completeOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": Cannot complete, order is not yet delivered.");
    }

    @Override
    public String getStateName() {
        return "Processing";
    }
}

