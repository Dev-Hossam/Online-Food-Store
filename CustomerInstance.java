package test1;

import java.util.ArrayList;
import java.util.List;

public class CustomerInstance {

//   private List<Order> orderHistory;
//    private List<FoodItem> cart;
//   private List<PaymentStrategy> paymentMethods;
//
//    public CustomerInstance() {
//      this.orderHistory = new ArrayList<>();
//        this.cart = new ArrayList<>();
//        this.paymentMethods = new ArrayList<>();
//    }
//
//    // Getter & Setter for OrderHistory
//   public List<Order> getOrderHistory() {
//        return orderHistory;
//    }
//
//    public void setOrderHistory(List<Order> orderHistory) {
//        this.orderHistory = orderHistory;
//    }
//
//    // Getter & Setter for Cart
//    public List<FoodItem> getCart() {
//        return cart;
//    }
//
//    public void setCart(List<FoodItem> cart) {
//        this.cart = cart;
//    }
//
//    // Getter & Setter for PaymentMethods
//    public List<PaymentStrategy> getPaymentMethods() {
//        return paymentMethods;
//    }
//
//    public void setPaymentMethods(List<PaymentStrategy> paymentMethods) {
//        this.paymentMethods = paymentMethods;
//    }
//
//    // Place Order
//    public void placeOrder(Order order) {
//        orderHistory.add(order);
//        cart.clear();  // assuming order clears the cart
//        System.out.println("Order placed: " + order.getOrderID());
//    }
//
//    // View Order History
//    public List<Order> viewOrderHistory() {
//        return orderHistory;
//    }
//
//    // Cancel Order by ID
//    public boolean cancelOrder(int orderID) {
//        for (Order order : orderHistory) {
//            if (order.getOrderID() == orderID) {
//                order.setStatus("Cancelled");
//                System.out.println("Order " + orderID + " cancelled.");
//                return true;
//            }
//        }
//        System.out.println("Order " + orderID + " not found.");
//        return false;
//    }
//
//    // Cart Functions
//    public void addToCart(FoodItem item) {
//        cart.add(item);
//        System.out.println(item.getName() + " added to cart.");
//    }
//
//    public void removeFromCart(FoodItem item) {
//        if (cart.remove(item)) {
//            System.out.println(item.getName() + " removed from cart.");
//        } else {
//            System.out.println(item.getName() + " not found in cart.");
//        }
//    }
//
//    public void clearCart() {
//        cart.clear();
//        System.out.println("Cart cleared.");
//    }
//
//    // Payment Methods
//    public void addPaymentMethod(PaymentStrategy method) {
//        paymentMethods.add(method);
//        System.out.println("Payment method added.");
//    }
//
//    public void removePaymentMethod(PaymentStrategy method) {
//        if (paymentMethods.remove(method)) {
//            System.out.println("Payment method removed.");
//        } else {
//            System.out.println("Payment method not found.");
//        }
//    }
}
