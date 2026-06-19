package kg.megalab.receiptanalyzerservice.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        BigDecimal amount,
        Integer quantity
) {
}
