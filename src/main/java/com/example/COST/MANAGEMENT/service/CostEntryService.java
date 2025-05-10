package com.example.COST.MANAGEMENT.service;

import com.example.COST.MANAGEMENT.dto.CostEntryRequest;
import com.example.COST.MANAGEMENT.dto.CostEntryResponse;
import com.example.COST.MANAGEMENT.dto.CustomResponse;
import com.example.COST.MANAGEMENT.exception.NotFoundException;
import com.example.COST.MANAGEMENT.mapper.CostEntryMapper;
import com.example.COST.MANAGEMENT.model.CostEntry;
import com.example.COST.MANAGEMENT.repository.CostEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CostEntryService {
    private final CostEntryRepository costEntryRepository;
    private final CostEntryMapper costEntryMapper;
    public CostEntryResponse createCostEntry(CostEntryRequest costEntryRequest) {
        CostEntry costEntry=costEntryMapper.toCostEntry(costEntryRequest);
        CostEntry savedCostEntry=costEntryRepository.save(costEntry);
        CostEntryResponse costEntryResponse=costEntryMapper.toCostEntryResponse(savedCostEntry);
        return costEntryResponse;
    }

    public CostEntryResponse getCostEntryById(Long id) {
        CostEntry costEntry=costEntryRepository.findById(id).orElseThrow(()->new NotFoundException(String.format(
                "CostEntry with id %s not found", id
        )));
        CostEntryResponse costEntryResponse=costEntryMapper.toCostEntryResponse(costEntry);
        return costEntryResponse;
    }
    public List<CostEntryResponse> getAllCostEntries() {
        List<CostEntry>costEntries=costEntryRepository.findAll();
        List<CostEntryResponse>costEntriesResponse=costEntries.stream().map(costEntry->{
            CostEntryResponse costEntryResponse=costEntryMapper.toCostEntryResponse(costEntry);
            return costEntryResponse;
        }).toList();
        return costEntriesResponse;
    }
    public CustomResponse deleteCostEntry(Long id) {
        costEntryRepository.findById(id).orElseThrow(()->new NotFoundException(String.format("CostEntry with id %s not found", id)));
        costEntryRepository.deleteById(id);
        return new CustomResponse("Cost Entry deleted successfully", LocalDateTime.now());
    }
}
