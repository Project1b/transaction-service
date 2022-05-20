package pe.com.bank.transaction.service;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.AllArgsConstructor;
import lombok.var;
import pe.com.bank.transaction.client.AccountRestClient;
import pe.com.bank.transaction.client.CreditRestClient;
import pe.com.bank.transaction.client.ProductRestClient;
import pe.com.bank.transaction.dto.BalanceSummaryDTO;
import pe.com.bank.transaction.dto.InfoAccount;
import pe.com.bank.transaction.dto.ReportComissionDTO;
import pe.com.bank.transaction.dto.TransactionDTO;
import pe.com.bank.transaction.entity.TransactionEntity;
import pe.com.bank.transaction.repository.TransactionRepository;
import pe.com.bank.transaction.util.TransactionUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService{

	TransactionUtil transactionUtil;
	TransactionRepository transactionRepository;
	AccountRestClient accountRestClient;
	ProductRestClient productRestClient;
	CreditRestClient creditRestClient;
	
	public Flux<TransactionEntity> getTransactions() {
		return transactionRepository.findAll();
	}

	
	public Mono<TransactionEntity> getTransactionById(String id) {
		return transactionRepository.findById(id);
	}

	
	public Mono<TransactionEntity> newTransaction(TransactionEntity transaction) {
		return transactionRepository.save(transaction);
	}

	
	public Mono<Void> deleteTransactionById(String id) {
		return transactionRepository.deleteById(id);
	}

	
	public Mono<TransactionEntity> updateTransaction(TransactionEntity transaction, String id) {
		
		return transactionRepository.findById(id)
				.flatMap(a -> {
					a.setAmount(transaction.getAmount());
					a.setDate(transaction.getDate());
					a.setType(transaction.getType());
					return transactionRepository.save(a);
				})
				;
	}

	public Flux<TransactionEntity> getAllTransactionsByCredit(String creditId) {
		return transactionRepository.findTransactionEntitiesByCreditId(creditId);

	}

	// WILMER
	
	public Mono<TransactionEntity> createTransaction(TransactionEntity transactionEntity){
		return transactionRepository.save(transactionEntity);
	}
	

/*
	@Override
	public Mono<TransactionEntity> getTransactionByNroAccount(String id) {
		return transactionRepository.findTransactionsEntitiesByAccountNumber(id).last();
	} */

	// EDWIN
	

	
	public Mono<TransactionEntity> createTransactionAddAmount(TransactionEntity transactionEntity){
		return transactionRepository.save(transactionEntity);
	}


	public Flux<TransactionEntity> getTransactionsByDateAndAccountId(Date startDate, Date endDate,String accountId){
		
		return transactionRepository.findByDateBetweenAndAccountId(startDate, endDate, accountId);
		
	}

	public Mono<Long> countTransac(String typ,String accountId){
		return transactionRepository.countTransactionEntitiesByTypeAndAccountId(typ,accountId);
	}
	
	
	public Flux<TransactionEntity> getTransactionsByDateAndCreditId(Date startDate, Date endDate,String creditId){
		
		return transactionRepository.findByDateBetweenAndCreditIdOrderByDateDesc(startDate, endDate, creditId);
		
	}
	
	
	
	public Mono<BalanceSummaryDTO> getResumenByCustomerId(String customerId,Date startDate, Date endDate){
				
		var prueba = Flux.just(1,2,3,4,5);
		var infoAccount = accountRestClient.getAccountByCustomerId(customerId).flatMap( account -> {			
					
			var transaction =transactionRepository.findByDateBetweenAndAccountId(startDate, endDate, account.getId()).collectList();
			var  currentAmount = account.getAmount();
			 	
			 	var daySum =Flux.range(1, 30).flatMap( d -> {
			 		System.out.println("Day : "+transactionUtil.dateToLocalDate(endDate).minusDays(d+1));	
			 		
			 		var day = transactionUtil.dateToLocalDate(endDate).minusDays(d+1);
			 		
			 		
			 		var abono =transaction.flatMap(x -> {
			 		var sum =	x.stream().filter( t -> t.getType().equals("Abono")&& transactionUtil.dateToLocalDate(t.getDate()).isEqual(day))
			 			.map(TransactionEntity::getAmount).reduce(0.0,Double::sum);

			 			return Mono.just(sum);
			 		});
			 		
			 		abono.doOnNext(a -> System.out.println("sumaExorto : "+a));
			 		System.out.println("sumaAbono : "+abono);
			 		
			 		//System.out.println("sumaAbono : "+abono);	
			 		
			 		var exorto =transaction.flatMap(x -> {
				 		var sum =	x.stream().filter( t -> t.getType().equals("Exorto")&& transactionUtil.dateToLocalDate(t.getDate()).isEqual(day))
				 			.map(TransactionEntity::getAmount).reduce(0.0,Double::sum);
				 							 						 		
				 			return Mono.just(sum);
				 		});
			 		//exorto.subscribe( s -> System.out.println("sumaExorto : "+s));
			 		System.out.println("sumaExorto : "+exorto);
			 		exorto.doOnNext(e -> System.out.println("sumaExorto : "+e));
			 		var sum = abono.zipWith(exorto,(a,e) -> e-a);
			 		System.out.println("suma : "+sum);
			 		
			 		
			 		return sum.map( s -> s).doOnNext(a->System.out.println("sumaFinal Dia "+day+" accountId "+account.getId()+ ": " +a));
			 
			 	//return	Mono.just(LocalDate.parse("2018-12-06"));
			 		
			 		
			 	}).reduce(0.0, (x1, x2) -> x1 + x2);
			 	
			 	//currentAmount=currentAmount+daySum;
			 	
			 	
			 	return daySum.map(x -> new InfoAccount(account.getId(),x/30));
			 	
			 	
			 	//return Mono.just(new InfoAccount());
			});
		
		return infoAccount.collectList().map(info -> new BalanceSummaryDTO(customerId,info));
		//return infoAccount.map( info -> new BalanceSummaryDTO());
		 //return Mono.just(new BalanceSummaryDTO());
		//return infoAccount.collectList().map( info -> new BalanceSummaryDTO(customerId,info));
		 //return Mono.just(new BalanceSummaryDTO(customerId,infoAccount.));
				
				//transactionRepository.findByDateBetweenAndCreditId(startDate, endDate, accountId);
		
	}
	
	
	
	
	public Flux<ReportComissionDTO> getReportCommision(Date startDate,Date endDate){
		return productRestClient.getProducts().flatMap( product -> {
						
			 var transactionAccount = accountRestClient.getAccountByProductId(product.getProductId()).flatMap(account ->  {				
				 return  transactionRepository.findByDateBetweenAndAccountId(startDate,endDate,account.getId()).flatMap(c -> {
					 return c.getCommission()!=null? Mono.just(c.getCommission()):Mono.empty();
				 })	;							
			});
				var transactionCredit = creditRestClient.getCreditByProductId(product.getProductId()).flatMap( credit -> {			
				 return transactionRepository.findByDateBetweenAndCreditId(startDate,endDate,credit.getCreditId()).flatMap(c -> {
					 return c.getCommission()!=null? Mono.just(c.getCommission()):Mono.empty();
				   });
				});
			 	
			var concat = Flux.concat(transactionCredit,transactionAccount).collectList();
			return concat.map(t -> new ReportComissionDTO(product.getProductId(),product.getProductName(),t));

		});
	}
	
	/*
	 	public Mono<TransactionEntity> update(TransactionEntity transaction, String id) {
		
		return transactRepository.findById(id)
				.flatMap(a -> {
					a.setAmount(transaction.getAmount());
					a.setDate(transaction.getDate());
					a.setType(transaction.getType());
					a.setAccountNumber(transaction.getAccountNumber());
					a.setCreditId(transaction.getCreditId());
					
					return transactRepository.save(a);
				});
	}
	
	//
	
	public Mono<ServerResponse> getTransactions(ServerRequest request) {
		var accountId = request.queryParam("accountNumber");
		if (accountId.isPresent()) {
			var accountFlux = transactRepository.findTransactionsEntitiesByAccountNumber(String.valueOf(accountId.get()));
			return buildTransactionEntityResponse(accountFlux); 
		} else {
			var accountFlux = transactRepository.findAll();
			return buildTransactionEntityResponse(accountFlux);
		}
	}

	private Mono<ServerResponse> buildTransactionEntityResponse(Object accountFlux){
		return ServerResponse.ok().body(accountFlux, TransactionEntity.class);
	}
	 */

}
