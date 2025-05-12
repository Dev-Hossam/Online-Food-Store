/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;


public class CafeMenu extends Menu {

     public CafeMenu() {
        super("CAFE MENU", "Dinner items");
        
        // Add some menu items
        add(new MenuItem(
            "Veggie Burger and Air Fries",
            "Veggie burger on a whole wheat bun, lettuce, tomato, and fries",
            true, 
            3.99));
            
        add(new MenuItem(
            "Soup of the day",
            "A cup of the soup of the day, with a side salad",
            false, 
            3.69));
            
        add(new MenuItem(
            "Burrito",
            "A large burrito, with whole pinto beans, salsa, guacamole",
            true, 
            4.29));
    }
}



