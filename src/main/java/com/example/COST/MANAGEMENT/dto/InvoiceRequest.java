package com.example.COST.MANAGEMENT.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceRequest {
    @NotNull(message = "Client Id required")
    private Long clientId;
    private List<InvoiceItemRequest> items;
    @NotNull(message = "tax is required")
    @Positive(message = "tax should be positive")
    private BigDecimal tax;
    @NotNull(message = "tax is required")
    @Positive(message = "tax should be positive")
    private BigDecimal discount;
}
