package com.example.product_service.config;


import com.example.product_service.client.InventeryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Bean
    public InventeryClient inventeryClient() {
        RestClient restClient=RestClient.builder().baseUrl("http://localhost:8082").build();
        var resClientAdapterProxyFactory= RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(resClientAdapterProxyFactory).build();
        return httpServiceProxyFactory.createClient(InventeryClient.class);
    }
}
