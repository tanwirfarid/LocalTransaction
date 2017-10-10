package Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by faridt on 04.10.2017.
 */
public class Run {

    public static List<Transaction> transactions = new ArrayList<Transaction>();

    public static void main(String[] args){

        transactions.add(new Transaction("Testü", "041017-10:00:00"));
        transactions.add(new Transaction("Test2", "041017-10:01:00"));
        transactions.add(new Transaction("Test3"));

        for (Transaction transaction : transactions) {
            System.out.print(transaction.getTransactionText());
        }
    }

}
