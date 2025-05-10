package com.example.COST.MANAGEMENT.repository;

import com.example.COST.MANAGEMENT.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
