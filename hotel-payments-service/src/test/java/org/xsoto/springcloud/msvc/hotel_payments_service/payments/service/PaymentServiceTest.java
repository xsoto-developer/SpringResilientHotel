//package org.xsoto.springcloud.msvc.hotel_payments_service.payments.service;

package org.xsoto.springcloud.msvc.hotel_payments_service.payments.service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.xsoto.springcloud.msvc.hotel_payments_service.payments.entity.Payment;
import org.xsoto.springcloud.msvc.hotel_payments_service.payments.repository.PaymentRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @Autowired
    private RetryRegistry retryRegistry;

    @InjectMocks
    private PaymentService paymentService;

    private CircuitBreaker circuitBreaker;
    private Retry retry;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        circuitBreaker = circuitBreakerRegistry.circuitBreaker("paymentService");
        retry = retryRegistry.retry("paymentService");
        circuitBreaker.transitionToClosedState(); // Reset CircuitBreaker to CLOSED
    }

    @Test
    void testProcessPayment_Success() {
        // Arrange
        String reservationId = "res1";
        double amount = 150.0;
        Payment payment = new Payment(UUID.randomUUID().toString(), reservationId, amount, "COMPLETED");
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        // Act (Override random failure for success)
        Payment result = retry.executeSupplier(() -> {
            circuitBreaker.decorateSupplier(() -> paymentService.processPayment(reservationId, amount));
            return paymentService.processPayment(reservationId, amount);
        });

        // Assert
        assertEquals("COMPLETED", result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testProcessPayment_FallbackTriggered() {
        // Arrange
        String reservationId = "res1";
        double amount = 150.0;
        Payment payment = new Payment(UUID.randomUUID().toString(), reservationId, amount, "PENDING");

        // Simulate failure by forcing CircuitBreaker to OPEN
        circuitBreaker.transitionToOpenState();
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        // Act
        Payment result = paymentService.processPayment(reservationId, amount);

        // Assert
        assertEquals("PENDING", result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testProcessPayment_RetryAttempts() {
        // Arrange
        String reservationId = "res1";
        double amount = 150.0;
        Payment payment = new Payment(UUID.randomUUID().toString(), reservationId, amount, "PENDING");

        // Simulate failure by throwing exception multiple times
        PaymentService spyService = spy(paymentService);
        doThrow(new RuntimeException("Payment service failed"))
                .doThrow(new RuntimeException("Payment service failed"))
                //.thenReturn(payment)
                .doReturn(payment)
                .when(spyService).processPayment(reservationId, amount);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        // Act
        Payment result = retry.executeSupplier(() -> spyService.processPayment(reservationId, amount));

        // Assert
        assertEquals("PENDING", result.getStatus());
        verify(spyService, times(3)).processPayment(reservationId, amount); // 3 attempts due to maxAttempts=3
    }
}
