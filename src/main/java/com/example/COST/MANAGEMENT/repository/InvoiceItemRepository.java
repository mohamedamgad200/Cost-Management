package com.example.COST.MANAGEMENT.repository;

import com.example.COST.MANAGEMENT.model.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
}
