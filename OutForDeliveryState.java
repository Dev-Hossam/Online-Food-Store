/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;



/**
 *
 * @author Dell
 */
public class OutForDeliveryState implements OrderState {
    @Override
    public void processOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": Cannot process, order is out for delivery.");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": Already out for delivery.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": Cannot cancel, order is out for delivery.");
    }

    @Override
    public void completeOrder(Order order) {
        System.out.println("Order " + order.getOrderId() + ": Order delivered and completed.");
        order.setState(new DeliveredState());
    }

    @Override
    public String getStateName() {
        return "OutForDelivery";
    }
}

