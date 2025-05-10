package com.example.COST.MANAGEMENT.mapper;

import com.example.COST.MANAGEMENT.dto.ClientResponse;
import com.example.COST.MANAGEMENT.dto.InvoiceItemResponse;
import com.example.COST.MANAGEMENT.dto.InvoiceResponse;
import com.example.COST.MANAGEMENT.model.Invoice;
import com.example.COST.MANAGEMENT.model.InvoiceItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceMapper {
    private final ClientMapper clientMapper;

    public InvoiceResponse toInvoiceResponse(Invoice invoice) {
        ClientResponse clientResponse = clientMapper.toClientResponse(invoice.getClient());

        List<InvoiceItemResponse> invoiceItemResponses = invoice.getItems().stream()
                .map(InvoiceMapper::toInvoiceItemResponse)
                .collect(Collectors.toList());

        return InvoiceResponse.builder()
                .id(invoice.getId())
                .invoiceNumber(invoice.getInvoiceNumber())
                .issueDate(invoice.getIssueDate())
                .dueDate(invoice.getDueDate())
                .subtotal(invoice.getSubtotal())
                .tax(invoice.getTax())
                .discount(invoice.getDiscount())
                .total(invoice.getTotal())
                .client(clientResponse)
                .items(invoiceItemResponses)
                .build();
    }

    public static InvoiceItemResponse toInvoiceItemResponse(InvoiceItem item) {
        return InvoiceItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .totalPrice(item.getTotalPrice())
                .build();
    }
}
