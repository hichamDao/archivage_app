package com.archivage.demo.controller;


import com.archivage.demo.service.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin("*")
public class PayPalController {

    private final PayPalService payPalService;

    public PayPalController(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody Map<String, Object> body) {
        try {
            String userId = (String) body.get("userId");
            Double amount = Double.parseDouble(body.get("amount").toString());

            String approvalUrl = payPalService.createOrder(userId, amount);
            if (approvalUrl == null)
                return ResponseEntity.internalServerError().body("Erreur lors de la création du paiement.");

            return ResponseEntity.ok(Map.of("url", approvalUrl));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Erreur PayPal : " + e.getMessage());
        }
    }


    @GetMapping("/capture/{orderId}")
    public ResponseEntity<?> capture(@PathVariable String orderId,  @RequestParam String userId) {
        try {
            payPalService.captureOrder(orderId);

            return ResponseEntity.ok("Paiement capturé avec succès !");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}