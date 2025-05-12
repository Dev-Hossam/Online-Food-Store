package Junit_testing;

import org.junit.*;
import static org.junit.Assert.*;
import test1.CustomerService;
import test1.SupportAgent;
import test1.SupportTicket;

import java.util.List;

public class CustomerServiceTest {

    private CustomerService service;
    private SupportAgent agent;
    private SupportTicket ticket;

    @Before
    public void setUp() {
        service = new CustomerService();
        service.setServiceID(10);
        service.setName("TechSupport");

        agent = new SupportAgent(101, "Alice", "SupportDept");
        ticket = new SupportTicket(201, 202, "Cannot login");
    }

    @Test
    public void testIsValid() {
        // name is non-null/non-empty ⇒ valid
        assertTrue(service.isValid());

        service.setName("");
        assertFalse(service.isValid());

        service.setName(null);
        assertFalse(service.isValid());
    }

    @Test
    public void testAddRemoveSupportAgent() {
        assertFalse(service.addSupportAgent(null));

        assertTrue(service.addSupportAgent(agent));
        List<SupportAgent> agents = service.getSupportAgents();
        assertEquals(1, agents.size());
        assertSame(agent, agents.get(0));

        assertFalse(service.removeSupportAgent(null));
        assertTrue(service.removeSupportAgent(agent));
        assertTrue(service.getSupportAgents().isEmpty());
        assertFalse(service.removeSupportAgent(agent));
    }

    @Test
    public void testAssignAgentToTicket() {
        // happy path: non-null ticket & agent ⇒ service returns true and ticket gets the agent
        assertTrue(service.assignAgentToTicket(ticket, agent));
        assertSame(agent, ticket.getAssignedAgent());

        // null arguments ⇒ return false
        assertFalse(service.assignAgentToTicket(null, agent));
        assertFalse(service.assignAgentToTicket(ticket, null));

        // agent unavailable ⇒ service still returns true (per your code),
        // but ticket.assignAgent(agent) will fail internally, so no agent set
        agent.setAvailable(false);
        SupportTicket t2 = new SupportTicket(202, 203, "Another issue");
        assertTrue(service.assignAgentToTicket(t2, agent));   // still true
        assertNull(t2.getAssignedAgent());                     // but no assignment
    }

    @Test
    public void testTrackTicketStatus() {
        assertEquals("Open", service.trackTicketStatus(ticket));

        ticket.setStatus("Closed");
        assertEquals("Closed", service.trackTicketStatus(ticket));

        assertEquals("Invalid ticket.", service.trackTicketStatus(null));
    }

    @After
    public void tearDown() {
        service = null;
        agent = null;
        ticket = null;
    }
}
