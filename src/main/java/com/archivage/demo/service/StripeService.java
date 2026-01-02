package com.archivage.demo.service;


import com.archivage.demo.model.Payment;
import com.archivage.demo.model.PaymentIntent;
import com.archivage.demo.repository.PaymentRepository;
import com.stripe.Stripe;
//import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    private final PaymentRepository paymentRepository;

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @Value("${stripe.publishable.key}")
    private String stripePublicKey;

    public void init() {
        // Tu peux initialiser Stripe ici
        com.stripe.Stripe.apiKey = stripeSecretKey;
    }

    public StripeService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

public Map<String, String> createCheckoutSession(String userId, Long amount, String currency)throws Exception{

    Stripe.apiKey= stripeSecretKey;

    SessionCreateParams params= SessionCreateParams.builder()
             .setMode(SessionCreateParams.Mode.PAYMENT)
            .setSuccessUrl("http://localhost:3000/success")
            .setCancelUrl("http://localhost:3000/cancel")

            .addLineItem(
                    SessionCreateParams.LineItem.builder()
                    .setQuantity(1L)
                    .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency(currency)
                            .setUnitAmount(amount) // ex: 5000 = 50.00$
                            .setProductData(
                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                    .setName("Abonnement Archivage Premium")
                                    .build())

                            .build())
                    .build())
            .build();

    Session session= Session.create(params);


    // Sauvegarder un enregistrement du paiement dans MongoDB
    PaymentIntent paymentIntent =new PaymentIntent(
            userId,
            "stripe",
            "pending",
            amount,
            currency,
            session.getId());
    paymentIntent.setCreatedAt(LocalDateTime.now());
paymentRepository.save(paymentIntent);

    Map<String, String> response = new HashMap<>();
    response.put("url", session.getUrl());
    response.put("sessionId", session.getId());
    return response;
}

/**
 * Met à jour le statut du paiement
 */
 public void updatePaymentStatus(String sessionId, String status){
     PaymentIntent paymentIntent= paymentRepository.findAll()
             .stream()
             .filter(p-> sessionId.equals(p.getTransactionId()))
             .findFirst()
             .orElse(null);

     if(paymentIntent != null){
         paymentIntent.setStatus(status);
         paymentRepository.save(paymentIntent);
     }


 }




}
