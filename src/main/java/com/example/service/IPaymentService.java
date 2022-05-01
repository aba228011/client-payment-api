package com.example.service;

import com.example.model.PaymentRequest;
import com.example.model.PaymentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IPaymentService {
    PaymentResponse createPayment(PaymentRequest paymentRequest);

    PaymentResponse updatePayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentByPaymentId(String paymentId);

    void deleteByPaymentId(String paymentId);

    Page<PaymentResponse> getPaymentsByClientId(String clientId, Pageable pageable);

    Page<PaymentResponse> getPaymentsByDate(Date date, Pageable pageable) throws ParseException;

    List<PaymentResponse> getAllPayments();
}
