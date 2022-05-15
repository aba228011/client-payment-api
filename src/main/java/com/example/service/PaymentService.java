package com.example.service;

import com.example.model.PaymentRequest;
import com.example.model.PaymentResponse;
import com.example.model.ServiceClass;
import com.example.repository.PaymentEntity;
import com.example.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
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
    public List<PaymentResponse> createPayment(PaymentRequest paymentRequest) {
        List<PaymentResponse> paymentResponses = new ArrayList<>();
        if (paymentRequest.getTypeOfServices().length > 0) {
            for (ServiceClass item: paymentRequest.getTypeOfServices()) {
                PaymentEntity paymentEntity = new PaymentEntity();
                paymentEntity.setPaymentId(paymentRequest.getPaymentId());
                // prepare paymentEntity for save
                paymentEntity.setClientId(paymentRequest.getClientId());
                paymentEntity.setPaymentDate(paymentRequest.getPaymentDate());

                // item set
                paymentEntity.setCodeService(item.getCodeService());
                paymentEntity.setPriceService(item.getPriceService());

                paymentRepository.save(paymentEntity);
                paymentResponses.add(modelMapper.map(paymentEntity, PaymentResponse.class));
            }
        }
        return paymentResponses;
    }

    @Override
    public List<PaymentResponse> updatePayment(PaymentRequest paymentRequest) {
        List<PaymentResponse> paymentResponses = new ArrayList<>();
        if (paymentRequest.getTypeOfServices().length > 0) {

            for (ServiceClass item: paymentRequest.getTypeOfServices()) {
                PaymentEntity paymentEntity = new PaymentEntity();

                PaymentEntity dbEntity = paymentRepository.getPaymentEntityByPaymentId(paymentRequest.getPaymentId());

                paymentEntity.setPaymentId(dbEntity.getPaymentId());
                // prepare paymentEntity for save
                paymentEntity.setClientId(paymentRequest.getClientId());
                paymentEntity.setPaymentDate(paymentRequest.getPaymentDate());

                // item set
                paymentEntity.setCodeService(item.getCodeService());
                paymentEntity.setPriceService(item.getPriceService());

                paymentRepository.save(paymentEntity);
                paymentResponses.add(modelMapper.map(paymentEntity, PaymentResponse.class));
            }
        }
        return paymentResponses;
    }

    @Override
    public PaymentResponse getPaymentByPaymentId(String paymentId) {
        PaymentEntity paymentEntity = paymentRepository.getPaymentEntityByPaymentId(paymentId);

        return modelMapper.map(paymentEntity, PaymentResponse.class);
    }

    @Override
    public List<PaymentResponse> getPaymentsByClientId(String clientId) {
        return paymentRepository.getPaymentEntitiesByClientId(clientId).stream().map(
                el -> modelMapper.map(el, PaymentResponse.class)
        ).collect(Collectors.toList());
    }

    @Override
    public void deleteByPaymentId(String paymentId) {
        paymentRepository.deletePaymentEntitiesByPaymentId(paymentId);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.getPaymentEntitiesBy().stream()
                .map(post -> modelMapper.map(post, PaymentResponse.class))
                .collect(Collectors.toList());
    }
}
