package pe.com.bank.transaction.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="transaction")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionEntity {

	@Id
	private String transactionId;
	private double amount;
	private String date;
	private String type;
	private String accountNumber;
	private String creditId;
}
