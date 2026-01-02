package com.archivage.demo.service;

import com.archivage.demo.model.PaymentIntent;
import com.archivage.demo.repository.PaymentRepository;
import com.archivage.demo.repository.UserRepository;
import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.orders.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PayPalService {

    private final PayPalHttpClient client;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    // 🧩 Ces valeurs viennent de application.properties
    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    // 🧠 Constructeur
    public PayPalService(
            @Value("${paypal.client.id}") String clientId,
            @Value("${paypal.client.secret}") String clientSecret,
            @Value("${paypal.mode}") String mode, PaymentRepository paymentRepository,
            UserRepository userRepository
    ) {
        PayPalEnvironment environment;

        // Choisir entre Sandbox et Live automatiquement
        if ("live".equalsIgnoreCase(mode)) {
            environment = new PayPalEnvironment.Live(clientId, clientSecret);
        } else {
            environment = new PayPalEnvironment.Sandbox(clientId, clientSecret);
        }

        this.client = new PayPalHttpClient(environment);
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;

    }

    // 💳 Créer une commande PayPal
    public String createOrder(String userId, Double amount) throws IOException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        ApplicationContext appContext = new ApplicationContext()
                .brandName("Archivage Documents")
                .landingPage("LOGIN")
                .cancelUrl("http://localhost:3000/cancel")
                .returnUrl("http://localhost:3000/success")
                .userAction("PAY_NOW");

        orderRequest.applicationContext(appContext);

        List<PurchaseUnitRequest> purchaseUnitRequests = List.of(
                new PurchaseUnitRequest()
                        .description("Abonnement Premium Archivage")
                        .amountWithBreakdown(new AmountWithBreakdown()
                                .currencyCode("EUR")
                                .value(String.format("%.2f", amount).replace(",", ".")))

                        );
        orderRequest.purchaseUnits(purchaseUnitRequests);

        OrdersCreateRequest request = new OrdersCreateRequest();
        request.requestBody(orderRequest);
        Order order = client.execute(request).result();
        PaymentIntent payment = new PaymentIntent(
                userId,
                "paypal",
                "CREATED",
                Math.round(amount * 100), // en cents
                "EUR",
                order.id()
        );
        payment.setCreatedAt(LocalDateTime.now());
        paymentRepository.save(payment);

        for (LinkDescription link : order.links()) {
            if ("approve".equals(link.rel())) {
                return link.href();

            }
        }
        return null;
    }


    // ✅ Capture du paiement après retour PayPal
    public void captureOrder(String orderId) throws IOException {
        OrdersCaptureRequest request = new OrdersCaptureRequest(orderId);
        client.execute(request);
    }
}
