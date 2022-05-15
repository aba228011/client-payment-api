package com.example.service;

import com.example.model.PaymentRequest;
import com.example.model.PaymentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IPaymentService {
    List<PaymentResponse> createPayment(PaymentRequest paymentRequest);

    List<PaymentResponse> updatePayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentByPaymentId(String paymentId);

    void deleteByPaymentId(String paymentId);

    List<PaymentResponse> getPaymentsByClientId(String clientId);

    List<PaymentResponse> getAllPayments();
}
