package pe.com.bank.transaction.service;


import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import pe.com.bank.transaction.dto.BalanceSummaryDTO;
import pe.com.bank.transaction.dto.ReportComissionDTO;
import pe.com.bank.transaction.dto.TransactionDTO;
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
	
	Flux<TransactionEntity> getTransactionsByDateAndAccountId(Date startDate, Date endDate,String accountId);
	Flux<TransactionEntity> getTransactionsByDateAndCreditId(Date startDate, Date endDate,String creditId);
	
	public Mono<TransactionEntity> createTransactionAddAmount(TransactionEntity transactionEntity);
	public Mono<BalanceSummaryDTO> getResumenByCustomerId(String customerId,Date startDate, Date endDate);
	
	public Flux<ReportComissionDTO> getReportCommision(Date startDate,Date endDate);
	
	public Mono<Long> countTransac(String typ,String accountId);

}
