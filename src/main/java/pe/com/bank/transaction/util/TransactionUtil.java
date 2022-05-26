package pe.com.bank.transaction.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import pe.com.bank.transaction.client.AccountRestClient;
import pe.com.bank.transaction.client.CreditRestClient;
import pe.com.bank.transaction.client.ProductRestClient;
import pe.com.bank.transaction.entity.TransactionEntity;
import pe.com.bank.transaction.repository.TransactionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class TransactionUtil {
	
	TransactionRepository transactionRepository;
	
	public LocalDate dateToLocalDate(Date date) {
		
		return  date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public Date localDateToDate (LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	
	public Date cutTimeFromDate(Date date) {
		 Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.set(Calendar.HOUR_OF_DAY, 0);
	        cal.set(Calendar.MINUTE, 0);
	        cal.set(Calendar.SECOND, 0);
	        cal.set(Calendar.MILLISECOND, 0);
	        return cal.getTime();
	}
	
	public  Supplier<DoubleStream> toCumulativeSumStream(List<Double> list){
		
		  AtomicLong sum = new AtomicLong(0);
		Supplier<DoubleStream> streamSupplier = () -> list.stream().sequential().mapToDouble(x ->  sum.addAndGet(x.longValue()));
	streamSupplier.get().forEachOrdered(System.out::println);
	System.out.println("SUMA :"+ streamSupplier.get().sum());
        return streamSupplier;
       // return list.stream().sequential().mapToDouble(x ->  sum.addAndGet(x.longValue()));
    }
	
	public Mono<List<TransactionEntity>> findByDateBetweenAndAccountIdOrderByDateDesc(Date startDate,Date endDate,String creditId){
	
	return	transactionRepository.findByDateBetweenAndCreditIdOrderByDateDesc(startDate, endDate, creditId).collectList();
	
	}
}
