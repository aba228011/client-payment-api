package com.example.controller;

import com.example.model.PaymentRequest;
import com.example.model.PaymentResponse;
import com.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private Environment env;

    @PostMapping
    public PaymentResponse createPayment(@RequestBody PaymentRequest paymentRequest){
        return paymentService.createPayment(paymentRequest);
    }

    @PutMapping
    public PaymentResponse updatePayment(@RequestParam String paymentId, @RequestBody PaymentRequest paymentRequest) {
        paymentRequest.setPaymentId(paymentId);
        return paymentService.updatePayment(paymentRequest);
    }

    @GetMapping
    public PaymentResponse getPaymentByPaymentId(@RequestParam String paymentId) {
        return paymentService.getPaymentByPaymentId(paymentId);
    }

    @GetMapping("/client")
    public Page<PaymentResponse> getPaymentByClientId(@RequestParam String clientId, Pageable pageable) {
        return paymentService.getPaymentsByClientId(clientId, pageable);
    }

    @GetMapping("/date")
    public Page<PaymentResponse> getPaymentByDate(@RequestParam String date, Pageable pageable) throws ParseException {
        return paymentService.getPaymentsByDate(date, pageable);
    }

    @GetMapping("/all")
    public List<PaymentResponse> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/check")
    public String checkController() {
        return "post-core-api is working at " + env.getProperty("local.server.port");
    }
}
