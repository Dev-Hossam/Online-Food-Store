package Junit_testing;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;


import org.junit.*; 
import static org.junit.Assert.*;

import test1.Customer;
import test1.ReadOnlyUser;
import test1.CustomerInstance;
public class CustomerTest {

    private Customer customer;
    private ReadOnlyUser stubUser;

    @Before
    public void setUp() {
        // A simple stub of ReadOnlyUser so we control every return value
        stubUser = new ReadOnlyUser() {
            public int    getUserID()   { return 42; }
            public String getName()     { return "Ahmed"; }
            public String getEmail()    { return "ahmed@mail.com"; }
            public String getLocation() { return "Giza"; }
            public String getPhone()    { return "0123456789"; }
            public String getPassword() { return "ignored"; }
            public String getCity()     { return "Giza City"; }
            public String getStreet()   { return "Tahrir St."; }
        };

        // Build a Customer with that stub
        customer = new Customer(
            /*customerID=*/ 1,
            /*username=*/   "ahmed_user",
            /*password=*/   "pass123",
            /*ROU=*/        stubUser
        );
    }

    @Test
    public void testConstructorAndBasicGetters() {
        assertEquals(1,   customer.getCustomerID());
        assertEquals("ahmed_user", customer.getUsername());
        assertEquals("pass123",    customer.getPassword());
        // The stub should come back unmodified
        assertSame(stubUser, customer.getROU());
    }

    @Test
    public void testAddAndGetInstances() {
        CustomerInstance inst = new CustomerInstance();
        customer.addInstance(inst);

        List<CustomerInstance> list = customer.getInstances();
        assertEquals(1, list.size());
        assertSame(inst, list.get(0));
    }

    @Test
    public void testDelegatedReadOnlyUserMethods() {
        // All of these should simply relay to stubUser
        assertEquals(42,   customer.getUserID());
        assertEquals("Ahmed",        customer.getName());
        assertEquals("ahmed@mail.com", customer.getEmail());
        assertEquals("Giza",         customer.getLocation());
        assertEquals("0123456789",   customer.getPhone());
        assertEquals("Giza City",    customer.getCity());
        assertEquals("Tahrir St.",   customer.getStreet());
    }

    @After
    public void tearDown() {
        customer = null;
    }
}
