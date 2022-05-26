package pe.com.bank.transaction.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.bank.transaction.client.entity.AccountEntity;
import pe.com.bank.transaction.client.entity.CreditEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeneralReportDTO {
	
	private String productId;
	private String productName;
	private List<AccountEntity> accountList;
	private List<CreditEntity> creditList;
		
}
