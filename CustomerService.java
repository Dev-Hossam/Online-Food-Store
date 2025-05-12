/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private int serviceID;
    private String name;
    private List<SupportAgent> supportAgents = new ArrayList<>();
    private List<SupportTicket> supportTickets = new ArrayList<>();

    // Getters and Setters
    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SupportAgent> getSupportAgents() {
        return supportAgents;
    }

    public void setSupportAgents(List<SupportAgent> supportAgents) {
        this.supportAgents = supportAgents;
    }

    public List<SupportTicket> getSupportTickets() {
        return supportTickets;
    }

    public void setSupportTickets(List<SupportTicket> supportTickets) {
        this.supportTickets = supportTickets;
    }

    // === Validation ===
    public boolean isValid() {
        return name != null && !name.isEmpty();
    }

    // === CRUD Operations ===

    // Add a Support Agent to the service
    public boolean addSupportAgent(SupportAgent agent) {
        if (agent != null) {
            supportAgents.add(agent);
            return true;
        }
        return false;
    }

    // Remove a Support Agent from the service
    public boolean removeSupportAgent(SupportAgent agent) {
        if (agent != null) {
            return supportAgents.remove(agent);
        }
        return false;
    }

    // Assign an agent to a ticket
    public boolean assignAgentToTicket(SupportTicket ticket, SupportAgent agent) {
        if (ticket != null && agent != null) {
            ticket.assignAgent(agent);
            return true;
        }
        return false;
    }

    // Track ticket status
    public String trackTicketStatus(SupportTicket ticket) {
        if (ticket != null) {
            return ticket.getStatus();
        }
        return "Invalid ticket.";
    }

    // Save CustomerService to the database
    public boolean saveToDatabase(Connection conn) {
        if (!isValid()) return false;
        try {
            String sql = "INSERT INTO customer_service (service_id, name) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, serviceID);
            stmt.setString(2, name);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update CustomerService in the database
    public boolean updateInDatabase(Connection conn) {
        if (!isValid()) return false;
        try {
            String sql = "UPDATE customer_service SET name=? WHERE service_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, serviceID);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete CustomerService from the database
    public boolean deleteFromDatabase(Connection conn) {
        try {
            String sql = "DELETE FROM customer_service WHERE service_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, serviceID);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Fetch CustomerService by ID from the database
    public static CustomerService fetchById(Connection conn, int serviceId) {
        try {
            String sql = "SELECT * FROM customer_service WHERE service_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, serviceId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                CustomerService service = new CustomerService();
                service.setServiceID(rs.getInt("service_id"));
                service.setName(rs.getString("name"));
                return service;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Generate a report (implementation can vary)
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Customer Service Report\n");
        report.append("Service ID: ").append(serviceID).append("\n");
        report.append("Name: ").append(name).append("\n");
        report.append("Number of Support Agents: ").append(supportAgents.size()).append("\n");
        report.append("Number of Tickets: ").append(supportTickets.size()).append("\n");
        return report.toString();
    }

}
