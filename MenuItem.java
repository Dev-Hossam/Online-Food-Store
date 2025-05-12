/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

public class MenuItem extends MenuComponent {
    private String name;
    private String description;
    private boolean isVegetarian;
    private double price;
    private String category; // added category

    public MenuItem(String name, String description, boolean isVegetarian, double price) {
        this.name = name;
        this.description = description;
        this.isVegetarian = isVegetarian;
        this.price = price;
    }

    MenuItem(String pancake_1, String delicious_fluffy_pancake, boolean b, double d, String pancake) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    /**
     *
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isVegetarian() {
        return isVegetarian;
    }

    /**
     *
     * @return
     */
    @Override
    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    
    
    @Override
public void print() {
    System.out.print("  • " + getName());
    if (isVegetarian()) {
        System.out.print(" (v)");
    }
    System.out.println(" - $" + getPrice());
    System.out.println("     -- " + getDescription());
}

    
}


