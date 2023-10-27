package com.vitor.bezerra.purchaseapp.infrastructure.adapter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchase", schema = "public")
public class PurchaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_gen")
    @SequenceGenerator(name="purchase_gen", sequenceName = "sq_purchase_idt", allocationSize = 1)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
}
