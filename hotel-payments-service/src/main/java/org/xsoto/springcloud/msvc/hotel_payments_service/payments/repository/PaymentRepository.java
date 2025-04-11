package org.xsoto.springcloud.msvc.hotel_payments_service.payments.repository;

import org.xsoto.springcloud.msvc.hotel_payments_service.payments.entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {}