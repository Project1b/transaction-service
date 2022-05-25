package pe.com.bank.transaction.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import pe.com.bank.transaction.client.entity.AccountEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AccountRestClient {
	
	private WebClient webClient;		
	  
	  public AccountRestClient(WebClient webClient) {
	        this.webClient = webClient;
	    }
	  
	  
	  @Value("${restClient.accountUrl}")
	  private String accountUrl;
	  
	  
	  public Flux<AccountEntity> getAccountByProductId(String productId){
		  var url = accountUrl.concat("/productId/{id}");
		  
		  return  webClient
	                .get()
	                .uri(url,productId)
	                .retrieve()
	                .bodyToFlux(AccountEntity.class)
	                .log();

	  }  
	  
	  public Flux<AccountEntity> getAccountByCustomerId(String customerId){
		  var url = accountUrl.concat("/customer/{id}");
		  
		  return  webClient
	                .get()
	                .uri(url,customerId)
	                .retrieve()
	                .bodyToFlux(AccountEntity.class)
	                .log();

	  }

	  public Flux<AccountEntity> getAccountCard(String cardId){
		  return webClient.get().uri(uriBuilder -> uriBuilder.scheme("http")
				  .host("gateway-server-service")
				  .path("/api/account/getAccountCard")
				  .queryParam("cardId",cardId)
				  .build())
				  .retrieve()
				  .bodyToFlux(AccountEntity.class);
	  }
}
