package pe.com.bank.transaction.client;

import lombok.extern.slf4j.Slf4j;
import pe.com.bank.transaction.client.entity.ProductEntity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@Component
public class ProductRestClient {

    private WebClient webClient;

    
    public ProductRestClient(WebClient webClient) {
        this.webClient = webClient;
    }
    
    @Value("${restClient.productUrl}")
    private String productUrl;

    public Flux<ProductEntity> getProducts(){

        var url = productUrl.concat("/v1/products/getAllProduct");
        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(ProductEntity.class)
                .log();
    }

}