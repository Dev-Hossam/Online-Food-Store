package test1;



public class Order {
    private int orderId;
    private OrderState state;

    public Order(int orderId) {
        this.orderId = orderId;
        this.state = new PendingState(); // Default initial state
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void processOrder() {
        this.state.processOrder(this);
    }

    public void deliverOrder() {
        this.state.deliverOrder(this);
    }

    public void cancelOrder() {
        this.state.cancelOrder(this);
    }

    public void completeOrder() {
        this.state.completeOrder(this);
    }

    public String getStateName() {
        return state.getStateName();
    }
}
