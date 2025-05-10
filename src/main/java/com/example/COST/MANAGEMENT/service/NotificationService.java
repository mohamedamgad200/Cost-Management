package com.example.COST.MANAGEMENT.service;

import com.example.COST.MANAGEMENT.model.Invoice;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final JavaMailSender emailSender;

    @Async
    public void sendDueReminder(Invoice invoice) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(invoice.getClient().getEmail());
            helper.setSubject("Invoice Due Reminder - Invoice #" + invoice.getInvoiceNumber());
            String body = "<html><body style='font-family: Arial, sans-serif;'>" +
                    "<h2 style='color: #4CAF50;'>Dear " + invoice.getClient().getName() + ",</h2>" +
                    "<p>This is a friendly reminder that your invoice with number <strong>" + invoice.getInvoiceNumber() + "</strong> is due soon.</p>" +
                    "<h3>Invoice Details:</h3>" +
                    "<table style='border-collapse: collapse; width: 100%;'>"+
                    "<tr><td><strong>Invoice Amount:</strong></td><td>" + invoice.getTotal() + "</td></tr>" +
                    "<tr><td><strong>Due Date:</strong></td><td>" + invoice.getDueDate() + "</td></tr>" +
                    "</table>" +
                    "<p>Please make the payment before the due date to avoid any late fees.</p>" +
                    "<p>Thank you for your prompt attention to this matter.</p>" +
                    "<p>Best regards,<br>Your Company Name</p>" +
                    "</body></html>";
            helper.setText(body, true);
            log.info("📨 Sending email to {}", invoice.getClient().getEmail());
            emailSender.send(message);
            log.info("✅ Email sent successfully to {}", invoice.getClient().getEmail());
        } catch (Exception ex) {
            log.error("❌ Failed to send email to {}", invoice.getClient().getEmail(), ex);
            throw new RuntimeException(ex);
        }
    }
}
