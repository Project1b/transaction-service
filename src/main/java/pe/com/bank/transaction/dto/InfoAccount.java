package pe.com.bank.transaction.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InfoAccount {
	
	private String accountId;
	private double balanceMonth;

}
