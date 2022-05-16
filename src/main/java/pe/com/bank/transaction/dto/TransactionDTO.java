package pe.com.bank.transaction.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDTO {
	
	
	private String transactionId;
	private Double amount;
	private Date date;
	private String type;
	private String accountNumber;
	private String accountId;
	private String creditId;
	private Date startDate;
	private Date endDate;

}
