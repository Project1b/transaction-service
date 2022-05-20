package pe.com.bank.transaction.client.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {

	@Id
	private String id;
	private String accountNumber;
	private Double amount;
	private String dateOpen;
	private String amounttype;	
	private String productId;
	private String customerId;
}
