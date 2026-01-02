package com.archivage.demo.controller;


import com.archivage.demo.model.PaymentIntent;
import com.archivage.demo.model.PaymentRequest;
import com.archivage.demo.service.PaymentService;
import com.archivage.demo.service.StripeService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    private final StripeService stripeService;
    private final PaymentService paymentService;

    public PaymentController(StripeService stripeService, PaymentService paymentService) {
        this.stripeService = stripeService;
        this.paymentService = paymentService;
    }


    @PostMapping("/create-checkout-session")
    public Map<String, String> createCheckout(@RequestBody PaymentRequest request) throws Exception{
        return stripeService.createCheckoutSession(request.getUserId(), request.getAmount(), request.getCurrency());
    }

    @PostMapping("/update-status")
    public void updateStatus(@RequestParam String sessionId, @RequestParam String status){
    stripeService.updatePaymentStatus(sessionId, status);
    }








}
