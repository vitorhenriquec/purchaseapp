package com.vitor.bezerra.purchaseapp.infrastructure.adapter.repository;

import com.vitor.bezerra.purchaseapp.infrastructure.adapter.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
}
