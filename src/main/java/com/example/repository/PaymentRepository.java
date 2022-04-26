package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentRepository extends ElasticsearchRepository<PaymentEntity, String> {
    PaymentEntity getPaymentEntityByPaymentId(String paymentId);

    Page<PaymentEntity> getPaymentEntityByClientId(String clientId, Pageable pageable);

    Page<PaymentEntity> getPaymentEntityByPaymentDate(Date data, Pageable pageable);

    void deletePaymentEntitiesByPaymentId(String paymentId);

    List<PaymentEntity> getPaymentEntitiesBy();
}
