package com.example.COST.MANAGEMENT.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CostEntryResponse {
    private Long id;
    private String category;
    private BigDecimal amount;
    private String description;
    private LocalDateTime date;
}
