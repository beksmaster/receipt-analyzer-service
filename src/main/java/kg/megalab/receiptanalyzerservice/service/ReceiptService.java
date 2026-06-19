package kg.megalab.receiptanalyzerservice.service;

import kg.megalab.receiptanalyzerservice.dto.CreateReceiptRequest;
import kg.megalab.receiptanalyzerservice.dto.ReceiptResponse;

import java.util.List;

public interface ReceiptService {
    Long createReceipt(CreateReceiptRequest request);

    List<ReceiptResponse> getAllReceipts();

    ReceiptResponse getReceiptById(Long id);

    void deleteReceipt(long id);
}
