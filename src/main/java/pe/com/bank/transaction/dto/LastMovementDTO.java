package pe.com.bank.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.bank.transaction.entity.TransactionEntity;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LastMovementDTO {

    private List<TransactionEntity> transactionCredit;
    private List<TransactionEntity> transactionDebit;
}
