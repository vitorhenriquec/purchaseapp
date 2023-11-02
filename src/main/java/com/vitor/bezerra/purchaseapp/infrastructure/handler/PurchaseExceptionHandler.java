package com.vitor.bezerra.purchaseapp.infrastructure.handler;

import com.vitor.bezerra.purchaseapp.domain.exception.InvalidPurchaseValueException;
import com.vitor.bezerra.purchaseapp.domain.exception.PurchaseNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PurchaseExceptionHandler {

    @ExceptionHandler(InvalidPurchaseValueException.class)
    public ResponseEntity<?> handleInvalidPurchaseValueException() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(PurchaseNotFoundException.class)
    public ResponseEntity<?> handlePurchaseNotFoundException() {
        return ResponseEntity.notFound().build();
    }
}
