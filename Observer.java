/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

/**
 * Observer interface for the Observer design pattern.
 * Defines the method that gets called when the subject state changes.
 * Includes identity methods to support CRUD operations.
 */
public interface Observer {
    
    /**
     * Called when the subject updates its state.
     * 
     * @param notification the message or update from the subject
     */
    void update(String notification);

    /**
     * Gets the observer's name (useful for identification and persistence).
     *
     * @return the name of the observer
     */
    String getName();

    /**
     * Gets the observer's email (used for contact and unique ID in some systems).
     *
     * @return the email of the observer
     */
    String getEmail();
}
