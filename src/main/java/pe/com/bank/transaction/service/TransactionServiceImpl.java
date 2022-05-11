package pe.com.bank.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.var;
import pe.com.bank.transaction.entity.TransactionEntity;
import pe.com.bank.transaction.repository.TransactionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TransactionServiceImpl implements TransactionService{

	@Autowired
	TransactionRepository transactionRepository;
	
	@Override
	public Flux<TransactionEntity> getTransactions() {
		// TODO Auto-generated method stub
		return transactionRepository.findAll();
	}

	@Override
	public Mono<TransactionEntity> getTransactionById(String id) {
		// TODO Auto-generated method stub
		return transactionRepository.findById(id);
	}

	@Override
	public Mono<TransactionEntity> newTransaction(TransactionEntity transaction) {
		// TODO Auto-generated method stub
		return transactionRepository.save(transaction);
	}

	@Override
	public Mono<Void> deleteTransactionById(String id) {
		// TODO Auto-generated method stub
		return transactionRepository.deleteById(id);
	}

	@Override
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
