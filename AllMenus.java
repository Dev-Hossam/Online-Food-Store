package test1;

import java.util.Iterator;

/**
 * Top-level menu that holds all restaurant menus
 */
public class AllMenus extends Menu {
    
    public AllMenus() {
        super("ALL MENUS", "All menus combined");
    }
    
    public void printVegetarianMenu() {
        System.out.println("\n\nVEGETARIAN MENU\n--------------------");
        printVegetarianItems(menuComponents.iterator());
    }
    
    private void printVegetarianItems(Iterator<MenuComponent> iterator) {
        while (iterator.hasNext()) {
            MenuComponent menuComponent = iterator.next();
            try {
                if (menuComponent.isVegetarian()) {
                    menuComponent.print();
                }
            } catch (UnsupportedOperationException e) {
                // This is a menu, not a menu item
                try {
                    Iterator<MenuComponent> subIterator = ((Menu)menuComponent).createIterator();
                    printVegetarianItems(subIterator);
                } catch (Exception ex) {
                    // Not a Menu with an iterator
                }
            }
        }
    }
}