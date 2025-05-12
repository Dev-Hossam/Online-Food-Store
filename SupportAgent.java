/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupportAgent {
    private int agentID;
    private String name;
    private String department;
    private List<String> specialization = new ArrayList<>();
    private List<SupportTicket> activeTickets = new ArrayList<>();
    private boolean available;

    public SupportAgent(int agentID, String name, String department) {
        this.agentID = agentID;
        this.name = name;
        this.department = department;
        this.available = true;
    }

    // Getters and Setters
    public int getAgentID() { return agentID; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public List<SupportTicket> getActiveTickets() { return activeTickets; }

    public void addSpecialization(String specialization) {
        this.specialization.add(specialization);
    }

    public boolean canHandle(String issueType) {
        return specialization.contains(issueType);
    }

    public void assignTicket(SupportTicket ticket) {
        activeTickets.add(ticket);
    }

    public boolean resolveTicket(SupportTicket ticket) {
        return activeTickets.remove(ticket);
    }

    // === Validation ===
    public boolean isValid() {
        return name != null && !name.isEmpty()
            && department != null && !department.isEmpty();
    }

    // === CRUD Methods using JDBC ===

    public boolean saveToDatabase(Connection conn) {
        if (!isValid()) return false;
        try {
            String sql = "INSERT INTO support_agents (agent_id, name, department, available) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, agentID);
            stmt.setString(2, name);
            stmt.setString(3, department);
            stmt.setBoolean(4, available);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateInDatabase(Connection conn) {
        if (!isValid()) return false;
        try {
            String sql = "UPDATE support_agents SET name=?, department=?, available=? WHERE agent_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, department);
            stmt.setBoolean(3, available);
            stmt.setInt(4, agentID);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFromDatabase(Connection conn) {
        try {
            String sql = "DELETE FROM support_agents WHERE agent_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, agentID);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static SupportAgent fetchById(Connection conn, int agentId) {
        try {
            String sql = "SELECT * FROM support_agents WHERE agent_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, agentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                SupportAgent agent = new SupportAgent(
                    rs.getInt("agent_id"),
                    rs.getString("name"),
                    rs.getString("department")
                );
                agent.setAvailable(rs.getBoolean("available"));
                return agent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
