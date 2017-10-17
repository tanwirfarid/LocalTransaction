package Transaction;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by faridt on 04.10.2017.
 */

public class Transaction {

    private final int TRANSACTION_ID;
    private final int GROUP_ID = 0;
    private final int PROCESS_ID = 1;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy-HH:mm:ss");
    private final String transactionText;
    private final String hash;

    private LocalDateTime time;
    private String timeText;

    private String text;


    public Transaction(String text) {

        TRANSACTION_ID = (Run.transactions.isEmpty()) ? 1 : Run.transactions.get(Run.transactions.size() - 1).TRANSACTION_ID + 1;

        this.text = text;

        this.time = LocalDateTime.now();
        this.timeText = time.format(formatter);

        this.transactionText = calcTransactionText();
        this.hash = this.transactionText.substring(transactionText.length() - 65);
    }

    public Transaction(String text, String fakeTime) {

        TRANSACTION_ID = (Run.transactions.isEmpty()) ? 1 : Run.transactions.get(Run.transactions.size() - 1).TRANSACTION_ID + 1;

        this.text = text;

        this.timeText = fakeTime;

        this.transactionText = calcTransactionText();
        this.hash = this.transactionText.substring(transactionText.length() - 65);
    }

    private String calcTransactionText() {

        byte[] digest = null;

        StringBuilder transactionBuilder = new StringBuilder();
        transactionBuilder.append(String.format("%08d", TRANSACTION_ID));
        transactionBuilder.append(";");
        transactionBuilder.append(timeText);
        transactionBuilder.append(";");
        transactionBuilder.append(String.format("%02d", GROUP_ID));
        transactionBuilder.append(";");
        transactionBuilder.append(String.format("%02d", PROCESS_ID));
        transactionBuilder.append(";");
        transactionBuilder.append(text);
        transactionBuilder.append(";");

        StringBuilder allTransactions = new StringBuilder();


        /*for (int i = 0; i < TRANSACTION_ID - 1; i++) {
            allTransactions.append(Run.transactions.get(i).getTransactionText());
        }*/

        allTransactions.append(transactionBuilder);

        if (Run.transactions.size() > 1)
            allTransactions.append(Run.transactions.get(TRANSACTION_ID-1).getHash());

        try {
            byte[] bytes = allTransactions.toString().getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes);
            digest = md.digest();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        transactionBuilder.append(String.format("%x", new BigInteger(1, digest)).toUpperCase());
        transactionBuilder.append("\n");

        return transactionBuilder.toString();
    }

    public String getTransactionText() {
        return transactionText;
    }

    public String getHash() {
        return hash;
    }

}