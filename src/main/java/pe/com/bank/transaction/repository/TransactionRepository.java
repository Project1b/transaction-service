package pe.com.bank.transaction.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.bank.transaction.entity.TransactionEntity;
import reactor.core.publisher.Flux;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<TransactionEntity, String>{

	Flux<TransactionEntity> findTransactionsEntitiesByAccountNumber(String accountNumber);
	
}
