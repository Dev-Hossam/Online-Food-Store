package test1;

public class CustomValidator {
    public static boolean validateMenuName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() <= 100;
    }

    public static boolean validateDescription(String description) {
        return description != null && !description.trim().isEmpty() && description.length() <= 255;
    }

    public static boolean validatePrice(double price) {
        return price > 0 && price < 10000;  // Realistic price bounds
    }
}
