/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package test1;

import test1.Order;

/**
 *
 * @author Dell
 */
public interface OrderState {
     void processOrder(Order order);
    void cancelOrder(Order order);
    void deliverOrder(Order order);
    void completeOrder(Order order);
    String getStateName();
}
