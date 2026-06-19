package kg.megalab.receiptanalyzerservice.exception;

public class ReceiptNotFoundException
        extends RuntimeException {

    public ReceiptNotFoundException(Long id) {
        super("Receipt with id " + id + " not found");
    }
}