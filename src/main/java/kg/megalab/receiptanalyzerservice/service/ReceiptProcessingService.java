package kg.megalab.receiptanalyzerservice.service;

import kg.megalab.receiptanalyzerservice.parser.ParsedProduct;

import java.util.List;

public interface ReceiptProcessingService {

    List<ParsedProduct> parseReceipt(Long imageId);
}
