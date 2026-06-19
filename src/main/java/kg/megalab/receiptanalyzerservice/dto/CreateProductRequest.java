package kg.megalab.receiptanalyzerservice.dto;

import java.math.BigDecimal;

public record CreateProductRequest(
        String name,
        BigDecimal price,
        Integer quantity
) {
}
