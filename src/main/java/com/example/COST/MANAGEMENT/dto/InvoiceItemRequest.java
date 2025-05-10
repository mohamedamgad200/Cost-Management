package com.example.COST.MANAGEMENT.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceItemRequest {
    private Long id;
    @NotBlank(message = "name is required")
    private String name;
    @NotNull(message = "tax is required")
    @Positive(message = "tax should be positive")
    private int quantity;
    @NotNull(message = "tax is required")
    @Positive(message = "tax should be positive")
    private BigDecimal unitPrice;
}
