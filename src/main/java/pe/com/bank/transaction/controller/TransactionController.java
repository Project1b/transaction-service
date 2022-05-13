package pe.com.bank.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.bank.transaction.entity.TransactionEntity;
import pe.com.bank.transaction.service.TransactionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class TransactionController {

	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/transactions")
	public Mono<ResponseEntity<Flux<TransactionEntity>>> listarTransactions(){	//OK
		return Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(transactionService.getTransactions()));
	}
	
	@GetMapping("/transactions/{id}")
	public Mono<ResponseEntity<TransactionEntity>> listTransactionId(@PathVariable String id){	//OK
		return transactionService.getTransactionById(id).map(t -> ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(t)).defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/transactions")
	public Mono<TransactionEntity> agregarAccount(@RequestBody TransactionEntity transaction){	//
		return transactionService.newTransaction(transaction);
	}


	
	@DeleteMapping("/transactions/{id}")
	public Mono<Void> deleteAccount(@PathVariable String id){	//OK
		return transactionService.deleteTransactionById(id);
	}



	@PutMapping("/transactions/{id}")
	public Mono<ResponseEntity<TransactionEntity>> updateAccount(@RequestBody TransactionEntity transaction, @PathVariable String id){
		return transactionService.updateTransaction(transaction, id)
				.map(ResponseEntity.ok()::body)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	
	//prueba
	@GetMapping("/transactions/account/{id}")
	public Mono<ResponseEntity<Flux<TransactionEntity>>> listTransactionByAccountNumberX(@PathVariable("id") String accountNumber){
		
		return Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(transactionService.getTransactionsByNroAccountX(accountNumber)));
	
				
	}

	@GetMapping("/transaction/credit/{id}")
	public Flux<TransactionEntity> getAllTransactionByCredit(@PathVariable("id") String creditId) {
		return transactionService.getAllTransactionsByCredit(creditId);
	}

	@PostMapping ("/createTransaction")
	public Mono<TransactionEntity> addTransaction(@RequestBody TransactionEntity transactionEntity){
		return transactionService.createTransaction(transactionEntity);
	}
	
}
