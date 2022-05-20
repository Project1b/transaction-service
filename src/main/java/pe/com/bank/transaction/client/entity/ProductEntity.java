package pe.com.bank.transaction.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    private String productId;
    private String type;
    private String productName;
    private Double commissionMaintenance;
    private String transactionLimit;

}
