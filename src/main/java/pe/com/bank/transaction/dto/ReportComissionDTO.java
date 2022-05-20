package pe.com.bank.transaction.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportComissionDTO {
	
	private String productId;
	private String productName;
	private List<Double> comissions;

}
