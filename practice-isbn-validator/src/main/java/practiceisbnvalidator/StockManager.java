package practiceisbnvalidator;

public class StockManager {

    private ExternalISBNDataService webService;
    private ExternalISBNDataService dataBaseService;

    public StockManager(ExternalISBNDataService webService, ExternalISBNDataService dataBaseService) {
        this.webService = webService;
        this.dataBaseService = dataBaseService;
    }

    public String getLocatorCode(String isbn) {
        Book book = dataBaseService.lookup(isbn);
        if(book == null)
            book = webService.lookup(isbn);
        StringBuilder sb = new StringBuilder();
        sb.append(isbn.substring(isbn.length() - 4)); // last 4 digit of isbn
        sb.append(book.getAuthor().substring(0, 1)); // 1st digit of surname of author
        sb.append(book.getTitle().split(" ").length);// number of word in book;
        return sb.toString();
    }

}
