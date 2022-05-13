package pe.com.bank.transaction.entity;

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
	private String date;
	private String type;
	private String accountNumber;
	private String creditId;
}
