package kg.megalab.receiptanalyzerservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank(message = "Product name cannot be empty")
        String name,

        @Positive(message = "Price must be greater than 0")
        BigDecimal price,

        @Positive(message = "Quantity must be greater than 0")
        Integer quantity
) {
}
