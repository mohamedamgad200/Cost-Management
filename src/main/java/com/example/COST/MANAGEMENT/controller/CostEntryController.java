package com.example.COST.MANAGEMENT.controller;

import com.example.COST.MANAGEMENT.dto.CostEntryRequest;
import com.example.COST.MANAGEMENT.dto.CostEntryResponse;
import com.example.COST.MANAGEMENT.service.CostEntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/costentry")
@RequiredArgsConstructor
public class CostEntryController {
    private final CostEntryService costEntryService;

    @PostMapping
    public ResponseEntity<CostEntryResponse>createCostEntry(@Valid @RequestBody CostEntryRequest costEntryRequest) {
        CostEntryResponse costEntryResponse = costEntryService.createCostEntry(costEntryRequest);
        return new ResponseEntity<>(costEntryResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostEntryResponse>getCostEntryById(@PathVariable Long id){
        CostEntryResponse costEntryResponse = costEntryService.getCostEntryById(id);
        return new ResponseEntity<>(costEntryResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CostEntryResponse>>getAllCostEntry()
    {
        List<CostEntryResponse>costEntryResponses=costEntryService.getAllCostEntries();
        return new ResponseEntity<>(costEntryResponses, HttpStatus.OK);
    }
}
