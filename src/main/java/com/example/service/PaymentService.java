package com.example.service;

import com.example.model.PaymentRequest;
import com.example.model.PaymentResponse;
import com.example.repository.PaymentEntity;
import com.example.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        paymentRequest.setPaymentId(UUID.randomUUID().toString());

        PaymentEntity paymentEntity = modelMapper.map(paymentRequest, PaymentEntity.class);

        paymentEntity = paymentRepository.save(paymentEntity);

        return modelMapper.map(paymentEntity, PaymentResponse.class);
    }

    @Override
    public PaymentResponse updatePayment(PaymentRequest paymentRequest) {
        PaymentEntity paymentEntity = modelMapper.map(paymentRequest, PaymentEntity.class);

        PaymentEntity dbEntity = paymentRepository.getPaymentEntityByPaymentId(paymentRequest.getPaymentId());

        paymentEntity.setPaymentId(dbEntity.getPaymentId());

        return modelMapper.map(paymentEntity, PaymentResponse.class);
    }

    @Override
    public PaymentResponse getPaymentByPaymentId(String paymentId) {
        PaymentEntity paymentEntity = paymentRepository.getPaymentEntityByPaymentId(paymentId);

        return modelMapper.map(paymentEntity, PaymentResponse.class);
    }

    @Override
    public Page<PaymentResponse> getPaymentsByClientId(String clientId, Pageable pageable) {
        return paymentRepository.getPaymentEntityByClientId(clientId, pageable).map(
                el -> modelMapper.map(el, PaymentResponse.class)
        );
    }

    @Override
    public void deleteByPaymentId(String paymentId) {
        paymentRepository.deletePaymentEntitiesByPaymentId(paymentId);
    }

    @Override
    public Page<PaymentResponse> getPaymentsByDate(Date date, Pageable pageable) {
        return paymentRepository.getPaymentEntityByPaymentDate(date, pageable).map(
                el -> modelMapper.map(el, PaymentResponse.class)
        );
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.getPaymentEntitiesBy().stream()
                .map(post -> modelMapper.map(post, PaymentResponse.class))
                .collect(Collectors.toList());
    }
}
