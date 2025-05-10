package com.example.COST.MANAGEMENT.service;

import com.example.COST.MANAGEMENT.model.Invoice;
import com.example.COST.MANAGEMENT.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceReminderService {
    private final InvoiceRepository invoiceRepository;
    private final NotificationService notificationService;

    @Scheduled(cron = "0 30 17 * * ?")
    public void sendInvoiceDueReminders() {
        LocalDate currentDate = LocalDate.now();
        List<Invoice> invoices = invoiceRepository.findByDueDateBetween(currentDate, currentDate.plusDays(5));
        for (Invoice invoice : invoices) {
            notificationService.sendDueReminder(invoice);
        }
    }
}
