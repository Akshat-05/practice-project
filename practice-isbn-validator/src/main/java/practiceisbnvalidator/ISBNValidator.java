package practiceisbnvalidator;

public class ISBNValidator {

    // ISBN 10 digit -> multiply 1st by 10 2nd by 9 .. 10th by 1
    // add all and mod by 11 result should equal last digit
    // last char of isbn can be char -> 012000030X and X means 10
    // for 13 digit isbn -> multiply alternate digit by 1 and 3
    // them divide by 10
    public boolean checkISBN(String isbn) {
        if (isbn.length() == 10) {
            return isbn10Digits(isbn);
        } else if (isbn.length() == 13) {
            return isbn13Digite(isbn);
        } else {
            throw new NumberFormatException("ISBN must be 10 or 13 digit long");
        }
    }

    private boolean isbn10Digits(String isbn) {
        int sum = 0;
        int digit;
        for (int i = 10; i > 0; i--) {
            //sum += Integer.parseInt(String.valueOf(isbn.charAt(10 - i))) * i;
            if (i == 1 && isbn.charAt(10 - i) == 'X') {
                sum += 10;
            } else {
                digit = Character.getNumericValue(isbn.charAt(10 - i));
                if (digit > 9) {
                    throw new NumberFormatException();
                }
                sum += digit * i;
            }
        }
        return (sum % 11) == 0;
    }

    private static boolean isbn13Digite(String isbn) {
        int sum = 0;
        int digit;
        for (int i = 0; i < 13; i++) {
            digit = Character.getNumericValue(isbn.charAt(i));
            if (digit > 9) {
                throw new NumberFormatException();
            }
            if ((i & 1) == 0) {
                sum += digit;
            } else {
                sum += digit * 3;
            }
        }
        return (sum % 10) == 0;
    }

}
