package com.example.COST.MANAGEMENT.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CostEntryRequest {
    @NotBlank(message = "Category should not be empty")
    private String category;
    @NotNull(message = "Amount should not be empty")
    @Positive(message = "Amount should be positive")
    private BigDecimal amount;
    @NotBlank(message = "Description should not be empty")
    @Size(min=20,message = "Description should be grater than 20 character")
    private String description;
}
