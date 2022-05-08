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
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public List<PaymentResponse> getPaymentByClientId(@RequestParam String clientId) {
        return paymentService.getPaymentsByClientId(clientId);
    }

    @GetMapping("/date/{date}")
    public Page<PaymentResponse> getPaymentByDate(@PathVariable String date, Pageable pageable) {
        try {
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            Date dateToRepo = simpleDateFormat.parse(date);

            return paymentService.getPaymentsByDate(dateToRepo, pageable);

        } catch (ParseException parseException) {
            return null;
        }
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
