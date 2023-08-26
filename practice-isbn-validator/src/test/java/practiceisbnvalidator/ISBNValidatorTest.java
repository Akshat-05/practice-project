package practiceisbnvalidator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


// ISBN are 10 or 13 digits long
// valid ISBN -> 0140449116, 0140177396, 9781853260087
// last char of isbn can be char -> 012000030X
public class ISBNValidatorTest {

    @Test
    public void checkValidISBN() {
        ISBNValidator validator = new ISBNValidator();
        boolean result = validator.checkISBN("0140449116");
        assertTrue(result);
        //this way can also be written i.e. with message
        //assertTrue("First value", result);
        result = validator.checkISBN("0140177396");
        assertTrue(result);
        result = validator.checkISBN("012000030X");
        assertTrue(result);
        result = validator.checkISBN("9781853260087");
        assertTrue(result);
    }

    @Test
    public void checkInValidISBN() {
        ISBNValidator validator = new ISBNValidator();
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }

    //@Test(expected = NumberFormatException.class) this is present in JUnit4
    @Test
    public void checkInValidISBN9Digit() {
        ISBNValidator validator = new ISBNValidator();
        assertThrows(NumberFormatException.class, () -> validator.checkISBN("123456789")); // JUnit 5
    }

    @Test
    public void checkInValidISBNNonNumeric() {
        ISBNValidator validator = new ISBNValidator();
        assertThrows(NumberFormatException.class, () -> validator.checkISBN("HelloWorld")); // JUnit 5
        assertThrows(NumberFormatException.class, () -> validator.checkISBN("HelloWorld123"));
    }

}
