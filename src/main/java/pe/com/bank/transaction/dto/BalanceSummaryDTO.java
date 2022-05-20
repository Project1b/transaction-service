package pe.com.bank.transaction.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BalanceSummaryDTO {
	
	private String customerId;
	private List<InfoAccount> accountList;
//	private List<InfoCredit> creditList;

}
