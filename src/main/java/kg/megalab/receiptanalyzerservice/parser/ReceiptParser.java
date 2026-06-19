package kg.megalab.receiptanalyzerservice.parser;

import java.util.List;

public interface ReceiptParser {
    List<ParsedProduct> parse(String text);
}
