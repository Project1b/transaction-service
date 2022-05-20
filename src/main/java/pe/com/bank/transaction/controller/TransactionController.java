package pe.com.bank.transaction.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import pe.com.bank.transaction.dto.BalanceSummaryDTO;
import pe.com.bank.transaction.dto.ReportComissionDTO;
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
	public Mono<TransactionEntity> agregarAccount(@RequestBody TransactionEntity transaction){	
		//
		transaction.setDate(new Date());
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
	
	
	// EDWIN 
	
	// lista transacciones por Numero de cuenta / id de cuenta
	/*
	@GetMapping("/transactions/account/{id}")
	public Mono<ResponseEntity<Flux<TransactionEntity>>> listTransactionByAccountNumberX(@PathVariable("id") String accountNumber){	
		
		return Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(transactionService.getTransactionsByNroAccountX(accountNumber)));
	
				
	}*/
	
	@PostMapping("/transactions/amountUpdate")
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
	
	@GetMapping ("/transactions/accountId/{accountId}/{startDate}/{endDate}")
		public Flux<TransactionEntity> getTransactionsByDateAndAccountId(@PathVariable String accountId,
				@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
				@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate){
				
					return transactionService.getTransactionsByDateAndAccountId(startDate,endDate,accountId);
		
	}
	
	@GetMapping ("/transactions/creditId/{creditId}/{startDate}/{endDate}")
	public Flux<TransactionEntity> getTransactionsByDateAndCreditId(@PathVariable String creditId,
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate){
		
		return transactionService.getTransactionsByDateAndCreditId(startDate,endDate,creditId);


	}
	
	@GetMapping ("/transactions/resumeCustomer/{customerId}/{startDate}/{endDate}")
	public Mono<BalanceSummaryDTO> getResumenByCustomerId (@PathVariable String customerId,
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate) {
		return transactionService.getResumenByCustomerId(customerId, startDate, endDate);
	}
	
	@GetMapping ("/transactions/reportByProduct/{startDate}/{endDate}")
	public Flux<ReportComissionDTO> getReportCommision(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy")Date endDate){
		
		return transactionService.getReportCommision(startDate,endDate);
	}
}
