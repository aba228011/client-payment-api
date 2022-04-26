package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private String paymentId;

    private String clientId;

    private String paymentDate;

    private double paymentAmount;

    private String typeOfServices;
}
