package pe.com.bank.transaction.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.com.bank.transaction.dto.TransactionDTO;
import pe.com.bank.transaction.entity.TransactionEntity;
import pe.com.bank.transaction.service.TransactionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class TransactionController {

	// CRUD
	
	@Autowired
	private TransactionService transactionService;
	private TransactionDTO transactionDTO;
	
	@GetMapping("/transactions")
	public Mono<ResponseEntity<Flux<TransactionEntity>>> getAllTransactions(){	//OK
		return Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(transactionService.getTransactions()));
	}
	
	@GetMapping("/transactions/{id}")
	public Mono<ResponseEntity<TransactionEntity>> getTransactionById(@PathVariable String id){	//OK
		return transactionService.getTransactionById(id).map(t -> ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(t)).defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/transactions")
	public Mono<TransactionEntity> addNewTransaction(@RequestBody TransactionEntity transaction){	
		//
		transaction.setDate(new Date());
		return transactionService.newTransaction(transaction);
	}


	
	@DeleteMapping("/transactions/{id}")
	public Mono<Void> deleteTransactionById(@PathVariable String id){	//OK
		return transactionService.deleteTransactionById(id);
	}


	// Funcion OK / completar el tratamiento de valores null 
	@PutMapping("/transactions/{id}")
	public Mono<ResponseEntity<TransactionEntity>> updateTransactionByI(@RequestBody TransactionEntity transaction, @PathVariable String id){
		return transactionService.updateTransaction(transaction, id)
				.map(ResponseEntity.ok()::body)
				.defaultIfEmpty(ResponseEntity.notFound()
				.build());
	}
	
	
	// EDWIN 
	
	// lista transacciones por Numero de cuenta / id de cuenta
	@GetMapping("/transactions/account/{id}")
	public Mono<ResponseEntity<Flux<TransactionEntity>>> listTransactionByAccountNumberX(@PathVariable("id") String accountId){	
		
		return Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(transactionService.getTransactionsByAccountId(accountId)));
	
				
	}
	
	@PostMapping("transactions/amountUpdate")
	public Mono<TransactionEntity> pruebaInsertTransaction(@RequestBody TransactionEntity transactionEntity){
		return transactionService.createTransaction(transactionEntity);
	}
	

	
	//WILMER
	
	@GetMapping("/transaction/credit/{id}")
	public Flux<TransactionEntity> getAllTransactionByCredit(@PathVariable("id") String creditId) {
		return transactionService.getAllTransactionsByCredit(creditId);
	}

	@PostMapping ("/createTransaction")
	public Mono<TransactionEntity> addTransaction(@RequestBody TransactionEntity transactionEntity){
		return transactionService.createTransaction(transactionEntity);
	}
	
	@GetMapping ("transactions/{accountId}/{startDate}/{endDate}")
		public Flux<TransactionEntity> getTransactionsByDateAndAccountId(@PathVariable String accountId,
				@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
				@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate){
		
		
		
					return transactionService.getTransactionsByDateAndAccountId(startDate,endDate,accountId);
	
	
	}

	@GetMapping ("/transaction/count")
	public Mono<Long> countTransac(@RequestParam(name = "accountId") String accountId, @RequestParam String typ){
		return transactionService.countTransac(typ,accountId);
	}
}
