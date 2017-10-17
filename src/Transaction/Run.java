package Transaction;

import java.util.ArrayList;
import java.util.List;
import java.time.Instant;
import java.time.Duration;

/**
 * Created by faridt on 04.10.2017.
 */
public class Run {

    public static List<Transaction> transactions = new ArrayList<Transaction>();

    public static void main(String[] args){

        Instant start = Instant.now();

        transactions.add(new Transaction("Testü", "041017-10:00:00"));
        transactions.add(new Transaction("Test2", "041017-10:01:00"));
        transactions.add(new Transaction("Test3"));

        for (Transaction transaction : transactions) {
            System.out.print(transaction.getTransactionText());
        }

        for (int i = 0; i < 1000; i++) {
            transactions.add(new Transaction(String.valueOf(Math.round(Math.random() * 1000))));
        }
        System.out.print(transactions.get(1002).getHash());

        Instant end = Instant.now();

        Duration timeElapsed = Duration.between(start, end);

        System.out.print(timeElapsed.toMillis()+"\n");

        start = Instant.now();
        transactions.add(new Transaction(String.valueOf(Math.round(Math.random() * 1000))));
        System.out.print(transactions.get(1003).getTransactionText());
        end = Instant.now();

        timeElapsed = Duration.between(start, end);

        System.out.print(timeElapsed.toMillis()+"\n");

    }

}