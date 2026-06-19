package kg.megalab.receiptanalyzerservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ReceiptResponse(
        Long id,
        String storeName,
        LocalDateTime purchaseDate,
        BigDecimal totalAmount,
        List<ProductResponse> products
) {
}
