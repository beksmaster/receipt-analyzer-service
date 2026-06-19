package kg.megalab.receiptanalyzerservice.parser;

import java.math.BigDecimal;

public record ParsedProduct(
        String name,
        BigDecimal price
) {
}
