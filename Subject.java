/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

/**
 * Subject interface for the Observer pattern.
 * Any class implementing this interface can be observed by implementing Observer classes.
 */
public interface Subject {

    /**
     * Registers an observer (e.g., a customer) to receive updates.
     *
     * @param o The observer to add.
     */
    void addObserver(Observer o);

    /**
     * Unregisters an observer from receiving updates.
     *
     * @param o The observer to remove.
     */
    void removeObserver(Observer o);

    /**
     * Notifies all registered observers of an event or message.
     *
     * @param notification The message to broadcast.
     */
    void notifyAllObservers(String notification);
}


