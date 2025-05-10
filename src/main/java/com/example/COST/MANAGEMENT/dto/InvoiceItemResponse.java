package com.example.COST.MANAGEMENT.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceItemResponse {
    private Long id;
    private String name;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
