package org.xsoto.springcloud.msvc.hotel_payments_service.payments.service;

import io.github.resilience4j.retry.annotation.Retry;
import org.xsoto.springcloud.msvc.hotel_payments_service.payments.entity.Payment;
import org.xsoto.springcloud.msvc.hotel_payments_service.payments.repository.PaymentRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final PaymentRepository paymentRepository;
    private static int callCount = 0;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

//    @CircuitBreaker(name = "paymentService", fallbackMethod = "processPaymentFallback")
//    @Retry(name = "paymentService", fallbackMethod = "processPaymentFallback")
//    public Payment processPayment(String reservationId, double amount) {
//        logger.info("Processing payment for reservationId: {}, attempt: {}", reservationId, callCount);
//        if (callCount++ < 6) {
//            if (Math.random() > 0.7) {
//            logger.error("Simulating failure for call #{}", callCount);
//            throw new RuntimeException("Payment service failed");
//        }
//        logger.info("Processing payment successfully for reservationId: {}", reservationId);
//        Payment payment = new Payment(UUID.randomUUID().toString(), reservationId, amount, "COMPLETED");
//        return paymentRepository.save(payment);
//    }

    @CircuitBreaker(name = "paymentService", fallbackMethod = "processPaymentFallback")
    @Retry(name = "paymentService", fallbackMethod = "processPaymentFallback")
    public Payment processPayment(String reservationId, double amount) {
        logger.info("Processing payment for reservationId: {}", reservationId);
        if (Math.random() > 0.7) {
            logger.error("Simulating failure");
            throw new RuntimeException("Payment service failed");
        }
        logger.info("Processing payment successfully for reservationId: {}", reservationId);
        Payment payment = new Payment(UUID.randomUUID().toString(), reservationId, amount, "COMPLETED");
        return paymentRepository.save(payment);
    }

    public Payment processPaymentFallback(String reservationId, double amount, Throwable t) {
        logger.warn("Fallback triggered for reservationId: {} due to: {}", reservationId, t.getMessage());
        Payment payment = new Payment(UUID.randomUUID().toString(), reservationId, amount, "PENDING");
        return paymentRepository.save(payment);
    }
}