package pe.com.bank.transaction.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import pe.com.bank.transaction.client.entity.CreditEntity;
import reactor.core.publisher.Flux;

@Component
public class CreditRestClient {
	
	private WebClient webClient;		
	  
	  public CreditRestClient(WebClient webClient) {
	        this.webClient = webClient;
	    }
	  
	  
	  @Value("${restClient.creditUrl}")
	  private String creditUrl;
	  
	  
	  public Flux<CreditEntity> getCreditByProductId(String productId){
		  var url = creditUrl.concat("/v1/credits/productId/{id}");
		  
		  return  webClient
	                .get()
	                .uri(url,productId)
	                .retrieve()
	                .bodyToFlux(CreditEntity.class)
	                .log();

	  }  
	  
	  public Flux<CreditEntity> getCreditByCustomerId(String customerId){
		  var url = creditUrl.concat("/v1/credits/productId/{id}");
		  
		  return  webClient
	                .get()
	                .uri(url,customerId)
	                .retrieve()
	                .bodyToFlux(CreditEntity.class)
	                .log();

	  } 

}
