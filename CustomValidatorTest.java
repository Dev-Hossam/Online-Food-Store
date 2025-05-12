package Junit_testing;

import org.junit.*;
import static org.junit.Assert.*;
import test1.CustomValidator;

public class CustomValidatorTest {

    @Test
    public void testValidateMenuName_valid() {
        // non-null, non-blank, within length
        assertTrue(CustomValidator.validateMenuName("Breakfast Specials"));
        // edge of max length (100)
        String longName = new String(new char[100]).replace('\0', 'A');
        assertTrue(CustomValidator.validateMenuName(longName));
    }

    @Test
    public void testValidateMenuName_invalid() {
        assertFalse(CustomValidator.validateMenuName(null));
        assertFalse(CustomValidator.validateMenuName(""));
        assertFalse(CustomValidator.validateMenuName("   "));
        // just over max length
        String tooLong = new String(new char[101]).replace('\0', 'A');
        assertFalse(CustomValidator.validateMenuName(tooLong));
    }

    @Test
    public void testValidateDescription_valid() {
        assertTrue(CustomValidator.validateDescription("Delicious pancakes with syrup."));
        // edge of max length (255)
        String longDesc = new String(new char[255]).replace('\0', 'B');
        assertTrue(CustomValidator.validateDescription(longDesc));
    }

    @Test
    public void testValidateDescription_invalid() {
        assertFalse(CustomValidator.validateDescription(null));
        assertFalse(CustomValidator.validateDescription(""));
        assertFalse(CustomValidator.validateDescription("   "));
        // just over max length
        String tooLongDesc = new String(new char[256]).replace('\0', 'B');
        assertFalse(CustomValidator.validateDescription(tooLongDesc));
    }

    @Test
    public void testValidatePrice_valid() {
        assertTrue(CustomValidator.validatePrice(0.01));
        assertTrue(CustomValidator.validatePrice(9999.99));
    }

    @Test
    public void testValidatePrice_invalid() {
        assertFalse(CustomValidator.validatePrice(0.0));
        assertFalse(CustomValidator.validatePrice(-5.0));
        assertFalse(CustomValidator.validatePrice(10000.0));
    }
}
