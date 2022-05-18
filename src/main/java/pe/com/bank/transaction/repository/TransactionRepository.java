package pe.com.bank.transaction.repository;

import java.util.Date;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.bank.transaction.entity.TransactionEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<TransactionEntity, String>{

	//Flux<TransactionEntity> findTransactionsEntitiesByAccountNumber(String accountNumber);
	Flux<TransactionEntity> findTransactionsEntitiesByAccountId(String accountId);

	Flux<TransactionEntity> findTransactionEntitiesByCreditId(String creditId);
	
	Flux<TransactionEntity> findByDateBetweenAndAccountId(Date startDate,Date endDate,String accountId);
	
	Mono<Long> countTransactionEntitiesByTypeAndAccountId(String typ,String accountId);

}
