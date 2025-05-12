/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

public class Waitress {
    private MenuComponent allMenus;
    
    public Waitress(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }
    
    public void printMenu() {
        allMenus.print();
    }
    
    public void printVegetarianMenu() {
        System.out.println("\n\nVEGETARIAN MENU\n--------------------");
        
        // Call the method from AllMenus if it's that type
        if (allMenus instanceof AllMenus) {
            ((AllMenus) allMenus).printVegetarianMenu();
        } else {
            System.out.println("Sorry, vegetarian menu is only available with AllMenus");
        }
    }
}

