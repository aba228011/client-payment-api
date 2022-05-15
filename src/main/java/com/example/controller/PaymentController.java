package com.example.controller;

import com.example.model.PaymentRequest;
import com.example.model.PaymentResponse;
import com.example.service.PaymentService;
import com.example.service.SendMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private Environment env;

    @Autowired
    private SendMessage sendMessage;

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping
    public List<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest) throws JsonProcessingException {
        paymentRequest.setPaymentId(UUID.randomUUID().toString());
        List<PaymentResponse> paymentResponses =  paymentService.createPayment(paymentRequest);
        sendMessage.send(objectMapper.writeValueAsString(paymentRequest));
        return paymentResponses;
    }

    @PutMapping
    public List<PaymentResponse> updatePayment(@RequestParam String paymentId, @RequestBody PaymentRequest paymentRequest) {
        paymentRequest.setPaymentId(paymentId);
        return paymentService.updatePayment(paymentRequest);
    }

    @GetMapping
    public PaymentResponse getPaymentByPaymentId(@RequestParam String paymentId) {
        return paymentService.getPaymentByPaymentId(paymentId);
    }

    @GetMapping("/client")
    public List<PaymentResponse> getPaymentByClientId(@RequestParam String clientId) {
        return paymentService.getPaymentsByClientId(clientId);
    }

    @GetMapping("/all")
    public List<PaymentResponse> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/check")
    public String checkController() {
        return "client-payment-api is working at " + env.getProperty("local.server.port");
    }
}
