package com.archivage.demo.service;


import com.archivage.demo.model.Payment;
import com.archivage.demo.model.PaymentIntent;
import com.archivage.demo.repository.PaymentRepository;
import com.archivage.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service

public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    public PaymentService(PaymentRepository paymentRepository, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }


    public PaymentIntent savePayment(PaymentIntent paymentIntent)

    {
        PaymentIntent savedPayment = paymentRepository.save(paymentIntent);

        if ("succeeded".equalsIgnoreCase(paymentIntent.getStatus())) {
            userRepository.findById(paymentIntent.getUserId()).ifPresent(user -> {
                user.setSubscriptionEnd(LocalDateTime.now().plusMonths(1));
                userRepository.save(user);
            });

        }
        return savedPayment;
    }

    }

