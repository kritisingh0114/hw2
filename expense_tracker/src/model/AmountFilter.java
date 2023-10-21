package model;
import java.util.*;
// A class that implements the TrasactionFilter interface.
public class AmountFilter implements TransactionFilter {
    //returns the filtered list of transactions based on amount provided
    public List<Transaction> filter(List<Transaction> transactions, String filterValue){
        double updatedFilterValue = Double.parseDouble(filterValue);
        List<Transaction> filtered_transactions = Collections.emptyList();
        for(Transaction curTransaction: filtered_transactions) {
            double curAmount = curTransaction.getAmount();
            if(curAmount == updatedFilterValue) {
                filtered_transactions.add(curTransaction);
            }
        }
        return filtered_transactions;
    }
}