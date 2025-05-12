package test1;

import javax.swing.*;
import java.awt.*;

public class VegetarianMenuGUI extends JFrame {
    private JTextPane textPane;
    private AllMenus allMenus;

    public VegetarianMenuGUI() {
        setTitle("Vegetarian Menu Viewer");
        setSize(550, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Setup JTextPane to render HTML
        textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setEditable(false);
        textPane.setBackground(Color.WHITE);
        textPane.setMargin(new Insets(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setBounds(10, 10, 510, 430);
        add(scrollPane);

        // Create menu hierarchy
        allMenus = new AllMenus();
        allMenus.add(new PancakeHouseMenu());
        allMenus.add(new DinerMenu());
        allMenus.add(new CafeMenu());

        // Add buttons
        JButton btnVegetarian = new JButton("Show Vegetarian Only");
        btnVegetarian.setBounds(10, 450, 170, 30);
        add(btnVegetarian);

        JButton btnAll = new JButton("Show Full Menu");
        btnAll.setBounds(190, 450, 170, 30);
        add(btnAll);

        
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(370, 450, 100, 30);
        add(btnBack);
        btnBack.addActionListener(e -> {
            // close this window
            dispose();
            // return to the main menu
            // replace 1 and "Test User" with your real customerId/name if you have them
            MasterGUI dashboard = new MasterGUI(1, "Test User");
            dashboard.setLocationRelativeTo(null);
            dashboard.setVisible(true);
        });

        // Button actions
        btnVegetarian.addActionListener(e -> showVegetarianMenu());
        btnAll.addActionListener(e -> showFullMenu());

        // Show vegetarian menu by default
        showVegetarianMenu();
    }

    private void showVegetarianMenu() {
        String html = generateVegetarianHTML(allMenus);
        textPane.setText(html);
    }

    private void showFullMenu() {
        String html = generateFullMenuHTML(allMenus);
        textPane.setText(html);
    }

    private String generateVegetarianHTML(AllMenus allMenus) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");
        sb.append("<p><font color='blue'><b><span style='font-size: 16pt;'>🥗 VEGETARIAN MENU</span></b></font></p><ul>");

        for (MenuComponent menu : allMenus.menuComponents) {
            if (menu instanceof Menu) {
                sb.append("<li><b>").append(menu.getName()).append("</b><ul>");
                boolean isPancakeMenu = menu instanceof PancakeHouseMenu;
                boolean skipFirstPancakeItem = isPancakeMenu;

                for (MenuComponent item : ((Menu) menu).menuComponents) {
                    if (item instanceof MenuItem && item.isVegetarian()) {
                        // Skip first item in PancakeHouseMenu
                        if (isPancakeMenu && skipFirstPancakeItem) {
                            skipFirstPancakeItem = false;
                            continue;
                        }

                        sb.append("<li><b>")
                          .append(item.getName())
                          .append("</b> - $").append(String.format("%.2f", item.getPrice()))
                          .append("<br>").append(item.getDescription())
                          .append("</li>");
                    }
                }
                sb.append("</ul></li>");
            }
        }

        sb.append("</ul></body></html>");
        return sb.toString();
    }

    private String generateFullMenuHTML(AllMenus allMenus) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");
        sb.append("<p><font color='blue'><b><span style='font-size: 16pt;'>🍽️ FULL MENU</span></b></font></p><ul>");

        for (MenuComponent menu : allMenus.menuComponents) {
            if (menu instanceof Menu) {
                sb.append("<li><b>").append(menu.getName()).append("</b><ul>");
                for (MenuComponent item : ((Menu) menu).menuComponents) {
                    if (item instanceof MenuItem) {
                        sb.append("<li><b>")
                          .append(item.getName())
                          .append("</b> - $").append(String.format("%.2f", item.getPrice()))
                          .append("<br>").append(item.getDescription())
                          .append("</li>");
                    }
                }
                sb.append("</ul></li>");
            }
        }

        sb.append("</ul></body></html>");
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VegetarianMenuGUI().setVisible(true));
    }
}
