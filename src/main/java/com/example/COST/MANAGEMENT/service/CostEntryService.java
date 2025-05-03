package com.example.COST.MANAGEMENT.service;

import com.example.COST.MANAGEMENT.dto.CostEntryRequest;
import com.example.COST.MANAGEMENT.dto.CostEntryResponse;
import com.example.COST.MANAGEMENT.model.CostEntry;
import com.example.COST.MANAGEMENT.repository.CostEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CostEntryService {
    private final CostEntryRepository costEntryRepository;
    public CostEntryResponse createCostEntry(CostEntryRequest costEntryRequest) {
        CostEntry costEntry=CostEntry.builder()
                .amount(costEntryRequest.getAmount())
                .description(costEntryRequest.getDescription())
                .category(costEntryRequest.getCategory())
                .build();
        CostEntry savedCostEntry=costEntryRepository.save(costEntry);
        CostEntryResponse costEntryResponse=CostEntryResponse.builder()
                .id(savedCostEntry.getId())
                .description(savedCostEntry.getDescription())
                .category(savedCostEntry.getCategory())
                .amount(savedCostEntry.getAmount())
                .date(savedCostEntry.getDate())
                .build();
        return costEntryResponse;
    }
    public CostEntryResponse getCostEntryById(Long id) {
        CostEntry costEntry=costEntryRepository.findById(id).orElse(null);
        CostEntryResponse costEntryResponse=CostEntryResponse.builder()
                .id(costEntry.getId())
                .description(costEntry.getDescription())
                .category(costEntry.getCategory())
                .amount(costEntry.getAmount())
                .date(costEntry.getDate())
                .build();
        return costEntryResponse;
    }
    public List<CostEntryResponse> getAllCostEntries() {
        List<CostEntry>costEntries=costEntryRepository.findAll();
        List<CostEntryResponse>costEntriesResponse=costEntries.stream().map(costEntry->{
            CostEntryResponse costEntryResponse=CostEntryResponse.builder()
                    .id(costEntry.getId())
                    .description(costEntry.getDescription())
                    .category(costEntry.getCategory())
                    .amount(costEntry.getAmount())
                    .date(costEntry.getDate())
                    .build();
            return costEntryResponse;
        }).toList();
        return costEntriesResponse;
    }
}
