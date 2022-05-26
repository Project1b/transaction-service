package pe.com.bank.transaction.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection="transaction")
public class TransactionEntity {

	@Id
	private String transactionId;
	private Double amount;
	private Double commission;
	private Date date;
	private String type;
	private String typeDetails;
	private String accountId;
	private Double commissionTr;
	private String creditId;
	private String loanId;
}
