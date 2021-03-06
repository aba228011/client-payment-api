package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private String paymentId;

    private String clientId;

    private Date paymentDate;

    private String codeService;

    private double priceService;
}
