/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

public class DessertMenu extends Menu {

       public DessertMenu() {
        super("DESSERT MENU", "Dessert of course!");
        
        // Add some dessert items
        add(new MenuItem(
            "Apple Pie",
            "Apple pie with a flakey crust, topped with vanilla ice cream",
            true,
            1.59));
            
        add(new MenuItem(
            "Cheesecake",
            "Creamy New York cheesecake, with a chocolate graham crust",
            true,
            1.99));
            
        add(new MenuItem(
            "Sorbet",
            "A scoop of raspberry and a scoop of lime",
            true,
            1.89));
            
        add(new MenuItem(
            "Chocolate Cake",
            "Rich chocolate cake with chocolate ganache",
            true,
            2.59));
    }
}



