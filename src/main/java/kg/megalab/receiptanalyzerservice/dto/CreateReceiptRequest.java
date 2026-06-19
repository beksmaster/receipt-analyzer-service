package kg.megalab.receiptanalyzerservice.dto;

import java.math.BigDecimal;
import java.util.List;

public record CreateReceiptRequest(
        String storeName,
        BigDecimal totalAmount,
        List<CreateProductRequest> products
) {
}
