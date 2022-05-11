package pe.com.bank.transaction.service;

import pe.com.bank.transaction.entity.TransactionEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

public Flux<TransactionEntity> getTransactions();
	
	public Mono<TransactionEntity> getTransactionById(String id);
	
	public Mono<TransactionEntity> newTransaction(TransactionEntity transaction);
	
	public Mono<Void> deleteTransactionById(String id);
	
	public Mono<TransactionEntity> updateTransaction(TransactionEntity transaction, String id);
}
