package com.example.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "payment_table")
public class PaymentEntity {
    @Id
    @Field(type = FieldType.Keyword)
    private String paymentId;

    @Field(type = FieldType.Keyword)
    private String clientId;

    @Field(type = FieldType.Date)
    private Date paymentDate;

    @Field(type = FieldType.Text)
    private String codeService;

    @Field(type = FieldType.Double)
    private double priceService;
}
