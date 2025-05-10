package com.example.COST.MANAGEMENT.service;

import com.example.COST.MANAGEMENT.dto.InvoiceItemRequest;
import com.example.COST.MANAGEMENT.dto.InvoiceRequest;
import com.example.COST.MANAGEMENT.dto.InvoiceResponse;
import com.example.COST.MANAGEMENT.exception.NotFoundException;
import com.example.COST.MANAGEMENT.mapper.InvoiceMapper;
import com.example.COST.MANAGEMENT.model.Client;
import com.example.COST.MANAGEMENT.model.Invoice;
import com.example.COST.MANAGEMENT.model.InvoiceItem;
import com.example.COST.MANAGEMENT.repository.ClientRepository;
import com.example.COST.MANAGEMENT.repository.InvoiceItemRepository;
import com.example.COST.MANAGEMENT.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ClientRepository clientRepository;
    private final InvoiceItemRepository invoiceItemRepository;
    private final InvoiceMapper invoiceMapper;
    public InvoiceResponse createInvoice(InvoiceRequest invoiceRequest) {
        Client client=clientRepository.findById(invoiceRequest.getClientId()).orElseThrow(
                ()->new NotFoundException("Client not found"+invoiceRequest.getClientId()));
        List<InvoiceItem> items = new ArrayList<>();
        BigDecimal subtotal = BigDecimal.ZERO;

        for (InvoiceItemRequest itemRequest : invoiceRequest.getItems()) {
            InvoiceItem item = new InvoiceItem();
            item.setName(itemRequest.getName());
            item.setQuantity(itemRequest.getQuantity());
            item.setUnitPrice(itemRequest.getUnitPrice());
            item.setTotalPrice(itemRequest.getUnitPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));

            subtotal = subtotal.add(item.getTotalPrice());
            items.add(item);
        }
        BigDecimal taxAmount = subtotal.multiply(invoiceRequest.getTax().divide(BigDecimal.valueOf(100)));
        BigDecimal discountAmount = subtotal.multiply(invoiceRequest.getDiscount().divide(BigDecimal.valueOf(100)));
        BigDecimal total = subtotal.add(taxAmount).subtract(discountAmount);
        Invoice invoice = Invoice.builder()
                .invoiceNumber(generateInvoiceNumber())
                .issueDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(5))
                .subtotal(subtotal)
                .tax(taxAmount)
                .discount(discountAmount)
                .total(total)
                .client(client)
                .items(items)
                .build();
        invoiceRepository.save(invoice);
        for (InvoiceItem item : items) {
            item.setInvoice(invoice);
            invoiceItemRepository.save(item);
        }
        return invoiceMapper.toInvoiceResponse(invoice);
    }
    public InvoiceResponse updateInvoice(Long invoiceId, InvoiceRequest invoiceRequest) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new NotFoundException("Invoice not found with id: " + invoiceId));

        Client client = clientRepository.findById(invoiceRequest.getClientId())
                .orElseThrow(() -> new NotFoundException("Client not found: " + invoiceRequest.getClientId()));

        Map<Long, InvoiceItem> existingItemsMap = invoice.getItems().stream()
                .collect(Collectors.toMap(InvoiceItem::getId, item -> item));

        List<InvoiceItem> finalItems = new ArrayList<>();
        BigDecimal subtotal = BigDecimal.ZERO;

        for (InvoiceItemRequest itemRequest : invoiceRequest.getItems()) {
            InvoiceItem item;

            if (itemRequest.getId() != null && existingItemsMap.containsKey(itemRequest.getId())) {
                item = existingItemsMap.get(itemRequest.getId());
                item.setName(itemRequest.getName());
                item.setQuantity(itemRequest.getQuantity());
                item.setUnitPrice(itemRequest.getUnitPrice());
                item.setTotalPrice(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));

                existingItemsMap.remove(itemRequest.getId());
            } else {
                item = InvoiceItem.builder()
                        .name(itemRequest.getName())
                        .quantity(itemRequest.getQuantity())
                        .unitPrice(itemRequest.getUnitPrice())
                        .totalPrice(itemRequest.getUnitPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())))
                        .invoice(invoice)
                        .build();
            }

            subtotal = subtotal.add(item.getTotalPrice());
            finalItems.add(item);
        }
        for (InvoiceItem toDelete : existingItemsMap.values()) {
            invoiceItemRepository.delete(toDelete);
        }

        BigDecimal taxAmount = subtotal.multiply(invoiceRequest.getTax().divide(BigDecimal.valueOf(100)));
        BigDecimal discountAmount = subtotal.multiply(invoiceRequest.getDiscount().divide(BigDecimal.valueOf(100)));
        BigDecimal total = subtotal.add(taxAmount).subtract(discountAmount);
        invoice.setClient(client);
        invoice.setItems(finalItems);
        invoice.setSubtotal(subtotal);
        invoice.setTax(taxAmount);
        invoice.setDiscount(discountAmount);
        invoice.setTotal(total);

        invoiceRepository.save(invoice);
        invoiceItemRepository.saveAll(finalItems);

        return invoiceMapper.toInvoiceResponse(invoice);
    }

    public List<InvoiceResponse>getAllInvoices() {
        return invoiceRepository.findAll().stream().map(invoiceMapper::toInvoiceResponse).collect(Collectors.toList());
    }
    public InvoiceResponse getInvoiceById(Long id) {
        return invoiceMapper.toInvoiceResponse(invoiceRepository.findById(id).orElseThrow(()->new NotFoundException("Invoice Not Found")));
    }
    private String generateInvoiceNumber() {
        return "INV-" + UUID.randomUUID().toString().substring(0, 8);
    }
}
