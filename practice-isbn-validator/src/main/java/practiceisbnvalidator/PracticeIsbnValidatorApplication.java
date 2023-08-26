package practiceisbnvalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticeIsbnValidatorApplication {

	public static void main(String[] args) {
		ISBNValidator isbnValidator = new ISBNValidator();
		SpringApplication.run(PracticeIsbnValidatorApplication.class, args);
	}

}
