package org.xsoto.springcloud.msvc.hotel_payments_service.payments.controller;

import org.xsoto.springcloud.msvc.hotel_payments_service.payments.entity.Payment;
import org.xsoto.springcloud.msvc.hotel_payments_service.payments.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payments/process")
    public Payment processPayment(@RequestBody PaymentRequest request) {
        return paymentService.processPayment(request.getReservationId(), request.getAmount());
    }

    static class PaymentRequest {
        private String reservationId;
        private double amount;

        public String getReservationId() { return reservationId; }
        public void setReservationId(String reservationId) { this.reservationId = reservationId; }
        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }
    }
}