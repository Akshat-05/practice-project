package practiceisbnvalidator;

public interface ExternalISBNDataService {
    public Book lookup(String isbn);
}
