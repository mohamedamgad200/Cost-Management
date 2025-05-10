package com.example.COST.MANAGEMENT.mapper;

import com.example.COST.MANAGEMENT.dto.CostEntryRequest;
import com.example.COST.MANAGEMENT.dto.CostEntryResponse;
import com.example.COST.MANAGEMENT.model.CostEntry;
import org.springframework.stereotype.Service;

@Service
public class CostEntryMapper {

    public CostEntry toCostEntry(CostEntryRequest costEntryRequest) {
        return CostEntry.builder()
                .amount(costEntryRequest.getAmount())
                .description(costEntryRequest.getDescription())
                .category(costEntryRequest.getCategory())
                .build();
    }

    public CostEntryResponse toCostEntryResponse(CostEntry costEntry) {
        return CostEntryResponse.builder()
                .id(costEntry.getId())
                .description(costEntry.getDescription())
                .category(costEntry.getCategory())
                .amount(costEntry.getAmount())
                .date(costEntry.getDate())
                .build();
    }
}
