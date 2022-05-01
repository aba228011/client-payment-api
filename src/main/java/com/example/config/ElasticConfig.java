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
                .connectedTo("payment-depl.es.us-central1.gcp.cloud.es.io:9243")
                .usingSsl()
                .withBasicAuth("elastic", "tt4Y7Q62E3ali674hOmLwIYG")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
