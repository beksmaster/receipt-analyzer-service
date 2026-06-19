package kg.megalab.receiptanalyzerservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record CreateReceiptRequest(
        @NotBlank(message = "Store name cannot be empty")
        String storeName,

        @Positive(message = "Total amount must be greater than 0")
        BigDecimal totalAmount,

        @NotEmpty(message = "Receipt must contain at least one product")
        List<@Valid CreateProductRequest> products
) {
}
