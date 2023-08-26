package practiceisbnvalidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class StockManagementTest {


    ExternalISBNDataService dataService;
    ExternalISBNDataService webService;
    StockManager stockManager;

    // run before every test run
    @BeforeEach
    public void setup(){
        // Example of mock  implementation
        dataService = mock(ExternalISBNDataService.class);
        webService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager(webService, dataService);
    }

    @Test
    public void testGetCorrectLocatorCode() {
        // Example of Stub  implementation
        ExternalISBNDataService webService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book("isbn", "of mice and men", "J. Steinbeck");
            }
        };

        ExternalISBNDataService dataService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return null;
            }
        };
        String isbn = "0140177396";
        StockManager stockManager = new StockManager(webService, dataService);
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void testGetCorrectLocatorCodeAnotherWay() {

        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);
                when(webService.lookup(anyString())).
                        thenReturn(new Book("isbn", "of mice and men", "J. Steinbeck"));
        // fake -> this will just get ignore
        ExternalISBNDataService dataService = mock(ExternalISBNDataService.class);
        String isbn = "0140177396";
        StockManager stockManager = new StockManager(webService, dataService);
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void testDataFromDataBase() {
        // Example of mock  implementation
        when(dataService.lookup("0140177396"))
                .thenReturn(new Book("isbn", "of mice and men", "J. Steinbeck"));

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        // this verifies how many times a method is called
        verify(dataService, times(1)).lookup("0140177396");
        verify(webService, never()).lookup(anyString());

        // this verifies result
        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void testDataFromWeb() {

        when(dataService.lookup(anyString()))
                .thenReturn(null);

        when(webService.lookup("0140177396"))
                .thenReturn(new Book("isbn", "of mice and men", "J. Steinbeck"));

        String isbn = "0140177396";
        StockManager stockManager = new StockManager(webService, dataService);
        String locatorCode = stockManager.getLocatorCode(isbn);

        // this verifies how many times a method is called
        verify(dataService, times(1)).lookup(any(String.class));
        verify(webService, times(1)).lookup("0140177396");

        // this verifies result
        assertEquals("7396J4", locatorCode);
    }

}
