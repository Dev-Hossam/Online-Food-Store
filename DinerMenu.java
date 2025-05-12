/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

import java.sql.*;

public class DinerMenu extends Menu {

    public DinerMenu() {
        super("DINER MENU", "Lunch items");
        
        // Add some menu items
        add(new MenuItem(
            "Vegetarian BLT",
            "(Fakin') Bacon with lettuce & tomato on whole wheat", 
            true, 
            2.99));
        
        add(new MenuItem(
            "BLT",
            "Bacon with lettuce & tomato on whole wheat", 
            false, 
            2.99));
        
        add(new MenuItem(
            "Soup of the day",
            "Soup of the day, with a side of potato salad", 
            false, 
            3.29));
            
        add(new MenuItem(
            "Hotdog",
            "A hot dog, with sauerkraut, relish, onions, topped with cheese",
            false, 
            3.05));
            
        // Add dessert submenu
        add(new DessertMenu());
    }
}



