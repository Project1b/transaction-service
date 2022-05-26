package pe.com.bank.transaction.service;


import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import pe.com.bank.transaction.dto.*;
import pe.com.bank.transaction.entity.TransactionEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface TransactionService {

	public Flux<TransactionEntity> getTransactions();
	
	public Mono<TransactionEntity> getTransactionById(String id);
	
	public Mono<TransactionEntity> newTransaction(TransactionEntity transaction);
	
	public Mono<Void> deleteTransactionById(String id);
	
	public Mono<TransactionEntity> updateTransaction(TransactionEntity transaction, String id);
	/*
	public Mono<TransactionEntity> getTransactionByNroAccount(String id);
	
	public Flux<TransactionEntity> getTransactionsByNroAccountX(String accountNumber);
*/
	public Flux<TransactionEntity> getAllTransactionsByCredit(String creditId);

	public Mono<TransactionEntity> createTransaction(TransactionEntity transactionEntity);
	
	public Flux<TransactionEntity> getTransactionsByAccountId(String accountId);
	public Flux<TransactionEntity> getTransactionsByDateAndAccountId(String accountId,Date startDate, Date endDate);
	
	public Flux<TransactionEntity> getTransactionsByCreditId(String creditId);
	public Flux<TransactionEntity> getTransactionsByDateAndCreditId(String creditId,Date startDate, Date endDate);
	
	public Flux<TransactionEntity> getTransactionsByLoanId(String loanId);
	public Flux<TransactionEntity> getTransactionsByDateAndLoanId(String loanId,Date startDate, Date endDate);
	
	public Mono<TransactionEntity> createTransactionAddAmount(TransactionEntity transactionEntity);
	public Mono<BalanceSummaryDTO> getResumenByCustomerId(String customerId,Date startDate, Date endDate);
	
	public Flux<ReportComissionDTO> getReportCommision(Date startDate,Date endDate);
	
	public Mono<Long> countTransac(String typ,String accountId);
	//public Flux<TransactionEntity> getTransactionsByDateAndAccountIdMono(Date startDate, Date endDate,String accountId);

	public Flux<TransactionEntity> getReportLastMovement(String creditId);

	public Flux<TransactionEntity> getLastCardTransaction(String cardId);

	public Mono<LastMovementDTO> getLastMovement(String creditId, String cardId);

}
