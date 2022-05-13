package pe.com.bank.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.AllArgsConstructor;
import lombok.var;
import pe.com.bank.transaction.entity.TransactionEntity;
import pe.com.bank.transaction.repository.TransactionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService{

	
	TransactionRepository transactionRepository;
	
	
	public Flux<TransactionEntity> getTransactions() {
		// TODO Auto-generated method stub
		return transactionRepository.findAll();
	}

	
	public Mono<TransactionEntity> getTransactionById(String id) {
		// TODO Auto-generated method stub
		return transactionRepository.findById(id);
	}

	
	public Mono<TransactionEntity> newTransaction(TransactionEntity transaction) {
		// TODO Auto-generated method stub
		return transactionRepository.save(transaction);
	}

	
	public Mono<Void> deleteTransactionById(String id) {
		// TODO Auto-generated method stub
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


	@Override
	public Mono<TransactionEntity> getTransactionByNroAccount(String id) {
		
		return transactionRepository.findTransactionsEntitiesByAccountNumber(id).last();
	}

	// Prueba
	
	@Override
	public Flux<TransactionEntity> getTransactionsByNroAccountX(String accountNumber) {
		
		return transactionRepository.findTransactionsEntitiesByAccountNumber(accountNumber);
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
