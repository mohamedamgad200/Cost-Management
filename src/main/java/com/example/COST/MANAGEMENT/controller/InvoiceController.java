package com.example.COST.MANAGEMENT.controller;
import com.example.COST.MANAGEMENT.dto.InvoiceRequest;
import com.example.COST.MANAGEMENT.dto.InvoiceResponse;
import com.example.COST.MANAGEMENT.model.Invoice;
import com.example.COST.MANAGEMENT.service.InvoiceReminderService;
import com.example.COST.MANAGEMENT.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final InvoiceReminderService invoiceReminderService;
    @PostMapping
    public ResponseEntity<InvoiceResponse> createInvoice(@Valid @RequestBody InvoiceRequest invoiceRequest) {
        return new ResponseEntity<>(invoiceService.createInvoice(invoiceRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceResponse> createInvoice(@PathVariable Long id,@Valid @RequestBody InvoiceRequest invoiceRequest) {
        return new ResponseEntity<>(invoiceService.updateInvoice(id,invoiceRequest), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<InvoiceResponse>> getAllInvoice() {
        return new ResponseEntity<>(invoiceService.getAllInvoices(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponse> getInvoiceById(@PathVariable Long id) {
        return new ResponseEntity<>(invoiceService.getInvoiceById(id), HttpStatus.OK);
    }

}
