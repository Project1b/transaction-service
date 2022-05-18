package pe.com.bank.transaction.service;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.AllArgsConstructor;
import lombok.var;
import pe.com.bank.transaction.dto.TransactionDTO;
import pe.com.bank.transaction.entity.TransactionEntity;
import pe.com.bank.transaction.repository.TransactionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService{

	
	TransactionRepository transactionRepository;
	//test
	
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
	
	@Override
	public Flux<TransactionEntity> getTransactionsByAccountId(String accountId) {
		return transactionRepository.findTransactionsEntitiesByAccountId(accountId);
	}
	
	public Mono<TransactionEntity> createTransactionAddAmount(TransactionEntity transactionEntity){
		return transactionRepository.save(transactionEntity);
	}


	public Flux<TransactionEntity> getTransactionsByDateAndAccountId(Date startDate, Date endDate,String accountId){	
		return transactionRepository.findByDateBetweenAndAccountId(startDate, endDate, accountId);
		
	}

	public Mono<Long> countTransac(String typ){
		return transactionRepository.countTransactionEntitiesByType(typ);
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
