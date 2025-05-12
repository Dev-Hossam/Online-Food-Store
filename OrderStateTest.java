package Junit_testing;

import org.junit.*;
import static org.junit.Assert.*;
import test1.Order;

public class OrderStateTest {

    private Order order;

    @Before
    public void setUp() {
        order = new Order(100);
    }

    @Test
    public void testInitialStatePending() {
        // New orders start in Pending
        assertEquals("Pending", order.getStateName());
    }

    @Test
    public void testPendingToProcessing() {
        order.processOrder();
        assertEquals("Processing", order.getStateName());
    }

    @Test
    public void testProcessingToOutForDeliveryThenDelivered() {
        order.processOrder();
        // now Processing
        assertEquals("Processing", order.getStateName());

        order.deliverOrder();
        // moves to OutForDelivery
        assertEquals("OutForDelivery", order.getStateName());

        order.completeOrder();
        // finally to Delivered
        assertEquals("Delivered", order.getStateName());
    }

    @Test
    public void testCancelFromPending() {
        order.cancelOrder();
        assertEquals("Cancelled", order.getStateName());
    }
}
