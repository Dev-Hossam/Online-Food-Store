/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

import java.sql.*;
import java.util.Date;

public class SupportTicket {
    private int ticketID;
    private int customerID;
    private String issue;
    private String status;
    private Date creationTime;
    private Date resolutionTime;
    private SupportAgent assignedAgent;

    // Constructor
    public SupportTicket(int ticketID, int customerID, String issue) {
        this.ticketID = ticketID;
        this.customerID = customerID;
        this.issue = issue;
        this.status = "Open"; // Default status
        this.creationTime = new Date(); // Initialize creation time to the current date/time
    }

    // Getters and Setters
    public int getTicketID() { return ticketID; }
    public void setTicketID(int ticketID) { this.ticketID = ticketID; }

    public int getCustomerID() { return customerID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }

    public String getIssue() { return issue; }
    public void setIssue(String issue) { this.issue = issue; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getCreationTime() { return creationTime; }

    public Date getResolutionTime() { return resolutionTime; }
    public void setResolutionTime(Date resolutionTime) { this.resolutionTime = resolutionTime; }

    public SupportAgent getAssignedAgent() { return assignedAgent; }
    public void setAssignedAgent(SupportAgent agent) { this.assignedAgent = agent; }

    // === Validation ===
    public boolean isValid() {
        return customerID > 0 && issue != null && !issue.isEmpty();
    }

    // === CRUD Methods using JDBC ===

    // Save ticket to the database
    public boolean saveToDatabase(Connection conn) {
        if (!isValid()) return false;
        try {
            String sql = "INSERT INTO support_tickets (ticket_id, customer_id, issue, status, creation_time) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ticketID);
            stmt.setInt(2, customerID);
            stmt.setString(3, issue);
            stmt.setString(4, status);
            stmt.setTimestamp(5, new Timestamp(creationTime.getTime()));
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update ticket in the database
    public boolean updateInDatabase(Connection conn) {
        if (!isValid()) return false;
        try {
            String sql = "UPDATE support_tickets SET issue=?, status=?, resolution_time=? WHERE ticket_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, issue);
            stmt.setString(2, status);
            if (resolutionTime != null) {
                stmt.setTimestamp(3, new Timestamp(resolutionTime.getTime()));
            } else {
                stmt.setNull(3, Types.TIMESTAMP);
            }
            stmt.setInt(4, ticketID);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete ticket from the database
    public boolean deleteFromDatabase(Connection conn) {
        try {
            String sql = "DELETE FROM support_tickets WHERE ticket_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ticketID);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Fetch ticket from database by ticket ID
    public static SupportTicket fetchById(Connection conn, int ticketId) {
        try {
            String sql = "SELECT * FROM support_tickets WHERE ticket_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ticketId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                SupportTicket ticket = new SupportTicket(
                    rs.getInt("ticket_id"),
                    rs.getInt("customer_id"),
                    rs.getString("issue")
                );
                ticket.setStatus(rs.getString("status"));
                ticket.setcreationTime(rs.getTimestamp("creation_time"));
                ticket.setResolutionTime(rs.getTimestamp("resolution_time"));
                return ticket;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Assign agent to the ticket
    public boolean assignAgent(SupportAgent agent) {
        if (agent != null && agent.isAvailable()) {
            this.assignedAgent = agent;
            return true;
        }
        return false;
    }

    private void setcreationTime(Timestamp timestamp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
