package pe.com.bank.transaction.service;

import org.springframework.stereotype.Service;

import pe.com.bank.transaction.entity.TransactionEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface TransactionService {

	public Flux<TransactionEntity> getTransactions();
	
	public Mono<TransactionEntity> getTransactionById(String id);
	
	public Mono<TransactionEntity> newTransaction(TransactionEntity transaction);
	
	public Mono<Void> deleteTransactionById(String id);
	
	public Mono<TransactionEntity> updateTransaction(TransactionEntity transaction, String id);
	
	public Mono<TransactionEntity> getTransactionByNroAccount(String id);
	
	//prueba
	public Flux<TransactionEntity> getTransactionsByNroAccountX(String id);
	
}
