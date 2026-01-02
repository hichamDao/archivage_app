package com.archivage.demo.repository;


import com.archivage.demo.model.Payment;
import com.archivage.demo.model.PaymentIntent;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentRepository extends MongoRepository<PaymentIntent, String> {
    List<PaymentIntent> findByUserId(String userId);
    PaymentIntent findByTransactionId(String transactionId);

}
