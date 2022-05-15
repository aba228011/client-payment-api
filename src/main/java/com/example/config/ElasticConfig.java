package com.example.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class ElasticConfig extends AbstractElasticsearchConfiguration {
    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("my-deployment-07bf2b.es.us-central1.gcp.cloud.es.io:9243")
                .usingSsl()
                .withBasicAuth("elastic", "FElTjmHLdeXnWOV7Yx8evgAl")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
