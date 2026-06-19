package kg.megalab.receiptanalyzerservice.exception;

public class InvalidFileTypeException extends RuntimeException{
    public InvalidFileTypeException(String message) {
        super(message);
    }
}
